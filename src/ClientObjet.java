import requete.AjoutSurnom;
import requete.ListerPersonne;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by user on 27/11/15.
 */
public class ClientObjet {
    private Socket nameSocket;
    ObjectInputStream in;
    ObjectOutputStream out;
    public byte[] addrEdgard = new byte[] {(byte)10, (byte)212, (byte)121, (byte)165};
    int portEdgard = 8888;

    public ClientObjet() {
        try {
            String messageEnvoye="";
            InetAddress addr = Inet4Address.getByAddress(addrEdgard);
            this.nameSocket = new Socket(addr, portEdgard);
            in = new ObjectInputStream(nameSocket.getInputStream());
            out = new ObjectOutputStream(nameSocket.getOutputStream());
            //Scanner sc = new Scanner(System.in);
            System.out.println(in.readObject());
            ListerPersonne list = new ListerPersonne();
            out.writeObject(list);
            System.out.println(in.readObject());
            AjoutSurnom nouveauSurnom = new AjoutSurnom("Jackie", "<html><body>salut</body></html>");
            out.writeObject(nouveauSurnom);
            System.out.println(in.readObject());
            out.writeObject(list);
            System.out.println(in.readObject());
            out.writeObject(null);
            /*
            //System.out.println(in.readLine());
            while(){
                System.out.printf("Ecrire une requête et appuyer sur ENTER\n");
                messageEnvoye=sc.nextLine();
                System.out.println(messageEnvoye);
                out.println(messageEnvoye);
                System.out.println(in.readLine());
            }

            //System.out.println(in.readLine());
            //Close
            sc.close();*/
            nameSocket.close();
        } catch (IOException IOE){
            System.err.println(IOE);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ClientObjet cli = new ClientObjet();
    }
}
