package utils;

import java.util.Date;
import java.util.UUID;

public class ApplicationUtils {

    public static String generateNewUUID() {
        return UUID.randomUUID().toString();
    }

    public static String getDateFromSQLDate() {
        //SQL: 2002-02-02       DATE: 02.02.2002  02/02/2002
        java.util.Date date = new Date();
        Object toReturn = new java.sql.Timestamp(date.getTime());
        return toReturn.toString();
    }
}
