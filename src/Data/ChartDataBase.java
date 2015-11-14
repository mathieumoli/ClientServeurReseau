package Data;

import java.util.*;

/**
 * Created by user on 13/11/15.
 */
public class ChartDataBase {
    private HashMap<String, HashSet<String>> mapNicknames;

    public ChartDataBase() {
        this.mapNicknames = new HashMap<>();
    }

    public HashMap<String, HashSet<String>> getMapNicknames() {
        return mapNicknames;
    }

    public void addName(String name){
        mapNicknames.put(name, new HashSet<>());
    }

    public boolean alreadyKnown(String name) {
        return mapNicknames.containsKey(name);
    }

    public void addNickname(String name, String nickname){
        mapNicknames.get(name).add(nickname);
    }

    public void addListOfNicknames(String name, List<String> allNicknames){
        for(String nickname : allNicknames){
            mapNicknames.get(name).add(nickname);
        }
    }

    public void printDatas(){
        for(Map.Entry<String, HashSet<String>> entry : mapNicknames.entrySet()){
            System.out.print(entry.getKey() + " = ");
            for(String str : entry.getValue()){
                System.out.print(str + " ");
            }
            System.out.println();
        }
    }
}
