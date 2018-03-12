/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.adridi.reisebuero.db;

import at.adridi.reisebuero.model.Benutzer;
import at.adridi.reisebuero.model.Reisetyp;
import at.adridi.reisebuero.model.Reiseveranstaltung;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

/**
 *
 * @author adridi
 */
public class DAO implements AutoCloseable {

    public DAO() {

    }

    /**
     * Speichern eines Benutzers in die DB
     *
     * @param b zum Speichern fertiges Benutzer-Objekt
     * @return true, wenn erfolgreiche Speicherung
     */
    public boolean insertBenutzer(Benutzer b) {

        Session s = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = null;
        boolean ret = false;
        try {

            tx = s.beginTransaction();
            s.save(b);

            tx.commit();
            ret = true;
        } catch (HibernateException ex) {
            System.out.println("Fehler in insertBenutzer: " + ex);
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            s.close();
        }

        return ret;
    }

    /**
     * Benutzers in der DB aktualisieren Benutzer loeschen und neuanlegen
     *
     * @param b zum Speichern fertiges Benutzer-Objekt
     * @return true, wenn erfolgreiche Speicherung
     */
    public boolean updateBenutzer(Benutzer b) {

        Session s = HibernateUtil.getSessionFactory().openSession();

        DAO dao = new DAO();
        Transaction tx = null;
        boolean ret = false;
        try {

            tx = s.beginTransaction();
            s.update(b);
            tx.commit();

            ret = true;
        } catch (HibernateException ex) {
            System.out.println("Fehler in updateBenutzer: " + ex);
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            s.close();
        }

        return ret;
    }

    /**
     * Speichern eines Reisetyps in die DB
     *
     * @param z zum Speichern fertiges Reisetyp-Objekt
     * @return true, wenn erfolgreiche Speicherung
     */
    public boolean insertReisetyp(Reisetyp z) {

        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        boolean ret = false;
        try {
            tx = s.beginTransaction();
            s.save(z);
            tx.commit();
            ret = true;
        } catch (HibernateException ex) {
            System.out.println("Fehler in insertReisetyp: " + ex);
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            s.close();
        }

        return ret;
    }

    /**
     * Speichern einer Reiseveranstaltung in die DB
     *
     * @param p zum Speichern fertiges Objekt vom Typ Reiseveranstaltung
     * @return true, wenn erfolgreiche Speicherung
     */
    public boolean insertReiseveranstaltung(Reiseveranstaltung p) {

        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        boolean ret = false;
        try {
            tx = s.beginTransaction();

            s.save(p);
            tx.commit();

            ret = true;
        } catch (HibernateException ex) {
            System.out.println("Fehler in insertReiseveranstaltung: " + ex);
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            s.close();
        }

        return ret;
    }

    /**
     * Löschen einer Reiseveranstaltung in der DB
     *
     * @param t das zu löschende Objekt vom Typ Reiseveranstaltung
     * @return true, wenn erfolgreicher Löschvorgang
     */
    public boolean deleteReiseveranstaltung(Reiseveranstaltung t) {

        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        boolean ret = false;
        try {
            tx = s.beginTransaction();
            s.delete(t);
            tx.commit();
            ret = true;
        } catch (HibernateException ex) {
            System.out.println("Fehler in deleteReiseveranstaltung: " + ex);
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            s.close();
        }

        return ret;
    }

    /**
     * Löschen eines Benutzers in der DB
     *
     * @param b das zu löschende Objekt vom Typ Benutzer
     * @return true, wenn erfolgreicher Löschvorgang
     */
    public boolean deleteBenutzer(Benutzer b) {

        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        boolean ret = false;
        try {
            tx = s.beginTransaction();
            s.delete(b);
            tx.commit();
            ret = true;
        } catch (HibernateException ex) {
            System.out.println("Fehler in deleteBenutzer: " + ex);
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            s.close();
        }

        return ret;
    }

