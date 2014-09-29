/**
 * 
 */
package com.xsc.lottery.service.business;


import java.util.List;

import com.xsc.lottery.entity.business.Company;
import com.xsc.lottery.entity.business.Odd;
import com.xsc.lottery.service.LotteryBaseService;

public interface OddService extends LotteryBaseService<Odd>
{
	//根据matchId和pankouType的到相应的最后的odd
	public List<Odd> getOddLastListByMatchId(String matchId, String pankouType);
	
	//得到这场比赛这个公司type类型的所有odd
    public List<Odd> getOddListByMatchIdCompanyAndType(String matchId, Company company, String type, String max);
    
    //得到pankouId=1的odd
    public List<Odd> getOddFirstListByMatchId(String matchId, String pankouType);
    
    /**得到某场比赛的所有的odd*/
    public List<Odd> getAllOddList(String matchId, String pankouType);
}
