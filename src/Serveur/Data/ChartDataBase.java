package Serveur.Data;

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

    /**
     * return the number of name in the hashmap
     * @return
     */
    public int getNbNom(){
        return mapNicknames.entrySet().size();
    }

    /**
     * check if the nickname is already used in the hashmap
     * @param nickname
     * @return
     */
    public boolean nicknameAlreadyUsed(String nickname){
        for(Map.Entry<String, HashSet<String>> entry : mapNicknames.entrySet()){
            for(String str : entry.getValue()){
                if(str.equals(nickname))
                    return true;
            }
        }
        return false;
    }

    /**
     * add all the nicknames in the list for the name in param
     * @param name
     * @param allNicknames
     * @return
     */
    public boolean addListOfNicknames(String name, List<String> allNicknames){
        boolean noConflict = true;
        for(String nickname : allNicknames){
            if(! addNickname(name, nickname))
                noConflict = false;
        }
        return noConflict;
    }

    /**
     * return all the datas in a list with the syntaxe nom:nicknames1,nicknames2
     * @return
     */
    public List<String> getAllDatasInListString(){
        List<String> allDatas = new ArrayList<>();
        for(Map.Entry<String, HashSet<String>> entry : mapNicknames.entrySet()){
            String strName = entry.getKey() + ":";
            for(String str : entry.getValue()){
                strName += str + ",";
            }
            allDatas.add(strName.substring(0, strName.length() - 1));
        }
        return allDatas;
    }

    /**
     * return all the names concact in one string
     * @return
     */
    public String getAllNamesInString(){
        String str = "";
        for(Map.Entry<String, HashSet<String>> entry : mapNicknames.entrySet()){
            str += entry.getKey() + ",";
        }
        return str.substring(0, str.length() - 1);
    }

    /**
     * return all the nicknames concact in one string
     * @return
     */
    public String getAllNicknamesInString(){
        String str = "";
        for(Map.Entry<String, HashSet<String>> entry : mapNicknames.entrySet()){
            for(String s : entry.getValue()){
                str += s + ",";
            }
        }
        return str.substring(0, str.length() - 1);
    }

    /**
     * write on the standard output the content of the hashmap
     */
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
