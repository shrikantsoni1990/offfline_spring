package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the balance_record database table.
 * 
 */
@Entity
@Table(name="balance_record")
@NamedQuery(name="BalanceRecord.findAll", query="SELECT b FROM BalanceRecord b")
public class BalanceRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bal_id")
	private int balId;

	private int isactive;

	@Column(name="remaining_bal")
	private double remainingBal;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account account;

	public BalanceRecord() {
	}

	public int getBalId() {
		return this.balId;
	}

	public void setBalId(int balId) {
		this.balId = balId;
	}

	public int getIsactive() {
		return this.isactive;
	}

	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}

	public double getRemainingBal() {
		return this.remainingBal;
	}

	public void setRemainingBal(double remainingBal) {
		this.remainingBal = remainingBal;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}