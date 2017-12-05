/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import logika.HerniPlan;
import utils.Observer;

/**
 * Panel Východů lsoužíá pro zobrazení východů z konkrétní místonosi. Východy lze
 * vybírat kliknutím
 * @author Lukáš
 */
public class PanelVychodu implements Observer{
    private HerniPlan plan;
    ListView<String> list;
    ObservableList<String> data;
    
    /**
     * 
     * @param plan herní plán
     */
    public PanelVychodu(HerniPlan plan) {
        this.plan = plan;
        plan.registerObserver(this);
        init();
    }

    /**
     * inicializace
     */
    private void init() {
        list = new ListView<>();
        data = FXCollections.observableArrayList();
        list.setItems(data);
        list.setPrefWidth(100);

        String vychody = plan.getAktualniProstor().seznamVychodu();

        String[] oddeleneVychody = vychody.split(" ");
        for (int i = 1; i < oddeleneVychody.length; i++) {
            data.add(oddeleneVychody[i]);
        }
    }

    /**
     * 
     * @return list vrací list věcí v batohu
     */
    public ListView<String> getList() {
        return list;
    }
/**
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     * 
     * @param plan instance herního plánu
     */
    public void nastaveniHernihoPlanu (HerniPlan plan){
        this.plan = plan;
        plan.registerObserver(this);
        this.update();
    }

    /**
     * Aktualizace východů z postoru
     */
    @Override
    public void update() {
        String vychody = plan.getAktualniProstor().seznamVychodu();
        data.clear();
        String[] oddeleneVychody = vychody.split(" ");
        for (int i = 1; i < oddeleneVychody.length; i++) {
            data.add(oddeleneVychody[i]);
        }
    }

    /**
     * 
     * @param plan herní plán, ve kterém je batoh
     * vatvoření nové instance
     */
    public void novaHra(HerniPlan plan) {
        plan.deleteObserver(this);
        this.plan = plan;
        plan.registerObserver(this);
        this.update();
    }
}
