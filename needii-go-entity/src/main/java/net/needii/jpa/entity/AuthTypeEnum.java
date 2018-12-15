package net.needii.jpa.entity;

public enum AuthTypeEnum {
	NEEDII(0), 
	FACEBOOK(1),
	GOOGLE(2),
	TWITTER(3),
	OTHER(4);

    private final int value;
    AuthTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}