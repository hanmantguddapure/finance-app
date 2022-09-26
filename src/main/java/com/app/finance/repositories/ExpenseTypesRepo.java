package com.app.finance.repositories;

import org.springframework.data.repository.CrudRepository;

import com.app.finance.entity.ExpenseTypes;

public interface ExpenseTypesRepo extends CrudRepository<ExpenseTypes, Long>{

}
