/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import UI.Mapa;
import UI.MenuPole;
import com.sun.javafx.collections.MapAdapterChange;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import sun.applet.Main;
import uiText.TextoveRozhrani;

/**
 *
 * @author nekl00
 */
public class NewFXMain extends Application {
    private Object conterText;
    
    private Mapa mapa;
    private MenuPole menu;
    
    @Override
    public void start(Stage primaryStage) {
        /*Button btn = new Button();
        btn.setText("Start adventura");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                IHra hra = new Hra();
                TextoveRozhrani textoveRozhrani = new TextoveRozhrani(hra);
                textoveRozhrani.hraj();
            }
        });*/
        
        IHra hra = new Hra();
        
        mapa = new Mapa(hra);
        menu = new MenuPole();
        
        BorderPane borderPane = new BorderPane();
        
        TextArea centerText = new TextArea();
        centerText.setText(hra.vratUvitani());
        centerText.setEditable(false);
        borderPane.setCenter(centerText);
       
        
        Label zadejPrikazLabel = new Label("Zadej prikaz");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        
        TextField zadejPrikazTextField = new TextField("Sem zadej prikaz");
        
        zadejPrikazTextField.requestFocus();
        
        zadejPrikazTextField.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String zadanyPrikaz = zadejPrikazTextField.getText();
                String odpoved = hra.zpracujPrikaz(zadanyPrikaz);
                
                centerText.appendText("\n" + zadanyPrikaz + "\n");
                centerText.appendText("\n" + odpoved + "\n");
                
                zadejPrikazTextField.setText("");
                
                if (hra.konecHry()) {
                    zadejPrikazTextField.setEditable(false);
                }
            }
        });
        
                       
        FlowPane dolniPanel = new FlowPane();
        dolniPanel.setAlignment(Pos.CENTER);
        dolniPanel.getChildren().addAll(zadejPrikazLabel, zadejPrikazTextField);
        
        //obrazek s mapou
        
        
        //panel prikaz
        borderPane.setBottom(dolniPanel);
        //obrazek s mapou
        borderPane.setLeft(mapa);
        //menu adventury
        borderPane.setTop(menu);
        
        
        
        /*TextoveRozhrani textoveRozhrani = new TextoveRozhrani(hra);
        textoveRozhrani.hraj();*/
        
        
       /* StackPane root = new StackPane();
        root.getChildren().add();*/
        
        Scene scene = new Scene(borderPane, 700, 400);
        
        primaryStage.setTitle("Moje adventura");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        }
        else{
            if (args[0].equals("-text")){
            IHra hra = new Hra();
            TextoveRozhrani textoveRozhrani = new TextoveRozhrani(hra);
            textoveRozhrani.hraj();
                
            }
            else{
                System.out.println("Neplatny parametr");
                System.exit(1);
                    }
        }
    }
    
}
