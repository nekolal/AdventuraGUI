package logika;

import java.util.ArrayList;
import java.util.List;
import utils.Observer;
import utils.Subject;


/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * 
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny prostory, propojuje je vzájemně pomocí východů
 * a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha, Lukáš Nekola
 * @version    ZS 2016/2017
 */
public class HerniPlan implements Subject{

    private static final String CILOVY_PROSTOR = "moře";
    
    
    public boolean zemrelNaDehydrataci = false;
    private HerniPlan herniPlan;
    private Hra hra;

    private Prostor aktualniProstor;
    Batoh batoh = new Batoh(herniPlan);
    Prostor potok; //potřeba při sbírání věci "voda", aby voda nebyla odebrána

    private List<Observer> listObserveru = new ArrayList<Observer>();
    
    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     */
    public HerniPlan(Hra hra) {
        zalozProstoryHry();
        
        
        
    
        this.hra = hra;
        //listObserveru = new ArrayList<>();
    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví pláž.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
    
        Prostor pláž = new Prostor("pláž", "místo, na kterém jsi se probudil, normální pláž", 220, 355);
        Prostor les = new Prostor("les", "normální les, plný listnatých stromů", 200, 310);
        Prostor prales = new Prostor("prales", "Je tu materiál, který se Ti může hodit\n" +
                                                " a potrava ve formě ovoce. Číhá tu však nebezpečí\n" +
                                                " a tak není od věci být na pozoru", 220, 250);
        Prostor jeskyně = new Prostor("jeskyně", "holá jeskyně, je tu zima a tma", 195, 330);
        Prostor moře = new Prostor ("moře", "Spojnice mezi Tebou a Tvým domovem, máš štěstí, že tu nenarazíš na žraloka", 220, 380);
        potok = new Prostor("potok", "Jediný zdroj pitné vody na tomto ostrově.", 220, 220);

        // přiřazují se průchody mezi prostory (sousedící prostory)
       
        moře.setVychod(pláž);
        pláž.setVychod(moře);
        pláž.setVychod(les);
        pláž.setVychod(jeskyně);
        les.setVychod(pláž);
        les.setVychod(jeskyně);
        les.setVychod(prales);
        jeskyně.setVychod(pláž);
        jeskyně.setVychod(les);
        prales.setVychod(les);
        prales.setVychod(potok);
        potok.setVychod(prales);
        
        
        // vytvoříme několik věcí
        Vec kapesníNůž = new Vec("kapesní_nůž", "tohle je jediné co Ti zbylo z lodě, může se Ti hodit", true);
        kapesníNůž.setZdroj("/zdroje/nuz.png");
        Vec klády = new Vec("klády", "klády z vysušených spadlých stromů", true);
        klády.setZdroj("/zdroje/klady.jpg");
        Vec lijány = new Vec("lijány", "lijány, vysí tu ze stromů", true);
        lijány.setZdroj("/zdroje/lijany.png");
        Vec stromy = new Vec("stromy", "obyčejné stromy", false);
        stromy.setZdroj("/zdroje/stromy.png");
        Vec ostrýKámen = new Vec("ostrý_kámen", "velmi ostrý kámen, může se hodit jako zbraň", true);
        ostrýKámen.setZdroj("/zdroje/kamen.png");
        Vec kokosovýOřech = new Vec("kokosový_ořech", "Obyčejný kokosový ořech, s trochou snahy ho upravíš a budeš si v něm moct vzít vodu s sebou", true);
        kokosovýOřech.setZdroj("/zdroje/kokosovyorech.png");
        Vec voda = new Vec("voda", "pitná voda, vem si ji s sebou do kokosu, bude se ti hodit na cestu", true);
        voda.setZdroj("/zdroje/voda.png");
        Vec písek = new Vec("písek", "co jiného by se také dalo očekávat na pláži.", false);
        písek.setZdroj("/zdroje/pisek.jpg");
        Vec křoví = new Vec("křoví", "křoví rostoucí všemoženě, kde je volné místo", false);
        křoví.setZdroj("/zdroje/krovi.png");
        Vec balvany = new Vec("balvany", "obrovské balvany", false);
        balvany.setZdroj("/zdroje/balvany.png");

        //vytvoříme potraviny
        Potrava banány = new Potrava("banány", "chutné zralé banány, ideální svačinka");
        Potrava voda1 = new Potrava("voda", "pitná voda");
        Potrava bobule = new Potrava("bobule", "chtutné šťavňaté bobule, však halucinogonní");
        

        // umístíme věci do prostorů

        batoh.pridej(kapesníNůž);
        
        prales.vlozPotravinu(banány);
        prales.vlozPotravinu(bobule);
        potok.vlozPotravinu(voda1);
        
        pláž.vlozVec(ostrýKámen);
        pláž.vlozVec(písek);
        
        les.vlozVec(klády);
        les.vlozVec(stromy);
        
        potok.vlozVec(voda);
        
        prales.vlozVec(lijány);
        prales.vlozVec(křoví);
        prales.vlozVec(stromy);
        prales.vlozVec(kokosovýOřech);
        
        jeskyně.vlozVec(balvany);

        aktualniProstor = pláž;  // hra začíná na pláži
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     * Metoda vrací odkaz na prostor potok
     * 
     * @return prostor potok
     */
    public Prostor getProstorPotok()
    {
        return potok;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
       notifyAllObservers();
    }

    /**
     * Metoda vratcí true v případě, že hráč vyhraje, jinak false. Pro to, aby hráč mohl vyhrát
     * musí vstoupit do prostoru CILOVY_PROSTOR a zárověň musí mít u sebe vor a vodu
     * 
     * @return true (vyhrál) jinak false
     */
    public boolean hracVyhral() {        
        if (aktualniProstor.getNazev().equals(CILOVY_PROSTOR) && getBatoh().isInBag("vor") && getBatoh().isInBag("voda")) 
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * Metoda vraců true v případě, že hráč prohrál. Prohraje v případě, že při vstupu do lesa nebude mít u sebe věc
     * "ostrý_kámen"
     * 
     * @return true pokud prohrál jinak false
     */
    public boolean hracProhral()
    {
        if (aktualniProstor.getNazev().equals("prales") && !getBatoh().isInBag("ostrý_kámen"))
        {
            return true;
        }
        
        if (aktualniProstor.getNazev().equals(CILOVY_PROSTOR) && getBatoh().isInBag("vor")) 
        {
            zemrelNaDehydrataci = true;
            return true;
        }
        return false;
    }

    /**
     * Metoda vrací instanci třídy Batoh()
     * 
     * @return batoh - vrací instanci třídy Batoh()
     */
    public Batoh getBatoh()
    {
        return batoh;
    }
    
    public Hra getHra() {
        return hra;
    }

    @Override
    public void registerObserver(Observer observer) {
        listObserveru.add(observer);
    }

    @Override
    public void deleteObserver(Observer observer) {
        listObserveru.remove(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (Observer listObserveruItem : listObserveru) {
            listObserveruItem.update();
        }
    }
}
