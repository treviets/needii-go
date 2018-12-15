package net.needii.jpa.entity;

public enum CardTypeEnum {
	VISA(1), 
	MASTERCARD(2), 
	AMERICAN_EXPRESS(3),
	DISCOVER(4),
	MAESTRO(5),
	JCB(6),
	OTHER(7);

    private final int value;
    private CardTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}