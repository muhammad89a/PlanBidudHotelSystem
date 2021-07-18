package planBidudHotel.utils;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static boolean isFutureDate(Date date) {
        return new Date().before(date);
    }

    public static boolean isValidEmailAddress(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    public static boolean isTextIsNumbers(String text) {
        return  (text.matches("[0-9]+") && text.length() > 0);
    }

    public static boolean isPhoneNumber(String number) {
        Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }
}
