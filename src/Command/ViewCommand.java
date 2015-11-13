package Command;

import Data.ChartDataBase;

/**
 * Created by user on 13/11/15.
 */
public class ViewCommand extends Command{

    public ViewCommand() {
        super("VIEW", "");
    }

    public ViewCommand(String commandWord, String arguments) {
        super(commandWord, arguments);
    }

    @Override
    public boolean use(ChartDataBase data){
        System.out.println("J'exécute le view");
        return false;
    }

}
