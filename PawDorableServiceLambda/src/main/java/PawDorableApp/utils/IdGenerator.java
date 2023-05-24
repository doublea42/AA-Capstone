package PawDorableApp.utils;

import java.util.UUID;

public class IdGenerator {
    private static final int ID_LENGTH = 5;

    public static String generateId() {
        UUID uuid = UUID.randomUUID();
        long mostSignificantBits = uuid.getMostSignificantBits();
        long leastSignificantBits = uuid.getLeastSignificantBits();
        long combinedBits = mostSignificantBits ^ leastSignificantBits;
        String id = Long.toString(combinedBits, 36).toUpperCase();
        return id.substring(Math.max(id.length() - ID_LENGTH, 0));
    }

}
