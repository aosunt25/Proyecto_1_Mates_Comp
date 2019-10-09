import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import java.util.*;


public class Ndfa<T extends Comparable>{
    public HashMap<String, HashMap<String,ArrayList<String>>> transitionTable = new HashMap<String, HashMap<String,ArrayList<String>>>();

    public Ndfa(){
        
    }

    public void addState(String llave){
        transitionTable.put(llave, new HashMap<String, ArrayList<String>>());
    }

    public void addTrans(String state1,String symbol , String state2 ){
        if(transitionTable.get(state1).get(symbol) == null){
            transitionTable.get(state1).put(symbol, new ArrayList<>());
        }
        

        transitionTable.get(state1).get(symbol).add(state2);
        //System.out.println(transitionTable.get(state1).get(symbol));
    }
    

    public void stringProcessing(String str){

    }
}
