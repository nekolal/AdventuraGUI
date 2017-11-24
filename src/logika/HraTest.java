package logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra.
 *
 * @author   Jarmila Pavlíčková
 * @version  ZS 2016/2017
 */
public class HraTest {
    private Hra hra1;
    private HerniPlan hP;

    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra1 = new Hra();
        hP = new HerniPlan();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testPrubehHry() {
        assertEquals("pláž", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertTrue(hra1.getHerniPlan().getBatoh().isInBag("kapesní_nůž"));
        hra1.zpracujPrikaz("vezmi ostrý_kámen");
        assertTrue(hra1.getHerniPlan().getBatoh().isInBag("ostrý_kámen"));
        hra1.zpracujPrikaz("jdi les");
        hra1.zpracujPrikaz("vezmi klády");
        assertTrue(hra1.getHerniPlan().getBatoh().isInBag("klády"));
        hra1.zpracujPrikaz("jdi prales");
        hra1.zpracujPrikaz("vezmi lijány");
        hra1.zpracujPrikaz("vezmi kokosový_ořech");
        assertTrue(hra1.getHerniPlan().getBatoh().isInBag("lijány"));
        hra1.zpracujPrikaz("pouzij lijány kapesní_nůž");
        hra1.zpracujPrikaz("vezmi nařezané_lijány");
        assertTrue(hra1.getHerniPlan().getBatoh().isInBag("nařezané_lijány"));
        hra1.zpracujPrikaz("pouzij nařezané_lijány klády");
        hra1.zpracujPrikaz("vezmi vor");
        assertFalse(hra1.getHerniPlan().getBatoh().isInBag("klády"));
        assertTrue(hra1.getHerniPlan().getBatoh().isInBag("vor"));
        hra1.zpracujPrikaz("jdi potok");
        hra1.zpracujPrikaz("pouzij ostrý_kámen kokosový_ořech");
        hra1.zpracujPrikaz("vezmi rozpůlený_kokosový_ořech");
        hra1.zpracujPrikaz("vezmi voda");
        hra1.zpracujPrikaz("jdi prales");
        hra1.zpracujPrikaz("jdi les");
        hra1.zpracujPrikaz("jdi pláž");
        hra1.zpracujPrikaz("jdi moře");
        assertTrue(hra1.getHerniPlan().hracVyhral());
        assertEquals(true, hra1.konecHry());
    }
    
    @Test
    public void testPridavaniVeciDoBatohu()
    {
        Vec vecA = new Vec("aaa", "Asdfsd", false);
        Vec vecB = new Vec("bbb", "Asdfsd", true);
        
        Prostor prostor1 = new Prostor("prostor1", "asdf");
        
        prostor1.vlozVec(vecA);
        prostor1.vlozVec(vecB);
        
        hra1.getHerniPlan().setAktualniProstor(prostor1);
        
        hra1.zpracujPrikaz("vezmi aaa");
        assertFalse(hra1.getHerniPlan().getBatoh().isInBag("aaa"));
        hra1.zpracujPrikaz("vezmi bbb");
        assertTrue(hra1.getHerniPlan().getBatoh().isInBag("bbb"));
    } 
    
    @Test
    public void testVyhazovaniVeciZBatohu()
    {
        Vec vecA = new Vec("aaa", "Asdfsd", true);
        Vec vecB = new Vec("bbb", "Asdfsd", true);
        
        Prostor prostor1 = new Prostor("prostor1", "asdf");
        
        prostor1.vlozVec(vecA);
        prostor1.vlozVec(vecB);
        
        hra1.getHerniPlan().setAktualniProstor(prostor1);
        
        hra1.zpracujPrikaz("vezmi aaa");
        hra1.zpracujPrikaz("vezmi bbb");
        
        assertTrue(hra1.getHerniPlan().getBatoh().isInBag("aaa"));
        assertTrue(hra1.getHerniPlan().getBatoh().isInBag("bbb"));
        
        hra1.zpracujPrikaz("zahod aaa");
        assertFalse(hra1.getHerniPlan().getBatoh().isInBag("aaa"));
        assertTrue(hra1.getHerniPlan().getBatoh().isInBag("bbb"));
    }
    
    @Test
    public void testKapacityBatohu()
    {
        Vec vecA = new Vec("aaa", "Asdfsd", true);
        Vec vecB = new Vec("bbb", "Asdfsd", true);
        Vec vecC = new Vec("ccc", "Asdfsd", true);
        Vec vecD = new Vec("ddd", "Asdfsd", true);
        Vec vecE = new Vec("eee", "Asdfsd", true);
        Vec vecF = new Vec("fff", "Asdfsd", true);
        
        Prostor prostor1 = new Prostor("prostor1", "asdf");
        
        prostor1.vlozVec(vecA);
        prostor1.vlozVec(vecB);
        prostor1.vlozVec(vecC);
        prostor1.vlozVec(vecD);
        prostor1.vlozVec(vecE);
        prostor1.vlozVec(vecF);
        
        hra1.getHerniPlan().setAktualniProstor(prostor1);
        
        hra1.zpracujPrikaz("vezmi aaa");
        hra1.zpracujPrikaz("vezmi bbb");
        hra1.zpracujPrikaz("vezmi ccc");
        hra1.zpracujPrikaz("vezmi ddd");
        hra1.zpracujPrikaz("vezmi eee");
        hra1.zpracujPrikaz("vezmi fff");
        
        assertTrue(hra1.getHerniPlan().getBatoh().isInBag("aaa"));
        assertTrue(hra1.getHerniPlan().getBatoh().isInBag("bbb"));
        assertTrue(hra1.getHerniPlan().getBatoh().isInBag("ccc"));
        assertTrue(hra1.getHerniPlan().getBatoh().isInBag("ddd"));
        assertTrue(hra1.getHerniPlan().getBatoh().isInBag("eee"));
        assertFalse(hra1.getHerniPlan().getBatoh().isInBag("fff"));
    }
    
    @Test
    public void testSmrtiPrales()
    {
        assertEquals("pláž", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("vezmi ostrý_kámen");
        hra1.zpracujPrikaz("jdi les");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi prales");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi les");
        hra1.zpracujPrikaz("zahod ostrý_kámen");
        hra1.zpracujPrikaz("jdi prales");
        assertTrue(hra1.getHerniPlan().hracProhral());
        assertEquals(true, hra1.konecHry());
    }
    
    @Test
    public void testSmrtiMoreBezVody()
    {
        assertEquals("pláž", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertTrue(hra1.getHerniPlan().getBatoh().isInBag("kapesní_nůž"));
        hra1.zpracujPrikaz("vezmi ostrý_kámen");
        assertTrue(hra1.getHerniPlan().getBatoh().isInBag("ostrý_kámen"));
        hra1.zpracujPrikaz("jdi les");
        hra1.zpracujPrikaz("vezmi klády");
        assertTrue(hra1.getHerniPlan().getBatoh().isInBag("klády"));
        hra1.zpracujPrikaz("jdi prales");
        hra1.zpracujPrikaz("vezmi lijány");
        assertTrue(hra1.getHerniPlan().getBatoh().isInBag("lijány"));
        hra1.zpracujPrikaz("pouzij lijány kapesní_nůž");
        hra1.zpracujPrikaz("vezmi nařezané_lijány");
        assertTrue(hra1.getHerniPlan().getBatoh().isInBag("nařezané_lijány"));
        hra1.zpracujPrikaz("pouzij nařezané_lijány klády");
        hra1.zpracujPrikaz("vezmi vor");
        assertFalse(hra1.getHerniPlan().getBatoh().isInBag("klády"));
        assertTrue(hra1.getHerniPlan().getBatoh().isInBag("vor"));
        hra1.zpracujPrikaz("jdi les");
        hra1.zpracujPrikaz("jdi pláž");
        hra1.zpracujPrikaz("jdi moře");
        assertTrue(hra1.getHerniPlan().hracProhral());
        assertEquals(true, hra1.konecHry());
    }
    
    @Test
    public void testPrikazuJdi()
    {
        hra1.zpracujPrikaz("jdi les");
        hra1.zpracujPrikaz("jdi potok");
        assertEquals("les", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi prales");
        assertEquals("prales", hra1.getHerniPlan().getAktualniProstor().getNazev());
    }
    
    @Test
    public void testZustavaniPredmetu()
    {
        hra1.zpracujPrikaz("vezmi ostrý_kámen");
        hra1.zpracujPrikaz("jdi les");
        hra1.zpracujPrikaz("jdi prales");
        hra1.zpracujPrikaz("vezmi kokosový_ořech");
        hra1.zpracujPrikaz("jdi potok");
        hra1.zpracujPrikaz("pouzij ostrý_kámen kokosový_ořech");
        hra1.zpracujPrikaz("vezmi rozpůlený_kokosový_ořech");
        hra1.zpracujPrikaz("vezmi voda");
        assertTrue(hra1.getHerniPlan().getBatoh().isInBag("voda"));
        hra1.zpracujPrikaz("jdi prales");
        hra1.zpracujPrikaz("zahod voda");
        assertFalse(hra1.getHerniPlan().getBatoh().isInBag("voda"));
        hra1.zpracujPrikaz("jdi potok");
        hra1.zpracujPrikaz("vezmi voda");
        assertTrue(hra1.getHerniPlan().getBatoh().isInBag("voda"));
        hra1.zpracujPrikaz("zahod voda");
        hra1.zpracujPrikaz("jdi prales");
        hra1.zpracujPrikaz("vezmi voda");
        assertFalse(hra1.getHerniPlan().getBatoh().isInBag("voda"));
    }
}
