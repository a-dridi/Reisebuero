/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.adridi.reisebuero.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.Check;

/**
 *
 * @author adridi
 */
@Entity
//Passwort hat eine Länge von min. 6 Zeichen
@Check(constraints = "character_length(passwort) >= 6")

public class Benutzer implements Serializable {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "benutzer_reisetyp")
    private Set<Reisetyp> interessen = new HashSet<Reisetyp>(0);

    @Id
    @Column(name = "email")
    private String email;

    //Passwort min. 6 Zeichen´
    @Column(name = "passwort")
    private String passwort;

    @Column(name = "newsletter")
    private Boolean newsletter;

    public Benutzer() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public Boolean getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(Boolean newsletter) {
        this.newsletter = newsletter;
    }

    /**
     * Fügt weitere Interessen zu (z.b.: für Registrierungsformular)
     *
     * @param reisetyp
     */
    public void addInteressen(Reisetyp reisetyp) {
        this.interessen.add(reisetyp);
    }

    public void setInteressen(Set<Reisetyp> interessen) {
        this.interessen = interessen;
    }

    public Set<Reisetyp> getInteressen() {
        return interessen;

    }
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.email);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Benutzer other = (Benutzer) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }

        return true;
    }

}
