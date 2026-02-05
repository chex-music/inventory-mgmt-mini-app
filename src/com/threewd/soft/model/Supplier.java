package com.threewd.soft.model;

public class Supplier {

	private int supplierId;
	private String supplierName;
	private String contactNo;
	private String email;

	public Supplier() {
	}

	public Supplier(int supplierId, String supplierName, String contactNo, String email) {
		super();
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.contactNo = contactNo;
		this.email = email;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Supplier [supplierId=" + supplierId + ", supplierName=" + supplierName + ", contactNo=" + contactNo
				+ ", email=" + email + "]";
	}
	
}
