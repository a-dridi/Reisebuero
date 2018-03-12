/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.adridi.reisebuero.validator;

import at.adridi.reisebuero.db.DAO;
import at.adridi.reisebuero.model.Benutzer;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Überprüft ob eine E-Mail schon in der DB vorhanden ist. Validator für
 * Registration
 *
 * @author adridi
 */
@FacesValidator(value = "emailValidator")
public class EmailValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        DAO dao = new DAO();

        if (value == null) {
            throw new ValidatorException(new FacesMessage("Bitte eine E-Mail eingeben!"));

        } else { //Email in DB checken
            String email = (String) value;

            List<Benutzer> benutzerListe = dao.getAllBenutzer();
            for (Benutzer benutzer : benutzerListe) {
                if (benutzer.getEmail().equals(email)) {
                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-Mail existiert schon!", " Bitte eine andere E-Mail eingeben!"));

                }
            }

        }

    }
}
