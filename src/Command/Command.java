package Command;

import Data.ChartDataBase;
import Utils.Parser;

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

    public boolean use(ChartDataBase data, String answer, Parser parser){
        return false;
    }

    public void setArguments(String args){
        this.arguments = args;
    }

    public String getArguments() {
        return arguments;
    }
}
