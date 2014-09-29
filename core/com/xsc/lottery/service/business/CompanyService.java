/**
 * 
 */
package com.xsc.lottery.service.business;


import java.util.List;

import com.xsc.lottery.entity.business.Company;
import com.xsc.lottery.service.LotteryBaseService;

public interface CompanyService extends LotteryBaseService<Company>
{
    //得到所有的company
    public List<Company> getAllCompanyList();
    
    /**根据名字得到相应的company*/
    public Company getCompanyByName(String companyName);
}
