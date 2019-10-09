import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import javax.sound.midi.Receiver;

import java.util.*;

class Main{
    public static void main(String[] args){
        Ndfa<String> ndfa = new Ndfa<>();
        int index = 0;
        String strn;
        Object val;
        boolean appear = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the file's name");
        String nombreArchivo = scanner.nextLine() + ".txt";
        File archivo = new File (nombreArchivo);
        System.out.println(archivo.exists());
        
        String initState = null;
        ArrayList cadenaAlfabeto = new ArrayList<String>();
        ArrayList <String> reachable = new ArrayList<String>();
        ArrayList language = new ArrayList<String>();
        ArrayList <String> finalState = new ArrayList<String>();

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
                            ndfa.addTrans(l, "lmd", l);
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
                        ArrayList <String> arrStr = new ArrayList<String>();
                        int i=0;
                        delimitar.useDelimiter("\\s*,|=>\\s*");
                        while(delimitar.hasNext()){
                            arrStr.add(delimitar.next());
                        i++;
                        }
                        for(int j = 2; j<arrStr.size();j++){
                            //System.err.println("Holis: "+ arrStr.get(0)+" "+arrStr.get(1)+" "+arrStr.get(j));
                            if((arrStr.get(1)=="lmd") && (arrStr.get(0)==arrStr.get(j))){
                                System.out.println("State already in table");
                            }
                            else{
                                ndfa.addTrans(arrStr.get(0),arrStr.get(1),arrStr.get(j));
                            }
                                
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
        System.out.println(cadenaAlfabeto);
       
        
        language = cadenaAlfabeto;
        
        strn = "aba";
        System.out.println(" ");
        System.out.println(ndfa.transitionTable);
        System.out.println(" ");

    
        reachable = ndfa.stringProcessing(strn, initState);
        boolean stringAcc = false;
        for( int i = 0 ; i<reachable.size() && !stringAcc ;i++){
            if(finalState.contains(reachable.get(i))){
                stringAcc=true;
                
            }
            
        }
        if (stringAcc) {
            System.out.println("The String: "+strn+" is accepted by the language");
            
        } else {
            System.out.println("The String: "+strn+" is not accepted by the language");
        }

       
        
}
}
   