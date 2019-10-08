import java.util.*;
import java.util.HashMap;


public class Ndfa<T extends Comparable>{
    //Ver su puedo hacer una lista con transitions

    public HashMap<String,String> transitions = new HashMap<String, String>();
    public HashMap<String,String> transitions1 = new HashMap<String, String>();
    public HashMap<String,String> transitions2 = new HashMap<String, String>();
    public List<T> trans;
    public HashMap<String, HashMap<String,String>> transitionTable = new HashMap<String, HashMap<String,String>>();

    public Ndfa(){
        
    }

    public void addState(String llave){
        if(llave == "q0"){
            transitionTable.put(llave, transitions);
        }
        else if(llave == "q1"){
            transitionTable.put(llave, transitions1);
        }
        
    }

    public void addTrans(String state1,String symbol , String state2 ){
        //Cada uno tiene un array de transitions
        if(state1 == "q0"){
            transitions.put(symbol, state2);
            transitionTable.put(state1, transitions);
        }
        else if(state1 == "q1"){
            transitions1.put(symbol, state2);
            transitionTable.put(state1, transitions1);
        }
        System.out.println(transitionTable);
    }

    
}