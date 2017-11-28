/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import UI.Mapa;
import UI.MenuPole;
import UI.PanelBatoh;
import UI.PanelProstor;
import UI.PanelVychodu;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import uiText.TextoveRozhrani;

/**
 *
 * @author nekl00
 */ 
public class Main extends Application {

    private Mapa mapa;
    private MenuPole menu;
    private IHra hra;
    
    private TextArea centerText;
    private Stage primaryStage;
    private PanelVychodu panelVychodu;
    private PanelBatoh panelBatoh;
    private PanelProstor panelProstor;
    private ListView<String> listVychodu;


    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;

        hra = new Hra();
        mapa = new Mapa(hra);
        menu = new MenuPole(this);

        BorderPane borderPane = new BorderPane();
        BorderPane leftPane = new BorderPane();
        
        centerText = new TextArea();
        centerText.setText(hra.vratUvitani());
        centerText.setEditable(false);
        borderPane.setCenter(centerText);
        
        Label zadejPrikazLabel = new Label("Zadej prikaz");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        
        TextField zadejPrikazTextField = new TextField("Sem zadej prikaz");
        
        panelVychodu = new PanelVychodu(hra.getHerniPlan());
        listVychodu = panelVychodu.getList();
        
        listVychodu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {

                String selected = listVychodu.getSelectionModel()
                        .getSelectedItem();
                String text = hra.zpracujPrikaz("jdi " + selected);
                centerText.appendText("\n\n" + "jdi " + selected + "\n");
                centerText.appendText("\n" + text + "\n");

                zadejPrikazTextField.setText("");
                if (hra.konecHry()) {
                    zadejPrikazTextField.setEditable(false);
                }
            }
        });

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
        
        FlowPane paneBatoh = new FlowPane();
        FlowPane paneProstor = new FlowPane();

        
        paneBatoh.setPrefWidth(100);
        paneProstor.setPrefWidth(100);
        
        
        dolniPanel.setAlignment(Pos.CENTER);
        dolniPanel.getChildren().addAll(zadejPrikazLabel, zadejPrikazTextField);

        //panel prikaz
        borderPane.setBottom(dolniPanel);
        //obrazek s mapou
        leftPane.setCenter(mapa);
        //menu adventury
        borderPane.setTop(menu);
        //panel
        borderPane.setRight(listVychodu);
        
        panelBatoh = new PanelBatoh(hra.getHerniPlan(), centerText);
        panelProstor = new PanelProstor(hra.getHerniPlan(),centerText);
        
        Label lBatoh = new Label("Batoh");
        lBatoh.setFont(Font.font("Arial", FontWeight.BOLD, 16)); 
        Label lVeci = new Label("Věci v místnosti");
        lVeci.setFont(Font.font("Arial", FontWeight.BOLD, 16));   
        
        paneBatoh.getChildren().addAll(lBatoh,panelBatoh.getList());
        paneProstor.getChildren().addAll(lVeci,panelProstor.getList());
        
        leftPane.setLeft(paneBatoh);
        leftPane.setRight(paneProstor);
        
        borderPane.setLeft(leftPane);

        Scene scene = new Scene(borderPane, 1150, 700);

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
        } else {
            if (args[0].equals("-text")) {
                IHra hra = new Hra();
                TextoveRozhrani textoveRozhrani = new TextoveRozhrani(hra);
                textoveRozhrani.hraj();

            } else {
                System.out.println("Neplatny parametr");
                System.exit(1);
            }
        }
    }

    public void novaHra() {
        hra = new Hra();
        centerText.setText(hra.vratUvitani());

        //to same pro vsechny observery
        mapa.novaHra(hra);
        panelBatoh.novaHra(hra.getHerniPlan());
        panelVychodu.novaHra(hra.getHerniPlan());
        panelProstor.novaHra(hra.getHerniPlan());
    }

    /**
     * @return the primaryStage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
