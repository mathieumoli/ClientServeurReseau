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
            //erreur syntaxe
        }
        if(this.getArguments().isEmpty()){
            //return tout
        }

        if(this.getArguments().get(0).toLowerCase().equals("names")){
            //affiche les nomns
        } else if(this.getArguments().get(0).toLowerCase().equals("nicknames")){
            //affiche les surnoms
        } else {
            //erreur nom inconnu
        }

        return false;
    }

}
