package Command;

import Data.ChartDataBase;

/**
 * Created by user on 13/11/15.
 */
public class AddCommand extends Command {

    public AddCommand(){
        super("ADD","");
    }

    public AddCommand(String commandWord, String arguments) {
        super(commandWord, arguments);
    }

    @Override
    public boolean use(ChartDataBase data){
        System.out.println("Je fais un add");
        return false;
    }
}
