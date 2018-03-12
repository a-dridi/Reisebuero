/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.adridi.reisebuero.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.Check;

/**
 *
 * @author adridi
 */
@Entity

//Die Beschreibung hat eine Zeichenlänge von max. 300 Zeichen und das Zielort von max. 30
@Check(constraints = "character_length(beschreibung) <= 300"
        + "and character_length(zielort) <=30"
)
@SequenceGenerator(name = "reiseveranstaltung_seq", sequenceName = "reiseveranstaltung_id_seq", allocationSize = 1)
public class Reiseveranstaltung implements Serializable {

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "reiseveranstaltung_per_id")
    private Reisetyp reiseart;

    @Id
    @GeneratedValue(generator = "reiseveranstaltung_seq")
    private int id;

    @Column(nullable = false, length = 30)
    private String zielort;

    private String beschreibung;

    @Column(nullable = false)
    private Date beginn;

    private Date ende;

    @Column(nullable = false)
    private Double preis;

    private String hyperlink;

    public Reiseveranstaltung() {
    }

    public Reisetyp getReiseart() {
        return reiseart;
    }

    public void setReiseart(Reisetyp reiseart) {
        this.reiseart = reiseart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZielort() {
        return zielort;
    }

    public void setZielort(String zielort) {
        this.zielort = zielort;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Date getBeginn() {
        return beginn;
    }

    public void setBeginn(Date beginn) {
        this.beginn = beginn;
    }

    public Date getEnde() {
        return ende;
    }

    public void setEnde(Date ende) {
        this.ende = ende;
    }

    public Double getPreis() {
        return preis;
    }

    public void setPreis(Double preis) {
        this.preis = preis;
    }

    public String getHyperlink() {
        return hyperlink;
    }

    public void setHyperlink(String hyperlink) {
        this.hyperlink = hyperlink;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.id;

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
        final Reiseveranstaltung other = (Reiseveranstaltung) obj;
        if (this.id != other.id) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return this.beschreibung;
    }
}
