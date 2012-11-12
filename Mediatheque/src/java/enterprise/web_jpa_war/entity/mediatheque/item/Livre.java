/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.entity.mediatheque.item;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author user
 */
@Entity
@Table(name = "LIVRE")
public class Livre extends Oeuvre implements Serializable {

    public final static String AUTEUR = "auteur";
    public final static String EDITEUR = "editeur";
    
    private static final long serialVersionUID = 1L;
    public static final String SUPPORT = "Livre";
    @Column(name = "SAUTEUR")
    private String auteur;
    @Column(name = "SEDITEUR")
    private String editeur;

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Livre)) {
            return false;
        }
        Livre other = (Livre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "enterprise.web_jpa_war.entity.mediatheque.item.Livre[ id=" + id + " ]";
    }

    @Override
    public String getStrType() {
        return SUPPORT;
    }
}
