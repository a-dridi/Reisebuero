/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.adridi.reisebuero.main;

import at.adridi.reisebuero.db.DAO;
import at.adridi.reisebuero.model.Benutzer;
import at.adridi.reisebuero.model.Reisetyp;
import at.adridi.reisebuero.model.Reiseveranstaltung;
import java.time.LocalDate;
import java.sql.Date;

/**
 *
 * @author adridi
 */
public class TestdatenSpeichern {

    /**
     * Erstelllen und Speichern von Testdaten in die DB
     * 
     * 
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DAO dao = new DAO();

        Reisetyp rt1 = new Reisetyp();
        rt1.setBezeichnung("Städtereisen");
        Reisetyp rt2 = new Reisetyp();
        rt2.setBezeichnung("Italien");
        Reisetyp rt3 = new Reisetyp();
        rt3.setBezeichnung("Last Minute");
        Reisetyp rt4 = new Reisetyp();
        rt4.setBezeichnung("Sportreisen");
        Reisetyp rt5 = new Reisetyp();
        rt5.setBezeichnung("Gruppenreisen");

        Benutzer ben1 = new Benutzer();
        ben1.setEmail("max.maier@gmail.com");
        ben1.setPasswort("198548");
        ben1.setNewsletter(Boolean.TRUE);
        ben1.addInteressen(rt1);
        ben1.addInteressen(rt2);

        Benutzer ben2 = new Benutzer();
        ben2.setEmail("johannes.apfeltahler@googlemail.com");
        ben2.setPasswort("1948548");
        ben2.setNewsletter(Boolean.TRUE);
        ben2.addInteressen(rt4);
        ben2.addInteressen(rt2);

        Benutzer ben3 = new Benutzer();
        ben3.setEmail("skennedyb@gravatar.com");
        ben3.setPasswort("323435352");
        ben3.setNewsletter(Boolean.TRUE);
        ben3.addInteressen(rt5);
        ben3.addInteressen(rt2);
        ben3.addInteressen(rt1);

        Benutzer ben4 = new Benutzer();
        ben4.setEmail("egrahamm@phoca.cz");
        ben4.setPasswort("ld_3235352");
        ben4.setNewsletter(Boolean.TRUE);
        ben4.addInteressen(rt4);
        ben4.addInteressen(rt1);

        Benutzer ben5 = new Benutzer();
        ben5.setEmail("hwardu@yahoo.de");
        ben5.setPasswort("gf_3725352");
        ben5.setNewsletter(Boolean.TRUE);
        ben5.addInteressen(rt4);
        ben5.addInteressen(rt1);

        //Reiseveranstaltungen:
        Reiseveranstaltung rv1 = new Reiseveranstaltung();
        rv1.setBeginn(Date.valueOf(LocalDate.parse("2010-12-12")));
        rv1.setBeschreibung("Rom Stadtreise");
        rv1.setEnde(Date.valueOf(LocalDate.parse("2010-12-24")));
        rv1.setHyperlink("www.Italien-Reise.at");
        rv1.setPreis(255.50);
        rv1.setReiseart(rt2);
        rv1.setZielort("Rom");

        Reiseveranstaltung rv2 = new Reiseveranstaltung();
        rv2.setBeginn(Date.valueOf(LocalDate.parse("2017-10-13")));
        rv2.setBeschreibung("Udine Stadtreise");
        rv2.setEnde(Date.valueOf(LocalDate.parse("2017-10-22")));
        rv2.setHyperlink("www.reisengmbh.at/Udine");
        rv2.setPreis(155.50);
        rv2.setReiseart(rt2);
        rv2.setZielort("Udine");

        Reiseveranstaltung rv3 = new Reiseveranstaltung();
        rv3.setBeginn(Date.valueOf(LocalDate.parse("2017-01-10")));
        rv3.setBeschreibung("New York All Inclusive 5*");
        rv3.setEnde(Date.valueOf(LocalDate.parse("2017-01-22")));
        rv3.setHyperlink("www.reisengmbh.at/New_York");
        rv3.setPreis(800.50);
        rv3.setReiseart(rt3);
        rv3.setZielort("New York");

        Reiseveranstaltung rv4 = new Reiseveranstaltung();
        rv4.setBeginn(Date.valueOf(LocalDate.parse("2017-06-10")));
        rv4.setBeschreibung("Südkorea 4* All Inclusive");
        rv4.setEnde(Date.valueOf(LocalDate.parse("2017-06-22")));
        rv4.setHyperlink("www.reisengmbh.at/Südkorea");
        rv4.setPreis(2100.50);
        rv4.setReiseart(rt5);
        rv4.setZielort("Seoul");

        Reiseveranstaltung rv5 = new Reiseveranstaltung();
        rv5.setBeginn(Date.valueOf(LocalDate.parse("2017-03-11")));
        rv5.setBeschreibung("Sydney");
        rv5.setEnde(Date.valueOf(LocalDate.parse("2017-03-20")));
        rv5.setHyperlink("www.reisengmbh.at/Sydney");
        rv5.setPreis(900.40);
        rv5.setReiseart(rt4);
        rv5.setZielort("Sydney");

        Reiseveranstaltung rv6 = new Reiseveranstaltung();
        rv6.setBeginn(Date.valueOf(LocalDate.parse("2017-09-11")));
        rv6.setBeschreibung("Malibu Hotel 5*");
        rv6.setEnde(Date.valueOf(LocalDate.parse("2017-09-24")));
        rv6.setHyperlink("www.reisengmbh.at/Malibu");
        rv6.setPreis(1150.90);
        rv6.setReiseart(rt1);
        rv6.setZielort("Los Angeles");

        System.out.println("******* Das Speichern der Testdaten in die DB wurde gestartet *******");

        //Alle Inserts:
        if (dao.insertReisetyp(rt1)) { //Erfolgreiche Speicherung
            System.out.println("Reisetyp 1 wurde erfolgreich gespeichert.");
        } else {
            System.out.println("!Fehler!: Reisetyp 1 konnte nicht gespeichert werden.");

        }

        if (dao.insertReisetyp(rt2)) {
            System.out.println("Reisetyp 2 wurde erfolgreich gespeichert.");
        } else {
            System.out.println("!Fehler!: Reisetyp 2 konnte nicht gespeichert werden.");

        }

        if (dao.insertReisetyp(rt3)) {
            System.out.println("Reisetyp 3 wurde erfolgreich gespeichert.");
        } else {
            System.out.println("!Fehler!: Reisetyp 3 konnte nicht gespeichert werden.");

        }

        if (dao.insertReisetyp(rt4)) {
            System.out.println("Reisetyp 4 wurde erfolgreich gespeichert.");
        } else {
            System.out.println("!Fehler!: Reisetyp 4 konnte nicht gespeichert werden.");

        }

        if (dao.insertReisetyp(rt5)) {
            System.out.println("Reisetyp 5 wurde erfolgreich gespeichert.");
        } else {
            System.out.println("!Fehler!: Reisetyp 5 konnte nicht gespeichert werden.");

        }

        if (dao.insertBenutzer(ben1)) {
            System.out.println("Benutzer 1 wurde erfolgreich gespeichert.");
        } else {
            System.out.println("!Fehler!: Benutzer 1 konnte nicht gespeichert werden.");

        }

        if (dao.insertBenutzer(ben2)) {
            System.out.println("Benutzer 2 wurde erfolgreich gespeichert.");
        } else {
            System.out.println("!Fehler!: Benutzer 2 konnte nicht gespeichert werden.");

        }

        if (dao.insertBenutzer(ben3)) {
            System.out.println("Benutzer 3 wurde erfolgreich gespeichert.");
        } else {
            System.out.println("!Fehler!: Benutzer 3 konnte nicht gespeichert werden.");

        }

        if (dao.insertBenutzer(ben4)) {
            System.out.println("Benutzer 4 wurde erfolgreich gespeichert.");
        } else {
            System.out.println("!Fehler!: Benutzer 4 konnte nicht gespeichert werden.");

        }

        if (dao.insertBenutzer(ben5)) {
            System.out.println("Benutzer 5 wurde erfolgreich gespeichert.");
        } else {
            System.out.println("!Fehler!: Benutzer 5 konnte nicht gespeichert werden.");

        }

        if (dao.insertReiseveranstaltung(rv1)) {
            System.out.println("Reiseveranstaltung 1 wurde erfolgreich gespeichert.");
        } else {
            System.out.println("!Fehler!: Reiseveranstaltung 1 konnte nicht gespeichert werden.");

        }

        if (dao.insertReiseveranstaltung(rv2)) {
            System.out.println("Reiseveranstaltung 2 wurde erfolgreich gespeichert.");
        } else {
            System.out.println("!Fehler!: Reiseveranstaltung 2 konnte nicht gespeichert werden.");

        }

        if (dao.insertReiseveranstaltung(rv3)) {
            System.out.println("Reiseveranstaltung 3 wurde erfolgreich gespeichert.");
        } else {
            System.out.println("!Fehler!: Reiseveranstaltung 3 konnte nicht gespeichert werden.");

        }
        if (dao.insertReiseveranstaltung(rv4)) {
            System.out.println("Reiseveranstaltung 4 wurde erfolgreich gespeichert.");
        } else {
            System.out.println("!Fehler!: Reiseveranstaltung 4 konnte nicht gespeichert werden.");

        }

        if (dao.insertReiseveranstaltung(rv5)) {
            System.out.println("Reiseveranstaltung 5 wurde erfolgreich gespeichert.");
        } else {
            System.out.println("!Fehler!: Reiseveranstaltung 5 konnte nicht gespeichert werden.");

        }

        if (dao.insertReiseveranstaltung(rv6)) {
            System.out.println("Reiseveranstaltung 6 wurde erfolgreich gespeichert.");
        } else {
            System.out.println("!Fehler!: Reiseveranstaltung 6 konnte nicht gespeichert werden.");

        }

        System.out.println("******* Das Speichern der Testdaten in die DB wurde abgeschlossen *******");
    }

}
