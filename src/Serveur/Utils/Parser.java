package Serveur.Utils;

import Serveur.Command.Command;
import Serveur.Exceptions.SyntaxeException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
    public Command getCommand(String req) throws SyntaxeException{
        String reqTrim = req.trim();
        if(!reqTrim.endsWith(";"))
            throw new SyntaxeException(new Command("RE", new ArrayList<>()));
        else
            reqTrim = reqTrim.substring(0, reqTrim.length() - 1);

        List<String> lStrReq = new LinkedList<String>(Arrays.asList(reqTrim.split(":")));
        String firstWord = lStrReq.get(0);
        lStrReq.remove(0);

        return new Command(firstWord, lStrReq);
    }

    /**
     * Return List<String> which contains the elements of str parsed by the token
     * @param str
     * @param token
     * @return The List contains one element at least
     */
    public List<String> getParsedListByToken(String str, String token){
        if(str == null) return new ArrayList<>();
        return Arrays.asList(str.split(token));
    }

    /**
     * Return the result of the command
     * @param noError
     * @param cmd
     * @param text
     * @return
     */
    public String getCommandResult(boolean noError, Command cmd, List<String> text){
        String result = "";
        result = (noError == true) ? "OK" : "ERR";
        result += ":" + cmd.getCommandWord();
        if(text.size() != 0)
            result += ":";
        for(int i = 0 ; i < text.size() ; ++i){
            result += text.get(i);
            if(i != text.size() - 1)
                result += "$";
        }
        return result += ";";
    }
}
