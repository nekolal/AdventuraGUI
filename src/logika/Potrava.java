/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


/*******************************************************************************
 * Instance třídy {@code Potrava} představují ...
 *
 * Tato třída popisuje potraviny hry
 * 
 * Potrava představuje předmět ve hře, který je možno konzumovat. 
 * Potrava je přiřazena do určitého prostoru, kde se nachází.
 * 
 * @author    Lukáš Nekola
 * @version   ZS 2016/2017
 */
public class Potrava
{
    private String nazev;
    private String popis;
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
     * Vytvoření potravy se zadaným názvem a popisem
     * 
     * @param nazev nazev potravy, jednoznačný identifikátor jendo slovo, nebo více
     * slov bez mezer
     * @param popis popis potravy
     */
    public Potrava(String nazev, String popis)
    {
        this.nazev = nazev;
        this.popis = popis;
    }
    
    /**
     * Vrací název potravy (byl zadán při vytváření potraviny jako parametr konstruktoru)
     * 
     * @return název potraviny
     */
    public String getNazev()
    {
        return nazev;
    }
    
    /**
     * Vrací popis potravy (byl zadán při vytváření potraviny jako parametr konstruktoru)
     * 
     * @return popis potraviny
     */
    public String getPopis()
    {
        return popis;
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
    //         Potrava instance = new Potrava();
    //     }
    //     /** @param args Parametry příkazového řádku - nepoužívané. */
    //     public static void main(String[] args)  {  test();  }
}
