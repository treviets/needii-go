package net.needii.jpa.entity;

public enum PaymentStatusEnum {
	WAITING(0), 
	PROCESSING(1), 
	PAID(2),
	UNKNOW(3);

    private final int value;
    private PaymentStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}