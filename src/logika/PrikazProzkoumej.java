/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Třída PrikazProzkoumej implementuje pro hru příkaz prozkoumej
 * 
 * @author    Lukáš Nekola
 * @version   ZS 2016/2017
 */
public class PrikazProzkoumej implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "prozkoumej";
    
    private HerniPlan hPlan;

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor třídy
     * 
     *  @param hPlan herní plán, ve kterém se budou "prozkoumávat" předměty
     */
    public PrikazProzkoumej(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    
    /**
     *  Provádí příkaz prozkoumej. Zkouší prozkoumat danou věc. Pokud se dané věc
     *  nachází v aktuálním prostoru, vrátí její popis, jinak vrátí chybovou hlášku.
     *  
     *  @param parametry - jako parametr obsahuje název věco k prozkoumání
     *  @return zpráva, kterou vypíše hráčí
     */
    @Override
    public String proved(String... parametry) {
        if (parametry.length < 1) {
            return "nevím, co mám prozkoumat";
        }
        
        String nazevVeci = parametry[0];
        
        Vec vec = hPlan.getAktualniProstor().odeberVec(nazevVeci);
        if (vec == null) {
            return "věc '" + nazevVeci + "' tu není";
        }
        
        hPlan.getAktualniProstor().vlozVec(vec);
        
        return nazevVeci + ": " + vec.getPopis();
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
