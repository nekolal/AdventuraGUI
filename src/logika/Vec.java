/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Třída Vec - popisuje jednotlivé věci hry
 *
 * "Věc" reprezentuje věc, která je umístěna v určitém prostoru
 *
 * @author    Lukáš Nekola
 * @version   ZS 2016/2017
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private String popis;
    private boolean prenositelna;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Vytvoření věci se zadaným názvem, popisem a přenositelností
     *  
     *  @param nazev - název věci, jednoznačný identifikátor jendo slovo, nebo více
     * slov bez mezer.
     *  @param popis - popis věci, několik slov
     *  @param prenositelna - true/false přenositelná/nepřenostitelná
     */
    public Vec(String nazev, String popis, boolean prenositelna)
    {
        this.nazev = nazev;
        this.popis = popis;
        this.prenositelna = prenositelna;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Vrátí název věci (byl zadán při vytváření věci jako parametr konstruktoru)
     * 
     * @return název věci
     */
    public String getNazev() {
        return nazev;
    }
    
    /**
     * Vrátí popis věci (byl zadán při vytváření věciy jako parametr konstruktoru)
     * 
     * @return popis věci
     */
    public String getPopis() {
        return popis;
    }
    
    /**
     * Vrátí přenositelnost věci (byla zadán při vytváření věciy jako parametr konstruktoru)
     * 
     * @return přenositelnost (ano/ne)
     */
    public boolean isPrenositelna() {
        return prenositelna;
    }

    
    // Možná bude potřeba přidat settery pro atributy 'popis' a 'prenositelna'.
    // Atribut 'nazev' by se měnit neměl.

    //== Soukromé metody (instancí i třídy) ========================================

}
