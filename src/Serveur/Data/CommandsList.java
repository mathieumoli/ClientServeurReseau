package Serveur.Data;

import Serveur.Command.*;

import java.util.ArrayList;

/**
 * Created by mathieumolinengo on 21/11/2015.
 */
public class CommandsList extends ArrayList{

    public CommandsList(){
        super();
        this.add(new ViewCommand());
        this.add(new AddCommand());
        this.add(new QuitCommand());
        this.add(new HelpCommand());
        this.add(new CountCommand());
    }
}
