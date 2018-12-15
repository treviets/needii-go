package net.needii.jpa.entity;

public enum BannerTypeEnum {
	LINK(1), 
	CATEGORY(2), 
	PRODUCT(3), 
	SUPPLIER(4), 
	UNKNOW(5);

    private final int value;
    private BannerTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}