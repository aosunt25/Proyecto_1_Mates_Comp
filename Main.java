import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import estados.*;

class Main{
    public static void main(String[] args){
        
        File archivo = new File ("test1.txt");
        System.out.println(archivo.exists());
        ArrayList cadenaStados = new ArrayList<String>();
        ArrayList cadenaAlfabeto = new ArrayList<String>();
        cadenaAlfabeto.add("lmd");
        Scanner scanner;
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
                        cadenaStados.add(delimitar.next());;
                        break;  
                        case 2:
                        cadenaAlfabeto.add(delimitar.next());
                        break;
                        default:
                        delimitar.next();
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
        System.out.println(cadenaStados);
        System.out.println(cadenaAlfabeto);
        


    }
}