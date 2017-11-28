/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logika.Batoh;
import logika.HerniPlan;
import logika.IHra;
import logika.Vec;
import main.Main;
import utils.Observer;
import logika.Prostor;

/**
 *
 * @author Lukáš
 */
public class PanelProstor implements Observer{
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//    private Batoh batoh;
//    private List<ImageView> imageView;
//    private FlowPane flowPane;
//    private Label label;
//    private HBox hBox;
//    private Vec veci;
//    private Main main;
//    private IHra hra;
    
    //nové
    private HerniPlan plan;
    ListView<Object> list;
    ObservableList<Object> data;
    private TextArea centerText;

    public PanelProstor(HerniPlan plan, TextArea text) {
        this.plan = plan;
//        imageView = new ArrayList<>();
//        flowPane = new FlowPane();
        plan.registerObserver(this);
        centerText = text;
//        hBox = new HBox();
//        label = new Label("Obsah protoru: ");
//        label.setFont(Font.font("Arial", FontWeight.BOLD, 18));
//        label.setAlignment(Pos.CENTER);
        init();
    }

    /**
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     *
     * @param plan
     */
    public void nastaveniHernihoPlanu(HerniPlan plan) {
        this.plan = plan;
        plan.registerObserver(this);
        this.update();
    }

    private void init() {
//        batoh = plan.getBatoh();
        
//        List<Vec> listVeci = batoh.getListVeci();
//        for (Vec vec : listVeci) {
//            ImageView img = new ImageView(new Image(Main.class.getResourceAsStream(vec.getZdroj())));
//            imageView.add(img);
//        }        

//        flowPane.getChildren().addAll(imageView);
//        hBox.getChildren().addAll(label, flowPane);

        list = new ListView<>();
        data = FXCollections.observableArrayList();
        list.setItems(data);
        list.setPrefWidth(200);
        
        list.setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent click)
            {
                
                    int selected = list.getSelectionModel().getSelectedIndex();
                    
                    Map<String, Vec> veci;
                    veci = plan.getAktualniProstor().getVeci();
                    
                    String nazev = "";
                    int pomocna = 0;
                    for (String x : veci.keySet()) 
                    {
                       if(pomocna == selected)
                       {
                           nazev = x;
                       }
                       pomocna++;
                    }
                    
                    String prikaz = "vezmi "+nazev;
                    String text = plan.getHra().zpracujPrikaz(prikaz);

                
                    centerText.appendText("\n" + prikaz + "\n");
                    centerText.appendText("\n" + text + "\n");
               
                    plan.notifyAllObservers();
                
            }
        });
        
        
        
        
        update();
    }

//    public HBox getHBox() {
//        return hBox;
//    }
//
//    public FlowPane getPanel() {
//        return flowPane;
//    }
    
    @Override
    public void update() {
//        batoh = plan.getBatoh();
//        hBox.getChildren().clear();
//        flowPane.getChildren().clear();
//        imageView.clear();
//        List<Vec> listVeci = batoh.getListVeci();
//        for (Vec vec : listVeci) {
//            ImageView img = new ImageView(new Image(Main.class.getResourceAsStream(vec.getZdroj()), 90, 90, false, false));
//            imageView.add(img);
//        }

//        flowPane.getChildren().addAll(imageView);
//        hBox.getChildren().addAll(label, flowPane);
        Map<String, Vec> seznam;
        seznam = plan.getAktualniProstor().getVeci();
        data.clear();
        for (String x : seznam.keySet()) 
        {
        Vec pomocna = seznam.get(x);
        ImageView obrazek = new ImageView(new Image(main.Main.class.getResourceAsStream(pomocna.getZdroj()), 100, 100, false, false));
        data.add(obrazek);
        }
    }
    
    /*
    * Metoda vrací list.
    */
    public ListView<Object> getList() {
        return list;
    }

    public void novaHra(IHra hra) {
        hra.getHerniPlan().deleteObserver(this);
//        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        update();
    }
    
}


