package net.needii.dto;


public enum ResponseStatusEnum {
	SUCCESS(200),
	MISSING_PARAMS(400),
	NOT_FOUND(404),
	TOKEN_EXPIRED(410),
	UNAUTHORIZED(411),
	DEVICE_NOT_FOUND(412),
	FAIL(500);
	
	private final int value;
    private ResponseStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}