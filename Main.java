import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javax.sound.midi.Receiver;
import java.util.*;

/**
 * @author Alfredo Osuna Torres
 * @author Edgar Lopez Valdez
 */

class Main{
    /**
     * Main method
     * @param 
     */
    public static void main(String[] args){
        Ndfa<String> ndfa = new Ndfa<>();
        
        int index = 0;
        String strn;
        Object val;
        boolean appear = false;
        
        Scanner scanner = new Scanner(System.in);
       /**
        * Asks the user to write the name of the file
        * Add .txt to the string 
        */
        System.out.println("Write the file's name");
        String nombreArchivo = scanner.nextLine() + ".txt";
        File archivo = new File (nombreArchivo);
        System.out.println(archivo.exists());
        
        
        String initState = null;
        ArrayList alphabetArr = new ArrayList<String>();
        ArrayList <String> reachable = new ArrayList<String>();
        ArrayList language = new ArrayList<String>();
        ArrayList <String> finalState = new ArrayList<String>();

        alphabetArr.add("lmd");
        try{    
            scanner = new Scanner(archivo);
            int numDeLinea=1;
            while(scanner.hasNextLine()){
                String linea = scanner.nextLine();
                Scanner delimitar = new Scanner(linea);
                /**
                 * Changes case each line of the file 
                 * After line 4 the file shows all the transactions
                 */
                switch (numDeLinea){
                    /**
                     * Adds all the states of the NDFA
                     */
                    case 1:
                        delimitar.useDelimiter("\\s*,|=>\\s*");
                        while(delimitar.hasNext()){
                            String l = delimitar.next();
                            /**
                             * @see Ndfa#addState
                             * @see Ndfa#addTrans
                             */
                            ndfa.addState(l);
                            ndfa.addTrans(l, "lmd", l);
                        }
                    break; 
                    /**
                     * Adds the alphabet of the language 
                     */ 
                    case 2:
                        delimitar.useDelimiter("\\s*,|=>\\s*");
                        while(delimitar.hasNext()){
                            alphabetArr.add(delimitar.next());
                        }
                    break;
                    /**
                     * Adds the initial state of the NDFA
                     */
                    case 3:
                        delimitar.useDelimiter("\\s*,|=>\\s*");
                        while(delimitar.hasNext()){
                            initState= delimitar.next();
                        }
                    break;
                    /**
                     * Adds all the final states of the NDFA
                     */
                    case 4:
                        delimitar.useDelimiter("\\s*,|=>\\s*");
                        while(delimitar.hasNext()){
                            finalState.add(delimitar.next());
                        }
                    break;
                    /**
                     * Adds the transactions to the corresponding key
                     */
                    default:
                        ArrayList <String> arrStr = new ArrayList<String>();
                        int i=0;
                        delimitar.useDelimiter("\\s*,|=>\\s*");
                        /**
                         * Adds all the importante information in arrStr
                         * arrStr[0] is the initial state of the transaction
                         * arrStr[1] is the symbol of the stransaction
                         * arrStr[2] to arrStr[j] are the final states  after the transaction happens 
                         */
                        while(delimitar.hasNext()){
                            arrStr.add(delimitar.next());
                        i++;
                        }
                        /**
                        * This for add all the transaction in the HashMap
                        */
                        for(int j = 2; j<arrStr.size();j++){
                            String s =arrStr.get(j);
                            if((arrStr.get(1).contains("lmd")) && arrStr.get(0).contains(s)){
                            }
                            else{
                                /**
                                * @see Ndfa#addTrans
                                */
                                ndfa.addTrans(arrStr.get(0),arrStr.get(1),arrStr.get(j));
                            }
                                
                        }
                    break;  
                    }
                numDeLinea++;
            }

            scanner.close();
        }
        /**
         * Catch the exception if the file does not exist
         */
        catch (FileNotFoundException e){
            e.printStackTrace();
        }


        /**
         * Prints all the specifications of the Language
         */
        System.out.println("Alphabet");
        System.out.println(alphabetArr);
        System.out.println(" ");
        System.out.println("Initial State");
        System.err.println(initState);
        System.out.println(" ");
        System.out.println("Final State");
        System.out.println(finalState);
        System.out.println(" ");
       
       
        
        language = alphabetArr;
        String continuar = "n";
        /**
         * This loop will continue until the user does not want to 
         * try any more string in the language 
         */
        do{
            Scanner sn = new Scanner(System.in);
            /**
             * Asks the user the string to be analyzed
             */
            System.out.println("Write the string: ");
            strn = sn.nextLine();
            /**
             * @see Ndfa#stringProcessing
             */
            reachable = ndfa.stringProcessing(strn, initState);
            boolean stringAcc = false;
            /**
             * It checks if any states in reachable are final states
             */
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

            /**
             * Asks if the user wants to analys another string 
             */
            System.out.println("Do you want to write another string: ");
            System.out.println("Y/N");
            continuar = sn.nextLine().toLowerCase();

        }while(continuar.contentEquals("y"));
      
}
}
   