package net.needii.jpa.entity;

public enum GenderEnum {
	OTHER(0),
	MALE(1), 
	FEMALE(2);

    private final int value;
    private GenderEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}