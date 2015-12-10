/**
 * Created by user on 09/12/15.
 */
public class MultiClient {

    public static void main(String[] args) {
        for(int i = 0 ; i < 100000 ; ++i){
            Client cli = new Client();
            cli.processWithOneRequest("add","name"+i);
        }
        Client cli = new Client();
        cli.countDB();
    }
}
