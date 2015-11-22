package Serveur.Command;

import Serveur.Data.ChartDataBase;
import Serveur.Utils.Parser;

import java.util.ArrayList;
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
        return this.commandWord.toUpperCase().equals(cmd.getCommandWord());
    }

    public String getCommandWord() {
        return commandWord.toUpperCase();
    }

    public boolean use(ChartDataBase data, StringBuffer answer, Parser parser){
        return false;
    }

    public void setArguments(List<String> args){
        this.arguments = args;
    }

    public List<String> getArguments() {
        List<String> args = new ArrayList<>();
        for(int i = 0 ; i < arguments.size() ; ++i){
            args.add(arguments.get(i).toUpperCase());
        }
        return args;
    }

    public List<String> getAllSyntaxes(){ return new ArrayList<>();};
}
