import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import javax.sound.midi.Receiver;

import java.util.*;


public class Ndfa<T extends Comparable>{
    public HashMap<String, HashMap<String,ArrayList<String>>> transitionTable = new HashMap<String, HashMap<String,ArrayList<String>>>();
    String chara;
    String temp;
    int len, i , j;
    ArrayList<String> reachable = new ArrayList<>();
    ArrayList<String> tempe = new ArrayList<>();
    public ArrayList<String> previous = new ArrayList<>();
    
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
                    reachable.add(previous.get(k));
                    reachable.addAll(transitionTable.get(previous.get(k)).get("lmd"));
                    
                }
                //System.out.println("Pre");
                //System.out.println(previous);
                System.out.println("Processing " + str.charAt(i));
                previous= reachable;
                reachable = new ArrayList<String>();
                for(j = 0; j < previous.size();j++){
                    if(transitionTable.get(previous.get(j)).containsKey(chara)){
                        reachable.addAll(transitionTable.get(previous.get(j)).get(chara));
                        break;
                    }
                    else{
                        reachable.clear();
                    }
                    
                }
                previous= reachable;
                reachable = new ArrayList<String>();
                System.out.println("Processing lambda");
                for(int k = 0; k < previous.size();k++){
                    reachable.add(previous.get(k));
                    reachable.addAll(transitionTable.get(previous.get(k)).get("lmd"));
                    
                }
                
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

