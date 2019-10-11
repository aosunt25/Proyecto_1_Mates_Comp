import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javax.sound.midi.Receiver;



import java.util.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
import javafx.beans.binding.Bindings;
import javafx.beans.value.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.scene.Group;
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javafx.*;
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
 * 
 * The following code creates a Window in which the user writes the name of a .txt file with the specifications 
 * to create an NDFA-lambda, once the txt is processed, the user can switch between txt files within the directory
 * and validate if a string belongs to the language.
 */

public class Main extends Application {
    Scanner scanner = new Scanner(System.in);
    VBox vbox = new VBox();
    VBox vbox2 = new VBox();
    VBox vbox3 = new VBox();
    HBox hbox = new HBox();
    HBox hbox2 = new HBox();
	Group group;
    Ndfa<String> ndfa = new Ndfa();
    String strn;
    Text elemento;
    TextField fileText = new TextField();
    Label label = new Label();
    Label text = new Label(); 
    Label ini = new Label();
    Label end = new Label();
    String texte;
    File archivo;
    String textFinalState;
    TextField fileText2 = new TextField();
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
        pane.getChildren().clear();
        pane.setPickOnBounds(false);
	    Scene scene= new Scene(pane,500,500);
        vbox.getChildren().addAll(fileText, button1);
        hbox.getChildren().addAll(vbox,vbox2);
        pane.getChildren().addAll(hbox);
        hbox.relocate(100, 50);
        hbox2.relocate(100, 200);
        
        
        button1.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
            pane.getChildren().clear();

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
        try{
        File archivo = new File (nombreArchivo);
            finalState.clear();
            alphabetArr.clear();
            alphabetArr.add("lmd");
            try{
            scanner = new Scanner(archivo);
            }
            catch(FileNotFoundException ex){

            }
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
        /**
         * Catch the exception if the file does not exist
         */
        }catch(IllegalStateException ex){

        }
        

        /**
         * Prints all the specifications of the Language
         */
        try{
        textFinalState=""+finalState.get(0);
        texte=""+alphabetArr.get(0);
        for(int i = 1; i < alphabetArr.size(); i++){
            texte = texte + "-" + alphabetArr.get(i);
        }
        for(int i = 1; i < finalState.size(); i++){
            textFinalState = textFinalState + "-" + finalState.get(i);
        }
        
        
        TableView table = new TableView();
        TableColumn <String,LanguageTable> initStateCol = new TableColumn<>("Initial State");
        TableColumn <String,LanguageTable> finalStateCol = new TableColumn<>("Final State");
        TableColumn <String,LanguageTable> alphCol = new TableColumn<>("Alphabet");
        table.getColumns().addAll(initStateCol, finalStateCol, alphCol);

        initStateCol.setCellValueFactory(
            new PropertyValueFactory<>("InitState")
        );
        finalStateCol.setCellValueFactory(
            new PropertyValueFactory<>("FinalState")
        );
        alphCol.setCellValueFactory(
            new PropertyValueFactory<>("Alphabet")
        );
        
        table.setFixedCellSize(25);

        table.prefHeightProperty().bind(Bindings.size(table.getItems()).multiply(table.getFixedCellSize()).add(60));
        ObservableList<LanguageTable> languageInfo = FXCollections.observableArrayList();

        languageInfo.add(new LanguageTable(initState, textFinalState, texte));

        table.getItems().setAll(languageInfo);

        pane.getChildren().clear();
        

        TableView tableNDFA = new TableView();
        vbox3.getChildren().clear();
        vbox3.getChildren().addAll(table);
        hbox2.setAlignment(Pos.BOTTOM_LEFT);
        
        pane.getChildren().addAll(hbox,hbox2);
        hbox.relocate(100, 50);
        hbox2.relocate(100, 200);
       
        TableColumn char1Col = new TableColumn("Initial State");
        TableColumn char2Col = new TableColumn("Final State");
        TableColumn lmdCol = new TableColumn("Alphabet");
       
     
        /**
         * This loop will continue until the user does not want to 
         * try any more string in the language 
         */
        
            }catch(IndexOutOfBoundsException e){
                System.out.println("The file is not in the directory");
                start(primaryStage);
            }
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

                    label.setText("The String: "+strn+" is accepted by the language");
                    vbox3.getChildren().remove(label);
                    vbox3.getChildren().add(label);
                    
                } else {
                    label.setText("The String: "+strn+" is not accepted by the language");
                    vbox3.getChildren().remove(label);
                    vbox3.getChildren().add(label);
                }
    
                /**
                 * Asks if the user wants to analys another string 
                 */
                
                //continuar = sn.nextLine().toLowerCase();
                System.out.println(fileText.getText());
            
                
    
				}
        });
        pane.getChildren().clear();
        hbox2.getChildren().clear();
        vbox3.getChildren().clear();
        vbox3.getChildren().add(label);
        hbox2.getChildren().addAll(vbox3);
        pane.getChildren().addAll(hbox,hbox2);
        primaryStage.setScene(scene);
        primaryStage.show();
    
       
    }

    

}  