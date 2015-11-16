package Exceptions;

import Utils.Utils;

/**
 * Created by user on 16/11/15.
 */
public class UnknownCmdException extends Exception {

    public UnknownCmdException(){
        super("Requete Inconnue");
    }

    public String getMessage(){
        return super.getMessage();
    }
}
