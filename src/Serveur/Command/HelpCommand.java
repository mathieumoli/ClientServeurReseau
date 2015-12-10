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

    /**
     * process executed when the client send a help command
     * @param data
     * @param answer
     * @param parser
     * @return
     */
    @Override
    public boolean use(ChartDataBase data, StringBuffer answer, Parser parser) {
        List<String> allAnswers = new ArrayList<>();
        if (this.getArguments().size() >= 1) {
            allAnswers.add("Syntaxe incorrect");
            answer.append(parser.getCommandResult(false, this, allAnswers));
            return false;
        } else {
            List<Command> commandes = new CommandsList();
            for(Command cmd : commandes) {
                for (String str : cmd.getAllSyntaxes()) {
                    allAnswers.add(str);
                }
            }
            answer.append(parser.getCommandResult(true, this, allAnswers));
            return false;
        }
    }

    /**
     * return all the syntaxes available for a help request
     * @return
     */
    @Override
    public List<String> getAllSyntaxes(){
        List<String> allSyntaxes = new ArrayList<>();
        allSyntaxes.add("HELP");
        return allSyntaxes;
    }


}
