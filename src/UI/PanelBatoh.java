/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import logika.HerniPlan;
import logika.Hra;
import logika.IHra;
import logika.Vec;
import utils.Observer;

/**
 *
 * @author Lukáš
 */
public class PanelBatoh implements Observer{

    private HerniPlan plan;
    private IHra hra;
//    private List<ImageView> imageView;
    private FlowPane flowPane;
   
    
    //nové
    private ListView<Object> list;
    ObservableList<Object> data;
    private TextArea centerText;


    public PanelBatoh(HerniPlan plan, TextArea text) {
        this.plan = plan;
        hra = new Hra();
        flowPane = new FlowPane();
        plan.registerObserver(this);
        centerText = text;
        init();
    }

    /**
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     *
     * @param plan
     */
    public void nastaveniHernihoPlanu(HerniPlan plan) {
        plan.registerObserver(this);
        this.update();
    }

    private void init() {
        list = new ListView<>();
        data = FXCollections.observableArrayList();
        list.setItems(data);
        list.setPrefWidth(120);
        
        if (!hra.konecHry()) {
            
            Boolean b = hra.konecHry();
            
            

        list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                
                centerText.setText(String.valueOf(b));
                int selected = list.getSelectionModel().getSelectedIndex();
                
                Map<String, Vec> veci;
                veci = plan.getBatoh().getVeci(); 
                
                String nazev = "";
                int pomocna = 0;
                for (String x : veci.keySet()) {
                   if(pomocna == selected) {
                       nazev = x;
                   }
                   pomocna++;
                }
                String prikaz = "zahod " + nazev;
                
                String text = plan.getHra().zpracujPrikaz(prikaz);
                
                centerText.appendText("\n\n" + prikaz + "\n");
                centerText.appendText("\n" + text + "\n");

                plan.notifyAllObservers();
            }
        });}
        
        
        
    update();
    }
        

    @Override 
    public void update() {
          
        Map<String, Vec> veci;
        veci = plan.getBatoh().getVeci();
        data.clear();
        for (String x : veci.keySet()) {
            Vec pomocna = veci.get(x);
            ImageView obrazek = new ImageView(new Image(main.Main.class.getResourceAsStream(pomocna.getZdroj()), 100, 100, false, false));
            data.add(obrazek);
        }
    }

    public FlowPane getPanel() {
        return flowPane;
    }
    
    public ListView<Object> getList() {
        return list;
    }
    
    public void novaHra(HerniPlan plan) {
        this.plan = plan;
        plan.registerObserver(this);
        this.update();
    }
}
