# OTM-harjoitustyö


__Dokumentaation__ alapuolelta löytyy __sovelluksen dokumentaatio__ _linkkien_ takana on __vaativuusmäärittely__, __työaikakirjanpito__, __arkkitehtuuri__ ja itse __sovellus__ **!** Sovellus on sanakirjaa jäljittelevä. Käyttäjä voi luoda käyttäjä tunnuksen ja kirjautua sisään, jonka jälkeen voi harjoitella suomi-englanti sanoja.


## Dokumentaatio

[Vaativuusmäärittely](https://github.com/SIholin/otm-harjoitustyo/blob/master/dokumentaatio/vaatimuusmaarittely.md)

[Työaikakirjanpito](https://github.com/SIholin/otm-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuuri](https://github.com/SIholin/otm-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Käyttöohje](https://github.com/SIholin/otm-harjoitustyo/blob/master/dokumentaatio/k%C3%A4ytt%C3%B6ohje.md)

[Testaus](https://github.com/SIholin/otm-harjoitustyo/blob/master/dokumentaatio/testaus.md)

[Sovellus](https://github.com/SIholin/otm-harjoitustyo/tree/master/Sanakirjatietokanta)

## Releaset

[viikko5](https://github.com/SIholin/otm-harjoitustyo/releases/tag/viikko5)

[viikko6](https://github.com/SIholin/otm-harjoitustyo/releases/tag/vko6)

[viimeinen release](https://github.com/SIholin/otm-harjoitustyo/releases/tag/Loppullinen)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_


### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/SIholin/otm-harjoitustyo/blob/master/Sanakirjatietokanta/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _Sanakirja-1.0-SNAPSHOT.jar_

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_


