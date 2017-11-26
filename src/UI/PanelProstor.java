///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package UI;
//
//import javafx.scene.control.Label;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
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
//public class PanelProstor implements Observer{
//    private IHra hra;
//    private JFrame frame;
//    private JPanel panel;
//    private JLabel label;
//
//    
//    public PanelProstor(){
//        this.hra = hra;
//        hra.getHerniPlan().registerObserver(this);
//        init();
//    }
//    
//    private void init(){
//        frame = new JFrame("prostory");
//        frame.setVisible(true);
//        frame.setSize(300, 150);
//        update();
//        
//        panel = new JPanel();
//        
//        label = new JLabel("ahoj");
//        
//        panel.add(label);
//        
//        frame.add(panel);
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
//   // @Override
//    public void aktualniProstor() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//}
