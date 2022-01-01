package io.github.kingschan1204.istock.module.maindata.repository;

import io.github.kingschan1204.istock.module.maindata.po.StockDividend;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Stock History Dividend  dao define
 * @author chenguoxiang
 * @create 2018-02-01 15:46
 **/
@Repository
public interface StockHisDividendRepository extends PagingAndSortingRepository<StockDividend,String> {



}
