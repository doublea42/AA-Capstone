package PawDorableApp.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;
import java.util.regex.Pattern;

public class PawDorableServiceUtils {
    private static final Pattern INVALID_CHARACTER_PATTERN = Pattern.compile("[\"'\\\\]");
    private static final int ID_LENGTH = 5;

    public static String generateId() {
        UUID uuid = UUID.randomUUID();
        long mostSignificantBits = uuid.getMostSignificantBits();
        long leastSignificantBits = uuid.getLeastSignificantBits();
        long combinedBits = mostSignificantBits ^ leastSignificantBits;
        String id = Long.toString(combinedBits, 36).toUpperCase();
        return id.substring(Math.max(id.length() - ID_LENGTH, 0));
    }

    public static boolean isValidString(String stringToValidate) {
        if (StringUtils.isBlank(stringToValidate)) {
            return false;
        } else {
            return !INVALID_CHARACTER_PATTERN.matcher(stringToValidate).find();
        }
    }

    public static boolean validAge(int age){
        return age > 17 && age < 100;
    }

}
