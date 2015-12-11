package Serveur.Exceptions;

import Serveur.Command.Command;

/**
 * Created by user on 16/11/15.
 * SyntaxeException class
 */
public class SyntaxeException extends Exception {
    private Command cmdInError;

    public SyntaxeException(Command cmdErr){
        super("Syntaxe incorrect");
        cmdInError = cmdErr;
    }

    public Command getCmdInError() {
        return cmdInError;
    }

    public String getMessage(){

        return super.getMessage();
    }
}
