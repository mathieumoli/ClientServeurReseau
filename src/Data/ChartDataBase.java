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

    public boolean addNickname(String name, String nickname){
        if(! nicknameAlreadyUsed(nickname)){
            mapNicknames.get(name).add(nickname);
            return true;
        }
        return false;
    }

    public boolean nicknameAlreadyUsed(String nickname){
        for(Map.Entry<String, HashSet<String>> entry : mapNicknames.entrySet()){
            for(String str : entry.getValue()){
                if(str.equals(nickname))
                    return true;
            }
        }
        return false;
    }

    public boolean addListOfNicknames(String name, List<String> allNicknames){
        boolean noConflict = true;
        for(String nickname : allNicknames){
            if(! addNickname(name, nickname))
                noConflict = false;
        }
        return noConflict;
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
