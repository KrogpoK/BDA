/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.util;

import enterprise.web_jpa_war.entity.mediatheque.item.Oeuvre;
import java.util.HashMap;

/**
 *
 * @author jeremiefabre
 */
public class DaoTool {

    public static String analyseParams(HashMap<String, String> mapParamsOeuvre, String variable) {
        StringBuilder retour = new StringBuilder();
        if (!("".equals(mapParamsOeuvre.get(Oeuvre.TITRE)))) {
            retour.append(variable+".titre like '%" + mapParamsOeuvre.get(Oeuvre.TITRE) + "%' AND ");
        }
        if (!("".equals(mapParamsOeuvre.get(Oeuvre.GENRE)))) {
            retour.append(variable+".genre = '" + mapParamsOeuvre.get(Oeuvre.GENRE) + "' AND ");
        }
        if (!("".equals(mapParamsOeuvre.get(Oeuvre.LANGUE)))) {
            retour.append(variable+".langue = '" + mapParamsOeuvre.get(Oeuvre.LANGUE) + "' AND ");
        }
        if (!("".equals(mapParamsOeuvre.get(Oeuvre.DATEPARUTION)))) {
            retour.append(variable+".dateParution ");
            retour.append((mapParamsOeuvre.get(Oeuvre.DATEPARUTIONINDICATEUR).equals("avant")) ? "<" : ">");
            retour.append("= '" + mapParamsOeuvre.get(Oeuvre.DATEPARUTION) + "' AND ");
        }
        return retour.toString();
    }
}
