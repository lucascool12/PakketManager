/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucas
 */
@Entity
@Table(name = "BEDIENDES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bediendes.findAll", query = "SELECT b FROM Bediendes b")
    , @NamedQuery(name = "Bediendes.findByBnr", query = "SELECT b FROM Bediendes b WHERE b.bnr = :bnr")
    , @NamedQuery(name = "Bediendes.findByBnaam", query = "SELECT b FROM Bediendes b WHERE b.bnaam = :bnaam")
    , @NamedQuery(name = "Bediendes.findByPaswoord", query = "SELECT b FROM Bediendes b WHERE b.paswoord = :paswoord")})
public class Bediendes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "BNR")
    private Integer bnr;
    @Size(max = 30)
    @Column(name = "BNAAM")
    private String bnaam;
    @Size(max = 30)
    @Column(name = "PASWOORD")
    private String paswoord;

    public Bediendes() {
    }

    public Bediendes(Integer bnr) {
        this.bnr = bnr;
    }

    public Integer getBnr() {
        return bnr;
    }

    public void setBnr(Integer bnr) {
        this.bnr = bnr;
    }

    public String getBnaam() {
        return bnaam;
    }

    public void setBnaam(String bnaam) {
        this.bnaam = bnaam;
    }

    public String getPaswoord() {
        return paswoord;
    }

    public void setPaswoord(String paswoord) {
        this.paswoord = paswoord;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bnr != null ? bnr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bediendes)) {
            return false;
        }
        Bediendes other = (Bediendes) object;
        if ((this.bnr == null && other.bnr != null) || (this.bnr != null && !this.bnr.equals(other.bnr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Bediendes[ bnr=" + bnr + " ]";
    }
    
}
