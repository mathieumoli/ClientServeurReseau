/**
 * Created by Soumille Lucas 09/12/15.
 * Main class to create 1000 clients to test the multithread server
 */


public class MultiClient {

    public static void main(String[] args) {
        for(int i = 0 ; i < 1000 ; ++i){
            Client cli = new Client();
            cli.processWithOneRequest("add","name"+i);
        }
        Client cli = new Client();
        cli.countDB();
    }
}
