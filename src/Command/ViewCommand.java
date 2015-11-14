package Command;

import Data.ChartDataBase;
import Utils.Parser;

import java.util.ArrayList;

/**
 * Created by user on 13/11/15.
 */
public class ViewCommand extends Command{

    public ViewCommand() {
        super("VIEW", new ArrayList<>());
    }

    @Override
    public boolean use(ChartDataBase data, StringBuffer command, Parser parser){
        System.out.println("J'exécute le view");
        return false;
    }

}
