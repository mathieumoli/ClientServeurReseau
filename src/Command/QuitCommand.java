package Command;

import Data.ChartDataBase;
import Utils.Parser;

import java.util.ArrayList;

/**
 * Created by user on 13/11/15.
 */
public class QuitCommand extends Command {

    public QuitCommand() {
        super("QUIT", new ArrayList<>());
    }

    @Override
    public boolean use(ChartDataBase data , StringBuffer answer, Parser parser){
        return true;
    }
}
