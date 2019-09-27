import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import estados.*;

class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escribe el nombre del Archivo");
        String nombreArchivo = scanner.nextLine() + ".txt";
        File archivo = new File (nombreArchivo);
        System.out.println(archivo.exists());
        ArrayList cadenaStados = new ArrayList<String>();
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