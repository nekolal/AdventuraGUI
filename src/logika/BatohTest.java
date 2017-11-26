/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;



/*******************************************************************************
 * Testovací třída {@code BatohTest} slouží ke komplexnímu otestování
 * třídy {@link BatohTest}.
 *
 * @author    Lukáš Nekola
 * @version   ZS 2016/2017
 */
public class BatohTest
{
    //== KONSTANTNÍ ATRIBUTY TŘÍDY =============================================
    //== PROMĚNNÉ ATRIBUTY TŘÍDY ===============================================
    //== STATICKÝ INICIALIZAČNÍ BLOK - STATICKÝ KONSTRUKTOR ====================
    //== KONSTANTNÍ ATRIBUTY INSTANCÍ ==========================================
    //== PROMĚNNÉ ATRIBUTY INSTANCÍ ============================================
    //== PŘÍSTUPOVÉ METODY VLASTNOSTÍ TŘÍDY ====================================
    //== OSTATNÍ NESOUKROMÉ METODY TŘÍDY =======================================

    //##########################################################################
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------
    //== PŘÍPRAVA A ÚKLID PŘÍPRAVKU ============================================

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
    }
    private HerniPlan herniPlan;

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown()
    {
    }



    //== PŘÍSTUPOVÉ METODY VLASTNOSTÍ INSTANCÍ =================================
    //== OSTATNÍ NESOUKROMÉ METODY INSTANCÍ ====================================
    //== SOUKROMÉ A POMOCNÉ METODY TŘÍDY =======================================
    //== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ====================================
    //== INTERNÍ DATOVÉ TYPY ===================================================
    //== VLASTNÍ TESTY =========================================================
    //
    //     /********************************************************************
    //      *
    //      */
    //     @Test
    //     public void testXxx()
    //     {
    //     }

    @Test
    public void testMetodyPridej()
    {
        Batoh batoh1 = new Batoh(herniPlan);
        Vec vec1 = new Vec("a", "aaa", true);
        Vec vec2 = new Vec("b", "bbb", true);
        batoh1.pridej(vec1);
        assertEquals(true, batoh1.isInBag("a"));
        assertEquals(false, batoh1.isInBag("b"));
        batoh1.pridej(vec2);
        assertEquals(true, batoh1.isInBag("b"));
        
    }
    
    @Test
    public void testMetodyOdeber()
    {
        Batoh batoh1 = new Batoh(herniPlan);
        Vec vec1 = new Vec("a", "aaa", false);
        Vec vec2 = new Vec("b", "bbb", false);
        assertEquals(null, batoh1.odeber("b"));
        batoh1.pridej(vec2);
        assertEquals(true, batoh1.isInBag("b"));
        batoh1.odeber("b");
        assertEquals(false, batoh1.isInBag("b"));
    }
    
    @Test
    public void testMetodyBagIsFull()
    {
        Batoh batoh1 = new Batoh(herniPlan);
        Vec vec1 = new Vec("a", "aaa", false);
        Vec vec2 = new Vec("b", "bbb", false);
        Vec vec3 = new Vec("c", "ccc", false);
        Vec vec4 = new Vec("d", "ddd", false);
        Vec vec5 = new Vec("e", "eee", false);
        Vec vec6 = new Vec("f", "fff", false);
        batoh1.pridej(vec1);
        batoh1.pridej(vec2);
        assertEquals(false, batoh1.bagIsFull());
        batoh1.pridej(vec3);
        batoh1.pridej(vec4);
        assertEquals(false, batoh1.bagIsFull());
        batoh1.pridej(vec5);
        assertEquals(false, batoh1.bagIsFull());
        batoh1.pridej(vec6);
        assertEquals(true, batoh1.bagIsFull());
        
    }
    
    @Test
    public void testMetodyIsInBag()
    {
        Batoh batoh1 = new Batoh(herniPlan);
        Vec vec1 = new Vec("a", "aaa", false);
        Vec vec2 = new Vec("b", "bbb", false);
        Vec vec3 = new Vec("c", "ccc", false);
        Vec vec4 = new Vec("d", "ddd", false);
        batoh1.pridej(vec1);
        batoh1.pridej(vec2);
        assertEquals(true, batoh1.isInBag("a"));
        assertEquals(false, batoh1.isInBag("c"));
    }
}

