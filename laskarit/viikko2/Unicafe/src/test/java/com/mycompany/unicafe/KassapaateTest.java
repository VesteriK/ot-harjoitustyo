package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {

    Maksukortti kortti;
    Kassapaate kassa;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
        kassa = new Kassapaate();
    }
    
    @Test
    public void luodussaKassassaOikeaRahamaara(){
        assertTrue(kassa.kassassaRahaa() == 100000);
    }
    
    @Test
    public void luodussaKassassaEiMyytyjaLounaita(){
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 0 && kassa.edullisiaLounaitaMyyty() == 0);
    }
    
    @Test
    public void syoEdullisestiToimiiArvolla() {
        kassa.syoEdullisesti(240);
        assertTrue(kassa.kassassaRahaa() == 100240);
    }
    
    @Test
    public void syoEdullisestiPalauttaaOikeanVaihtorahan() {
        assertEquals(40, kassa.syoEdullisesti(280));
    }
    
    @Test
    public void syoEdullisestiLisaaKassaanOikeinKunVaihtorahaa() {
        kassa.syoEdullisesti(350);
        assertTrue(kassa.kassassaRahaa() == 100240);
    }
    
    @Test
    public void syoEdullisestiToimiiKortilla() {
        kassa.syoEdullisesti(kortti);
        assertTrue(kortti.saldo() == 760);
    }
    
    @Test
    public void syoEdullisestiEiLisaaKassaanLiianPienellaArvolla() {
        kassa.syoEdullisesti(220);
        assertTrue(kassa.kassassaRahaa() == 100000);
    }
    
    @Test
    public void syoEdullisestiEiOtaKortiltaJosLiianVahanSaldoa() {
        kortti.otaRahaa(900);
        assertFalse(kassa.syoEdullisesti(kortti));
    }
    @Test
    public void syoEdullisestiEiToimiJosLiianVahanRahaa() {
        assertEquals(220, kassa.syoEdullisesti(220));
    }
    
    @Test
    public void syoMaukkaastiToimiiArvolla() {
        kassa.syoMaukkaasti(400);
        assertTrue(kassa.kassassaRahaa() == 100400);
    }
    
    @Test
    public void syoMaukkaastiPalauttaaOikeanVaihtorahan() {
        assertEquals(50, kassa.syoMaukkaasti(450));
    }
    
    @Test
    public void syoMaukkaastiLisaaOikeinKassaanKunVaihtorahaa() {
        kassa.syoMaukkaasti(450);
        assertTrue(kassa.kassassaRahaa() == 100400);
    }
    
    @Test
    public void syoMaukkaastiToimiiKortilla() {
        kassa.syoMaukkaasti(kortti);
        assertTrue(kortti.saldo() == 600);
    }
    
    @Test
    public void syoMaukkaastiEiToimiJosLiianVahanRahaa() {
        assertEquals(220, kassa.syoEdullisesti(220));
    }
    
    @Test
    public void syoMaukkaastiEiLisaaKassaanLiianPienellaArvolla() {
        kassa.syoMaukkaasti(350);
        assertTrue(kassa.kassassaRahaa() == 100000);
    }
    
    @Test
    public void syoMaukkastiEiOtaKortiltaJosSaldoLiianPieni() {
        kortti.otaRahaa(900);
        assertFalse(kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void rahanLataaminenKortilleToimii() {
        kassa.lataaRahaaKortille(kortti, 1000);
        assertTrue(kortti.saldo() == 2000);
    }
    
    @Test
    public void rahanLataaminenKortilleMuuttaaKassaa() {
        kassa.lataaRahaaKortille(kortti, 1000);
        assertTrue(kassa.kassassaRahaa() == 101000);
    }
    
    @Test
    public void kortilleEiVoiLadataNegatiivista() {
        kassa.lataaRahaaKortille(kortti, -1500);
        assertTrue(kortti.saldo() == 1000);
    }
    
    @Test
    public void kassanSaldonNoutoToimii() {
        assertTrue(kassa.kassassaRahaa() == 100000);
    }
    
    
    @Test
    public void myytyjenEdullistenLounaidenMaaraOikeaKortilla() {
        kassa.syoEdullisesti(kortti);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 1);
    }
    @Test
    public void myytyjenEdullistenLounaidenMaaraOikeaKateisella() {
        kassa.syoEdullisesti(280);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 1);
    }
    
    @Test
    public void myytyjenEdullistenMaaraEiNouseLiianPienellaArvolla() {
        kassa.syoEdullisesti(220);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 0);
    }
    
    @Test
    public void myytyjenEdullistenMaaraEiNouseJosKortillaEiSaldoa() {
        kortti.otaRahaa(900);
        kassa.syoEdullisesti(kortti);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 0);
    }
    
    @Test
    public void myytyjenMaukkaidenLounaidenMaaraOikeaKortilla() {
        kassa.syoMaukkaasti(kortti);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 1);
    }
    
    @Test
    public void myytyjenMaukkaidenLounaidenMaaraOikeaKateisella() {
        kassa.syoMaukkaasti(450);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 1);
    }
    
    @Test
    public void myytyjenMaukkaidenMaaraEiNouseLiianPienellaArvolla() {
        kassa.syoMaukkaasti(330);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 0);
    }
    
    @Test
    public void myytyjenMaukkaidenMaaraEiNouseJosKortillaEiSaldoa() {
        kortti.otaRahaa(900);
        kassa.syoMaukkaasti(kortti);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 0);
    }
    
}
