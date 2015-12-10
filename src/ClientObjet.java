//import requete.AjoutSurnom;
//import requete.ListerPersonne;

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
    public byte[] addrGatien = new byte[] {(byte)10, (byte)212, (byte)121, (byte)165};
    int portGatien = 8888;

    public ClientObjet() {
        /**
         * Must include gatien's library and decomment the code below
         */
        /*try {
            String messageEnvoye="";
            InetAddress addr = Inet4Address.getByAddress(addrGatien);
            this.nameSocket = new Socket(addr, portGatien);
            in = new ObjectInputStream(nameSocket.getInputStream());
            out = new ObjectOutputStream(nameSocket.getOutputStream());
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
            nameSocket.close();
        } catch (IOException IOE){
            System.err.println(IOE);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }

    public static void main(String[] args) {
        ClientObjet cli = new ClientObjet();
    }
}
