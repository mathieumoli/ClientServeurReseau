package Utils;

import Command.Command;

import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 13/11/15.
 * Toutes les fonctions qui s'occupe de couper/decouper les chaines
 */
public class Parser {

    /**
     * Return the command corresponding to the client request
     * @param req
     * @return the second argument isn't parsed
     */
    public Command getCommand(String req){
        if(!req.endsWith(";"))
            return new Command("","");
        else
            req = req.substring(0, req.length() - 1);

        List<String> lStrReq = Arrays.asList(req.split(":"));
        String firstWord = lStrReq.get(0);
        String args = "";
        for(String str : lStrReq){
            args += args;
        }
        return new Command(firstWord, args);
    }


    /**
     * Return List<String> which contains the elements of str parsed by the token
     * @param str
     * @param token
     * @return The List contains one element at least
     */
    public List<String> getParsedListByToken(String str, String token){
        return Arrays.asList(str.split(token));
    }
}
