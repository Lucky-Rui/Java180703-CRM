package com.situ.crm.vo;

public class LayUISelectMVO {
	private Integer id;
	private String name;
	private Integer status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "LayUISelectMVO [id=" + id + ", name=" + name + ", status=" + status + "]";
	}

}
