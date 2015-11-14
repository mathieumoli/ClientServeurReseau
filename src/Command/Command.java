package Command;

import Data.ChartDataBase;
import Utils.Parser;

import java.util.List;

/**
 * Created by user on 13/11/15.
 */
public class Command {
    private String commandWord;

    private List<String> arguments;

    public Command(String commandWord, List<String> arguments) {
        this.commandWord = commandWord;
        this.arguments = arguments;
    }

    public boolean hasSameCommandWord(Command cmd){
        return this.commandWord.equals(cmd.getCommandWord());
    }

    public String getCommandWord() {
        return commandWord;
    }

    public boolean use(ChartDataBase data, StringBuffer answer, Parser parser){
        return false;
    }

    public void setArguments(List<String> args){
        this.arguments = args;
    }

    public List<String> getArguments() {
        return arguments;
    }
}
