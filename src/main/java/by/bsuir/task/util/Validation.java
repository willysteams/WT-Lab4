package by.bsuir.task.util;

import java.util.Map;

public class Validation {

    private static final String USERNAME = "username";
    private static final String REFILL = "refill";
    private static final String ROOM_NUMBER = "roomNumber";
    private static final String ROOM_ID = "roomId";

    private static final String ID_PATTERN = "^([1-9]{1}[0-9]{0,10})$";
    private static final String USERNAME_PATTERN = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$";
    private static final String PRICE_PATTERN = "^([1-9]{1}[0-9]{0,8})$";
    private static final String ROOM_NUMBER_PATTERN = "^([0-9]{3})$";

    private String invalidData;

    private String definePattern(String type) {
        switch (type) {
            case USERNAME:
                return USERNAME_PATTERN;
            case REFILL:
                return PRICE_PATTERN;
            case ROOM_NUMBER:
                return ROOM_NUMBER_PATTERN;
            case ROOM_ID:
                return ID_PATTERN;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public boolean isValid(Map<String, String> inputData) {
        for (Map.Entry<String, String> entry : inputData.entrySet()) {
            String value = entry.getValue();
            if (value == null) return false;
            String key = entry.getKey();
            String pattern = definePattern(key);
            if (!value.matches(pattern)) {
                invalidData = key;
                return false;
            }
        }
        return true;
    }

    public String getInvalidData() {
        return invalidData;
    }
}
