package net.needii.dto;

/**
 * @author longnguyen
 *
 */
public class BaseDto {
	private int status;
    private String message;
    private Object data;
    
    public BaseDto() {
    		this.setStatus(ResponseStatusEnum.SUCCESS);
    		this.setMessage(ResponseStatusEnum.SUCCESS);
    		this.setData(null);
    }
    
	public int getStatus() {
		return status;
	}
	public void setStatus(ResponseStatusEnum statusEnum) {
		this.status = statusEnum.getValue();
	}
	public String getMessage() {
		return this.message;
	}
	public void setMessage(ResponseStatusEnum statusEnum) {
		switch(statusEnum) {
		case SUCCESS:
			this.message = "Success";
			break;
		case MISSING_PARAMS:
			this.message = "Bad request";	
			break;
		case NOT_FOUND:
			this.message = "Not found";	
			break;
		case TOKEN_EXPIRED:
			this.message = "Token expired";	
			break;
		case UNAUTHORIZED:
			this.message = "Unauthorized";
			break;
		case DEVICE_NOT_FOUND:
			this.message = "Device not found";
			break;
		case FAIL:
			this.message = "Error";	
			break;
		default:
			this.message = "Not found";
			break;
		}
	}
	
	public void setMessageError(String message) {
		this.message = message;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

}
