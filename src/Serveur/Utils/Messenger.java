package Serveur.Utils;

import java.io.*;
import java.net.Socket;

/**
 * Created by user on 16/11/15.
 */
public class Messenger {

    private BufferedReader in;
    private BufferedWriter out;

    public Messenger(Socket socketToBind) {
        try {
            in = new BufferedReader(new InputStreamReader(socketToBind.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socketToBind.getOutputStream()));
        } catch (IOException IOE) {
            System.err.println(IOE);
        }
    }

    /**
     *
     * @param str
     */
    public void sendMessage(String str){
        try {
            out.write(str);
            out.newLine();
            out.flush();
        } catch (IOException IOE) {
            System.err.println(IOE);
        }
    }

    /**
     *
     * @return
     */
    public String readMessage(){
        try {
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
