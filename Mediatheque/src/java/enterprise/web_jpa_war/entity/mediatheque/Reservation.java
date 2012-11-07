/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.entity.mediatheque;

import enterprise.web_jpa_war.entity.mediatheque.item.Oeuvre;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author user
 */
@Entity
@Table(name = "RESERVATION")
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "IRESERVATION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "DDEBUT")
    @Temporal(TemporalType.DATE)
    private Date debut;
    @Column(name = "DDISPO")
    @Temporal(TemporalType.DATE)
    private Date dispo;
    @ManyToOne()
    @JoinColumn(name = "ICOMPTE_ID", nullable = false)
    @ForeignKey(name = "FK_RESA_COMPTE")
    private Compte compte;
    @ManyToOne()
    @JoinColumn(name = "IOeuvre_ID", nullable = false)
    @ForeignKey(name = "FK_RESA_OEUVRE")
    private Oeuvre oeuvre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getDispo() {
        return dispo;
    }

    public void setDispo(Date dispo) {
        this.dispo = dispo;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public Oeuvre getOeuvre() {
        return oeuvre;
    }

    public void setOeuvre(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
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
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "enterprise.web_jpa_war.entity.mediatheque.Reservation[ id=" + id + " ]";
    }
}
