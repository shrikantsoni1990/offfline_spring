package bootsample.dao;

import org.springframework.data.repository.CrudRepository;

import model.BalanceRecord;


public interface BalanceRepository extends CrudRepository<BalanceRecord, Integer>{

}
