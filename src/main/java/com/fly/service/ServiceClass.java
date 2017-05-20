package com.fly.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fly.bean.AccountBean;
import com.fly.bean.BalanceBean;
import com.fly.bean.TransactionBean;
import com.fly.dao.AccountRepository;
import com.fly.dao.BalanceRepository;
import com.fly.dao.TransectionRepository;
import com.fly.exception.CustomException;
import com.fly.model.Account;
import com.fly.model.BalanceRecord;
import com.fly.model.TransectionHistory;

@Service
public class ServiceClass {

	@Autowired 
	AccountRepository accountRepository;
	
	@Autowired 
	BalanceRepository balRepository;
	
	@Autowired 
	TransectionRepository transRepository;
	
	public AccountBean craeteAccount(AccountBean accountBean)
	{
		
		Account account = new Account();
		account.setActiveFlag((byte) 1);
		account.setActiveKey(accountBean.active_key);
		account.setMemoKey(accountBean.memo_key);
		account.setName(accountBean.name);
		account.setRefCode(accountBean.refcode);
		account.setReferrer(accountBean.referrer);
		Account accountn=accountRepository.save(account);
		BalanceRecord balanceRecord= new BalanceRecord();
		balanceRecord.setAccount(accountn);
		balanceRecord.setIsactive((byte) 1);
		balanceRecord.setRemainingBal(100);
		balRepository.save(balanceRecord);
		
		TransectionHistory history= new TransectionHistory();
		history.setAccount1(accountn);
		history.setAccount2(accountn);
		history.setDebitCredit("credit");
		history.setComments("welcome credit");
		
		transRepository.save(history);
		
		accountBean.acountId=(long) accountn.getAccountId();
		
		return accountBean;
		
	}

	public BalanceBean getBalance(String name) {
		
		BalanceBean balanceBean = new BalanceBean();
		try{
		Account account=accountRepository.getAccountByName(name);
		
		balanceBean.setBalanceAmount(account.getBalanceRecords().get(0).getRemainingBal());
		}catch(Exception e)
		{
			System.err.println("error occured"+e.toString());
		}
		return balanceBean;
	}

	@Transactional(rollbackOn= {CustomException.class,Exception.class})
	public BalanceBean performTransection(TransactionBean transactionBean) {
		Account destination;
		try {
			destination=accountRepository.getAccountByName(transactionBean.getDestination());
				
			if(null ==destination)
			{
				System.err.println("destination not found");
				throw new CustomException("destination not found");
			}
		} catch (Exception e) {
			System.err.println("destination not found");
			e.printStackTrace();
			throw new CustomException("Reciever not registered.");
		}
		
		Account source=accountRepository.getAccountByName(transactionBean.getSource());
		
		TransectionHistory historyS= new TransectionHistory();
		TransectionHistory historyD= new TransectionHistory();
		
		historyD.setAccount1(destination);
		historyD.setAccount2(source);
		historyD.setComments(transactionBean.getComment());
		historyD.setDebitCredit("credit");
		historyD.setIsactive(1);
		historyD.setTxAmount(transactionBean.getAmount());
		
		historyS.setAccount1(source);
		historyS.setAccount2(destination);
		historyS.setComments(transactionBean.getComment());
		historyS.setDebitCredit("Debit");
		historyS.setIsactive(1);
		historyS.setTxAmount(transactionBean.getAmount());
		transRepository.save(historyD);
		transRepository.save(historyS);
		
		
		BalanceRecord sourceBal=source.getBalanceRecords().get(0);
		
		Double newBal=sourceBal.getRemainingBal()-transactionBean.getAmount();
		
		if(newBal<0)
		{
			throw new CustomException("Insufficient Balance !!!");
		}
		sourceBal.setRemainingBal(newBal);
		balRepository.save(sourceBal);
		
		BalanceRecord desBal=destination.getBalanceRecords().get(0);
		
		Double newBalD =desBal.getRemainingBal()+transactionBean.getAmount();
		
		desBal.setRemainingBal(newBalD);
		balRepository.save(desBal);
		
		BalanceBean  balanceBean= new BalanceBean();
		balanceBean.setBalanceAmount(sourceBal.getRemainingBal());
		
		return balanceBean;
	}

	public List<TransactionBean> getTransection(String name) {
		
		List<TransactionBean> transactionBeans= new ArrayList<>();
		Account account=accountRepository.getAccountByName(name);
		List<TransectionHistory> accountHisList = account.getTransectionHistories1();
		if(null !=accountHisList && !accountHisList.isEmpty())
		{
			for (TransectionHistory transectionHistory : accountHisList) {
				TransactionBean bean=new TransactionBean();
				bean.setAmount(transectionHistory.getTxAmount());
				bean.setComment(transectionHistory.getComments());
				bean.setDate(transectionHistory.getTransectionDate().toString());
				bean.setCreditDebit(transectionHistory.getDebitCredit());
				bean.setToFrom(transectionHistory.getAccount2().getName());
				transactionBeans.add(bean);
			}
		}
		
		
		
		return  transactionBeans;
	}
	
}
