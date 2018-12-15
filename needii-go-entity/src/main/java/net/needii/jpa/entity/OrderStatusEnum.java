package net.needii.jpa.entity;

public enum OrderStatusEnum {
	PENDING(0), 
	PROCESSING(1),
	COMPLETED(2),
	CANCEL(3),
	UNKNOW(4);

    private final int value;
    private OrderStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}