import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Main{
    public static void main(String[] args){
        Ndfa<String> ndfa = new Ndfa<>();
        String strn;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the file's name");
        String nombreArchivo = scanner.nextLine() + ".txt";
        File archivo = new File (nombreArchivo);
        System.out.println(archivo.exists());
        
        ArrayList cadenaAlfabeto = new ArrayList<String>();
        ArrayList language = new ArrayList<String>();

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
                        //ndfa.addState(l);
                        //State s = new State(l);
                        //cadenaStados.add(s);
                        break;  
                        case 2:
                        cadenaAlfabeto.add(delimitar.next());
                        break;
                        case 3:
                        String s1 = delimitar.next();
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



        System.out.println("Language");
        System.out.println(" ");
        System.out.println(cadenaAlfabeto);

        language = cadenaAlfabeto;
        
        ndfa.addState("q0");
        ndfa.addState("q1");
        ndfa.addTrans("q0", "lmd", "q0");
        ndfa.addTrans("q0", "a", "q0");
        ndfa.addTrans("q0", "b", "q1");
        ndfa.addTrans("q1", "lmd", "q1");
        ndfa.addTrans("q1", "lmd", "q0");
        ndfa.addTrans("q1", "b", "q1");

        ndfa.addTrans("q1", "a", "q0");

        strn = "ababa";

        System.out.println(ndfa.transitionTable);
        
}
}
   