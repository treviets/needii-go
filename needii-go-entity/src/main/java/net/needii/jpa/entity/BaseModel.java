/**
 * 
 */
package net.needii.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import util.Utils;


/**
 * @author kelvin
 *
 */
@MappedSuperclass
public class BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name="is_deleted")
	private boolean isDeleted;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	@CreationTimestamp
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	@UpdateTimestamp
	private Date updatedAt;
	
	@PrePersist
	protected void onCreate() {
		this.isDeleted = false;
	}
	
	public boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public String getCreatedAtFormatVN() {
		return Utils.getDatetimeFormatVN(createdAt);
	}
	
	public String getUpdatedAtFormatVN() {
		return Utils.getDatetimeFormatVN(updatedAt);
	}
}
