package com.ics.mapper;

import java.util.List;
import java.util.Map;

public interface UserMapper {
	//获取我的cust_id
	public String getMyCustId(Map paramMap);
	//我的类别
	public String getMyClassInfo(Map paramMap);
    //我的收益
	public List getMyIncome(Map paramMap);
	//我的收益能力
	public List getMyIncomePower(Map paramMap);
	//同类和广发收益能力
	public List getOtherIncomePower(Map paramMap);
	//收益排行前三(股票)
	public List getIncomeStockRanking(Map paramMap);
	//收益排行前三（行业）
	public List getIncomeIndustryRanking(Map paramMap);
	//我的收益走势
	public List getMyIncomeTrend(Map paramMap);
	//其他收益走势
	public List getOtherIncomeTrend(Map paramMap);
	//我的收益来源
	public List getMyIncomeSource(Map paramMap);
	//其他收益来源
	public List getOtherIncomeSource(Map paramMap);
	//我的投资能力
	public List getMyInvestmentAbility(Map paramMap);
	//其他投资能力
	public List getOtherInvestmentAbility(Map paramMap);
	//我的风控能力
	public List getMyRiskControl(Map paramMap);
	//其他风控能力
	public List getOtherRiskControl(Map paramMap);
	//我的波动率
	public List getMyFluctuationRatio(Map paramMap);
	//其他波动率
	public List getOtherFluctuationRatio(Map paramMap);
	//我的最大回撤
	public List getMyMaxDrawdown(Map paramMap);
	//其他最大回撤
	public List getOterMaxDrawdown(Map paramMap);
	//投资偏好
	public List getInvestmentPreference(Map paramMap);
	//风格偏好
	public List getStylePreference(Map paramMap);
	//客户信息整合
	public List getInfoByKey(Map paramMap);
	
	/******
	 *  PC指标
	 * *****/
	//股票仓位
	public List getStockRatio(Map paramMap);
	//PC整合数据
	public List getPcData(Map paramMap);
	
	//PC客户综合能力
	public List getMyIncomePC(Map paramMap);
	//PC客户收益能力
	public List getMyIncomePowerPC(Map paramMap);
	//PC同类客户收益能力
	public List getOtherIncomePowerPC(Map paramMap);
	//PC同类客户收益走势
	public List getOtherIncomeTrendPC(Map paramMap);
	//收益来源
	public List getMyIncomeSourcePC(Map paramMap);
	public List getOtherIncomeSourcePC(Map paramMap);
	//投资能力
	public List getInvestAbilityPC(Map paramMap);
	//同类广发投资能力
	public List getInvestAbilityAvgPC(Map paramMap);
	//风险收益比
	public List getRiskProfitRatioPC(Map paramMap);
	//风险收益比(广发/同类)
	public List getRiskProfitRatioAvgPC(Map paramMap);
	//客户筛选
	public List getUserList(Map paramMap);
	//客户盈利次数
	public List getProfit_num(Map paramMap);
	//获取亏损次数
	public List getDeficit_num(Map paramMap);
	//获取盈利金额
	public List getProfit_balance(Map paramMap);
	//获取亏损金额
	public List getDeficit_balance(Map paramMap);
	//获取行业列表
	public List getInduList(Map paramMap);
	//获取收益率排名
	public List getEarningPower(Map paramMap);
	//获取筛选客户数目
	public List getUserListNum(Map paramMap);
}
