/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.entity.mediatheque;

import enterprise.web_jpa_war.entity.mediatheque.item.Oeuvre;
import enterprise.web_jpa_war.facade.impl.MediaDS;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author isso
 */
@Entity
@Table(name = "critique")
public class Critique implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idCritique")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @JoinColumn(name = "idCompte")
    @OneToOne()
    private Compte compte;
    
    @JoinColumn(name = "idOeuvre")
    @ManyToOne ()
    private Oeuvre oeuvre; 
    
    @Column(name = "titreCritiqie")
    private String titreCritiqie;

    @Column(name = "description")
    private String description;
    
    @Column(name = "note")
    private String note;
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Compte getCompte() {
        return compte;
    }

    public Oeuvre getOeuvre() {
        return oeuvre;
    }

    public String getDescription() {
        return description;
    }

    public String getNote() {
        return note;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public void setOeuvre(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setTitreCritiqie(String titreCritiqie) {
        this.titreCritiqie = titreCritiqie;
    }

    public String getTitreCritiqie() {
        return titreCritiqie;
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
        if (!(object instanceof Critique)) {
            return false;
        }
        Critique other = (Critique) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "enterprise.web_jpa_war.entity.mediatheque.Critique[ id=" + id + " ]";
    }

    
}
