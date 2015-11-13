package Command;

import Data.ChartDataBase;

/**
 * Created by user on 13/11/15.
 */
public class QuitCommand extends Command {

    public QuitCommand() {
        super("QUIT", "");
    }

    @Override
    public boolean use(ChartDataBase data){
        return true;
    }
}
