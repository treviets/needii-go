/**
 * 
 */
package net.needii.dto;

import java.util.ArrayList;
import java.util.List;

import net.needii.jpa.entity.Language;

/**
 * @author longnguyen
 *
 */
public class BaseModelResponseImpl implements BaseModelResponse {

	@Override
	public List<?> mapToListResponse(List<?> baseEntities) {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}
	
	@Override
	public List<?> mapToListResponse(List<?> baseEntities, Long optionId) {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

	@Override
	public List<?> mapToListResponse(List<?> baseEntities, Language language) {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}
	
	
}
