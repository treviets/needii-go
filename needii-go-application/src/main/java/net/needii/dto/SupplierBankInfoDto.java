package net.needii.dto;

public class SupplierBankInfoDto{
	private static final long serialVersionUID = 1L;

	
	private int id;
	
	private String bankAccount;
	
	private String bankAccountName;
	
	private String bankName;
	
	private String bankBranch;
	
	private SupplierDto supplier;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public SupplierDto getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierDto supplier) {
		this.supplier = supplier;
	}
	
	
	
	
}