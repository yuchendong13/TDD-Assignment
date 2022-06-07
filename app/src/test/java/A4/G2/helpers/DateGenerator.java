package A4.G2.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateGenerator {

    public static Date getSampleDateOver16() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date sampleDate = null;
        try {
            sampleDate = sdf.parse("1970-07-26");
        }
        catch (ParseException ex) {
            ex.printStackTrace();
        }
        return sampleDate;
    }

    public static Date getSampleDateUnder16() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date sampleDate = null;
        try {
            sampleDate = sdf.parse("2020-07-26");
        }
        catch (ParseException ex) {
            ex.printStackTrace();
        }
        return sampleDate;
    }
}
