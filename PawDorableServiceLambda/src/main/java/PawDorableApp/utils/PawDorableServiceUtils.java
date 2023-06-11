package PawDorableApp.utils;

import PawDorableApp.models.Gender;
import PawDorableApp.models.KindOfPet;
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

    public static boolean invalidString(String stringToValidate) {
        if (StringUtils.isBlank(stringToValidate)) {
            return true;
        } else {
            return INVALID_CHARACTER_PATTERN.matcher(stringToValidate).find();
        }
    }

    public static boolean invalidAge(int age){
        return age <= 17 || age >= 100;
    }

    public static KindOfPet petEnum(String UnknownPet){
        if(UnknownPet.isEmpty()){
            return null;
        }
        if (KindOfPet.CAT.toString().equalsIgnoreCase(UnknownPet)){
            return KindOfPet.CAT;
        }
        else if(KindOfPet.DOG.toString().equalsIgnoreCase(UnknownPet)){
            return KindOfPet.DOG;}
        else{
            return null;
        }
    }

    public static Gender genderEnum(String unknownGender){
        if(unknownGender.isEmpty()){
            return null;
        }
        if (Gender.MALE.toString().equalsIgnoreCase(unknownGender)){
            return Gender.MALE;
        }
        else if(Gender.FEMALE.toString().equalsIgnoreCase(unknownGender)){
            return Gender.FEMALE;}
        else{
            return null;
        }
    }

    public static boolean invalidScore (int score){
        return score <= 0 || score > 5;
    }

}
