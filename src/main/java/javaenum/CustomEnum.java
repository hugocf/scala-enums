package javaenum;

public enum CustomEnum {
    FOO("This is a foo"), BAR("This is a bar"), BAZ("This is a baz");

    public final String custom;

    CustomEnum(String value) {
        this.custom = value;
    }
}