    /**
     * Löschen eines Reisetyps in der DB
     *
     * @param r das zu löschende Objekt vom Typ Reisetyp
     * @return true, wenn erfolgreicher Löschvorgang
     */
    public boolean deleteReisetyp(Reisetyp r) {

        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        boolean ret = false;
        try {
            tx = s.beginTransaction();
            s.delete(r);
            tx.commit();
            ret = true;
        } catch (HibernateException ex) {
            System.out.println("Fehler in deleteReisetyp: " + ex);
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            s.close();
        }

        return ret;
    }

    //Abfragen
    /**
     * Liefert eine Liste aller Benutzer als List
     *
     *
     */
    public List<Benutzer> getAllBenutzer() {
        Session s = HibernateUtil.getSessionFactory().openSession();

        try {

            Query qu = s.createQuery("FROM Benutzer");
            return qu.list();

        } catch (Exception e) {
            System.out.println("Fehler in getAllBenutzer: " + e);

            return null;
        } finally {
            s.close();
        }

    }

    /**
     * Liefert eine Liste aller Reisetypen als List
     *
     *
     */
    public List<Reisetyp> getAllReisetyp() {
        Session s = HibernateUtil.getSessionFactory().openSession();

        try {

            Query qu = s.createQuery("FROM Reisetyp");
            return qu.list();

        } catch (Exception e) {
            System.out.println("Fehler in getAllReisetyp: " + e);

            return null;
        } finally {
            s.close();
        }

    }

    /**
     * Liefert eine Liste aller Reiseveranstaltungen als List
     *
     *
     */
    public List<Reiseveranstaltung> getAllReiseveranstaltung() {
        Session s = HibernateUtil.getSessionFactory().openSession();

        try {

            Query qu = s.createQuery("FROM Reiseveranstaltung");
            return qu.list();

        } catch (Exception e) {
            System.out.println("Fehler in getAllReiseveranstaltung: " + e);

            return null;
        } finally {
            s.close();
        }

    }

    /**
     * Liefert eine Liste der Interessen (Reisetyp) des übergebenen Benutzers
     *
     * @param email Die E-Mail-Adresse (ID) des Benutzers als String
     * @return Liste
     */
    public List<Reisetyp> getInteressenvonBenutzer(String email) {
        Session s = HibernateUtil.getSessionFactory().openSession();

        try {
            // Query qu = s.createQuery("FROM Reisetyp r JOIN r.interessenten i WHERE i.email = :benutzeremail");
            //qu.setString("benutzeremail", email);
            //                     return qu.list();

            return s.createQuery("interessen FROM Benutzer WHERE email = :benutzeremail").setString("benutzeremail", email).list();

        } catch (Exception e) {
            System.out.println("Fehler in getInteressenvonBenutzer: " + e);

            return null;
        } finally {
            s.close();
        }

    }

    /**
     * Liefert eine Liste die Veranstaltungen die den übergebenen Benutzers
     * interessieren. aufgrund seiner ausgewählten Reisetypen
     *
     * @param reisetyp Reisetyp für, welches Reiseveranstaltungen angezeigt
     * werden soll
     * @return Reiseveranstaltungen als Liste
     */
    public List<Reiseveranstaltung> getReiseveranstaltungvonReisetyp(Reisetyp reisetyp) {
        Session s = HibernateUtil.getSessionFactory().openSession();

        try {

            Query qu = s.createQuery("FROM Reiseveranstaltung WHERE reiseart = ?");
            qu.setParameter(0, reisetyp);

            return qu.list();

        } catch (Exception e) {
            System.out.println("Fehler in getReiseveranstaltungvonReisetyp: " + e);

            return null;
        } finally {
            s.close();
        }

    }

    /**
     * Profil updaten
     *
     * @param newsletter
     * @param benutzer
     *
     */
    public int updateNewsletter(boolean newsletter, String benutzer) {
        Session s = HibernateUtil.getSessionFactory().openSession();

        try {

            Query qu = s.createQuery("UPDATE Benutzer SET newsletter = :newsl  WHERE email = :emailBen");
            qu.setBoolean("newsl", newsletter);
            qu.setString("emailBen", benutzer);

            return qu.executeUpdate();

        } catch (Exception e) {
            System.out.println("Fehler in updateNewsletter: " + e);

            return 0;
        } finally {
            s.close();
        }
    }

    @Override
    public void close() {
        HibernateUtil.close();
    }

}
