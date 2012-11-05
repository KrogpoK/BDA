/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.entity.mediatheque.item;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author user
 */
@Entity
@Table(name = "OUVRAGE")
public class Ouvrage implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "IDOUVRAGE")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "OEUVRE")
    @OneToOne
    private Oeuvre oeuvre;
        
    @Column(name = "DATEARRIVEE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateArrivee;
        
    @Column(name = "DISPONIBILITE")
    private int disponibilite;
            
    @Column(name = "NBEMPRUNT")
    private int nbEmprunts;
                    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ouvrage)) {
            return false;
        }
        Ouvrage other = (Ouvrage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "enterprise.web_jpa_war.entity.mediatheque.item.Ouvrage[ id=" + id + " ]";
    }
    
}
