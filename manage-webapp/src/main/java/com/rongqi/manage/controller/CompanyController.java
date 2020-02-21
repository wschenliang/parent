package com.rongqi.manage.controller;

import com.rongqi.manage.pojo.CompanyDetail;
import com.rongqi.manage.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenliang
 * @version 1.0.0
 * Modification  History:
 * Date         Author        Version        Description
 * ------------------------------------------------------
 * 2020-1-6   chenliang     1.0.0          create
 */
@RestController
@RequestMapping("company")
public class CompanyController {

    @Autowired
    private ICompanyService companyService;

    @GetMapping(value = "detail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CompanyDetail> getCompanyDetail(){
        CompanyDetail companyDetail = companyService.getCompanyDetail();
        return ResponseEntity.ok(companyDetail);
    }

}
