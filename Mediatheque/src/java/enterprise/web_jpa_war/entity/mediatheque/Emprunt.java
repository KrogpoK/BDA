/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.entity.mediatheque;

import enterprise.web_jpa_war.entity.mediatheque.item.Oeuvre;
import enterprise.web_jpa_war.entity.mediatheque.item.Ouvrage;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author user
 */
@Entity
@Table(name = "EMPRUNT")
public class Emprunt implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static double PENALITE_JOURNALIERE = 0.5;
    @Id
    @Column(name = "IEMPRUNT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JoinColumn(name = "ICOMPTE_ID")
    @OneToOne
    private Compte eCompte;
        
    @JoinColumn(name = "IOUVRAGE_ID")
    @OneToOne
    private Ouvrage eOuvrage;
            
    @Column(name = "DDEBUT_EMPRUNT")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDebutEmprunt;
                
    @Column(name = "DFIN_EMPRUNT")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFinEmprunt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Compte geteCompte() {
        return eCompte;
    }

    public void seteCompte(Compte eCompte) {
        this.eCompte = eCompte;
    }

    public Ouvrage geteOuvrage() {
        return eOuvrage;
    }

    public void seteOuvrage(Ouvrage eOuvrage) {
        this.eOuvrage = eOuvrage;
    }

    public Date getDateDebutEmprunt() {
        return dateDebutEmprunt;
    }

    public void setDateDebutEmprunt(Date dateDebutEmprunt) {
        this.dateDebutEmprunt = dateDebutEmprunt;
    }

    public Date getDateFinEmprunt() {
        return dateFinEmprunt;
    }

    public void setDateFinEmprunt(Date dateFinEmprunt) {
        this.dateFinEmprunt = dateFinEmprunt;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emprunt)) {
            return false;
        }
        Emprunt other = (Emprunt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "enterprise.web_jpa_war.entity.mediatheque.Emprunt[ id=" + id + " ]";
    }
    
}