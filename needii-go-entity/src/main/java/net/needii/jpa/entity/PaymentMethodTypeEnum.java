package net.needii.jpa.entity;

public enum PaymentMethodTypeEnum {
	NEEDII_CASH(1), 
	CREDIT_CARD(2), 
	COD(3),
	UNKNOW(3);

    private final int value;
    private PaymentMethodTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}