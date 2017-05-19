package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the transection_history database table.
 * 
 */
@Entity
@Table(name="transection_history")
@NamedQuery(name="TransectionHistory.findAll", query="SELECT t FROM TransectionHistory t")
public class TransectionHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="trans_id")
	private int transId;

	private String comments;

	@Column(name="debit_credit")
	private String debitCredit;

	private int isactive;

	@Column(name="transection_date")
	private Timestamp transectionDate;

	@Column(name="tx_amount")
	private double txAmount;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account account1;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="to_from")
	private Account account2;

	public TransectionHistory() {
	}

	public int getTransId() {
		return this.transId;
	}

	public void setTransId(int transId) {
		this.transId = transId;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getDebitCredit() {
		return this.debitCredit;
	}

	public void setDebitCredit(String debitCredit) {
		this.debitCredit = debitCredit;
	}

	public int getIsactive() {
		return this.isactive;
	}

	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}

	public Timestamp getTransectionDate() {
		return this.transectionDate;
	}

	public void setTransectionDate(Timestamp transectionDate) {
		this.transectionDate = transectionDate;
	}

	public double getTxAmount() {
		return this.txAmount;
	}

	public void setTxAmount(double txAmount) {
		this.txAmount = txAmount;
	}

	public Account getAccount1() {
		return this.account1;
	}

	public void setAccount1(Account account1) {
		this.account1 = account1;
	}

	public Account getAccount2() {
		return this.account2;
	}

	public void setAccount2(Account account2) {
		this.account2 = account2;
	}

}