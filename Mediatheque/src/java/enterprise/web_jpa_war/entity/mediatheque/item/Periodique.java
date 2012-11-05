/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.entity.mediatheque.item;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author user
 */
@Entity
@Table(name = "PERIODIQUE")
public class Periodique extends Oeuvre implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String SUPPORT = "Periodique";
    @Column(name = "STHEME")
    private String theme;
    @Column(name = "STYPE_PERIODIQUE")
    private String type;
    @Column(name = "SPERIODICITE")
    private String periodicite;

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(String periodicite) {
        this.periodicite = periodicite;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Periodique)) {
            return false;
        }
        Periodique other = (Periodique) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "enterprise.web_jpa_war.entity.mediatheque.item.Periodique[ id=" + id + " ]";
    }
    
    
    @Override
    public String getStrType() {
        return SUPPORT;
    }
}
