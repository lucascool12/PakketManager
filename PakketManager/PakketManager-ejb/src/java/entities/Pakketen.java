/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucas
 */
@Entity
@Table(name = "PAKKETEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pakketen.findAll", query = "SELECT p FROM Pakketen p")
    , @NamedQuery(name = "Pakketen.findByPnr", query = "SELECT p FROM Pakketen p WHERE p.pnr = :pnr")
    , @NamedQuery(name = "Pakketen.findByPgewicht", query = "SELECT p FROM Pakketen p WHERE p.pgewicht = :pgewicht")
    , @NamedQuery(name = "Pakketen.findByPstatus", query = "SELECT p FROM Pakketen p WHERE p.pstatus = :pstatus")
    , @NamedQuery(name = "Pakketen.findByLnaam", query = "SELECT p FROM Pakketen p WHERE p.lnaam = :lnaam")
    , @NamedQuery(name = "Pakketen.findByLstraat", query = "SELECT p FROM Pakketen p WHERE p.lstraat = :lstraat")
    , @NamedQuery(name = "Pakketen.findByLnummer", query = "SELECT p FROM Pakketen p WHERE p.lnummer = :lnummer")
    , @NamedQuery(name = "Pakketen.findByLpostcode", query = "SELECT p FROM Pakketen p WHERE p.lpostcode = :lpostcode")
    , @NamedQuery(name = "Pakketen.findByLgemeente", query = "SELECT p FROM Pakketen p WHERE p.lgemeente = :lgemeente")
    , @NamedQuery(name = "Pakketen.findByBesteldatum", query = "SELECT p FROM Pakketen p WHERE p.besteldatum = :besteldatum")})
public class Pakketen implements Serializable {

    @Size(max = 80)
    @Column(name = "PCOMMENTAAR")
    private String pcommentaar;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PNR")
    private Integer pnr;
    @Column(name = "PGEWICHT")
    private Integer pgewicht;
    @Column(name = "PSTATUS")
    private Integer pstatus;
    @Size(max = 30)
    @Column(name = "LNAAM")
    private String lnaam;
    @Size(max = 30)
    @Column(name = "LSTRAAT")
    private String lstraat;
    @Column(name = "LNUMMER")
    private Integer lnummer;
    @Column(name = "LPOSTCODE")
    private Integer lpostcode;
    @Size(max = 30)
    @Column(name = "LGEMEENTE")
    private String lgemeente;
    @Column(name = "BESTELDATUM")
    @Temporal(TemporalType.DATE)
    private Date besteldatum;
    @JoinColumn(name = "KNR", referencedColumnName = "KNR")
    @ManyToOne
    private Koeriers knr;

    public Pakketen() {
    }

    public Pakketen(Integer pnr) {
        this.pnr = pnr;
    }

    public Integer getPnr() {
        return pnr;
    }

    public void setPnr(Integer pnr) {
        this.pnr = pnr;
    }

    public Integer getPgewicht() {
        return pgewicht;
    }

    public void setPgewicht(Integer pgewicht) {
        this.pgewicht = pgewicht;
    }

    public Integer getPstatus() {
        return pstatus;
    }

    public void setPstatus(Integer pstatus) {
        this.pstatus = pstatus;
    }

    public String getLnaam() {
        return lnaam;
    }

    public void setLnaam(String lnaam) {
        this.lnaam = lnaam;
    }

    public String getLstraat() {
        return lstraat;
    }

    public void setLstraat(String lstraat) {
        this.lstraat = lstraat;
    }

    public Integer getLnummer() {
        return lnummer;
    }

    public void setLnummer(Integer lnummer) {
        this.lnummer = lnummer;
    }

    public Integer getLpostcode() {
        return lpostcode;
    }

    public void setLpostcode(Integer lpostcode) {
        this.lpostcode = lpostcode;
    }

    public String getLgemeente() {
        return lgemeente;
    }

    public void setLgemeente(String lgemeente) {
        this.lgemeente = lgemeente;
    }

    public Date getBesteldatum() {
        return besteldatum;
    }

    public void setBesteldatum(Date besteldatum) {
        this.besteldatum = besteldatum;
    }

    public Koeriers getKnr() {
        return knr;
    }

    public void setKnr(Koeriers knr) {
        this.knr = knr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pnr != null ? pnr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pakketen)) {
            return false;
        }
        Pakketen other = (Pakketen) object;
        if ((this.pnr == null && other.pnr != null) || (this.pnr != null && !this.pnr.equals(other.pnr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Pakketen[ pnr=" + pnr + " ]";
    }

    public String getPcommentaar() {
        return pcommentaar;
    }

    public void setPcommentaar(String pcommentaar) {
        this.pcommentaar = pcommentaar;
    }
    
}
