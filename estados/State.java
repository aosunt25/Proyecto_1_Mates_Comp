package estados;


public class State{
    private String nombre;
    private boolean inicial=false;
    private boolean fin=false;

    public void setNombre(String s){
        nombre=s;
    }
    public String getNombre(){
        return nombre;
    }
    public void setInicial(boolean i){
        inicial=i;
    }
    public boolean getInicial(){
        return inicial;
    }
    public void setFin(boolean f){
        fin=f;
    }
    public boolean getFin(){
        return fin;
    }
}