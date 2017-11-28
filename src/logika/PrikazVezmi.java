/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import java.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/*******************************************************************************
 * Třída PrikazVezmi implementuje pro hru příkaz vezmi
 *
 * @author    Lukáš Nekola
 * @version   ZS 2016/2017
 */
public class PrikazVezmi implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "vezmi";
    
    private HerniPlan herniPlan;

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor třídy
     *  
     *  @param hPlan herní plán, ve kterém se budou ve hře "zvedat" věci 
     */
    public PrikazVezmi(HerniPlan hPlan)
    {
        this.herniPlan = hPlan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Provádí příkaz "vezmi". Zkouší vzít danou věc. Pokud věc je v auktuálním protoru,
     * potom se přidá do batohu, pkud je překročena kapacita batohu vypíše se chybová
     * hláška. Pokud věc není v aktuálním protoru vypíše se chobová hláška.
     * 
     * @param parametry - jako parametr obsahuje název věci, kterou má "vzít"
     * @return zpráva, kterou vypíše hráčí
     */
    @Override
    public String proved(String... parametry) {
        if (parametry.length < 1) {
            return "nevím, co mám sebrat";
        }
        
        String nazevVeci = parametry[0];
        
        Vec vec = herniPlan.getAktualniProstor().odeberVec(nazevVeci);
        if (vec == null) {
            return "věc '" + nazevVeci + "' tu není";
        }
        
        if (!vec.isPrenositelna()) {
            herniPlan.getAktualniProstor().vlozVec(vec);
            return "věc '" + nazevVeci + "' fakt neuneseš";
        }
        
        if(herniPlan.getBatoh().bagIsFull())
        {
            return "Můžeš nést maximálně 5 věcí";
        }
        
        if(nazevVeci.equals("voda") && (!herniPlan.getBatoh().isInBag("rozpůlený_kokosový_ořech")))
        {
            herniPlan.getAktualniProstor().vlozVec(vec);
            return "nemůžeš vzít vodu, do čeho by jsi ji dal?";
        }
        
        //zaručuje, aby zůstala voda v potoce
        if(nazevVeci.equals("voda"))
        {
            herniPlan.getProstorPotok().vlozVec(vec);
        }
        
       
        herniPlan.getBatoh().pridej(vec);
        herniPlan.notifyAllObservers();
        Media pick = new Media(this.getClass().getResource("/zdroje/pick.mp3").toString());
        MediaPlayer player = new MediaPlayer(pick);
        player.play();
        return "věc " + nazevVeci + " byla přidána do batohu";
        
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
