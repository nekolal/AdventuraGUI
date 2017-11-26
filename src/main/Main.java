/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import UI.Mapa;
import UI.MenuPole;
import UI.PanelBatoh;
import UI.PanelVychodu;
//import UI.PanelProstor;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logika.HerniPlan;
import logika.Hra;
import logika.IHra;
import uiText.TextoveRozhrani;
//import views.Panel;

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
    private ListView<String> listVychodu;
//    private HerniPlan hPlan;


    
    @Override
    public void start(Stage primaryStage) {
        
        this.primaryStage = primaryStage;
//        hPlan = new HerniPlan();
        
        hra = new Hra();
        mapa = new Mapa(hra);
        menu = new MenuPole(this);
  //      panel = new Panel();

        
        panelVychodu = new PanelVychodu(hra.getHerniPlan());
        listVychodu = panelVychodu.getList();
        
        BorderPane borderPane = new BorderPane();
        
        centerText = new TextArea();
        centerText.setText(hra.vratUvitani());
        centerText.setEditable(false);
        borderPane.setCenter(centerText);
       
        
        Label zadejPrikazLabel = new Label("Zadej prikaz");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
//        Label panelL = new Label(hPlan.getAktualniProstor().getNazev());
        
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
        
        //panel
        
//        FlowPane panel = new FlowPane();
//        panel.getChildren().addAll(panelL);
        
        //panel prikaz
        borderPane.setBottom(dolniPanel);
        //obrazek s mapou
        borderPane.setLeft(mapa);
        //menu adventury
        borderPane.setTop(menu);
        //panel
        borderPane.setRight(listVychodu);

        panelBatoh = new PanelBatoh(hra.getHerniPlan());
        
        VBox vBox = new VBox();
        vBox.getChildren().addAll(dolniPanel, panelBatoh.getHBox());
        borderPane.setBottom(vBox);
        
        
        Scene scene = new Scene(borderPane, 800, 700);
        
        primaryStage.setTitle("Moje adventura");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        java.awt.EventQueue.invokeLater(new Runnable(){
//            public void run(){
//                new Panel().setVisible(true);
//            }
//        });
        
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

    public void novaHra() {
        hra = new Hra();
        centerText.setText(hra.vratUvitani());
        
        //to same pro vsechny observery
        mapa.novaHra(hra);
    }
    /**
     * @return the primaryStage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
