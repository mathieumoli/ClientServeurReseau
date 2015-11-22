package Serveur.Command;

import Serveur.Data.ChartDataBase;
import Serveur.Utils.Parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 13/11/15.
 */
public class ViewCommand extends Command{

    private List<String> argumentsConnus = new ArrayList<String>(){{add("NAMES");add("NICKNAMES");}};

    public ViewCommand() {
        super("VIEW", new ArrayList<>());
    }

    @Override
    public boolean use(ChartDataBase data, StringBuffer answer, Parser parser){
        List<String> allAnswers = new ArrayList<>();
        if(this.getArguments().size() >= 2){
            allAnswers.add("Syntaxe incorrect");
            answer.append(parser.getCommandResult(false, this, allAnswers));
            return false;
        }
        if(this.getArguments().isEmpty()){
            answer.append(parser.getCommandResult(true, this, data.getAllDatasInListString()));
            return false;
        }
        //Verification si l'argument vaut NAMES
        if(this.getArguments().get(0).equals(argumentsConnus.get(0))){
            allAnswers.add(data.getAllNamesInString());
            answer.append(parser.getCommandResult(true, this, allAnswers));
            //Verification si l'argument vaut NICKNAMES
        } else if (this.getArguments().get(0).equals(argumentsConnus.get(1))){
            allAnswers.add(data.getAllNicknamesInString());
            answer.append(parser.getCommandResult(true, this, allAnswers));
        } else {
            allAnswers.add("Nom inconnu");
            answer.append(parser.getCommandResult(false, this, allAnswers));
        }
        return false;
    }

    public List<String> getArgumentsConnus() {
        return argumentsConnus;
    }

    @Override
    public List<String> getAllSyntaxes() {
        List<String> allSyntaxes = new ArrayList<>();
        for (String str : argumentsConnus) {
            allSyntaxes.add("VIEW:" + str.toLowerCase());
        }
        return allSyntaxes;
    }
}
