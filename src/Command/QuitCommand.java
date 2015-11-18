package Command;

import Data.ChartDataBase;
import Utils.Parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 13/11/15.
 */
public class QuitCommand extends Command {

    public QuitCommand() {
        super("QUIT", new ArrayList<>());
    }

    @Override
    public boolean use(ChartDataBase data , StringBuffer answer, Parser parser){
        answer.append(parser.getCommandResult(true, this, new ArrayList<>()));
        return true;
    }
}
