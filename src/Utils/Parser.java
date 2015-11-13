package Utils;

import Command.Command;

import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 13/11/15.
 * Toutes les fonctions qui s'occupe de couper/decouper les chaines
 */
public class Parser {

    public Command getCommand(String req){
        if(!req.endsWith(";"))
            return new Command("","");
        else
            req = req.substring(0, req.length() - 1);

        List<String> lStrReq = Arrays.asList(req.split(":"));
        String firstWord = lStrReq.get(0);
        System.out.println("GetCommand");
        String args = "";
        for(String str : lStrReq){
            args += args;
        }
        return new Command(firstWord, args);
    }
}
