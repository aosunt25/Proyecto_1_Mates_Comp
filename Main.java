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

        Ndfa<String> ndfa = new Ndfa<>();
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

       
        
        ndfa.addState("q0", "ini");
        ndfa.addState("q1", "fin");
        ndfa.addTrans("q0", "q0", 'l');
        ndfa.addTrans("q0", "q0", 'a');
        ndfa.addTrans("q0", "q1", 'b');
        ndfa.addTrans("q1", "q1", 'b');
        ndfa.addTrans("q1", "q0", 'l');


        System.out.println("Language");
        System.out.println(cadenaAlfabeto);

        String str = "abab";
        StrProcessing process = new StrProcessing<>(ndfa);


        System.out.println("This are the states with their transitions");
        for(int i = 0; i < ndfa.getStates().size();i++){
            System.out.print(ndfa.getStates().get(i).getElement());
            System.out.println(ndfa.getStates().get(i).getType());
           
            for(int j = 0; j < ndfa.getStates().get(i).getTrans().size();j++){
                if(ndfa.getStates().get(i).getTrans().get(j).getQ1().getElement() ==  ndfa.getStates().get(i).getElement()){
                    System.out.println("Going to " + ndfa.getStates().get(i).getTrans().get(j).getQ2().getElement() + " processing " + ndfa.getStates().get(i).getTrans().get(j).getSymbol()+ " " );
                    
                }
                else{
                    System.out.println("Coming from " + ndfa.getStates().get(i).getTrans().get(j).getQ1().getElement() + " processing " + ndfa.getStates().get(i).getTrans().get(j).getSymbol()+ " " );
                }
                
            }
            
        }
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");

        if(process.stringProcessing(str, str.length(), cadenaAlfabeto)){
            System.out.println("The string is accepted by the language as the intersection with the final state is not empty");
        }
        else{
            System.out.println("The string is not accepted by the language as the intersection with the final state is empty");;
        }
        System.out.println(process.reachableStates);
        

}
}   