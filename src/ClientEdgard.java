import Serveur.Utils.Utils;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by user on 25/11/15.
 */
public class ClientEdgard {
    private Socket nameSocket;
    BufferedReader in;
    PrintWriter out;
    public byte[] addrEdgard = new byte[] {(byte)10, (byte)212, (byte)107, (byte)171};
    int portEdgard = 1337;

    public ClientEdgard() {
        try {
            String messageEnvoye="";
            InetAddress addr = Inet4Address.getByAddress(addrEdgard);
            this.nameSocket = new Socket(addr, portEdgard);
            in = new BufferedReader(new InputStreamReader(nameSocket.getInputStream()));
            out = new PrintWriter(nameSocket.getOutputStream(), true);
            Scanner sc = new Scanner(System.in);


            //System.out.println(in.readLine());
            while(!messageEnvoye.toUpperCase().equals("EXIT")){
                System.out.printf("Ecrire une requête et appuyer sur ENTER\n");
                messageEnvoye=sc.nextLine();
                System.out.println(messageEnvoye);
                out.println(messageEnvoye);
                System.out.println(in.readLine());
            }

            //System.out.println(in.readLine());
            //Close
            sc.close();
            nameSocket.close();
        } catch (IOException IOE){
            System.err.println(IOE);
        }
    }

    public static void main(String[] args) {
        ClientEdgard cli = new ClientEdgard();
    }
}
