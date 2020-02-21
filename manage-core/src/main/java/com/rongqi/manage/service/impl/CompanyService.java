package com.rongqi.manage.service.impl;

import com.rongqi.manage.mapper.CompanyDetailMapper;
import com.rongqi.manage.pojo.CompanyDetail;
import com.rongqi.manage.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenliang
 * @version 1.0.0
 * Modification  History:
 * Date         Author        Version        Description
 * ------------------------------------------------------
 * 2020-1-6   chenliang     1.0.0          create
 */
@Service
public class CompanyService implements ICompanyService {

    @Autowired
    private CompanyDetailMapper companyDetailMapper;

    @Override
    public CompanyDetail getCompanyDetail() {
        List<CompanyDetail> details = companyDetailMapper.getAllOpenCompanyDetail();
        return details.get(0);
    }
}
