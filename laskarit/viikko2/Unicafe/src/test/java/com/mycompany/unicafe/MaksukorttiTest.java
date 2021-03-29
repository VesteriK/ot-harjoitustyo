package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoOikeinAlussa() {
        assertTrue(kortti.saldo() == 1000);
    }
    
    @Test
    public void rahanLisääminenKortilleToimii() {
        kortti.lataaRahaa(1500);
        assertTrue(kortti.saldo() == 2500);
    }
    
    @Test
    public void rahanOttaminenKortiltaToimii() {
        kortti.otaRahaa(500);
        assertTrue(kortti.saldo() == 500);
    }
    
    @Test
    public void kortinArvoEiMeneNegatiiviseksi() {
        kortti.otaRahaa(1500);
        assertTrue(kortti.saldo() == 1000);
    }
    
    @Test
    public void kortillaTarpeeksiRahaa() {
        assertTrue(kortti.otaRahaa(500));
    }
    
    @Test
    public void kortillaEiTarpeeksiRahaa() {
        assertFalse(kortti.otaRahaa(1500));
    }
    
    @Test
    public void toStringMetodiToimii() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
}
