package net.needii.jpa.entity;

public enum OrderDetailStatusEnum {
	PENDING(0), 
	PROCESSING(1),
	PACKAGED(2),
	DELIVERING(3),
	COMPLETED(4),
	CANCEL(5),
	UNKNOW(6);

    private final int value;
    private OrderDetailStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}