package Exceptions;

import Utils.Utils;

/**
 * Created by user on 16/11/15.
 */
public class UnknownCmdException extends Exception {

    public UnknownCmdException(){
        super("Requete inconnue");
    }

    public String getMessage(){
        return super.getMessage();
    }
}
