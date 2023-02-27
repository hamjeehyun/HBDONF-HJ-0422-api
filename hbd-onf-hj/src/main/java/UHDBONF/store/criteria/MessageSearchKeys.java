package UHDBONF.store.criteria;

public enum MessageSearchKeys {
    TID("tid"),
    UID("uid"),
    USER_UID("userUid"),
    EXPOSE("expose");

    private final String value;

    MessageSearchKeys(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
