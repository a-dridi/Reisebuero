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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author adridi
 */
@Entity
@SequenceGenerator(name = "reisetyp_seq", sequenceName = "reisetyp_id_seq", allocationSize = 1)
public class Reisetyp implements Serializable {

    @OneToMany(mappedBy = "reiseart")
    private Set<Reiseveranstaltung> reiseveranstaltungen = new HashSet<Reiseveranstaltung>(0);

    @ManyToMany(mappedBy = "interessen")
    private Set<Benutzer> interessenten = new HashSet<Benutzer>(0);

    @GeneratedValue(generator = "reisetyp_seq")
    @Id
    private Integer id;

    private String bezeichnung;

    public Reisetyp() {
    }

    public Set<Reiseveranstaltung> getReiseveranstaltungen() {
        return reiseveranstaltungen;
    }

    public void setReiseveranstaltungen(Set<Reiseveranstaltung> reiseveranstaltungen) {
        this.reiseveranstaltungen = reiseveranstaltungen;
    }

    public Set<Benutzer> getInteressenten() {
        return interessenten;
    }

    public void setInteressenten(Set<Benutzer> interessenten) {
        this.interessenten = interessenten;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
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
        final Reisetyp other = (Reisetyp) obj;
        if (!Objects.equals(this.bezeichnung, other.bezeichnung)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return this.bezeichnung;
    }

}
