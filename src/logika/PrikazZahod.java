/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;





/*******************************************************************************
 * Třída PrikazZahod implementuje pro hru příkaz zahod
 *
 * @author    Lukáš Nekola
 * @version   ZS 2016/2017
 */
public class PrikazZahod implements IPrikaz
{
    private static final String NAZEV = "zahod";
    
    private HerniPlan herniPlan;
    //Batoh batoh = new Batoh();
    //== KONSTANTNÍ ATRIBUTY TŘÍDY =============================================
    //== PROMĚNNÉ ATRIBUTY TŘÍDY ===============================================
    //== STATICKÝ INICIALIZAČNÍ BLOK - STATICKÝ KONSTRUKTOR ====================
    //== KONSTANTNÍ ATRIBUTY INSTANCÍ ==========================================
    //== PROMĚNNÉ ATRIBUTY INSTANCÍ ============================================
    //== PŘÍSTUPOVÉ METODY VLASTNOSTÍ TŘÍDY ====================================
    //== OSTATNÍ NESOUKROMÉ METODY TŘÍDY =======================================
    
    //##########################################################################
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================

    /***************************************************************************
     * Konstruktor třídy
     * 
     * @param hPlan - hezní plán, ve kterém se budou zahazovat věci
     */
    public PrikazZahod(HerniPlan hPlan)
    {
        this.herniPlan = hPlan;
    }

    /**
     * Provádí příkaz "zahod" Zkouší zahodit věci. Pokud je věc v batohu zahodí ji, 
     * jinak vypíše chybovou hlášku
     * 
     * @param parametr - jako parametr obsahuje název věci, které má být zahozena
     * @return zpráva, kterou vypíše hra hráči
     */
    public String proved(String... parametry)
    {
        if(parametry.length < 1)
        {
            return "nevím, co chceš zahodit";
        }
        String nazevVeci = parametry[0];
       
        if(parametry[0].equals("rozpůlený_kokosový_ořech") && herniPlan.getBatoh().isInBag("voda"))        {
            Vec vec = herniPlan.getBatoh().odeber(nazevVeci);
            herniPlan.getBatoh().odeber("voda");
            herniPlan.getAktualniProstor().vlozVec(vec);
            herniPlan.notifyAllObservers();
            return "věc " + nazevVeci + " byla zahozena";
        }
        
        if(nazevVeci.equals("voda"))
        {
            herniPlan.getBatoh().odeber(nazevVeci);
            //herniPlan.getAktualniProstor().vlozVec(vec);
            return "věc " + nazevVeci + " byla zahozena. Vsákla se, takže už jí tu nenajdeš.";
        }
        
        if(herniPlan.getBatoh().isInBag(parametry[0]))
        {
            //herniPlan.getBatoh().odeber(nazevVeci);
            Vec vec = herniPlan.getBatoh().odeber(nazevVeci);
            herniPlan.getAktualniProstor().vlozVec(vec);
            herniPlan.notifyAllObservers();
            return "věc " + nazevVeci + " byla zahozena";
            
           
        }
        
        return "tato věc není v batohu";
        
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev()
    {
        return NAZEV;
    }

    //== ABSTRAKTNÍ METODY =====================================================
    //== PŘÍSTUPOVÉ METODY VLASTNOSTÍ INSTANCÍ =================================
    //== OSTATNÍ NESOUKROMÉ METODY INSTANCÍ ====================================
    //== SOUKROMÉ A POMOCNÉ METODY TŘÍDY =======================================
    //== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ====================================
    //== INTERNÍ DATOVÉ TYPY ===================================================
    //== TESTOVACÍ METODY A TŘÍDY ==============================================
    //
    //     /********************************************************************
    //      * Testovací metoda.
    //      */
    //     public static void test()
    //     {
    //         PrikazZahod instance = new PrikazZahod();
    //     }
    //     /** @param args Parametry příkazového řádku - nepoužívané. */
    //     public static void main(String[] args)  {  test();  }
}
