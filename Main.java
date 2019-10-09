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
        
        String initState = null;
        ArrayList cadenaAlfabeto = new ArrayList<String>();
        ArrayList language = new ArrayList<String>();
        ArrayList finalState = new ArrayList<String>();

        cadenaAlfabeto.add("lmd");
        try{    
            scanner = new Scanner(archivo);
            int numDeLinea=1;
            while(scanner.hasNextLine()){
                String linea = scanner.nextLine();
                Scanner delimitar = new Scanner(linea);
                switch (numDeLinea){
                    case 1:
                    delimitar.useDelimiter("\\s*,|=>\\s*");
                    while(delimitar.hasNext()){
                        String l = delimitar.next();
                        ndfa.addState(l);
                    }
                    break;  
                    case 2:
                    delimitar.useDelimiter("\\s*,|=>\\s*");
                    while(delimitar.hasNext()){
                        cadenaAlfabeto.add(delimitar.next());
                    }
                    break;
                    case 3:
                    delimitar.useDelimiter("\\s*,|=>\\s*");
                    while(delimitar.hasNext()){
                        initState= delimitar.next();
                    }
                    break;
                    case 4:
                    delimitar.useDelimiter("\\s*,|=>\\s*");
                    while(delimitar.hasNext()){
                        finalState.add(delimitar.next());
                    }
                    break;
                    default:
                    //String arrStr [];
                    ArrayList <String> arrStr = new ArrayList<String>();
                    int i=0;
                    delimitar.useDelimiter("\\s*,|=>\\s*");
                    while(delimitar.hasNext()){
                       arrStr.add(delimitar.next());
                       i++;
                    }
                    for(int j = 2; j<arrStr.size();j++){
                        ndfa.addTrans(arrStr.get(0),arrStr.get(1),arrStr.get(j));
                    }
                    break;  
                }
                    
                numDeLinea++;
            }

            scanner.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }



        System.out.println("Language");
        System.out.println(cadenaAlfabeto);
        System.out.println(" ");
        System.out.println("Initial State");
        System.err.println(initState);
        System.out.println(" ");
        System.out.println("Final State");
        System.out.println(finalState);
        System.out.println(" ");

        language = cadenaAlfabeto;
        
        /*ndfa.addTrans("q0", "lmd", "q0");
        ndfa.addTrans("q0", "a", "q0");
        ndfa.addTrans("q0", "b", "q1");
        ndfa.addTrans("q1", "lmd", "q1");
        ndfa.addTrans("q1", "lmd", "q0");
        ndfa.addTrans("q1", "b", "q1");

        ndfa.addTrans("q1", "a", "q0");*/

        strn = "ababa";

        System.out.println(ndfa.transitionTable);
        
}
}
   