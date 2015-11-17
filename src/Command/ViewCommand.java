package Command;

import Data.ChartDataBase;
import Utils.Parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 13/11/15.
 */
public class ViewCommand extends Command{

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

        if(this.getArguments().get(0).equals("NAMES")){
            allAnswers.add(data.getAllNamesInString());
            answer.append(parser.getCommandResult(true, this, allAnswers));
        } else if (this.getArguments().get(0).equals("NICKNAMES")){
            allAnswers.add(data.getAllNicknamesInString());
            answer.append(parser.getCommandResult(true, this, allAnswers));
        } else {
            allAnswers.add("Nom inconnu");
            answer.append(parser.getCommandResult(false, this, allAnswers));
        }
        return false;
    }

}
