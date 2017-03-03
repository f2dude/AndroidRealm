package com.kristech.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mobileteam on 01/03/17.
 */

public class DateFormatter {

    private static String DATE_PATTERN = "dd/MM/yyyy";

    public static String convertDateToString(Date date){
        return new SimpleDateFormat(DATE_PATTERN).format(date);
    }
}
