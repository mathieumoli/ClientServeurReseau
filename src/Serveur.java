import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Molinengo/Soumille on 04/11/15.
 */
public class Serveur {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader in;
    private BufferedWriter out;
    private static final int numPort = 6969;

    public void launch(){
        try {
            serverSocket = new ServerSocket(numPort);
            clientSocket = serverSocket.accept();
            System.out.println("Nous sommes co");
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        } catch (IOException IOE) {
            System.err.println(IOE);
        }

    }
}
