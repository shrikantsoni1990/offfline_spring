package bootsample.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import model.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {

	@Query("from Account a  where a.name=:entName")
	Account getAccountByName(@Param("entName")String entName);

}
