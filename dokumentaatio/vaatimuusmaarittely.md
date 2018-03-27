# Alustava vaativuusmäärittely


## Sovelluksen tarkoitus

Sovelluksen avulla reisteröityneet käyttäjät voivat harjoitella kielten oikeinkirjoitusta. Kaikilla rekisteröityneillä käyttäjillä on oma harjoitelusivunsa. Aluksi suomi-engalanti sanakirja (kielen vaihto toiminnalissuus lisätään ajan salliessa) __!__

## Käyttäjät

Alkuvaiheessa sovelluksella on ainoastaan yksi käyttäjärooli eli __normaali käyttäjä__. Myöhemmin sovellukseen saatetaan lisätä suuremmilla oikeuksilla varustettu __pääkäyttäjä__.

## Käyttöliittymäluonnos

Sovellus koostuu viidestä eri näkymästä

<img src="https://github.com/SIholin/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/K%C3%A4ytt%C3%B6liittym%C3%A4t.jpg" width="750">

Sovellus aukeaa kirjautumisnäkymään, josta on mahdollista siirtyä uuden käyttäjän luomisnäkymään tai onnistuneen kirjautumisen yhteydessä kirjaantuneen käyttäjän harjoittelutilaan, josta päästään vastauksen oikeellisuudesta riippuen joko väärin tai oikein menneeseen tilaan.

## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista

- käyttäjä voi luoda järjestelmään käyttäjätunnuksen
  - käyttäjätunnuksen täytyy olla uniikki ja pituudeltaan vähintään 3 merkkiä
  - käyttäjätunnukseen täytyy liittyä salasana

- käyttäjä voi kirjautua järjestelmään
  - kirjautuminen onnistuu syötettäessä olemassaoleva käyttäjätunnus ja siihen liittyvä salasana kirjautumislomakkeelle
  - jos käyttäjää ei olemassa, ilmoittaa järjestelmä tästä

### Kirjautumisen jälkeen


- käyttäjä voi vastata kysymyksiin

- käyttäjä näkee oliko vastaus oikein vai väärin

- käyttäjä voi kirjautua ulos järjestelmästä

## Jatkokehitysideoita

Perusversion jälkeen järjestelmää täydennetään ajan salliessa esim. seuraavilla toiminnallisuuksilla

- käyttäjä voi valita harjoiteltavan kielen
- sovellus näyttää vastauksen yhteydessä mahdolliset synonyymit
- käyttäjä näkee tilaston kuinka hyvin osannut
- käyttäjä näkee mitkä sanat osannut ja mitä ei
- käyttäjätunnuksen (ja siihen liittyvien todojen) poisto
- vastaamiseen aikarajoite
- pääkäyttäjä
  - näkee käyttäjistä yhditettyjä tilastoja
  - voi poistaa käyttäjiä
  
