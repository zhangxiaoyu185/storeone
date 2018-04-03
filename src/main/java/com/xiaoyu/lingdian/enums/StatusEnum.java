package com.xiaoyu.lingdian.enums;

public enum StatusEnum {

	ENABLE(1, "启用"),
	UNENABLE(0, "禁用");

    private Integer code;
	private String description;

	StatusEnum(Integer code, String description) {
	    this.code = code;
		this.description = description;
	}

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
