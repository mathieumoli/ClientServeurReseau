package Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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
