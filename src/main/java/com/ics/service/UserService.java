package com.ics.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ics.mapper.UserMapper;
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    public String getMyCustId(Map paramMap){
        String ls=userMapper.getMyCustId(paramMap);
        return ls;
    }
    public String getMyClassInfo(Map paramMap){
        String ls=userMapper.getMyClassInfo(paramMap);
        return ls;
    }
    public List getMyIncome(Map paramMap){
        List ls=userMapper.getMyIncome(paramMap);
        return ls;
    }
    public List getMyIncomePower(Map paramMap){
        List ls=userMapper.getMyIncomePower(paramMap);
        return ls;
    }
    
    public List getOtherIncomePower(Map paramMap){
        List ls=userMapper.getOtherIncomePower(paramMap);
        return ls;
    }
    public List getIncomeStockRanking(Map paramMap){
        List ls=userMapper.getIncomeStockRanking(paramMap);
        return ls;
    }
    public List getIncomeIndustryRanking(Map paramMap){
        List ls=userMapper.getIncomeIndustryRanking(paramMap);
        return ls;
    }
    public List getMyIncomeTrend(Map paramMap){
        List ls=userMapper.getMyIncomeTrend(paramMap);
        return ls;
    }
    public List getOtherIncomeTrend(Map paramMap){
        List ls=userMapper.getOtherIncomeTrend(paramMap);
        return ls;
    }
    
    public List getMyIncomeSource(Map paramMap){
        List ls=userMapper.getMyIncomeSource(paramMap);
        return ls;
    }
    public List getOtherIncomeSource(Map paramMap){
        List ls=userMapper.getOtherIncomeSource(paramMap);
        return ls;
    }
    
    public List getMyInvestmentAbility(Map paramMap){
        List ls=userMapper.getMyInvestmentAbility(paramMap);
        return ls;
    }
    public List getOtherInvestmentAbility(Map paramMap){
        List ls=userMapper.getOtherInvestmentAbility(paramMap);
        return ls;
    }
    
    public List getMyRiskControl(Map paramMap){
        List ls=userMapper.getMyRiskControl(paramMap);
        return ls;
    }
    public List getOtherRiskControl(Map paramMap){
        List ls=userMapper.getOtherRiskControl(paramMap);
        return ls;
    }
    
    public List getMyFluctuationRatio(Map paramMap){
        List ls=userMapper.getMyFluctuationRatio(paramMap);
        return ls;
    }
    public List getOtherFluctuationRatio(Map paramMap){
        List ls=userMapper.getOtherFluctuationRatio(paramMap);
        return ls;
    }
    public List getMyMaxDrawdown(Map paramMap){
        List ls=userMapper.getMyMaxDrawdown(paramMap);
        return ls;
    }
    public List getOterMaxDrawdown(Map paramMap){
        List ls=userMapper.getOterMaxDrawdown(paramMap);
        return ls;
    }
    public List getInvestmentPreference(Map paramMap){
        List ls=userMapper.getInvestmentPreference(paramMap);
        return ls;
    }
    
    public List getStylePreference(Map paramMap){
        List ls=userMapper.getStylePreference(paramMap);
        return ls;
    }
    public List getInfoByKey(Map paramMap){
        List ls=userMapper.getInfoByKey(paramMap);
        return ls;
    }
    
    /****** PC指标 ******/
    public List getStockRatio(Map paramMap){
        List ls=userMapper.getStockRatio(paramMap);
        return ls;
    }
    
    public List getPcData(Map paramMap){
        List ls=userMapper.getPcData(paramMap);
        return ls;
    }   
  
    public List getMyIncomePC(Map paramMap){
        List ls=userMapper.getMyIncomePC(paramMap);
        return ls;
    }
    public List getOtherIncomePowerPC(Map paramMap){
        List ls=userMapper.getOtherIncomePowerPC(paramMap);
        return ls;
    }
    public List getMyIncomePowerPC(Map paramMap){
        List ls=userMapper.getMyIncomePowerPC(paramMap);
        return ls;
    }
    public List getOtherIncomeTrendPC(Map paramMap){
        List ls=userMapper.getOtherIncomeTrendPC(paramMap);
        return ls;
    }
    public List getMyIncomeSourcePC(Map paramMap){
        List ls=userMapper.getMyIncomeSourcePC(paramMap);
        return ls;
    }
    public List getOtherIncomeSourcePC(Map paramMap){
        List ls=userMapper.getOtherIncomeSourcePC(paramMap);
        return ls;
    }
    public List getInvestAbilityPC(Map paramMap){
        List ls=userMapper.getInvestAbilityPC(paramMap);
        return ls;
    } 
    public List getInvestAbilityAvgPC(Map paramMap){
        List ls=userMapper.getInvestAbilityAvgPC(paramMap);
        return ls;
    }  
    public List getRiskProfitRatioPC(Map paramMap){
        List ls=userMapper.getRiskProfitRatioPC(paramMap);
        return ls;
    } 
    public List getRiskProfitRatioAvgPC(Map paramMap){
        List ls=userMapper.getRiskProfitRatioAvgPC(paramMap);
        return ls;
    }   
    public List getUserList(Map paramMap){
        List ls=userMapper.getUserList(paramMap);
        return ls;
    }  
    
    public List getProfit_num(Map paramMap){
        List ls=userMapper.getProfit_num(paramMap);
        return ls;
    }  
    public List getDeficit_num(Map paramMap){
    	List ls=userMapper.getDeficit_num(paramMap);
        return ls;
    }  
    public List getProfit_balance(Map paramMap){
    	List ls=userMapper.getProfit_balance(paramMap);
        return ls;
    }  
    public List getDeficit_balance(Map paramMap){
    	List ls=userMapper.getDeficit_balance(paramMap);
        return ls;
    }  
    
    
    public List getInduList(Map paramMap){
        List ls=userMapper.getInduList(paramMap);
        return ls;
    } 
    public List getEarningPower(Map paramMap){
        List ls=userMapper.getEarningPower(paramMap);
        return ls;
    }  
    public List getUserListNum(Map paramMap){
    	List ls=userMapper.getUserListNum(paramMap);
        return ls;
    }  
    

}
