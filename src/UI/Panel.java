/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import logika.HerniPlan;
import logika.IHra;
//import logika.Hra;
import logika.Prostor;
import utils.Observer;

/**
 *
 * @author Lukáš
 */
public class Panel extends AnchorPane implements Observer{
    
    private HerniPlan hPlan;
    private IHra hra;
    private Label panelL;
    
    public Panel(IHra hra){
        this.hra = hra;
        hPlan = new HerniPlan();
        //hPlan.registerObserver(this);
        hra.getHerniPlan().registerObserver(this);
        init();

        

    }
    private void init(){
        panelL = new Label(hPlan.getAktualniProstor().getNazev());
        FlowPane panel = new FlowPane();
        panel.getChildren().addAll(panelL);
        //this.update(hPlan.getAktualniProstor());      
       
        update();
    }
       
        
    
    @Override
    public void update() {
        panelL.setText(hPlan.getAktualniProstor().getNazev());
    }

    @Override
    public void novaHra(IHra hra) {
        hra.getHerniPlan().deleteObserver(this);
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        update();
    }

//    private void update(Prostor aktualniProstor) {
//        //this.update(hPlan.getAktualniProstor());  
//        panelL.setText(aktualniProstor.getNazev());
////        for (Prostor i : plan.getAktualniProstor().getVychody()) {
////            model.addElement(i.getNazev());
////        }
//    }
}

