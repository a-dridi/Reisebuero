/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.adridi.reisebuero.controller;

import at.adridi.reisebuero.db.DAO;
import at.adridi.reisebuero.model.Reisetyp;
import at.adridi.reisebuero.model.Reiseveranstaltung;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 * Liste liefern mit Veranstaltungen für den eingeloggten Benutzer
 * initialisieren Für Menüpunkt Veranstaltung
 * @author adridi
 */
@Named(value = "veranstaltungenController")
@RequestScoped
public class VeranstaltungenController {

    private List<Reisetyp> benutzerReisetypen = new ArrayList<>(0);
    private List<Reiseveranstaltung> benutzerReiseveranstaltung = new ArrayList<>(0);

    @Inject
    private BenutzerController benutzerCon;

    /**
     * Reisetypen (DB-Abfrage) von Benutzer initialisieren
     */
    public VeranstaltungenController() {
        DAO dao = new DAO();
        FacesContext context = FacesContext.getCurrentInstance();
        String benutzer = context.getExternalContext().getSessionMap().get("user").toString();
        benutzerReisetypen = dao.getInteressenvonBenutzer(benutzer);

    }

    /**
     * Liefert Veranstaltungen zu einem Reisetyp (DB-Abfrage)
     *
     *
     * @return Liste
     */
    public List<Reiseveranstaltung> reiseveranstaltungList() {
        DAO dao = new DAO();
        Set<Reisetyp> benutzerRT = benutzerCon.getBenutzer().getInteressen();
        List<Reiseveranstaltung> veranstaltungen = new ArrayList<>();
        for (Reisetyp rt : benutzerRT) {
            veranstaltungen.addAll(dao.getReiseveranstaltungvonReisetyp(rt));
        }

        return veranstaltungen;

    }

    public List<Reisetyp> getBenutzerReisetypen() {
        return benutzerReisetypen;
    }

    public void setBenutzerReisetypen(List<Reisetyp> benutzerReisetypen) {
        this.benutzerReisetypen = benutzerReisetypen;
    }

    public BenutzerController getBenutzerCon() {
        return benutzerCon;
    }

    public void setBenutzerCon(BenutzerController benutzerCon) {
        this.benutzerCon = benutzerCon;
    }

    public List<Reiseveranstaltung> getBenutzerReiseveranstaltung() {

        return benutzerReiseveranstaltung;
    }

    public void setBenutzerReiseveranstaltung(List<Reiseveranstaltung> benutzerReiseveranstaltung) {
        this.benutzerReiseveranstaltung = benutzerReiseveranstaltung;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.benutzerReisetypen);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VeranstaltungenController other = (VeranstaltungenController) obj;
        if (!Objects.equals(this.benutzerReisetypen, other.benutzerReisetypen)) {
            return false;
        }
        return true;
    }

}
