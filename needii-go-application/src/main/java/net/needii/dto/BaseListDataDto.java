/**
 * 
 */
package net.needii.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author longnguyen
 *
 */
public class BaseListDataDto {
	private List<?> data;
    private int limit;
    
    @JsonProperty("total_record")
    private long totalRecord;
    
    public BaseListDataDto() {}

	/**
	 * @return the data
	 */
	public List<?> getData() {
		return data;
	}

	/**
	 * @param list the data to set
	 */
	public void setData(List<?> list) {
		this.data = list;
	}

	/**
	 * @return the index
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * @param index the index to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * @return the totalRecord
	 */
	public long getTotalRecord() {
		return totalRecord;
	}

	/**
	 * @param totalRecord the totalRecord to set
	 */
	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
	}

}
