package org.jwxa.mybatis.subtable.entity;

public class Device extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private String imei;

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}
}
