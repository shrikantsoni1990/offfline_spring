package bootsample.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import model.Account;

@Repository

@Transactional
public class AccountDao {

	private final AccountRepository taskRepository;

	public AccountDao(AccountRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	public List<Account> findAll(){
		List<Account> tasks = new ArrayList<>();
		for(Account task : taskRepository.findAll()){
			tasks.add(task);
		}
		return tasks;
	}
	
	public Account findTask(int id){
		return taskRepository.findOne(id);
	}
	
	public void save(Account task){
		taskRepository.save(task);
	}
	
	public void delete(int id){
		taskRepository.delete(id);
	}
}
