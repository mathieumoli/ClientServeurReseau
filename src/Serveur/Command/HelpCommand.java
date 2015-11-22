package Serveur.Command;

import Serveur.Data.*;
import Serveur.Utils.Parser;

import java.util.ArrayList;
import java.util.List;

import Serveur.Serveur;

/**
 * Created by Molinengo/Soumille on 04/11/15.
 */
public class HelpCommand extends Command{

    public HelpCommand(){
        super("HELP",new ArrayList<>());
    }


    @Override
    public boolean use(ChartDataBase data, StringBuffer answer, Parser parser) {

        List<String> allAnswers = new ArrayList<>();

        if (this.getArguments().size() >= 1) {
            allAnswers.add("Syntaxe incorrect");
            answer.append(parser.getCommandResult(false, this, allAnswers));
            return false;
        } else {

            List<Command> commandes = new CommandsList();
            /*int i;
            for (i = 0; i < commandes.size(); ++i) {
                Command c = commandes.get(i);
                if (c.getCommandWord().equals("ADD")) {
                    allAnswers.add("ADD:nom:surnom1,surnom2");
                    allAnswers.add("ADD:nom:surnom");
                    allAnswers.add("ADD:nom");
                } else {
                    if (c.getCommandWord().equals("VIEW")) {
                        List<String> arg = ((ViewCommand) c).getArgumentsConnus();
                        int j;
                        for (j = 0; j < arg.size(); ++j) {
                            allAnswers.add("VIEW:" + arg.get(j).toLowerCase());
                        }
                    }
                    allAnswers.add(commandes.get(i).getCommandWord());
                }
            }*/
            for(Command cmd : commandes) {
                for (String str : cmd.getAllSyntaxes()) {
                    allAnswers.add(str);
                }
            }
            answer.append(parser.getCommandResult(true, this, allAnswers));
            return false;
        }
    }

    @Override
    public List<String> getAllSyntaxes(){
        List<String> allSyntaxes = new ArrayList<>();
        allSyntaxes.add("HELP");
        return allSyntaxes;
    }


}
