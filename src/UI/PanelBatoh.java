/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logika.Batoh;
import logika.HerniPlan;
import logika.Vec;
import main.Main;
import utils.Observer;

/**
 *
 * @author Lukáš
 */
public class PanelBatoh implements Observer{

    private Batoh batoh;
    private HerniPlan plan;
    private List<ImageView> imageView;
    private FlowPane flowPane;
    private Label label;
    private HBox hBox;

    public PanelBatoh(HerniPlan plan) {
        this.plan = plan;
        imageView = new ArrayList<>();
        flowPane = new FlowPane();
        plan.registerObserver(this);
        hBox = new HBox();
        label = new Label("Obsah batohu: ");
        label.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        label.setAlignment(Pos.CENTER);
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
        batoh = plan.getBatoh();
        List<Vec> listVeci = batoh.getListVeci();
        for (Vec vec : listVeci) {
            ImageView img = new ImageView(new Image(Main.class.getResourceAsStream(vec.getZdroj())));
            imageView.add(img);
        }

        flowPane.getChildren().addAll(imageView);
        hBox.getChildren().addAll(label, flowPane);
    }

    public HBox getHBox() {
        return hBox;
    }

    public FlowPane getPanel() {
        return flowPane;
    }
    
    @Override
    public void update() {
        batoh = plan.getBatoh();
        hBox.getChildren().clear();
        flowPane.getChildren().clear();
        imageView.clear();
        List<Vec> listVeci = batoh.getListVeci();
        for (Vec vec : listVeci) {
            ImageView img = new ImageView(new Image(Main.class.getResourceAsStream(vec.getZdroj()), 90, 90, false, false));
            imageView.add(img);
        }

        flowPane.getChildren().addAll(imageView);
        hBox.getChildren().addAll(label, flowPane);
    }
    
}
