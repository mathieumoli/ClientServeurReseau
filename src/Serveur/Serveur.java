package Serveur;

import Serveur.Command.*;
import Serveur.Data.ChartDataBase;
import Serveur.Data.CommandsList;
import Serveur.Exceptions.SyntaxeException;
import Serveur.Exceptions.UnknownCmdException;
import Serveur.Utils.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Molinengo/Soumille on 04/11/15.
 * Serveur Class
 */
public class Serveur {

    private ServerSocket serverSocket;
    private ChartDataBase datas;

    public Serveur(){
        datas = new ChartDataBase();
        try {
            serverSocket = new ServerSocket(Utils.NUM_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * infinite loop and create a new thread for each new client
     */
    private void boucleAttente(){
        try {
            while(true){
                new Traitement(serverSocket.accept()).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //inner class
    private class Traitement extends Thread {
        private Socket clientSocket;

        private List<Command> allCommands;
        private Parser parser;
        private Messenger messenger;

        public Traitement(Socket sock) {
            this.allCommands = new CommandsList();
            parser = new Parser();
            clientSocket = sock;
            messenger = new Messenger(sock);
        }

        /**
         * process executed for each thread
         */
        public void run(){
            String messageClient ="";
            messenger.sendMessage("Vous êtes connecté au serveur ! Envoyez votre premiere requête");
            //Communication
            boolean finished = false;
            StringBuffer answer;
            while ((! finished)) {
                answer = new StringBuffer();
                messageClient = messenger.readMessage();
                //si le client quitte sans prévenir
                if (messageClient == null) {
                    break;
                }
                Command commandReq;
                try {
                    //on récupere la commande
                    commandReq = parser.getCommand(messageClient);
                    //on fait le traitement de la commande
                    finished = traiterCommande(commandReq, answer, parser);
                } catch (SyntaxeException se) {
                    answer.append(parser.getCommandResult(false, se.getCmdInError(), Arrays.asList(se.getMessage())));
                } catch (UnknownCmdException uce) {
                    commandReq = new Command("RE", new ArrayList<>());
                    answer.append(parser.getCommandResult(false, commandReq, Arrays.asList(uce.getMessage())));
                } finally {
                    //On envoie le message au client
                    messenger.sendMessage(answer.toString());
                }
                //datas.printDatas();
            }
        }

        /**
         * Close sockets
         * @param ss
         * @param s
         */
        private void closeConnection(ServerSocket ss, Socket s){
            try {
                ss.close();
                System.out.println("client socket");
                s.close();
            } catch (IOException IOE){
                System.err.println(IOE);
            }

        }

        /**
         * Process the command sent by the client
         * @param cmd
         * @param answer
         * @param parser
         * @return
         * @throws UnknownCmdException
         */
        private boolean traiterCommande(Command cmd, StringBuffer answer, Parser parser) throws UnknownCmdException{
            Command usableCommand = getUsableCommand(cmd);
            if(usableCommand == null) {
                throw new UnknownCmdException();
            }
            usableCommand.setArguments(cmd.getArguments());
            return usableCommand.use(datas, answer, parser);
        }

        /**
         * Return the usable command corresponding to the command in parameter
         * @param cmd
         * @return
         */
        private Command getUsableCommand(Command cmd){
            for(Command c : allCommands){;
                if(c.hasSameCommandWord(cmd))
                    return c;
            }
            return null;
        }
    }

    public static void main(String[] args) {
        Serveur server = new Serveur();
        server.boucleAttente();
    }
}
