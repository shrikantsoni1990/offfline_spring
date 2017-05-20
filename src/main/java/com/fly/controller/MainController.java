package com.fly.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fly.bean.AccountBean;
import com.fly.bean.BalanceBean;
import com.fly.bean.Login;
import com.fly.bean.RegisterAccountResponse;
import com.fly.bean.TransactionBean;
import com.fly.bean.TransactionHistoryResponse;
import com.fly.bean.TransactionResponse;
import com.fly.exception.CustomException;
import com.fly.service.ServiceClass;

@RestController
@CrossOrigin
public class MainController {
	
	@Autowired
	private ServiceClass serviceTask;
	
	Long i=(long) 0;
	@RequestMapping(value="/Login" ,method=RequestMethod.POST, consumes="application/json") 
	public String allTasks(@RequestBody Login s){
		
		System.out.println("input :"+s);
		if("shrikant".equals(s.getPassword()) && "shri".equals(s.getUsername()))
		{
			return "true";
		}
		else 
		{
			return "false";
		}
	}
	
	@RequestMapping(value="/v1/create" ,method=RequestMethod.POST, consumes="application/json") 
	public RegisterAccountResponse createAccount(@RequestBody Map<String,Object> obj){
		
		
		System.out.println("input  create:"+obj.get("account"));
		ObjectMapper mapper= new ObjectMapper();
		AccountBean a=mapper.convertValue(obj.get("account"), AccountBean.class);
		RegisterAccountResponse accountResponse= new RegisterAccountResponse();
		try {
		
			a=serviceTask.craeteAccount(a);
			accountResponse.account=a;
		}
		catch (DataIntegrityViolationException e)
		{
			String[] arrayS= {"Name already taken."};
			RegisterAccountResponse.Error error= accountResponse.new Error();
			error.base=arrayS;
			accountResponse.error=error;
		}
		catch (Exception e) {
			String[] arrayS= {"error occured while creating"};
			RegisterAccountResponse.Error error= accountResponse.new Error();
			error.base=arrayS;
			accountResponse.error=error;
		}
		return accountResponse;
	}
	
	@RequestMapping(value="/v1/transfer" ,method=RequestMethod.POST, consumes="application/json") 
	public TransactionResponse performTrasaction(@RequestBody Map<String,Object> obj){
		
		
		System.out.println("input tx :"+obj.toString());
		ObjectMapper mapper= new ObjectMapper();
		TransactionBean transectionBean=mapper.convertValue(obj, TransactionBean.class);
		TransactionResponse transectionResponse= new TransactionResponse();
		try {
		
			BalanceBean bal=serviceTask.performTransection(transectionBean);
			transectionBean.setNewBalance(bal.getBalanceAmount());
			
			transectionResponse.transactionData=transectionBean;
		}
		catch (CustomException e) {
			String[] arrayS= {e.getMessage()};
			TransactionResponse.Error error= transectionResponse.new Error();
			error.base=arrayS;
			transectionResponse.error=error;
		}
		catch (Exception e) {
			String[] arrayS= {"Something not right...."};
			TransactionResponse.Error error= transectionResponse.new Error();
			error.base=arrayS;
			transectionResponse.error=error;
		}
		return transectionResponse;
	}
	@RequestMapping(value="/v1/getTrasaction" ,method=RequestMethod.POST, consumes="application/json") 
	public TransactionHistoryResponse getTx(@RequestBody Map<String,Object> obj){
		
		
		System.out.println("input tx his:"+obj.toString());
		TransactionHistoryResponse historyResponse=new TransactionHistoryResponse();
		try {
		
			List<TransactionBean> his=serviceTask.getTransection(obj.get("accountId").toString());
			historyResponse.transactiondata=his;
			
		}
		catch (CustomException e) {
			String[] arrayS= {e.getMessage()};
			TransactionHistoryResponse.Error error= historyResponse.new Error();
			error.base=arrayS;
			historyResponse.error=error;
		}
		catch (Exception e) {
			String[] arrayS= {"Something not right...."};
			TransactionHistoryResponse.Error error= historyResponse.new Error();
			error.base=arrayS;
			historyResponse.error=error;
		}
		return historyResponse;
	}
	
	@RequestMapping(value="/v1/getBalance" ,method=RequestMethod.POST, consumes="application/json") 
	public BalanceBean getBalance(@RequestBody Map<String,Object> obj){
		
		System.out.println("input :"+obj.get("accountId"));
		
		return serviceTask.getBalance(obj.get("accountId").toString());
	}
	
	
	@RequestMapping(value="/ping" ,method=RequestMethod.GET) 
	public String def(){
		
		System.out.println("input ping");
		RegisterAccountResponse accountResponse= new RegisterAccountResponse();
		accountResponse.account=new AccountBean("Test-shrikant");
		return "hi";

	}
		
}
