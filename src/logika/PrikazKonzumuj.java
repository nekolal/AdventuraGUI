/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;





/*******************************************************************************
 * Třída PrikazKonzumuj implementuje pro hru příkaz konzumuj
 *
 * @author    Lukáš Nekola
 * @version   ZS 2016/2017
 */
public class PrikazKonzumuj implements IPrikaz
{
    private static final String NAZEV = "konzumuj";
    
    private HerniPlan herniPlan;
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
     * Kontruktor třídy
     * 
     * @param hPlan herní plán, ve kterém ve hře půjde "konzumovat" potraviny
     */
    public PrikazKonzumuj(HerniPlan hPlan)
    {
        this.herniPlan = hPlan;
    }

    /**
     * Provádí příkaz "konzumuj". Zkouší konzumovat zadanou potravu. Pokud
     * potravina existuje a je v aktuálním prototoru, tka je skonzumována,
     * pokud ne, vypíše se chybové hlášení.
     * 
     * @param parametry - jako paramet obsahuje název potraviny
     * @return zpráva, kterou vypíše hráči
     */
    @Override
    public String proved(String... parametry)
    {
        if(parametry.length < 1)
        {
            return "nevím co chceš konzumovat";
        }
        
        String nazevPotravina = parametry[0];
        
        
        Potrava potravina = herniPlan.getAktualniProstor().odeberPotravinu(nazevPotravina);
 
        if (potravina == null)
        {
            return "potravina '" + nazevPotravina + "' tu není";
        }
        
        return "Skonzumoval jsi " + nazevPotravina;
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
    //         PrikazKonzumuj instance = new PrikazKonzumuj();
    //     }
    //     /** @param args Parametry příkazového řádku - nepoužívané. */
    //     public static void main(String[] args)  {  test();  }
}
