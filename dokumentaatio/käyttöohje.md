# Käyttöohje

Lataa tiedosto. Ohjelma olettaa, että käynnistyshakemistossa on sqlite tietokanta, joka määrittelee käyttäjät ja sanat. Tietokannan nimi on seuraava:

``` 
 Sanakirja.db
```

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla:

```
java -jar sanakirja.jar
```
 

## Kirjautuminen

Sovellus käynnistyy kirjoitusnäkymään:

<img src="https://github.com/SIholin/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Login.png" width="400">

Kirjautuminen onnistuu kirjoittamalla olemassaoleva käyttäjätunnus syötekenttään ja siihen kuuluva salasana ja sen jälkeen painamalla _Login_ näppäintä. Jos käyttäjää ei ole olemassa tai salasana väärä sovellus antaa siitä käyttäjälle virheviestin.

## Uuden käyttäjän luominen

Kirjautumisnäkymästä on mahdollista siirtyä uuden käyttäjän luomisnäkymään painikkeella _Create new user_:

<img src="https://github.com/SIholin/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/NewUser.png" width="400">

Uusi käyttäjä luodaan syöttämällä käytössä olematon käyttäjätunnus ja vähintään 2 merkin mittainen salasana niille tarkoitettuihin syöttökenttiin ja painamalla yämän jälkeen _Create_. Sieltä pääsee palaamaan kirjautumisnäkymään painamalla näppäintä _Back to login_. Jos käyttäjänimi jo käytössä tai salasana liian lyhyt sovellus antaa virheiestin käyttäjälle. Sovellus myös ilmoittaa kun käyttäjä on luotu onnistuneesti.

## Sanojen harjoittelu

Onnistuneen kirjautumisen seurauksena pääsee sanojen harjoittelu näkymään:

<img src="https://github.com/SIholin/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/mainEka.png" width="400">

tai

<img src="https://github.com/SIholin/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/MainToka.png" widht="400">

Jos tietokantaan ei ole vielä lisätty sanoja on mahdollista vain lisätä uusia sanoja tai kirjautua ulos. Muussa tapauksessa siellä on mahdollista kirjoittaa esillä olevan sanan käännös, joko englannin tai suomen kielellä riippuen siitä kummalla kielellä sana on näkyvissä. Tämän jälkeen painetaan _Answer_ painiketta, jonka jälkeen tulee ilmoitus onko vastaus oikein vai väärin.

Uuden sanan saa vaihdettua painamalla _New word_ painiketta.

Näkymän yläreunassa on tieto kuka käyttäjä on kirjautuneena sisään ja vieressä on _Logout_ painike, josta on mahdollista kirjautua ulos ja näkymä palaa kirjautumiseen.

## Uuden sanan lisääminen

Harjoittelu tilasta on mahdollista siirtyä _Add new word_ painiketta painamalla uuden sanan lisäämis näkymään: 

<img src="https://github.com/SIholin/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/AddNewWord.png" width="400">

Täällä voi lisätä uuden sanan kirjoittamalla itse sanan ja sen käännöksen niille tarkoitettuihin tekstikenttiin ja painamalla tämän jälkeen _Add_ näppäintä. Jos sana on jo tietokannassa tulee siitä virheilmoitus, samoin kun jos yritettään lisätä tyhjää sanaa. Sanan onnisuteesta lisäyksestä tulee ilmoitus.

Tästäkin näkymästä löytyy ylhäältä tieto kuka on kirjautuneena sisään ja _Logout_ näppäin, joka johtaa kirjautumisnäkymään sekä _Back to main_ näppäin joka johtaa takaisin sanojen harjoitteluun.

## Tilastot

Harjoittelu näkymästä voi siirtyä tilastoja näyttävään näkymään _Statistics_ näppäimellä:

<img src="https://github.com/SIholin/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Statistics.png" widht="400">

Se ketoo käyttäjälle kuinka monta hän on saanut oikein, väärin ja yrittänyt yhteensä myös onnistumisprosetti näkyy. Näkymä näyttää myös 9 viimeisintä väärin mennyttä sanaa (toki vähemmän jos yrityksiä ei ole vielä yli yhdeksää).

Näkymästä löytyy myös yläreunasta sisään kirjautuneen käyttäjän käyttäjätunnus, sekä _Back to main_ että _Logout_ näppäimet.

## Sanalista

Harjoittelu näkymästä voi siirtyä tietokantaan lisättyjen sanojen listaan _Practice_:

<img src="https://github.com/SIholin/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Practice.png" widht="400">

Siellä näkyy sanojen kirjoitusasu ja niiden käännös. Näkyästä löytyy myös yläreunasta sisään kirjautuneen käyttäjän käyttäjätunnus ja _Back to main_ sekä _Logout_ näppäimet.
