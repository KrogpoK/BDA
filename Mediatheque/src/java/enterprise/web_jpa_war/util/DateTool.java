/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author user
 */
public class DateTool {

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";

    public static Date parseDate(String date) {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            return df.parse(date);
        } catch (Exception ex) {
            return null;
        }
    }

    public static Date parseTime(String date) {
        try {
            DateFormat df = new SimpleDateFormat(TIME_FORMAT);
            return df.parse(date);
        } catch (ParseException ex) {
            return null;
        }
    }

    public static String printDate(Date date) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        if(date != null){
            return df.format(date);
        }
        else return null;
    }

    public static String printTime(Date date) {
        DateFormat df = new SimpleDateFormat(TIME_FORMAT);
        return df.format(date);
    }

    public static int dayToMillis() {
        return 24 * 3600 * 1000;
    }

    //Ne modifie pas la date passee en parametre
    public static Date ajouterJour(final Date d, int nbJours) {
        long toAdd = nbJours * DateTool.dayToMillis();
        return new Date(d.getTime() + toAdd);
    }

    public static int getDifference(final Date d1, final Date d2) {
        int rep;
        long diff = d1.getTime() - d2.getTime();
        rep = (int) (diff / dayToMillis());
        return Math.abs(rep);
    }
    
}
