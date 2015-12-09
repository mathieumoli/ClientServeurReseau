import Serveur.Utils.Utils;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;


/**
 * Created by Molinengo/Soumille on 04/11/15.
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

    public void process(){
        String messageEnvoye="";
        try {
            System.out.println(in.readLine());
            while(!messageEnvoye.toUpperCase().equals("QUIT;")){
                System.out.printf("Ecrire une requête et appuyer sur ENTER\n");
                messageEnvoye=sc.nextLine();
                System.out.println(messageEnvoye);
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

    public void processWithOneRequest(String name){
        String messageEnvoye = "add:" + name + ";";
        try {
            //System.out.println(in.readLine());
            in.readLine();
            out.write(messageEnvoye);
            out.newLine();
            out.flush();
            //System.out.println(in.readLine());
            in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
