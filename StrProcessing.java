import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Collections;


public class StrProcessing<T extends Comparable<T>>{

    int len = 0;
    String temp;
    private char symbol;
    private boolean acceptance = false;
    public boolean accepted,appear;
    Ndfa<String> ndfa = new Ndfa<>();
    ArrayList language = new ArrayList<String>();
    public String str;
    public ArrayList reachableStates = new ArrayList<State>();

    public StrProcessing(Ndfa<String> ndfa){   
        this.ndfa = ndfa;
    }
    
    public boolean stringProcessing(String strn, int n, ArrayList language){
        //This function is only to check if there are elements in the string that do not belong to the alphabet
        accepted = true;
        System.out.println(strn);
        if(strn.length()>0){
            for(int i = 0; i < strn.length(); i++){
          
                for(int j = 0; j < language.size();j++){
                    if(strn.charAt(i) == language.get(j).toString().charAt(0)){
                        accepted = true;
                        break;
                    }
                    else{
                        accepted = false;
                    }
                }
            }
        }

        if(accepted){
            accepted = stringProcessing(strn, n, ndfa.getStates().get(0).getElement());
        }
        else{
            System.out.println("There are elements in the string that do not belong to this alphabet");
        }
        return accepted;
    }


    public boolean stringProcessing(String str, int n, String state){
        reachableStates = new ArrayList<String>();
        appear = false;
        System.out.println(n);
        
        System.out.println(str);
        if(n == 0){
            System.out.println(str);
            int index = 0;
            
            reachableStates.add(state);

            for(int i = 0; i < ndfa.getStates().size();i++){
                if(ndfa.getStates().get(i).getElement() == state){
                    index = i;
                }
            }
            int reach = reachableStates.size();
            for(int j = 0; j < ndfa.getStates().get(index).getTrans().size();j++){   
                if(ndfa.getStates().get(index).getTrans().get(j).getSymbol() == 'l'){
                    for(int k = 0; k < reach ;k++){
                       if(reachableStates.get(k).toString() == ndfa.getStates().get(index).getTrans().get(j).getQ2().getElement()){
                            appear = true;
                        }
                        else{
                            appear = false;
                        }
                    }
                    if(appear == false){
                        reachableStates.add(ndfa.getStates().get(index).getTrans().get(j).getQ2().getElement());
                    }
                }
                
            }
            
            for(int i = 0; i < reachableStates.size();i++){
                for(int j = 0; j < ndfa.getStates().size();j++){
                    if(reachableStates.get(i) == ndfa.getStates().get(j).getElement()){
                        if(ndfa.getStates().get(j).getType() == "fin"){
                            acceptance = true;
                            System.out.println(ndfa.getStates().get(j).getElement() + " is a final state");
                        }
                    }
                }
                
            }
            System.out.println("Reachable states");
            System.out.println(reachableStates);

        }
        else{
            
            symbol = str.charAt(0);
            len = str.length();
            temp = str;
            str = "";
            for(int i = 1; i < len;i++){
                str += temp.charAt(i);
            }
                    
            int index = 0;

            System.out.println("Processing lambda on state " + state);
            reachableStates.add(state);
            for(int i = 0; i < ndfa.getStates().size();i++){
                if(ndfa.getStates().get(i).getElement() == state){
                    index = i;
                }
            }
            int reach = reachableStates.size();
            for(int j = 0; j < ndfa.getStates().get(index).getTrans().size();j++){
                
                if(ndfa.getStates().get(index).getTrans().get(j).getSymbol() == 'l'){
                    for(int k = 0; k < reach ;k++){
                        if(reachableStates.get(k).toString() == ndfa.getStates().get(index).getTrans().get(j).getQ2().getElement()){
                            appear = true;
                        }
                        else{
                            appear = false;
                        }
                    }
                    if(appear == false){
                        reachableStates.add(ndfa.getStates().get(index).getTrans().get(j).getQ2().getElement());
                    }
                }
                else if(ndfa.getStates().get(index).getTrans().get(j).getSymbol() == symbol){
                    System.out.println("Processing " + symbol + " from " + state);
                    for(int k = 0; k < reach ;k++){
                        if(reachableStates.get(k).toString() == ndfa.getStates().get(index).getTrans().get(j).getQ2().getElement()){
                            appear = true;
                        }
                        else{
                            appear = false;
                        }
                    }
                    if(appear == false){
                        reachableStates.add(ndfa.getStates().get(index).getTrans().get(j).getQ2().getElement());
                    }
                }
                
            }
            System.out.println("Reachable states");
            System.out.println(reachableStates);
            
            /*
            if(reachableStates.size()>0){
                for(int i = 0; i < reachableStates.size();i++){
                    if(str.length()>0){
                        System.out.println("Processing " + str.charAt(0) + " on state " + reachableStates.get(i).toString());
                        if(reachableStates.size()>1){
                            stringProcessing(str, n-1, reachableStates.get(0).toString());
                            stringProcessing(str, n-1, reachableStates.get(1).toString());
                        }
                        else{
                            stringProcessing(str, n-1, reachableStates.get(0).toString());
                        }

                    }
                    else{
                        stringProcessing("", n-1, reachableStates.get(i).toString());
                    }            
                }
            }*/
            
        }        
        
        
        
        
        
        return acceptance;
    }


}
     