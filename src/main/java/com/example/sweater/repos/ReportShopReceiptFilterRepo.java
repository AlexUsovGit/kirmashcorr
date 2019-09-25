package com.example.sweater.repos;

import com.example.sweater.domain.ReportShopReceiptFilter;
import org.springframework.data.repository.CrudRepository;

public interface ReportShopReceiptFilterRepo extends CrudRepository<ReportShopReceiptFilter, Long> {
    ReportShopReceiptFilter findFirstByOrderById();

}
