import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javax.sound.midi.Receiver;
import java.util.*;
/**
 * 
 * @param <T>
 */

public class Ndfa<T extends Comparable>{
    public HashMap<String, HashMap<String,ArrayList<String>>> transitionTable = new HashMap<String, HashMap<String,ArrayList<String>>>();
    String chara;
    String temp;
    int len, i , j;
    ArrayList<String> reachable = new ArrayList<>();
    ArrayList<String> tempe = new ArrayList<>();
    public ArrayList<String> previous = new ArrayList<>();
    /**
     * 
     */
    public Ndfa(){
        
    }
    /**
     * This method adds the states as a key of the HashMap
     * @param state The name of the state 
     * 
     */
    public void addState(String state){
        transitionTable.put(state, new HashMap<String, ArrayList<String>>());
    }
    /**
     * This method adds the transactions that can happen in a single state
     * Use another HashMap to add those transactions
     * The symbol is the Key of this new HashMap
     * 
     * @param state1 The key of the first HashMap, the initial state of the transaction
     * @param symbol The char which make the transaction posible
     * @param state2 The final state after the transaction
     * 
     */
    public void addTrans(String state1,String symbol , String state2 ){
        if(transitionTable.get(state1).get(symbol) == null){
            transitionTable.get(state1).put(symbol, new ArrayList<>());
        }
        transitionTable.get(state1).get(symbol).add(state2);
    }
    
    /**
     * This method analyzes the string
     * @param str The string that will be analys 
     * @param ini The initial state of the NDFA
     * @return All the reachable states when proccessing the whole string 
     * that will be analys
     */
    public ArrayList stringProcessing(String str, String ini){
        previous = new ArrayList<String>();
        previous.add(ini);
        if(str == ""){
            reachable.addAll(transitionTable.get(previous.get(0)).get("lmd"));
        }
        else{
            for(i = 0; i < str.length();i++){
                
                chara = String.valueOf(str.charAt(i)) ;
                reachable = new ArrayList<String>();
                System.out.println("Processing lambda");
                for(int k = 0; k < previous.size();k++){
                    reachable.addAll(transitionTable.get(previous.get(k)).get("lmd"));
                    
                }
                System.out.println(reachable);
                System.out.println("Processing " + str.charAt(i));
                previous= reachable;
                reachable = new ArrayList<String>();
                for(j = 0; j < previous.size();j++){
                    if(transitionTable.get(previous.get(j)).containsKey(chara)){
                        reachable.addAll(transitionTable.get(previous.get(j)).get(chara));
                        
                    }
                    else{
                        reachable.clear();
                    }
                    
                }
                System.out.println(reachable);
                previous= reachable;    
                reachable = new ArrayList<String>();
                System.out.println("Processing lambda");
                for(int k = 0; k < previous.size();k++){
                    reachable.addAll(transitionTable.get(previous.get(k)).get("lmd"));
                    
                }
                System.out.println(reachable);
                previous = reachable;
                System.out.println("reachable");
                System.out.println(reachable);
            }
            if(reachable.size()>1){
                return reachable;
            }
            else{
                System.out.println("Processing lambda");
                previous = reachable;
                reachable = new ArrayList<>();
                if(previous.size() > 0){
                    System.out.println(reachable + "holi");
                    for(int k = 0; k < previous.size();k++){
                        reachable.addAll(transitionTable.get(previous.get(k)).get("lmd"));
                    }
            }
            }
            
        }
        
        
        return reachable;
    }
        
}

