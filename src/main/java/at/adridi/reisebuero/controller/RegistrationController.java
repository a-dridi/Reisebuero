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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Führt Registrierungen durch
 *
 * @author adridi
 */
@Named(value = "registrationController")
@RequestScoped
public class RegistrationController implements Serializable {

    private String email;
    private String passwort1;
    private String passwort2;
    private List<Reisetyp> interessen = new ArrayList<>(0);
    private boolean newsletter = false;
    private List<Reisetyp> veranstaltungstypen = new ArrayList<>(0);

    /**
     * Alle in der DB verfügbaren Veranstaltungstypen initialisieren
     */
    public RegistrationController() {
        DAO dao = new DAO();
        veranstaltungstypen = dao.getAllReisetyp();

    }


    /**
     * Überprüft ob das Passwort 2mal eingegeben wurde und führt Registration
     * durch
     *
     * Überprüfung der Email-Addresse passiert in eigenen Validator
     *
     * @return String zur Login Seite
     */
    public String registrieren() {

        if (!this.passwort1.equals(this.passwort2)) { //Passwoerter nicht gleich
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwoerter sind unterschiedlich!!", "Bitte 2 Mal das gleiche Passwort eingeben."));

            return "registration.xhtml";
        } else {
            //Benutzer in die DB speichern
            DAO dao = new DAO();
            Benutzer ben1 = new Benutzer();
            ben1.setEmail(this.email);
            ben1.setPasswort(this.passwort1);
            ben1.setNewsletter(this.newsletter);

            for (int i = 0; i < this.interessen.size(); i++) {
                ben1.addInteressen(this.interessen.get(i));
            }

            if (dao.insertBenutzer(ben1)) //Erfolgreiches Speichern
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrierung war erfolgreich!", "Sie können sich nun einloggen"));
                System.out.println("*Neue Registration! - User: " + this.email);
                return "index.xhtml";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler beim Speichern!", "Bitte ihre Eingabe ueberprüfen"));
                System.out.println(" *** Fehler bei Registration! - ");
                return "registration.xhtml";
            }
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswort1() {
        return passwort1;
    }

    public void setPasswort1(String passwort1) {
        this.passwort1 = passwort1;
    }

    public String getPasswort2() {
        return passwort2;
    }

    public void setPasswort2(String passwort2) {
        this.passwort2 = passwort2;
    }

    public List<Reisetyp> getInteressen() {
        return interessen;
    }

    public void setInteressen(List<Reisetyp> interessen) {
        this.interessen = interessen;
    }

    public boolean isNewsletter() {
        return newsletter;
    }

    public void setNewsletter(boolean newsletter) {
        this.newsletter = newsletter;
    }

    public List<Reisetyp> getVeranstaltungstypen() {
        return veranstaltungstypen;
    }

    public void setVeranstaltungstypen(List<Reisetyp> veranstaltungstypen) {
        this.veranstaltungstypen = veranstaltungstypen;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.email);
        hash = 19 * hash + Objects.hashCode(this.passwort1);
        hash = 19 * hash + Objects.hashCode(this.passwort2);
        hash = 19 * hash + Objects.hashCode(this.interessen);
        hash = 19 * hash + (this.newsletter ? 1 : 0);
        hash = 19 * hash + Objects.hashCode(this.veranstaltungstypen);
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
        final RegistrationController other = (RegistrationController) obj;
        if (this.newsletter != other.newsletter) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.passwort1, other.passwort1)) {
            return false;
        }
        if (!Objects.equals(this.passwort2, other.passwort2)) {
            return false;
        }
        if (!Objects.equals(this.interessen, other.interessen)) {
            return false;
        }
        if (!Objects.equals(this.veranstaltungstypen, other.veranstaltungstypen)) {
            return false;
        }
        return true;
    }

}
