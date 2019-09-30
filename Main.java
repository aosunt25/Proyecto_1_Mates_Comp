import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
//import estados.*;

class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the file's name");
        String nombreArchivo = scanner.nextLine() + ".txt";
        File archivo = new File (nombreArchivo);
        System.out.println(archivo.exists());
        ArrayList cadenaStados = new ArrayList<State>();
        ArrayList cadenaAlfabeto = new ArrayList<String>();
        cadenaAlfabeto.add("lmd");
        try{    
            scanner = new Scanner(archivo);
            int numDeLinea=1;
            while(scanner.hasNextLine()){
                String linea = scanner.nextLine();
                Scanner delimitar = new Scanner(linea);
                delimitar.useDelimiter("\\s*,\\s*");
                while(delimitar.hasNext()){
                    switch (numDeLinea){
                        case 1:
                        String l = delimitar.next();
                        State s = new State(l);
                        cadenaStados.add(s);
                        break;  
                        case 2:
                        cadenaAlfabeto.add(delimitar.next());
                        break;
                        case 3:
                        String s1 = delimitar.next();
                        System.out.println();
                        break;
                        default:
                        System.out.println(delimitar.next());
                        
                        break;  
                    }
                    
                }
                numDeLinea++;
            }

            scanner.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        Ndfa<String> ndfa = new Ndfa<>();
        
        ndfa.addState("q0");
        ndfa.addState("q1");
        ndfa.addTrans("q0", "q1", 'a');
        System.out.println(cadenaStados);
        System.out.println(cadenaAlfabeto);

}
}   