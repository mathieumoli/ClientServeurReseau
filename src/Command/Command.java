package Command;

import Data.ChartDataBase;

/**
 * Created by user on 13/11/15.
 */
public class Command {
    private String commandWord;

    private String arguments;

    public Command(String commandWord, String arguments) {
        this.commandWord = commandWord;
        this.arguments = arguments;
    }

    public boolean hasSameCommandWord(Command cmd){
        return this.commandWord.equals(cmd.getCommandWord());
    }

    public String getCommandWord() {
        return commandWord;
    }

    public boolean use(ChartDataBase data){
        return false;
    }


}
