/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Maurits
 */
@Entity
@Table(name = "KOERIERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Koeriers.findAll", query = "SELECT k FROM Koeriers k")
    , @NamedQuery(name = "Koeriers.findByKnr", query = "SELECT k FROM Koeriers k WHERE k.knr = :knr")})
public class Koeriers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "KNR")
    private Integer knr;
    @JoinColumn(name = "KNAAM", referencedColumnName = "GEBRUIKERSNAAM")
    @ManyToOne
    private Gebruikers knaam;
    @OneToMany(mappedBy = "knr")
    private Collection<Pakketen> pakketenCollection;

    public Koeriers() {
    }

    public Koeriers(Integer knr) {
        this.knr = knr;
    }

    public Integer getKnr() {
        return knr;
    }

    public void setKnr(Integer knr) {
        this.knr = knr;
    }

    public Gebruikers getKnaam() {
        return knaam;
    }

    public void setKnaam(Gebruikers knaam) {
        this.knaam = knaam;
    }

    @XmlTransient
    public Collection<Pakketen> getPakketenCollection() {
        return pakketenCollection;
    }

    public void setPakketenCollection(Collection<Pakketen> pakketenCollection) {
        this.pakketenCollection = pakketenCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (knr != null ? knr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Koeriers)) {
            return false;
        }
        Koeriers other = (Koeriers) object;
        if ((this.knr == null && other.knr != null) || (this.knr != null && !this.knr.equals(other.knr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Koeriers[ knr=" + knr + " ]";
    }
    
}
