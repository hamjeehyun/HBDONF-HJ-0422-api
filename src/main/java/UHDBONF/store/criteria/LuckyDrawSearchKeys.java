package UHDBONF.store.criteria;

public enum LuckyDrawSearchKeys {
    UID("uid"),
    USER_UID("userUid"),
    TID("tid"),
    EXPOSE("expose");

    private final String value;

    LuckyDrawSearchKeys(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
