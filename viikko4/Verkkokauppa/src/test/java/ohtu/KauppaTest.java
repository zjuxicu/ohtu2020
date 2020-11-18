package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;

import ohtu.verkkokauppa.Kauppa;
import ohtu.verkkokauppa.Viitegeneraattori;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(1);
        varasto = mock(Varasto.class);
        k = new Kauppa(varasto, pankki, viite);    
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(5);
        when(varasto.saldo(3)).thenReturn(0);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "piimä", 45));
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "olut", 99));
    }
    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {
    k.aloitaAsiointi();
    k.lisaaKoriin(1);     
    k.tilimaksu("pekka", "12345");

    verify(pankki).tilisiirto(eq("pekka"), eq(1), eq("12345"), anyString(), eq(5));   
    }

    @Test
    public void eriTuotteetJoitaVarastossaSummautuuOikein(){
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(50));
    }
    @Test
    public void voitOstaaVaikka2Maitoa(){
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("maito", "383-838");
        verify(pankki).tilisiirto(eq("maito"), anyInt(), eq("383-838"), anyString(), eq(10));
    }

    @Test
    public void olutOnLoppuJotenEtVoiOstaa(){
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.lisaaKoriin(3);
        k.tilimaksu("maito", "383-838");
        verify(pankki).tilisiirto(eq("maito"), anyInt(), eq("383-838"), anyString(), eq(45));
    }

    @Test
    public void viitteetToimii(){
        when(viite.uusi()).thenReturn(1).thenReturn(2).thenReturn(3);
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("eka", "5215");
        verify(pankki).tilisiirto(eq("eka"), eq(1), eq("5215"), anyString(), anyInt());
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("toka", "6235");
        verify(pankki).tilisiirto(eq("toka"), eq(2), eq("6235"), anyString(), anyInt());
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("kolmas", "623625");
        verify(pankki).tilisiirto(eq("kolmas"), eq(3), eq("623625"), anyString(), anyInt());
    }
    @Test
    public void ostoskoristaVoiPoistaaSiellaOlevia(){
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.poistaKorista(1);
        k.poistaKorista(3);
        k.tilimaksu("matti", "00");
        verify(pankki).tilisiirto(eq("matti"), eq(1), eq("00"), anyString(), eq(45));
    }
}