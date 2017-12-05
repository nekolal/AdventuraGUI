/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/*******************************************************************************
 * Třída PrikazBatoh implementuje pro hru příkaz batoh
 *
 * @author    Lukáš Nekola
 * @version   ZS 2016/2017
 */
public class PrikazBatoh implements IPrikaz
{
    private static final String NAZEV = "batoh";
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
     *Kostruktor třídy
     *
     *@param herniPlan herní plán, ve kterém se budou ve hře "sbírat" věci
     */
    public PrikazBatoh(HerniPlan herniPlan)
    {
        this.herniPlan = herniPlan;
    }

    /**
     * Zavolá batoh a vypíše jeho obsaht
     * 
     * @return obsah batohu
     */
    @Override
    public String proved(String... parametry)
    {
		return herniPlan.getBatoh().toString();

    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu
     */
    @Override
    public String getNazev() {
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
    //         PrikazBatoh instance = new PrikazBatoh();
    //     }
    //     /** @param args Parametry příkazového řádku - nepoužívané. */
    //     public static void main(String[] args)  {  test();  }
}
