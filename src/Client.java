import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Molinengo/Soumille on 04/11/15.
 */
public class Client {
    private Socket nameSocket;
    BufferedReader in;

    public Client() {
        try {
            this.nameSocket = new Socket("localhost", 6969);
            in = new BufferedReader(new InputStreamReader(nameSocket.getInputStream()));
            System.out.println(in.readLine());
        } catch (IOException IOE){
            System.err.println(IOE);
        }
    }

    public static void main(String[] args) {
        Client cli = new Client();
    }
}
