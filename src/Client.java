import Serveur.Utils.Utils;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;


/**
 * Created by Molinengo/Soumille on 04/11/15.
 * Client which will be connected to our server
 */
public class Client {
    private Socket nameSocket;
    BufferedReader in;
    BufferedWriter out;
    Scanner sc;

    public Client() {
        try {
            InetAddress addr = Inet4Address.getByAddress(Utils.addresseServeur);
            this.nameSocket = new Socket("localhost", Utils.NUM_PORT);
            in = new BufferedReader(new InputStreamReader(nameSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(nameSocket.getOutputStream()));
            sc = new Scanner(System.in);
        } catch (IOException IOE){
            System.err.println(IOE);
        }
    }

    /**
     * client with standart input to write requests
     */
    public void process(){
        String messageEnvoye="";
        try {
            System.out.println(in.readLine());
            while(!messageEnvoye.toUpperCase().equals("QUIT;")){
                System.out.printf("Ecrire une requête et appuyer sur ENTER\n");
                messageEnvoye=sc.nextLine();
                out.write(messageEnvoye);
                out.newLine();
                out.flush();
                System.out.println(in.readLine());
            }
            endConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Just send the request in parameter
     * @param name
     * @param req
     */
    public void processWithOneRequest(String req, String name){
        String messageEnvoye = req + ":" + name + ";";
        try {
            in.readLine();
            out.write(messageEnvoye);
            out.newLine();
            out.flush();
            in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * sent a request count
     */
    public void countDB(){
        String messageEnvoye = "count;";
        try {
            //System.out.println();
            in.readLine();
            out.write(messageEnvoye);
            out.newLine();
            out.flush();
            System.out.println(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * close the connection with the server
     */
    private void endConnection(){
        sc.close();
        try {
            nameSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client cli = new Client();
        cli.process();
    }
}
