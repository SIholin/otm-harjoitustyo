# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne on nelitasoinen kerrosarkkitehtuuri, ja koodin pakkausrakenne on seuraava:
 
<img src="https://github.com/SIholin/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/pakkausrakenne.jpg" widht="400">

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

<img src=”https://github.com/SIholin/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Luokkakaavio.jpg”  widht=”400”>

## Tietojen pysyväistallennus

Pakkauksen sanakirja.dao luokat UserDao ja WordDao pitävät huolen tietojen tallentamisesta Sanakirja.db tietokantaan. Nämä noudattavat Data Access Object -suunnittelumallia. Luokat on eristetty Dao rajapinnan taakse.

### Tietokanta

Sanakirja.db tietokanta on sqlite tietokanta. Se pitää sisällään sanojen ja käyttäjien tallentamis ominaisuuden, joiden taulut on luotu seuraavilla lauseilla.

__User:__
 
<pre>
CREATE TABLE User (id integer PRIMARY KEY, username varchar(50), password varchar(50), failNumber integer, fails varchar(1000000), allAttempts integer);
</pre>

__Word:__

<pre>
CREATE TABLE Word (id integer PRIMARY KEY, form varchar(50), translation varcahr(50));
</pre>

## Päätoiminnallisuudet

Kuvataan seuraavaksi sovelluksen toimintalogiikka muutaman päätoiminnalisuuden osalta sekvenssikaaviona.

### Käyttäjän kirjautuminen

Kun kirjtumisikkunassa olevaan syötetkenttiin on kirjoitettu käyttäjätunnus ja salasana sekä painetaan LoginButton etenee sovelluksen kontrolli seuraavasti:

<img src="https://github.com/SIholin/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/otm_vko5.jpg" widht="400">

Painikkeen painamiseen reagoiva tapahtumankäsittelijä kutsuu sovelluslogiikan SanakirjaUI metodia login antaen parametriksi kirjautuneen käyttäjätunnuksen. Sovelluslogiikka selvittää userDao:n avulla onko käyttäjätunnus olemassa. Jos on ja kirjoitettu salasaana sille kuuluva, eli kirjautuminen onnistuu, on seurauksena se että käyttöliittymä vaihtaa näkymäksi mainScenen, eli sovelluksen varsinaisen päänäkymän.

### Sanojen harjoittelu

Kun päänäkymässä olevaan syötekenttään on kirjoitettu näkyvissä olevan sanan käännös ja painetaan Answer painiketta etenee sovelluksen kontrolli seuraavasti:

<img src="https://github.com/SIholin/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/harjoitteluSekvennsi.jpg" widht="400">

## Ohjelman rakenteeseen jääneet heikkoudet

Sovelluksessa muuttujien nimiä tulisi parantaa.

### Käyttöliittymä

Vaikka graafisen käyttöliittymän ikkunat on jaoteltu omiksi luokikseen ovat ne edelleen pitkiä ja erityisesti jokaisen ikkunan palauttava metodi on jokaisessa luokassa melko pitkä. Myös jokaisella ikkunan näyttävällä luokalla on hurja määrä eri attribuutteja. 

### User-luokka

User-luokalla on melko paljon attribuutteja näille olisi voinut tehdä jonkun yhdistelmätaulun kuten UserWord jossa olisi tietyn käyttäjän epäonnistuneet (ja onnistuneet sanat), ettei niitä käyttäjälle tarvitsisi pitkässä String rimpsussa tallettaa.
