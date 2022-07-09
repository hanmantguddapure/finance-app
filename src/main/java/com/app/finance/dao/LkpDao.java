package com.app.finance.dao;

import com.app.finance.entity.LkpType;
import com.app.finance.entity.LkpValue;

public interface LkpDao {
	public LkpValue findByLkpValueAndLkpTypeName(String lkpValueName, LkpType lkpTypeName);

	public LkpType findByLkpTypeName(String lkpTypeName);
}
