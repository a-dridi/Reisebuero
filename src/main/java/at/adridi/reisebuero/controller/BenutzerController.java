/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.adridi.reisebuero.controller;

import at.adridi.reisebuero.db.DAO;
import at.adridi.reisebuero.model.Benutzer;
import at.adridi.reisebuero.model.Reisetyp;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * Sessionverwaltung für Benutzer und stehlt Methoden zur Verfügung die dem
 * eingeloggten Benutzer betreffen
 *
 * Hier befindet sich die Logout-Methode und eine Methode zum Aktualisieren des
 * Benutzerprofils
 *
 * @author adridi
 */
@Named(value = "benutzerController")
@SessionScoped
public class BenutzerController implements Serializable {

    private Benutzer benutzer = null;

    private boolean newsletter = false;

    private Set<Reisetyp> benutzerReisetyp = new HashSet<>();
    private List<Reisetyp> veranstaltungstypen = new ArrayList<>(0);

    /**
     * Creates a new instance of BenutzerController
     */
    public BenutzerController() {
        DAO dao = new DAO();
        veranstaltungstypen = dao.getAllReisetyp();
    }


    /**
     * Ausloggen des Benutzers. Aktualisieren der Benutzerdaten Wird aufgerufen,
     * wenn der Logout-Button vom Benutzer getätigt wird. ODER: Beim Menüpunkt
     * Profil
     *
     * @return String Retourniert Loginseite als String
     */
    public String logout() {
        benutzer = null;
        HttpSession s = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        // Session holen und für ungültig erklären
        s.invalidate();
        return "index.xhtml";
    }

    /**
     * Profildaten aktualisieren (Newsletteranmeldung und Interessen des
     * Benutzers)
     *
     * @return String Link zur Startseite
     */
    public String updateProfil() {
        DAO dao = new DAO();
        FacesContext context = FacesContext.getCurrentInstance();

        //Name des eingeloggten Benutzes holen
        String benutzer = context.getExternalContext().getSessionMap().get("user").toString();

        Benutzer ben = this.benutzer;
        System.out.println("Benutzer " + ben.getEmail() + "hat sich eingeloggt.");

        //Newsletter flag und Interessen vom Benutzer aktualisieren
        for (Reisetyp rt : this.benutzerReisetyp) {
            ben.addInteressen(rt);
        }

        dao.updateNewsletter(newsletter, benutzer);

        if (dao.updateBenutzer(ben)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Profil aktualisiert!", "Ihr Profil wurde erfolgreich aktualisiert!"));

            return "index.xhtml";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fehler bei der Profilaktualisierung!", "Ihr Profil konnte nicht aktualisiert!"));

            return "index.xhtml";

        }

    }

    /**
     * Prüft ob ein gültiges Login besteht.
     *
     * @return true wenn ein Benutzer eingeloggt ist, sonst false
     *
     */
    public boolean isLoggedIn() {
        return benutzer != null;

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.benutzer);
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
        final BenutzerController other = (BenutzerController) obj;
        if (!Objects.equals(this.benutzer, other.benutzer)) {
            return false;
        }
        return true;
    }

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    public boolean isNewsletter() {
        return newsletter;
    }

    public void setNewsletter(boolean newsletter) {
        this.newsletter = newsletter;
    }

    public Set<Reisetyp> getBenutzerReisetyp() {
        return benutzerReisetyp;
    }

    public void setBenutzerReisetyp(Set<Reisetyp> benutzerReisetyp) {
        this.benutzerReisetyp = benutzerReisetyp;
    }

    public List<Reisetyp> getVeranstaltungstypen() {
        return veranstaltungstypen;
    }

    public void setVeranstaltungstypen(List<Reisetyp> veranstaltungstypen) {
        this.veranstaltungstypen = veranstaltungstypen;
    }

    @Override
    public String toString() {
        return "BenutzerController{" + "benutzer=" + benutzer + ", newsletter=" + newsletter + ", benutzerReisetyp=" + benutzerReisetyp + ", veranstaltungstypen=" + veranstaltungstypen + '}';
    }

}
