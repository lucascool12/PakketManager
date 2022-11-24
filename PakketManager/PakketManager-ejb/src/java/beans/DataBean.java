/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import entities.Koeriers;
import entities.Pakketen;
import javax.persistence.NoResultException;

/**
 *
 * @author lucas
 */
@Stateless
public class DataBean implements DataBeanRemote {
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public ArrayList<Object> getPakketen() {
        return new ArrayList<>(em.createQuery("Select p from Pakketen p").getResultList());
    }

    @Override
    public ArrayList<Object> getPakketen(int knr) {
        Query q = em.createQuery("select p from Pakketen p where p.knr = ?1");
        q.setParameter(1, knr);
        return new ArrayList<>(q.getResultList());
    }

    @Override
    public Object getPakket(int pnr) {
        return em.find(Pakketen.class, pnr);
    }

    @Override
    public void setPakketStatus(Object pakket, int status) {
       Pakketen p = (Pakketen) pakket;
       p.setPstatus(status);
       em.persist(pakket);
    }

    @Override
    public void setPakketStatus(int pnr, int status) {
        this.setPakketStatus(this.getPakket(pnr), status);
    }

    @Override
    public int getStatus(String status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getPakketStatus(Object pakket) {
        return ((Pakketen) pakket).getPstatus();
    }

    @Override
    public int getPakketStatus(int pnr) {
        return this.getPakketStatus(this.getPakket(pnr));
    }

    @Override
    public Object addPakket(int pgewicht, int pstatus, String lnaam, String lstraat, int lnummer, int lpostcode, String lgemeente) {
        Pakketen p = new Pakketen();
        p.setPnr(this.getMaxPakketNr());
        p.setPgewicht(pgewicht);
        p.setPstatus(pstatus);
        p.setLstraat(lstraat);
        p.setLnummer(lnummer);
        p.setLpostcode(lpostcode);
        p.setLgemeente(lgemeente);
        em.persist(p);
        return p;
    }

    @Override
    public void setPakketKoerier(int pnr, int knr) {
        this.setPakketKoerier(this.getPakket(pnr), this.getKoerier(knr));
    }

    @Override
    public int getPrimaryKey(Object ent) {
        if(ent instanceof Pakketen)
            return ((Pakketen) ent).getPnr();
        if(ent instanceof Koeriers)
            return ((Koeriers) ent).getKnr();
        throw(new IllegalArgumentException("ent is niet van classe Pakketen of Koeries"));
    }

    @Override
    public int getMaxPakketNr() {
        try{
            Object a = em.createQuery("Select max(p.pnr) From Pakketen p").getSingleResult();
            if(a == null)
                return 1;
            else
                return (Integer)a;
        }catch(NoResultException e){
            return 1;
        }
    }

    @Override
    public Object getKoerier(int knr) {
        return em.find(Koeriers.class, knr);
    }

    @Override
    public void setPakketKoerier(Object pakket, Object koerier) {
        Pakketen p = (Pakketen) pakket;
        p.setKnr((Koeriers) koerier);
        em.persist(p);
    }
    
    @Override
    public void printPakket(Object p){
        if(p instanceof Pakketen){
            Pakketen pak = ((Pakketen) p);
            System.out.println(
                    "---- " +
                    pak.getPnr() + "  " + pak.getPgewicht() + "  " + pak.getPstatus()  + "  " +
                            pak.getLnaam()  + "  " + pak.getLstraat()  + "  " + pak.getLnummer()
                     + "  " + pak.getLpostcode()  + "  " + pak.getLgemeente()
            );
        }
    }
}
