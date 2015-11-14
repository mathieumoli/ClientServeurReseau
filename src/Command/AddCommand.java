package Command;

import Data.ChartDataBase;
import Utils.Parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by user on 13/11/15.
 */
public class AddCommand extends Command {

    public AddCommand(){
        super("ADD","");
    }

    public AddCommand(String commandWord, String arguments) {
        super(commandWord, arguments);
    }

    @Override
    public boolean use(ChartDataBase data, String answer, Parser parser){
        System.out.println("Je fais un add");
        //On parse les arguments
        List<String> nameAndNicknames = parser.getParsedListByToken(this.getArguments(), ":");
        String name = nameAndNicknames.get(0);
        //si il est déjà existant
        if(data.getMapNicknames().containsKey(name)){
            answer = "Nom deja connu";
            return false;
        }
        //On fait les modifications sur les Datas
        data.getMapNicknames().put(name, new HashSet<>());
        if(nameAndNicknames.size() == 1){
            //ecrit result
            return false;
        }
        List<String> allNicknames = parser.getParsedListByToken(nameAndNicknames.get(1), ",");
        for(String nicknames : allNicknames){
            data.getMapNicknames().get(name).add(nicknames);
        }
        //On écrit la réponse (en utilisant parser)
        return false;
    }
}
