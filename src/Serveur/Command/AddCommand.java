package Serveur.Command;

import Serveur.Data.ChartDataBase;
import Serveur.Utils.Parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 13/11/15.
 */
public class AddCommand extends Command {

    public AddCommand(){
        super("ADD",new ArrayList<>());
    }

    /**
    * process executed when the client send an add command
    * @param data
    * @param answer
    * @param parser
    * @return
     */
    @Override
    public boolean use(ChartDataBase data, StringBuffer answer, Parser parser){
        List<String> allAnswers = new ArrayList<>();
        if(this.getArguments().isEmpty()){
            answer.append(parser.getCommandResult(true, this, new ArrayList<>()));
            return false;
        }
        String name = this.getArguments().get(0);
        //si il est déjà existant
        if(data.alreadyKnown(name)){
            if(this.getArguments().size() == 1){
                allAnswers.add("Nom deja connu");
                answer.append(parser.getCommandResult(false, this, allAnswers));
                return false;
            }
        } else {
            data.addName(name);
        }
        if(this.getArguments().size() != 1){
            List<String> allNicknames = parser.getParsedListByToken(this.getArguments().get(1), ",");
            if(! data.addListOfNicknames(name, allNicknames)){
                allAnswers.add("Surnom deja affecte a une autre personne");
                answer.append(parser.getCommandResult(false, this, allAnswers));
                return false;
            }
        }
        answer.append(parser.getCommandResult(true, this, new ArrayList<>()));
        return false;
    }

    /**
     * return all the syntaxes available for an add request
     * @return
     */
    @Override
    public List<String> getAllSyntaxes(){
        List<String> allSyntaxes = new ArrayList<>();
        allSyntaxes.add("ADD:nom:surnom1,surnom2");
        allSyntaxes.add("ADD:nom:surnom");
        allSyntaxes.add("ADD:nom");
        return allSyntaxes;
    }
}
