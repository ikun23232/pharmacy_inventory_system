package com.kgc.feign;

import com.kgc.remote.CwInvoiceRemote;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "pharmacyFinance",contextId = "saleCwInvoiceFeign")
public interface CwInvoiceFeign extends CwInvoiceRemote {


}
