/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.adridi.reisebuero.converter;

import at.adridi.reisebuero.db.DAO;
import at.adridi.reisebuero.model.Reisetyp;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Konvertiert ein Reisetyp in einem String und umgekehrt (für
 * Registrationsformular)
 *
 * @author adridi
 */
@FacesConverter("interessenConverter")
public class InteressenConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value == null || value.trim().isEmpty()) {
            return null;

        } else {
            DAO dao = new DAO();
            List<Reisetyp> veranstaltungstypen = dao.getAllReisetyp();
            Reisetyp reisetypAusgewaehlt = null; //Vom User ausgewählter Reisetyp in Reisetyp-Obj konvertieren
            for (Reisetyp rt : veranstaltungstypen) {
                if (rt.getBezeichnung().equals(value)) {
                    reisetypAusgewaehlt = rt;
                }
            }
            return reisetypAusgewaehlt;
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";

        } else {

            return value.toString();
        }
    }

}
