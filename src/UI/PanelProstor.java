///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package UI;
//
//
//import javafx.scene.control.TextArea;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.paint.Paint;
//import javafx.scene.shape.Circle;
//import logika.IHra;
//import main.Main;
//import utils.Observer;
//import utils.ObserverNovaHra;
//
//
///**
// *
// * @author Lukáš
// */
//public class PanelProstor extends AnchorPane implements Observer{
//    private IHra hra;
//    private TextArea centerText;
//    
//    public PanelProstor(IHra hra){
//        this.hra = hra;
//        hra.getHerniPlan().registerObserver(this);
//        BorderPane borderPane = new BorderPane();
//        centerText = new TextArea();
//        centerText.setEditable(false);
//        borderPane.setCenter(centerText);
//        init();
//    }
//    
//    private void init(){
//        
//        update();
//    }
//    
//    @Override
//    public void update() {
//       // this.hra.getHerniPlan().getAktualniProstor();
//    }
//    
////    @Override
//    public void novaHra(IHra hra) {
//        hra.getHerniPlan().deleteObserver(this);
//        this.hra = hra;
//        hra.getHerniPlan().registerObserver(this);
//        update();
//        
//    }
//
//    @Override
//    public void aktualniProstor() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//}
