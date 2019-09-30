//package estados;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List; 

public class State<T>{
    public List<Trans<T>> trans;
    private T element;
    private String type;
    private boolean visited;
    //private State<String> neighbour;

    public State(T element, String type) {
		this.element = element;
		this.type = type;
		trans= new ArrayList<>();
	}

    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return type;
    }

    public T getElement() {
		return element;
	}
	public void setElement(T element) {
		this.element = element;
    }
    
	public List<Trans<T>> getTrans() {
		return trans;
	}

	public void setTrans(List<Trans<T>> trans) {
		this.trans = trans;
	}

	public void addTrans(Trans<T> tr) {
		trans.add(tr);
    }
    
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
}		

