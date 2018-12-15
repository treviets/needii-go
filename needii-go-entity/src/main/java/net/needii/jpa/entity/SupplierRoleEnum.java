package net.needii.jpa.entity;

public enum SupplierRoleEnum {
	ADMIN(1), 
	UNKNOW(2);

    private final int value;
    private SupplierRoleEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}