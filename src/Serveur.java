import Command.*;
import Data.ChartDataBase;
import Exceptions.SyntaxeException;
import Exceptions.UnknownCmdException;
import Utils.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Molinengo/Soumille on 04/11/15.
 */
public class Serveur {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private List<Command> allCommands;
    private Parser parser;
    private ChartDataBase datas;
    private Messenger messenger;

    public Serveur() {
        this.allCommands = new ArrayList<>();
        allCommands.add(new ViewCommand());
        allCommands.add(new AddCommand());
        allCommands.add(new QuitCommand());
        parser = new Parser();
        datas = new ChartDataBase();
    }

    public void launch(){
        try {
            String messageClient = "";
            String messageEnvoye = "";
            int cptClient = 0;
            serverSocket = new ServerSocket(Utils.NUM_PORT);
            while(cptClient < 5){
                cptClient++;
                clientSocket = serverSocket.accept();
                messenger = new Messenger(clientSocket);
                messenger.sendMessage("Vous êtes connecté au serveur ! Envoyez votre premiere requête");
                //Communication
                boolean finished = false;
                StringBuffer answer;
                while ((! finished)|| ((messageClient = messenger.readMessage())!=null)) {
                    answer = new StringBuffer();
                   // messageClient = messenger.readMessage();
                    //si le client quitte sans prévenir
                    /*if (messageClient == null) {
                        closeConnection(serverSocket, clientSocket);
                        return;
                    }*/
                    System.out.println(messageClient);Command commandReq;
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
                    datas.printDatas();
                }
                messenger.sendMessage("Hasta La Vista Baby !\n***** Déconnexion *****");
            }
            closeConnection(serverSocket, clientSocket);
        } catch (IOException IOE) {
            System.err.println(IOE);
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

    /*
    private String traiterRequeteClient(String message){
        String result="";
        int i;

        if(!message.endsWith(";")) return "ERR:RE:la requete doit finir par un ';'" +
                " ou la message n'a pas été reçu en entiere " +
                "Veuillez recommencer";

        //decoupage de la requete en multisousrequetes
        List<String> lReq=Arrays.asList(message.split(";"));
        List<String> req;
        for(i=0;i<lReq.size();i++) {

            // a revoir je pense que ça serait mieux de decouper plus bas dans view et add et voir pour le $
            req = Arrays.asList(lReq.get(i).split(":|,"));

            // pour eviter les problemes de casse toUpper
            switch (req.get(0).toUpperCase()) {
                case "ADD":
                    System.out.println("case ADD:Découpage requete =" + req.toString());
                    result += ajouterPersonne(req)+";";
                    break;
                case "VIEW":
                    System.out.println("case VIEW : Découpage requete =" + req.toString());
                    result += "jai trouvé le VIEW"+";";
                    break;
                default:
                    result = "ERR:RE:requete inconnue !"+";";
                    System.out.println("case default : Découpage requete =" + req.toString());
            }

        }
        return result;
    }
    */
    /**
     *Fonction de gestion d'ajout de personne dans la HashMap
     *Cette fonction permet :
     *      d'ajouter un surnom à une personne déjà connu dans la hashmap
     *      d'ajouter un nom avec un ou plusieurs surnoms
     *
     **/
    /*String ajouterPersonne(List<String>requete){
        String messageRetour="";
        List<String> surnom=mapSurnom.get(requete.get(1));
        String nom=requete.get(1);
        //impossible de faire des remove à partir d'une liste crée grace à Array.asList
        ArrayList<String>copieRequete=new ArrayList<>(requete);

        //retrait des premiers champs inutiles
        //retrait du mot clé de la commande
        copieRequete.remove(0);
        //retrait du nom
        copieRequete.remove(0);
        //split de tous les surnoms
        if(surnom==null)surnom=new ArrayList<>();
        while(copieRequete.size()!=0){
            surnom.add(copieRequete.remove(0));
        }
        mapSurnom.put(nom,surnom);
        messageRetour="OK:ADD";
        return messageRetour;

    }*/

    public static void main(String[] args) {
        Serveur serveur = new Serveur();
        serveur.launch();
    }
}
