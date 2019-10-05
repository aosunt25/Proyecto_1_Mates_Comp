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
            accepted = stringProcessing(str, n);
        }
        else{
            System.out.println("There are elements in the string that do not belong to this alphabet");
        }
        return accepted;
    }

    public boolean stringProcessing(String str, int n){
        reachableStates = new ArrayList<String>();
        if(n == 0){
            //checar como cambiar la i
            for(int i = 0; i < ndfa.getStates().size();i++){
                if(ndfa.getStates().get(i).getType() == "ini"){
                    System.out.println("Processing lambda on initial state " + ndfa.getStates().get(i).getElement());
                    reachableStates.add(ndfa.getStates().get(i).getElement());

                    for(int j = 0; j < ndfa.getStates().get(i).getTrans().size();j++){
                        if(ndfa.getStates().get(i).getTrans().get(j).getSymbol() == 'l'){
                            if(reachableStates.size() > 1 && j>1 && reachableStates.get(j) != reachableStates.get(j-1)){
                                reachableStates.add(ndfa.getStates().get(i).getTrans().get(j).getQ2().getElement());
                            }
                        }
                        
                    }
                }                 
            }
        }
        else{

        }
        System.out.println("Reachable states");
        System.out.println(reachableStates);
        
        
        //Aqui checar la interseccion de estados
        /*if(System.out.println(ndfa.getStates().get(0).getType() == "fin"){
            acceptance = true;
        }*/
        return acceptance;
    }


}
     