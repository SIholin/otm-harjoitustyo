# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne on nelitasoinen kerrosarkkitehtuuri, ja koodin pakkausrakenne on seuraava:
 


Yläpakkaus [sanakirja](https://github.com/SIholin/otm-harjoitustyo/tree/master/Sanakirjatietokanta/src/main/java/sanakirja) sisältää sovelluksen aloituksen sekä muut pakkaukset [sanakirja.ui](https://github.com/SIholin/otm-harjoitustyo/tree/master/Sanakirjatietokanta/src/main/java/sanakirja/ui), [sanakirja.domain](https://github.com/SIholin/otm-harjoitustyo/tree/master/Sanakirjatietokanta/src/main/java/sanakirja/domain) ja [sanakirja.dao](https://github.com/Siholin/otm-harjoitustyo/tree/master/Sanakirjatietokanta/src/main/java/sanakirja/dao). Sanakirja.ui sisältää JavaFX:llä toteutetun käyttöliittymän sanakirja.domain sovelluslogiikan ja sanakirja.dao tietojen pysyväistallennuksesta vastaavan koodin.

## Käyttöliittymä

Käyttöliittymä sisältää viisi erillistä näkymää

- kirjautuminen
- uuden käyttäjän luominen
- sanojen harjoittelutila
- uuden sanan lisääminen
- käyttäjän hekilökohtaiset tilastot

Ne on toteutettu omina Scene-olioinaan, jotka yksi kerrallaan on käyttäjälle esillä eli sijoitettuna sovelluksen stageen. Käyttöliittymä aloitetataan sanakirja.Sanakirja luokassa, mutta sovelluksen käyttämät eri scenet sijaitsevat sanakirja.ui:n sisällä omina luokkinaan. 

## Sovelluslogiikka

Sovelluksen loogisen datamallin muodostavat [User](https://github.com/SIholin/otm-harjoitustyo/blob/master/Sanakirjatietokanta/src/main/java/sanakirja/domain/User.java) ja [Word](https://github.com/SIholin/otm-harjoitustyo/blob/master/Sanakirjatietokanta/src/main/java/sanakirja/domain/Word.java), jotka kuvaavat käyttäjiä ja harjoiteltavia sanoja:

<img src=”https://github.com/SIholin/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Luokkakaavio.jpg”  width=””400”>

