package Serveur.Command;

import Serveur.Data.ChartDataBase;
import Serveur.Utils.Parser;

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

    @Override
    public List<String> getAllSyntaxes() {
        List<String> allSyntaxes = new ArrayList<>();
        allSyntaxes.add("QUIT");
        return allSyntaxes;
    }
}
