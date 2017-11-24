/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/*******************************************************************************
 * Třída PrikazPouzij implementuje pro  hru příkaz použij
 *
 * @author    Lukáš Nekola
 * @version   ZS 2016/2017
 */
public class PrikazPouzij implements IPrikaz
{
    private static final String NAZEV = "pouzij";
    
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
     * Konstruktor třídy
     * 
     * @param herniPlan herní plán, ve kterém se budou "používat"věci
     */
    public PrikazPouzij(HerniPlan hPlan)
    {
        this.herniPlan = hPlan;
    }
    
    /**
     * Provádí příkaz "použij". Zkouší kombinovat 2 veci. Nezadá-li se
     * žádný parametr, vrátí se "co chceš použít. Zadá-li se jen jeden a daná věc
     * je v batohu vrátí se "jednou věcí nic nevytvoříš". Pro kombinaci dvou věcí 
     * je uvedeno níže.
     * 
     * Klády + lijány -> return "Lijány jsou moc dlouhé je potřeba upravit jejich délku"
     * kapesní_nůž + lijány ->  vytvoří se věc nařezané_lijány
     *                          nařezané_lijány se vloží do aktuálního prostoru
     *                          return "Nařezal jsi lijány na potřebnou délku"
     * klády + nařezané_lijány->vytvoří věc vor
     *                          vor se vloží do prostoru
     *                          return "Kombinuji\n Vytvořil jsi vor, pomocí kterého se můžeš dostat pryč z tohoto ostrova"
     * kokosový_ořech + ostrý_kámen ->  vytvoří věc rozpůlený_kokosový_ořech
     *                                  rozpůlený_kokosový_ořech se vloží do aktuálního prostoru
     *                                  přidá se zpět do batohu ostrý_kámen
     *                                  return "Kombinuji\n Vytvořil jsi nádobu na vodu z kokosového ořechu."
     *                        
     * jinak -> return "to by k ničemu nevedlo"
     * 
     * Je-li zadána věc, která není v batohu, vrátí se "zadanou věc nemáš u sebe".
     * @param parametry - jako parametry obsahuje název věci(věcí)
     * @return zpáva, která se vypíše hráči
     */
    @Override
    public String proved(String... parametry) 
    {
        if (parametry.length < 1)
        {
            return "co chceš použít?";
        }
        
        String nazevVeci1 = parametry[0];
        
        if (parametry.length == 1 && herniPlan.getBatoh().isInBag(nazevVeci1))
        {
            return "jednou věcí nic nevytvoříš";
        }
        
        String nazevVeci2 = parametry[1];
        
        if (herniPlan.getBatoh().isInBag(nazevVeci1) && herniPlan.getBatoh().isInBag(nazevVeci2))
        {
            Vec vec1 = herniPlan.getBatoh().odeber(nazevVeci1);
            Vec vec2 = herniPlan.getBatoh().odeber(nazevVeci2);
            
            
            if((vec1.getNazev().equals("klády") && vec2.getNazev().equals("lijány"))||(vec1.getNazev().equals("lijány") && vec2.getNazev().equals("klády")))
            {
                herniPlan.getBatoh().pridej(vec1); //přidá věc zpět do batohu, pokud není kombinovatelná s jinou věcí
                herniPlan.getBatoh().pridej(vec2); //přidá věc zpět do batohu, pokud není kombinovatelná s jinou věcí
                return "Lijány jsou moc dlouhé je potřeba upravit jejich délku";
            }
            
            if((vec1.getNazev().equals("kapesní_nůž") && vec2.getNazev().equals("lijány")))
            {
                Vec nařezanéLijány = new Vec("nařezané_lijány", "nařezané lijány důležité pro stavbu plavidla", true);
                herniPlan.getAktualniProstor().vlozVec(nařezanéLijány);
                herniPlan.getBatoh().pridej(vec1);
                return "Nařezal jsi lijány na potřebnou délku" + 
                    "\n nově vytvořenou věc najdeš v prostoru, kde se nacházíš pod názvem: " + nařezanéLijány.getNazev();
            }
            else if((vec1.getNazev().equals("lijány") && vec2.getNazev().equals("kapesní_nůž")))
            {
                Vec nařezanéLijány = new Vec("nařezané_lijány", "nařezané lijány důležité pro stavbu plavidla", true);
                herniPlan.getAktualniProstor().vlozVec(nařezanéLijány);
                herniPlan.getBatoh().pridej(vec2);
                return "Nařezal jsi lijány na potřebnou délku" + 
                    "\n nově vytvořenou věc najdeš v prostoru, kde se nacházíš pod názvem: " + nařezanéLijány.getNazev();
            }
            
            if((vec1.getNazev().equals("klády") && vec2.getNazev().equals("nařezané_lijány"))||(vec1.getNazev().equals("nařezané_lijány") && vec2.getNazev().equals("klády")))
            {
                Vec vor = new Vec("vor", "tohle sis postavil a to je tvá jediná záchrana", true);
                herniPlan.getAktualniProstor().vlozVec(vor);
                return "Kombinuji\n Vytvořil jsi vor, pomocí kterého se můžeš dostat pryč z tohoto ostrova" + 
                    "\n nově vytvořenou věc najdeš v prostoru, kde se nacházíš pod názvem: " + vor.getNazev();
            }
           
            /*
            if((vec1.getNazev().equals("kokosový_ořech") && vec2.getNazev().equals("ostrý_kámen"))||(vec1.getNazev().equals("ostrý_kámen") && vec2.getNazev().equals("kokosový_ořech")))
            {
                Vec rozpůlenýKokosovýOřech = new Vec("rozpůlený_kokosový_ořech", "Rozpůlený kokosový ořech, dobré na uchování vody", true);
                herniPlan.getAktualniProstor().vlozVec(rozpůlenýKokosovýOřech);
                return "Kombinuji\n Vytvořil jsi nádobu na vodu z kokosového ořechu.";
            }
            */
            
            if((vec1.getNazev().equals("kokosový_ořech") && vec2.getNazev().equals("ostrý_kámen")))
            {
                Vec rozpůlenýKokosovýOřech = new Vec("rozpůlený_kokosový_ořech", "Rozpůlený kokosový ořech, dobré na uchování vody", true);
                herniPlan.getAktualniProstor().vlozVec(rozpůlenýKokosovýOřech);
                herniPlan.getBatoh().pridej(vec2);
                return "Kombinuji\n Vytvořil jsi nádobu na vodu z kokosového ořechu." +
                    "\n nově vytvořenou věc najdeš v prostoru, kde se nacházíš pod názvem: " + rozpůlenýKokosovýOřech.getNazev();
            }
            else if((vec1.getNazev().equals("ostrý_kámen") && vec2.getNazev().equals("kokosový_ořech")))
            {
                Vec rozpůlenýKokosovýOřech = new Vec("rozpůlený_kokosový_ořech", "Rozpůlený kokosový ořech, dobré na uchování vody", true);
                herniPlan.getAktualniProstor().vlozVec(rozpůlenýKokosovýOřech);
                herniPlan.getBatoh().pridej(vec1);
                return "Kombinuji\n Vytvořil jsi nádobu na vodu z kokosového ořechu." +
                    "\n nově vytvořenou věc najdeš v prostoru, kde se nacházíš pod názvem: " + rozpůlenýKokosovýOřech.getNazev();
            }
            
            herniPlan.getBatoh().pridej(vec1); //přidá věc zpět do batohu, pokud není kombinovatelná s jinou věcí
            herniPlan.getBatoh().pridej(vec2); //přidá věc zpět do batohu, pokud není kombinovatelná s jinou věcí
            
            return "To by k ničemu nevedlo...";
        }
      
        return "Zadanou věc nemáš u sebe!";
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
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
    //         PrikazPouzij instance = new PrikazPouzij();
    //     }
    //     /** @param args Parametry příkazového řádku - nepoužívané. */
    //     public static void main(String[] args)  {  test();  }
}
