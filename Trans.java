
public class Trans<T> {
	
    private State<T> q1,q2;
    private char symbol;

	public Trans(State<T> q1, State<T> q2, char symbol) {
		super();
		this.q1 = q1;
        this.q2 = q2;
        this.symbol = symbol;
	}

	public State<T> getQ1() {
		return q1;
	}

	public void setQ1(State<T> q1) {
		this.q1 = q1;
	}

	public State<T> getQ2() {
		return q2;
	}

	public void setQ2(State<T> q2) {
		this.q2 = q2;
	}
    
    public void setSymbol(char symbol){
        this.symbol = symbol;
    }
    
    public char getSymbol(){
        return symbol;
    }
	

}