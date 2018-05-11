# Testausdokumentti

Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Automatisoitujen testien ytimen moudostavat sovelluslogiikkaa, eli pakkauksen [sanakirja.domain](https://github.com/SIholin/otm-harjoitustyo/tree/master/Sanakirjatietokanta/src/main/java/sanakirja/domain) luokkia testaavat integraatiotestit ja yksikkötestit [UserTest](https://github.com/SIholin/otm-harjoitustyo/blob/master/Sanakirjatietokanta/src/test/java/tests/UserTest.java) ja [WordTest](https://github.com/SIholin/otm-harjoitustyo/blob/master/Sanakirjatietokanta/src/test/java/tests/WordTest.java)

### DAO-luokat

Molempien DAO-luokkien toiminnallisuus on testattu käyttämällä sovelluksessakin olevaa Sanakirja.db tietokantaa.

### Testauskattavuus

Käyttöliittymäkerrosta lukuunottamatta sovelluksen testauksen rivikattavuus on 94% ja haarautumakattavuus 100%. 
Testaamatta jäi Sanakirja.java mainluokka.

<img src="https://github.com/SIholin/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/JacocoReport.png" width="800" height="200">

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

### Asennus ja kanfigurointi

Sovellusta on testattu vain tilanteissa, joissa käyttäjät ja sanat tallettavat tietokanta Sanakirja.db on olemassa.

### Toiminnallisuudet

Kaikki [määrittelydokumentin](https://github.com/SIholin/otm-harjoitustyo/blob/master/dokumentaatio/vaatimuusmaarittely.md) ja [käyttöohjeen](https://github.com/SIholin/otm-harjoitustyo/blob/master/dokumentaatio/k%C3%A4ytt%C3%B6ohje.md) listaamat toiminnallisuudet on käyty läpi. Kaikkien toiminnallisuuksien yhteydessä on syötekentät yritetty täyttää myös virheellisillä arvoilla kuten tyhjillä.

## Sovellukseen jääneet laatuongelmat

Sovellus ei itsenäisesti luo tietokantaa vaan se pitää olla valmiina ja User sekä Word taulut sisällään.
