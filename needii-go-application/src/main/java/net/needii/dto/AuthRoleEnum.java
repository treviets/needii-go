package net.needii.dto;

public enum AuthRoleEnum {
    GUEST(0),
    CUSTOMER(1),
    NEEDIIGO(2),
    SUPPLIER(3);

    private final int value;
    AuthRoleEnum(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}