/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
import java.util.Map;
import java.util.HashMap;


/*******************************************************************************
 * Instance třídy {@code Batoh} představují ...
 *
 * Tato třída popisuje batoh
 * 
 * Batoh je struktura pro ukládání věcí "k sobě" v průběhu hry, aby s nimi mohlo 
 * být nadále pracováno. Do batohu lze předměty přidávat, ale i je z něj vyhazovat.
 * Má omezenou maximální kapacitu "nesených" předmetů. Každý předmět se v batohu může 
 * nacházet 0krát nebo jednou.
 * 
 * @author    Lukáš Nekola
 * @version   ZS 2016/2017
 */
public class Batoh 
{
    private static final int KAPACITA = 5; //Možnost nést maximálně 5 věcí
    private Map<String, Vec> veci; //klíč - hodnota
    private HerniPlan herniPlan;
  
    //== KONSTANTNÍ ATRIBUTY TŘÍDY =============================================
    //== PROMĚNNÉ ATRIBUTY TŘÍDY ===============================================
    //== STATICKÝ INICIALIZAČNÍ BLOK - STATICKÝ KONSTRUKTOR ====================
    //== KONSTANTNÍ ATRIBUTY INSTANCÍ ==========================================
    //== PROMĚNNÉ ATRIBUTY INSTANCÍ ============================================
    //== PŘÍSTUPOVÉ METODY VLASTNOSTÍ TŘÍDY ====================================
    //== OSTATNÍ NESOUKROMÉ METODY TŘÍDY =======================================
   /* private String nazev;
    private String popis;
    private double hmotnost;*/
    //##########################################################################
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================

    /***************************************************************************
     * Vytvoření batohu. Batoh je reprezentován HashMapou
     * @param herniPlan instance herního plánu
     */

    public Batoh(HerniPlan herniPlan)
    {
       veci = new HashMap<>();
       this.herniPlan = herniPlan;
    }

    /**
     * Slouží pro výpis obsahu batohu
     * 
     * @return "Nic u sebe nemáš" v případě, že je batoh prázdný
     * jinak
     * @return vsechnyVeci všechny věci obsažené v batohu
     */
    
    @Override
    public String toString()
    {
        if(veci.isEmpty())
        {
            return "Nic u sebe nemáš";
        }
        String vsechnyVeci = "";
        for(String s : veci.keySet())
        {
            vsechnyVeci += s + ", ";
        }
        return vsechnyVeci;
       
    }
    
    /**
     * Slouží pro přidání věci do batohu
     * 
     * @param vec, věc, která má být vložena
     */
    public void pridej(Vec vec)
    {  
        veci.put(vec.getNazev(),vec);
    }
    
    /**
     * Slouží pro mazání věci z batohu
     * 
     * @param nazev, název mazané věci
     * @return maže položku z mapy
     */
    public Vec odeber(String nazev)
    {
        return veci.remove(nazev);
    }
    
    /**
     * Vrací kapacitu batohu
     * 
     * @return kapacita batohu
     */
    public int getKapacita()
    {
        return KAPACITA;
    }
    
    /**
     * Metoda pro zjištění, jestli již je batoh plný nebo ne.
     * 
     * @return hodnotu tru, pokud je batoh plný, jinak false
     */
    public boolean bagIsFull()
    {
        if(veci.size() <= KAPACITA)
        {
            return false;
        }
        return true;
    }
    
    /**
     * Metoda sloužící pro ověření, zda je věc vložena v batohu.
     * 
     * @param nazev název věci
     * @return true pokud je věc v batohu, jinak false
     */
    public boolean isInBag(String nazev)
    {
        if(veci.containsKey(nazev))
        {
            return true;
        }
        return false;
    }
    
    public Map<String, Vec>  getVeci() {
        return veci;
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
    //         Batoh instance = new Batoh();
    //     }
    //     /** @param args Parametry příkazového řádku - nepoužívané. */
    //     public static void main(String[] args)  {  test();  }
}
