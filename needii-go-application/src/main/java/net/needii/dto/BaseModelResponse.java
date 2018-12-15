/**
 * 
 */
package net.needii.dto;

import java.util.List;

import net.needii.jpa.entity.Language;

/**
 * @author longnguyen
 *
 */
public interface BaseModelResponse {
	
	public List<?> mapToListResponse(List<?> baseEntities);
	
	public List<?> mapToListResponse(List<?> baseEntities, Long optionId);
	
	public List<?> mapToListResponse(List<?> baseEntities, Language language);
}
