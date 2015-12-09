package Serveur.Command;

import Serveur.Data.ChartDataBase;
import Serveur.Utils.Parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 09/12/15.
 */
public class CountCommand extends Command {

    public CountCommand() { super("COUNT", new ArrayList<>());}

    @Override
    public boolean use(ChartDataBase data, StringBuffer answer, Parser parser) {

        List<String> allAnswers = new ArrayList<>();
        if (this.getArguments().size() >= 1) {
            allAnswers.add("Syntaxe incorrect");
            answer.append(parser.getCommandResult(false, this, allAnswers));
        } else {
            String nbNom = "NbNom = " + data.getNbNom();
            allAnswers.add(nbNom);
            answer.append(parser.getCommandResult(true, this, allAnswers));
        }
        return false;
    }

    @Override
    public List<String> getAllSyntaxes(){
        List<String> allSyntaxes = new ArrayList<>();
        allSyntaxes.add("COUNT");
        return allSyntaxes;
    }
}
