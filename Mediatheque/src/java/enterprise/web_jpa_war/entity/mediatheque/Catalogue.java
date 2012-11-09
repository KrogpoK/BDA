/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.entity.mediatheque;

import enterprise.web_jpa_war.entity.mediatheque.Compte;
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
@Table(name = "Catalogue")
public class Catalogue implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idCatalogue")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

     
    @JoinColumn(name = "idOeuvre")
    @OneToMany
    private ArrayList<Compte> oeuvre;
    
    
    @JoinColumn(name = "nameCatalogue")
    private String nameCatalogue;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getNameCatalogue() {
        return nameCatalogue;
    }

    public ArrayList<Compte> getOeuvre() {
        return oeuvre;
    }

    public void setOeuvre(ArrayList<Compte> oeuvre) {
        this.oeuvre = oeuvre;
    }

    

    public void setNameCatalogue(String nameCatalogue) {
        this.nameCatalogue = nameCatalogue;
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
        if (!(object instanceof Catalogue)) {
            return false;
        }
        Catalogue other = (Catalogue) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "enterprise.web_jpa_war.entity.Catalogue[ id=" + id + " ]";
    }
    
}
