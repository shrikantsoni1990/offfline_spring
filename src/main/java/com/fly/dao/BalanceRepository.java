package com.fly.dao;

import org.springframework.data.repository.CrudRepository;

import com.fly.model.BalanceRecord;


public interface BalanceRepository extends CrudRepository<BalanceRecord, Integer>{

}
