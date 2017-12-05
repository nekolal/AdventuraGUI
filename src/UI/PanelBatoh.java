/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logika.HerniPlan;
import logika.Vec;
import utils.Observer;

/**
 *
 * @author Lukáš
 * 
 * Pnale batoh slouží pro zobrazení obsahu batohu na panelu pomocí obrázků a 
 * možnost vybírat věci kliknutím
 */
public class PanelBatoh implements Observer{

    private HerniPlan plan;
    private ListView<Object> list;
    ObservableList<Object> data;

    /**
     * 
     * @param plan herní plán, ve kterém je batoh
     */
    public PanelBatoh(HerniPlan plan) {
        this.plan = plan;
        plan.registerObserver(this);
        init();
    }

    /**
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     *
     * @param plan instance herního plánu
     */
    public void nastaveniHernihoPlanu(HerniPlan plan) {
        plan.registerObserver(this);
        this.update();
    }

    /**
     * inicializace komponent panelu
     */
    private void init() {
        list = new ListView<>();
        data = FXCollections.observableArrayList();
        list.setItems(data);
        list.setPrefWidth(120);
        update();
    }
        
    /**
     * Aktualizace obrázků batohu
     */
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

    /**
     * @return vrací list věcí v batohu
     */
    public ListView<Object> getList() {
        return list;
    }
    
    /**
     * 
     * @param plan herní plán, ve kterém je batoh
     * vatvoření nové instance
     */
    public void novaHra(HerniPlan plan) {
        this.plan = plan;
        plan.registerObserver(this);
        this.update();
    }
}
