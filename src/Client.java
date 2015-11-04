import java.io.IOException;
import java.net.Socket;

/**
 * Created by Molinengo/Soumille on 04/11/15.
 */
public class Client {
    private Socket nameSocket;

    public Client() {
        try {
            this.nameSocket = new Socket("localhost", 6969);
        } catch (IOException IOE){
            System.err.println(IOE);
        }
    }

    public static void main(String[] args) {
        Client cli = new Client();
    }
}
