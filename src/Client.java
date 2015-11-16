import Utils.Utils;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


/**
 * Created by Molinengo/Soumille on 04/11/15.
 */
public class Client {
    private Socket nameSocket;
    BufferedReader in;
    BufferedWriter out;

    public Client() {
        try {
            String messageEnvoye="";
            this.nameSocket = new Socket("localhost", Utils.NUM_PORT);
            in = new BufferedReader(new InputStreamReader(nameSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(nameSocket.getOutputStream()));
            Scanner sc = new Scanner(System.in);


            System.out.println(in.readLine());
            while(!messageEnvoye.equals("QUIT")){
                System.out.printf("Ecrire une requête et appuyer sur ENTER\n");
                messageEnvoye=sc.nextLine();
                out.write(messageEnvoye);
                out.newLine();
                out.flush();
                System.out.println(in.readLine());
            }

            System.out.println(in.readLine());
            //Close
            sc.close();
            nameSocket.close();
        } catch (IOException IOE){
            System.err.println(IOE);
        }
    }

    public static void main(String[] args) {
        Client cli = new Client();
    }
}
