/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.entity.mediatheque;

import antlr.collections.List;
import enterprise.web_jpa_war.entity.mediatheque.item.Oeuvre;
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author isso
 */
@Entity
@Table(name = "bondecommande")

public class BonDeCommande implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idbondecommande")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JoinColumn(name = "idfournisseur")
    @OneToOne
    private Fournisseur fournisseur;
    
    @JoinColumn(name = "idOeuvre")
    @OneToMany
    private ArrayList<Oeuvre> oeuvre;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur idFournisseur) {
        this.fournisseur = idFournisseur;
    }

    public void setOeuvre(ArrayList<Oeuvre> oeuvre) {
        this.oeuvre = oeuvre;
    }

    public ArrayList<Oeuvre> getOeuvre() {
        return oeuvre;
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
        if (!(object instanceof BonDeCommande)) {
            return false;
        }
        BonDeCommande other = (BonDeCommande) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "enterprise.web_jpa_war.entity.mediatheque.BonDeCommande[ id=" + id + " ]";
    }
    
}
