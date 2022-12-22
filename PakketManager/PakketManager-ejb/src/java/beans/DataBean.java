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
import java.util.Date;
import javax.persistence.NoResultException;

/**
 *
 * @author lucas
 */
@Stateless
public class DataBean implements DataBeanRemote{
    public static final String TRANSIT = "Transit";
    public static final String PROBLEEM = "Probleem";
    public static final String GELEVERD = "Geleverd";
    public static final int TRANSIT_STAT = 0;
    public static final int PROBLEEM_STAT = 1;
    public static final int GELEVERD_STAT = 2;
    public static final int AANTAL_STATUS = 3; //begint bij 0 tot aantal - 1
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public ArrayList<Object> getPakketen() {
        return new ArrayList<>(em.createQuery("Select p from Pakketen p").getResultList());
    }

    @Override
    public ArrayList<Object> getPakketen(int knr) {
        Query q = em.createQuery("select p from Pakketen p where p.knr = ?1");
        Object k = getKoerier(knr);
        if(k == null){
            return new ArrayList<>();
        }
        q.setParameter(1, k);
        return new ArrayList<>(q.getResultList());
    }

    @Override
    public Object getPakket(int pnr) {
        return em.find(Pakketen.class, pnr);
    }

    @Override
    public void setPakketStatus(Object pakket, int status) {
       Pakketen p = (Pakketen) pakket;
       int old = p.getPstatus();
       p.setPstatus(status);
       em.persist(pakket);
    }

    @Override
    public void setPakketStatus(int pnr, int status) {
        this.setPakketStatus(this.getPakket(pnr), status);
    }

    @Override
    public int getStatus(String status) {
        if(status.trim().toLowerCase().equals(GELEVERD.toLowerCase())){
            return GELEVERD_STAT;
        }else if(status.trim().toLowerCase().equals(PROBLEEM.toLowerCase())){
            return PROBLEEM_STAT;
        }else {
            return TRANSIT_STAT;
        }
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
    public Object addPakket(int pgewicht, int pstatus, String lnaam, String lstraat, int lnummer, int lpostcode, String lgemeente, int knr) {
        Pakketen p = new Pakketen();
        System.out.println(this.getMaxPakketNr());
        p.setPnr(this.getMaxPakketNr() + 1);
        p.setPgewicht(pgewicht);
        p.setPstatus(pstatus);
        p.setLnaam(lnaam);
        p.setLstraat(lstraat);
        p.setLnummer(lnummer);
        p.setLpostcode(lpostcode);
        p.setLgemeente(lgemeente);
        p.setBesteldatum(new Date());
        Koeriers k = (Koeriers)getKoerier(knr);
        if(k == null)
            throw new IllegalArgumentException("Koerier bestaat nier");
        p.setKnr(k);
        
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

    @Override
    public String getStatusNaam(int status) {
        switch(status){
            case GELEVERD_STAT: return GELEVERD;
            case PROBLEEM_STAT: return PROBLEEM;
            default: return TRANSIT;
        }
    }
    
    @Override
    public long getAantalPakketMetStatus(int status) {
        try{
            Query q = em.createQuery("select count(p) from Pakketen p where p.pstatus = ?1");
            q.setParameter(1, status);
            Object a = q.getSingleResult();
            if(a == null)
                return 0;
            else
                return (long)a;
        }catch(NoResultException e){
            return 0;
        }
    }

    @Override
    public int getAantalStatussen() {
        return AANTAL_STATUS;
    }
}
