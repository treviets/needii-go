/**
 * 
 */
package net.needii.dto.request;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * @author longnguyen
 *
 */
public class SaveFavoriteCategoryRequest extends BaseRequest {
	
	@JsonProperty("category_ids")
	private List<Integer> categoryIds = new ArrayList<Integer>();

	public List<Integer> getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(List<Integer> categoryIds) {
		this.categoryIds = categoryIds;
	}
}
