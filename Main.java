import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javax.sound.midi.Receiver;
import java.util.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Application;
import java.awt.geom.Line2D;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.beans.value.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.Group; 
import javafx.scene.shape.Circle; 
import javafx.scene.shape.Line; 
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javafx.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List; 
import java.util.Random;
import java.io.*;

/**
 * @author Alfredo Osuna Torres
 * @author Edgar Lopez Valdez
 */

public class Main extends Application {
    Scanner scanner = new Scanner(System.in);
    Label label;
    Circle circle;
    VBox vbox = new VBox();
    VBox vbox2 = new VBox();
    HBox hbox = new HBox();
	Group group;
    Line line;
    Ndfa<String> ndfa = new Ndfa();
    String strn;
    Text elemento;
    TextField fileText = new TextField();
    TextField fileText2 = new TextField();
    int Circlex = 100;
    int Circley = 100;
    Pane pane = new Pane();
    String initState = null;
    ArrayList alphabetArr = new ArrayList<String>();
    ArrayList <String> reachable = new ArrayList<String>();
    ArrayList language = new ArrayList<String>();
    ArrayList <String> finalState = new ArrayList<String>();
    String continuar;
    Button button1 = new Button("Submit");
    Button button3 = new Button("Clear");

    Button button2 = new Button("Validate");
    /**
     * Main method
     * @param 
     */
    public static void main(String[] args){
        launch(args);
}

   
    @Override
    public void start(Stage primaryStage) {
        
        primaryStage.setTitle("NDFA");

        
        fileText.setPromptText("Write the file's name");
        fileText.setPrefColumnCount(10);
        
        
        fileText2.setPromptText("Write string");
        fileText2.setPrefColumnCount(10);
        
        vbox2.getChildren().addAll(fileText2,button2);
        Pane pane = new Pane();
	    Scene scene= new Scene(pane,1000,1000);
        vbox.getChildren().addAll(fileText, button1);
        hbox.getChildren().addAll(vbox,vbox2);
        pane.getChildren().addAll(hbox);
        //pane.add(fileText,5,5,5,5);
        
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Ndfa<String> ndfa = new Ndfa<>();

        int index = 0;
        String strn;
        Object val;
        boolean appear = false;
        
        //Scanner scanner = new Scanner(System.in);
       /**
        * Asks the user to write the name of the file
        * Add .txt to the string 
        */
        
        String nombreArchivo = fileText.getText() + ".txt";
        File archivo = new File (nombreArchivo);
        System.out.println(archivo.exists());
        
        
        

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
            System.out.println(ndfa.transitionTable);
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
       
       
     
        /**
         * This loop will continue until the user does not want to 
         * try any more string in the language 
         */
        
            }   
        });

        button2.addEventHandler(MouseEvent.MOUSE_CLICKED ,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
                System.out.println(" ");
                language = alphabetArr;
                String continuar = "n";
                /**
                 * Asks the user the string to be analyzed
                 */
                Scanner sn = new Scanner(System.in);
                System.out.println("Write the string: ");
                strn = fileText2.getText();
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
                
                continuar = sn.nextLine().toLowerCase();
                System.out.println(fileText.getText());

                circle = new Circle();
                elemento = new Text();
                circle.setFill(Color.rgb(255,0,0,.99));
                circle.setCenterX(Circlex);
                circle.setCenterY(Circley);
                Circlex = Circlex + 80;                
                Circley = Circley + 80;
                elemento.setText("q1");
                //elemento.setFill(Color.rgb(255,255,255,.99));
                elemento.setFill(Color.rgb(68,68,68)); 
                circle.toFront();
                elemento.toFront();
                elemento.setX(circle.getCenterX()-2);
                elemento.setY(circle.getCenterY()+5);
                group = new Group(circle, elemento);
                hbox.getChildren().clear();
                hbox.getChildren().addAll(vbox,vbox2, button3);
                pane.getChildren().addAll(group, hbox);
    
				}
		});
        primaryStage.setScene(scene);
        primaryStage.show();
    
       
    }

}  
