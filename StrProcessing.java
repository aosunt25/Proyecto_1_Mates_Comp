import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Collections;


public class StrProcessing<T extends Comparable<T>>{

    private boolean acceptance;
    public boolean accepted;
    Ndfa<String> ndfa = new Ndfa<>();
    ArrayList language = new ArrayList<String>();
    public String str;
    ArrayList reachableStates = new ArrayList<State>();

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
            accepted = stringProcessing(str, n, ndfa.getStates().get(0).getElement());
        }
        else{
            System.out.println("There are elements in the string that do not belong to this alphabet");
        }
        return accepted;
    }

    public boolean stringProcessing(String str, int n, String state){
        reachableStates = new ArrayList<String>();
        System.out.println(str);
        if(n == 0){
            //checar como cambiar la i
            //Aqui procesar cada simbolo posible
            System.out.println("Processing lambda on initial state " + ndfa.getStates().get(0).getElement());
            reachableStates.add(ndfa.getStates().get(0).getElement());

            for(int j = 0; j < ndfa.getStates().get(0).getTrans().size();j++){
                if(ndfa.getStates().get(0).getTrans().get(j).getSymbol() == 'l'){
                    //if(reachableStates.size() > 0 && j>1 && reachableStates.get(j) != reachableStates.get(j-1)){
                    reachableStates.add(ndfa.getStates().get(0).getTrans().get(j).getQ2().getElement());
                    //}
                }
                
            }

            System.out.println("Reachable states");
            System.out.println(reachableStates);
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
        }
        else if(n == 1){

        }
        else{
            str = "";
            for(int i = 1; i < str.length();i++){
                str += str.charAt(i);
            }
            //con un for enviar cada estado posible de reachable states con un solo string a procesar 
            System.out.println(str);
            stringProcessing(str, n-1, state);
        }

        
        
        return acceptance;
    }


}
     