package net.needii.jpa.entity;

public enum SocialTypeEnum {
	FACEBOOK(1), 
	GOOGLE(2),
	UNKNOW(3);

    private final int value;
    private SocialTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}