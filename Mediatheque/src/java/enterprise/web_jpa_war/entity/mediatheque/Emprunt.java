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
import javax.persistence.Temporal;

/**
 *
 * @author user
 */
@Entity
public class Emprunt implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "IDEMPRUNT")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "ECOMPTE")
    @OneToOne
    private Compte eCompte;
        
    @JoinColumn(name = "EOUVRAGE")
    @OneToOne
    private Ouvrage eOuvrage;
            
    @Column(name = "DATEDEBUTEMPRUNT")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDebutEmprunt;
                
    @Column(name = "DATEFINEMPRUNT")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFinEmprunt;
                    
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