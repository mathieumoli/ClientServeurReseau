import Command.*;
import Data.ChartDataBase;
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
    private BufferedReader in;
    private BufferedWriter out;
    private HashMap<String,List<String>> mapSurnom=new HashMap<String,List<String>>();
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
            String messageClient="init";
            String messageEnvoye;
            serverSocket = new ServerSocket(Utils.numPort);
            clientSocket = serverSocket.accept();
            messenger = new Messenger(clientSocket);
            /*in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            */
            //
            /*out.write("Vous êtes connecté au serveur ! Envoyez votre premiere requête");
            out.newLine();
            out.flush();*/
            messenger.sendMessage("Vous êtes connecté au serveur ! Envoyez votre premiere requête");
            /*
            //fermeture de la socket
            messageClient=in.readLine();
            while(!(messageClient.equals("QUIT"))){
                System.out.println(messageClient);
                out.write(traiterRequeteClient(messageClient));
                out.newLine();
                out.flush();
                messageClient=in.readLine();
            }
            System.out.println(messageClient);*/
            boolean finished = false;
            StringBuffer answer;
            while (! finished){
                answer = new StringBuffer();
                messageClient = messenger.readMessage(); //in.readLine();
                Command commandReq = parser.getCommand(messageClient);//mettre en place un système d'exceptions pour erreur dans parsage ?
                System.out.println("arguments : " + commandReq.getArguments());
                finished = traiterCommande(commandReq, answer, parser);
                System.out.println("reponse :" + answer);
                /*out.write(answer.toString());
                out.newLine();
                out.flush();*/
                messenger.sendMessage(answer.toString());
                datas.printDatas();
            }

            /*out.write("Hasta La Vista Baby !\n***** Déconnexion *****");
            out.newLine();
            out.flush();*/
            messenger.sendMessage("Hasta La Vista Baby !\n***** Déconnexion *****");
            serverSocket.close();
            clientSocket.close();
        } catch (IOException IOE) {
            System.err.println(IOE);
        }
    }

    private boolean traiterCommande(Command cmd, StringBuffer answer, Parser parser){
        Command usableCommand = getUsableCommand(cmd);
        if(usableCommand == null) {
            //throw exception comme quoi le commande n'est pas présente
        }
        System.out.println("arguments : " + cmd.getArguments());
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

    /**
     *Fonction de gestion d'ajout de personne dans la HashMap
     *Cette fonction permet :
     *      d'ajouter un surnom à une personne déjà connu dans la hashmap
     *      d'ajouter un nom avec un ou plusieurs surnoms
     *
     **/
    String ajouterPersonne(List<String>requete){
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

    }

    public static void main(String[] args) {
        Serveur serveur = new Serveur();
        serveur.launch();
    }
}
