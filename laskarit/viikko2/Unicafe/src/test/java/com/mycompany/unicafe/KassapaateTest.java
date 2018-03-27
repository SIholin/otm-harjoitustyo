/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/**
 *
 * @author ihqsanna
 */
public class KassapaateTest {
    Kassapaate paate;
    Maksukortti ekakortti;
    Maksukortti tokakortti;
    
    @Before
    public void setUp() {
        paate = new Kassapaate();
        ekakortti = new Maksukortti(1000);
        tokakortti = new Maksukortti(100);
    }
    
    @Test
    public void AlussaTiedotOikeat() {
        assertTrue(paate.edullisiaLounaitaMyyty()==0 && paate.maukkaitaLounaitaMyyty()==0 && paate.kassassaRahaa()==100000);
    }
    
    @Test
    public void kateinenRiittaaEdulliseen() {
        int vaihto = paate.syoEdullisesti(300);
        assertTrue(paate.edullisiaLounaitaMyyty()==1 && paate.kassassaRahaa()==100240 && vaihto==60);
    }
    @Test 
    public void kateinenRiittaaMaukkaaseen() {
        int vaihto = paate.syoMaukkaasti(500);
        assertTrue(paate.maukkaitaLounaitaMyyty()==1 && paate.kassassaRahaa()==100400 && vaihto == 100);
    }
    
    @Test
    public void kateinenEiRiitaEdulliseen() {
        int maksu = paate.syoEdullisesti(200);
        assertTrue(paate.edullisiaLounaitaMyyty()==0 && paate.kassassaRahaa()==100000 && maksu ==200);
    }
    
    @Test
    public void kateinenEiRiitaMaukkaaseen() {
        int maksu = paate.syoMaukkaasti(300);
        assertTrue(paate.maukkaitaLounaitaMyyty()==0 && paate.kassassaRahaa()==100000 && maksu == 300);
    }
    
    @Test
    public void korttiRiittaaEdulliseen() {
        boolean onnistuminen = paate.syoEdullisesti(ekakortti);
        
        assertTrue(onnistuminen == true && ekakortti.saldo()==760 && paate.edullisiaLounaitaMyyty()==1 && paate.kassassaRahaa()==100000);
    }
    @Test
    public void korttiRiittaaMaukkaaseen() {
        boolean onnistuminen = paate.syoMaukkaasti(ekakortti);
        assertTrue(onnistuminen == true && ekakortti.saldo()== 600 && paate.maukkaitaLounaitaMyyty()==1 && paate.kassassaRahaa()==100000);
        
    }
    
    @Test
    public void korttiEiRiitaEdulliseen() {
        boolean onnistuminen = paate.syoEdullisesti(tokakortti);
        assertTrue(onnistuminen==false && tokakortti.saldo()==100 && paate.edullisiaLounaitaMyyty()==0 && paate.kassassaRahaa() ==100000);
    }
    
    @Test
    public void korttiEiRiitaMaukkaaseen() {
         boolean onnistuminen = paate.syoMaukkaasti(tokakortti);
        assertTrue(onnistuminen==false && tokakortti.saldo()==100 && paate.maukkaitaLounaitaMyyty()==0 && paate.kassassaRahaa() ==100000);
    }
    
    @Test
    public void kortinLataus() {
        paate.lataaRahaaKortille(tokakortti, 100);
        assertEquals("saldo: 2.0", tokakortti.toString());
        assertTrue(paate.kassassaRahaa()==100100);
    }
    
    @Test
    public void kortinLatausEpaonnistuu() {
        paate.lataaRahaaKortille(tokakortti, -10);
        assertTrue(tokakortti.saldo()==100 && paate.kassassaRahaa()==100000);
    }
    
}
