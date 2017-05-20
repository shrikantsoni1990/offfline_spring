package com.fly.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the account database table.
 * 
 */
@Entity
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="account_id")
	private int accountId;

	@Column(name="active_flag")
	private int activeFlag;

	@Column(name="active_key")
	private String activeKey;

	@Column(name="last_updated_dt")
	private Timestamp lastUpdatedDt;

	@Column(name="memo_key")
	private String memoKey;

	private String name;

	@Column(name="ref_code")
	private String refCode;

	private String referrer;

	//bi-directional many-to-one association to BalanceRecord
	@OneToMany(mappedBy="account")
	private List<BalanceRecord> balanceRecords;

	//bi-directional many-to-one association to TransectionHistory
	@OneToMany(mappedBy="account1")
	private List<TransectionHistory> transectionHistories1;

	//bi-directional many-to-one association to TransectionHistory
	@OneToMany(mappedBy="account2")
	private List<TransectionHistory> transectionHistories2;

	public Account() {
	}

	public int getAccountId() {
		return this.accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getActiveKey() {
		return this.activeKey;
	}

	public void setActiveKey(String activeKey) {
		this.activeKey = activeKey;
	}

	public Timestamp getLastUpdatedDt() {
		return this.lastUpdatedDt;
	}

	public void setLastUpdatedDt(Timestamp lastUpdatedDt) {
		this.lastUpdatedDt = lastUpdatedDt;
	}

	public String getMemoKey() {
		return this.memoKey;
	}

	public void setMemoKey(String memoKey) {
		this.memoKey = memoKey;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRefCode() {
		return this.refCode;
	}

	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}

	public String getReferrer() {
		return this.referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	public List<BalanceRecord> getBalanceRecords() {
		return this.balanceRecords;
	}

	public void setBalanceRecords(List<BalanceRecord> balanceRecords) {
		this.balanceRecords = balanceRecords;
	}

	public BalanceRecord addBalanceRecord(BalanceRecord balanceRecord) {
		getBalanceRecords().add(balanceRecord);
		balanceRecord.setAccount(this);

		return balanceRecord;
	}

	public BalanceRecord removeBalanceRecord(BalanceRecord balanceRecord) {
		getBalanceRecords().remove(balanceRecord);
		balanceRecord.setAccount(null);

		return balanceRecord;
	}

	public List<TransectionHistory> getTransectionHistories1() {
		return this.transectionHistories1;
	}

	public void setTransectionHistories1(List<TransectionHistory> transectionHistories1) {
		this.transectionHistories1 = transectionHistories1;
	}

	public TransectionHistory addTransectionHistories1(TransectionHistory transectionHistories1) {
		getTransectionHistories1().add(transectionHistories1);
		transectionHistories1.setAccount1(this);

		return transectionHistories1;
	}

	public TransectionHistory removeTransectionHistories1(TransectionHistory transectionHistories1) {
		getTransectionHistories1().remove(transectionHistories1);
		transectionHistories1.setAccount1(null);

		return transectionHistories1;
	}

	public List<TransectionHistory> getTransectionHistories2() {
		return this.transectionHistories2;
	}

	public void setTransectionHistories2(List<TransectionHistory> transectionHistories2) {
		this.transectionHistories2 = transectionHistories2;
	}

	public TransectionHistory addTransectionHistories2(TransectionHistory transectionHistories2) {
		getTransectionHistories2().add(transectionHistories2);
		transectionHistories2.setAccount2(this);

		return transectionHistories2;
	}

	public TransectionHistory removeTransectionHistories2(TransectionHistory transectionHistories2) {
		getTransectionHistories2().remove(transectionHistories2);
		transectionHistories2.setAccount2(null);

		return transectionHistories2;
	}

}