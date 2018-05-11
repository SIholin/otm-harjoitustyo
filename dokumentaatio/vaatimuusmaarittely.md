# Vaativuusmäärittely


## Sovelluksen tarkoitus

Sovelluksen avulla reisteröityneet käyttäjät voivat harjoitella kielten oikeinkirjoitusta. Kaikilla rekisteröityneillä käyttäjillä on oma harjoitelusivunsa. Tarkoitus on harjoitella suomi-englanti sanastoa, mutta käyttäjä voi halutessaan lisätä myös omia kieli yhdistelmiään __!__

## Käyttäjät

Sovelluksella on ainoastaan yksi käyttäjärooli eli __normaali käyttäjä__. 

## Käyttöliittymä

Sovellus aukeaa kirjautumisnäkymään, josta on mahdollista siirtyä uuden käyttäjän luomisnäkymään tai onnistuneen kirjautumisen yhteydessä kirjaantuneen käyttäjän harjoittelutilaan, jos tietokanta on sanojen osalta tyhjä on käyttäjän vain mahdollista siirtyä uuteen näkymään lisäämään sanoja tähän tarkoitettua nappia painamalla (tai kirjautua ulos). Muulloin näkyvissä on myös sana johon käännöstä odotetaan, sekä vastaus nappi joka kertoo onko vastaus oikein vai väärin ja nappi jolla saa harjoiteltavan sanan vaihdettua uuteeen, sekä tilastonäkymään vievä nappi ja tietokannassa olevien sanojen sanalistaan vievä nappi. 

## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista

- käyttäjä voi luoda järjestelmään käyttäjätunnuksen
  - käyttäjätunnuksen täytyy olla uniikki ja pituudeltaan vähintään 3 merkkiä
  - käyttäjätunnukseen täytyy liittyä salasana

- käyttäjä voi kirjautua järjestelmään
  - kirjautuminen onnistuu syötettäessä olemassaoleva käyttäjätunnus ja siihen liittyvä salasana kirjautumislomakkeelle
  - jos käyttäjää ei olemassa tai salasana väärä, ilmoittaa järjestelmä tästä

### Kirjautumisen jälkeen


- käyttäjä voi vastata kysymyksiin
- käyttäjä näkee oliko vastaus oikein vai väärin
- käyttäjä voi kirjautua ulos järjestelmästä
- käyttäjä näkee omia tilastoja
- käyttäjä voi lisätä uuden sanan
- käyttäjä näkee tietokannassa olevat sanat

## Jatkokehitysideoita

Perusversion jälkeen järjestelmää täydennetään ajan salliessa esim. seuraavilla toiminnallisuuksilla

- käyttäjä voi valita harjoiteltavan kielen
- sovellus näyttää vastauksen yhteydessä mahdolliset synonyymit
- käyttäjä näkee mitkä sanat osannut
- käyttäjätunnuksen poisto
- vastaamiseen aikarajoite
- pääkäyttäjä
  - näkee käyttäjistä yhditettyjä tilastoja
  - voi poistaa käyttäjiä
  
