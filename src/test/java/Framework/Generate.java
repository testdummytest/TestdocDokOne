package Framework;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

public class Generate {

    public static String date(long minusYears) {
        String givenDate = LocalDate.now(ZoneOffset.UTC).minusYears(minusYears).toString();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-mm-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-mm-yyyy");

        Date date = null;
        try {
            date = inputFormat.parse(givenDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String requiredDate = outputFormat.format(date);

        return requiredDate;
    }

    public static String date(long plusDays, long minusYears) {
        String givenDate = LocalDate.now(ZoneOffset.UTC).plusDays(plusDays).minusYears(minusYears).toString();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-mm-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-mm-yyyy");

        Date date = null;
        try {
            date = inputFormat.parse(givenDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String requiredDate = outputFormat.format(date);

        return requiredDate;
    }

    public static String dateForAppointment(long plusDays) {
        String givenDate = LocalDate.now(ZoneOffset.UTC).plusDays(plusDays).toString();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-mm-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-mm-yyyy");

        Date date = null;
        try {
            date = inputFormat.parse(givenDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String requiredDate = outputFormat.format(date);

        return requiredDate;
    }

    public static String todayDate () {
        String givenDate = LocalDate.now(ZoneOffset.UTC).toString();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-mm-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-mm-yyyy");

        Date date = null;
        try {
            date = inputFormat.parse(givenDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String requiredDate = outputFormat.format(date);

        return requiredDate;
    }
}
