import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

//import estados.*;
public class Ndfa<T extends Comparable<T>>{

	private List<State<T>> states;
	private Stack<State<T>> stack;

    public Ndfa(){
		states = new ArrayList<>();
		stack= new Stack<>();
    }

    public void addState(T qi, String type){
        boolean visited = false;
        for(State<T> state : states){
            if(state.getElement().compareTo(qi)==0){
                visited=true;
            }
        }
        if(!visited){
            states.add(new State<>(qi, type));
        }
    }

	public List<State<T>> getStates(){
		return states;
	}


    public void addTrans(T q1, T q2, char symbol) {
		State<T> temp1= null;
		State<T> temp2= null;
		for(State <T> state: states) {
			if(state.getElement().compareTo(q1)==0) {
				temp1= state;
			}
		}
        for(State <T> state: states) {
			if(state.getElement().compareTo(q2)==0) {
				temp2= state;
			}
		}
		if(temp1!=null&&temp2!=null) {
			Trans<T> tr= new Trans<>(temp1,temp2,symbol);
			temp1.addTrans(tr);
			temp2.addTrans(tr);
		}
	}
    
    public void rein() {
		for(State<T> state:states) {
			state.setVisited(false);
		}
	}

}
 
