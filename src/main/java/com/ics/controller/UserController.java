package com.ics.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ics.exception.BadRequestException;
import com.ics.exception.GfTokenErrorException;
import com.ics.service.UserService;
import com.ics.service.VerificationService;
import com.ics.vo.Result;
import com.ics.vo.UserParam;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/client")
public class UserController {

	private Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private VerificationService verificationService;

//	@Value("${enableAccessControl}")
//	private boolean enableAccessControl;

	@RequestMapping(value = "/diagnosisInfotest", method = RequestMethod.GET)
	@ApiOperation(value = "Get Diagnosis Info", notes = "requires trade_id")
	@ResponseBody
	public Result getUserInfo(@RequestParam(value = "trade_id", required = true) String trade_id,
			@RequestParam(value = "period", required = false, defaultValue = "1") String period) {
		Map paramMap = new HashMap();
		Result result = new Result(1, null);
		paramMap.put("period", period);
		paramMap.put("trade_id", trade_id);

		String cust_id = userService.getMyCustId(paramMap);
		if (cust_id == null || cust_id.isEmpty()) {
			// 2找不到客户编号
			result.setRet(2);
		} else {
			paramMap.put("cust_id", cust_id);
			String class_id = userService.getMyClassInfo(paramMap);
			if (class_id == null || class_id.isEmpty()) {
				// 3找不到客户类别
				result.setRet(3);
			} else {
				paramMap.put("class_id", class_id);

				Calendar cal = Calendar.getInstance();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String begindate = "";
				String enddate = format.format(Calendar.getInstance().getTime());
				switch (period) {
				case "1":
					cal.add(Calendar.DAY_OF_MONTH, -32);
					break;
				case "2":
					cal.add(Calendar.DAY_OF_MONTH, -92);
					break;
				case "3":
					cal.add(Calendar.DAY_OF_MONTH, -182);
					break;
				case "4":
					cal.add(Calendar.DAY_OF_MONTH, -367);
					break;
				default:
					cal.add(Calendar.DAY_OF_MONTH, -32);
				}
				begindate = format.format(cal.getTime());
				paramMap.put("begindate", begindate);
				paramMap.put("enddate", enddate);

				try {
					List MyIncome = userService.getMyIncome(paramMap);
					List MyIncomePower = userService.getMyIncomePower(paramMap);
					List OtherIncomePower = userService.getOtherIncomePower(paramMap);
					List IncomeStockRanking = userService.getIncomeStockRanking(paramMap);
					List IncomeIndustryRanking = userService.getIncomeIndustryRanking(paramMap);
					// List MyIncomeTrend =
					// userService.getMyIncomeTrend(paramMap);
					List OtherIncomeTrend = userService.getOtherIncomeTrend(paramMap);
					List MyIncomeSource = userService.getMyIncomeSource(paramMap);
					List OtherIncomeSource = userService.getOtherIncomeSource(paramMap);
					List MyInvestmentAbility = userService.getMyInvestmentAbility(paramMap);
					List OtherInvestmentAbility = userService.getOtherInvestmentAbility(paramMap);
					List MyRiskControl = userService.getMyRiskControl(paramMap);
					List OtherRiskControl = userService.getOtherRiskControl(paramMap);
					List MyFluctuationRatio = userService.getMyFluctuationRatio(paramMap);
					List OtherFluctuationRatio = userService.getOtherFluctuationRatio(paramMap);
					List MyMaxDrawdown = userService.getMyMaxDrawdown(paramMap);
					List OterMaxDrawdown = userService.getOterMaxDrawdown(paramMap);
					List InvestmentPreference = userService.getInvestmentPreference(paramMap);
					List StylePreference = userService.getStylePreference(paramMap);

					Map data = new HashMap();
					data.put("MyIncome", MyIncome);
					data.put("MyIncomePower", MyIncomePower);
					data.put("OtherIncomePower", OtherIncomePower);
					data.put("IncomeStockRanking", IncomeStockRanking);
					data.put("IncomeIndustryRanking", IncomeIndustryRanking);
					// data.put("MyIncomeTrend", MyIncomeTrend);
					data.put("OtherIncomeTrend", OtherIncomeTrend);
					data.put("MyIncomeSource", MyIncomeSource);
					data.put("OtherIncomeSource", OtherIncomeSource);
					data.put("MyInvestmentAbility", MyInvestmentAbility);
					data.put("OtherInvestmentAbility", OtherInvestmentAbility);
					data.put("MyRiskControl", MyRiskControl);
					data.put("OtherRiskControl", OtherRiskControl);
					data.put("MyFluctuationRatio", MyFluctuationRatio);
					data.put("OtherFluctuationRatio", OtherFluctuationRatio);
					data.put("MyMaxDrawdown", MyMaxDrawdown);
					data.put("OterMaxDrawdown", OterMaxDrawdown);
					data.put("InvestmentPreference", InvestmentPreference);
					data.put("StylePreference", StylePreference);
					result.setData(data);
					result.setRet(0);
				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e);
				}
			}
		}

		return result;
	}

	@RequestMapping(value = "/investmentAbility", method = RequestMethod.GET)
	@ApiOperation(value = "Get Diagnosis Info", notes = "requires cust_id, appId, timestamp, token")
	@ResponseBody
	public Result getMyInvestmentAbility(@RequestParam(value = "trade_id", required = true) String trade_id,
			@RequestParam(value = "appId", required = true) String appId,
			@RequestParam(value = "timestamp", required = true) long timestamp,
			@RequestParam(value = "token", required = true) String token) throws Exception {

		int verify = verificationService.verifyToken(trade_id, appId, timestamp, token);
		if (verify != 0) {
			throw new GfTokenErrorException(verify);
		}

		Map paramMap = new HashMap();
		Result result = new Result(1, null);

		paramMap.put("trade_id", trade_id);
		String cust_id = userService.getMyCustId(paramMap);
		if (cust_id == null || cust_id.isEmpty()) {
			// 2找不到客户编号
			result.setRet(2);
		} else {
			paramMap.put("cust_id", cust_id);
			String class_id = userService.getMyClassInfo(paramMap);
			if (class_id == null || class_id.isEmpty()) {
				// 3找不到客户类别
				result.setRet(3);
			} else {
				paramMap.put("class_id", class_id);
				try {
					List MyInvestmentAbility = userService.getMyInvestmentAbility(paramMap);
					List OtherInvestmentAbility = userService.getOtherInvestmentAbility(paramMap);

					Map data = new HashMap();
					data.put("MyInvestmentAbility", MyInvestmentAbility);
					data.put("OtherInvestmentAbility", OtherInvestmentAbility);

					result.setData(data);
					result.setRet(0);
				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e);
				}
			}
		}
		return result;
	}

	@RequestMapping(value = "/diagnosisInfo", method = RequestMethod.GET)
	@ApiOperation(value = "Get Diagnosis Info", notes = "requires trade_id")
	@ResponseBody
	public Result getUserInfos(@RequestParam(value = "trade_id", required = true) String trade_id,
			@RequestParam(value = "period", required = false, defaultValue = "1") String period,
			@RequestHeader(value = "x-convert-user-info", defaultValue = "") String UserInfo) {

//		if (true) {
//			if (UserInfo == null || "".equals(UserInfo)) {
//				throw new BadRequestException("4001");
//			}
//			UserParam user = JSON.parseObject(UserInfo, new TypeReference<UserParam>() {
//			});
//			trade_id = user.getTradeUser().getId();
//		}

		Map paramMap = new HashMap();
		Result result = new Result(1, null);
		paramMap.put("period", period);
		paramMap.put("trade_id", trade_id);

		String cust_id = userService.getMyCustId(paramMap);
		if (cust_id == null || cust_id.isEmpty()) {
			// 2找不到客户编号
			result.setRet(2);
		} else {
			paramMap.put("cust_id", cust_id);
			String class_id = userService.getMyClassInfo(paramMap);
			if (class_id == null || class_id.isEmpty()) {
				// 3找不到客户类别
				result.setRet(3);
			} else {
				paramMap.put("class_id", class_id);
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String begindate = "";
				String enddate = format.format(Calendar.getInstance().getTime());
				switch (period) {
				case "1":
					cal.add(Calendar.DAY_OF_MONTH, -32);
					break;
				case "2":
					cal.add(Calendar.DAY_OF_MONTH, -92);
					break;
				case "3":
					cal.add(Calendar.DAY_OF_MONTH, -182);
					break;
				case "4":
					cal.add(Calendar.DAY_OF_MONTH, -367);
					break;
				default:
					cal.add(Calendar.DAY_OF_MONTH, -32);
				}
				begindate = format.format(cal.getTime());
				paramMap.put("begindate", begindate);
				paramMap.put("enddate", enddate);

				try {
					List OtherIncomePower = userService.getOtherIncomePower(paramMap);
					// List MyIncomeTrend =
					// userService.getMyIncomeTrend(paramMap);
					List MyIncomeSource = userService.getMyIncomeSource(paramMap);
					List OtherIncomeTrend = userService.getOtherIncomeTrend(paramMap);
					List OtherIncomeSource = userService.getOtherIncomeSource(paramMap);
					List OtherInvestmentAbility = userService.getOtherInvestmentAbility(paramMap);
					List OtherRiskControl = userService.getOtherRiskControl(paramMap);
					List MyFluctuationRatio = userService.getMyFluctuationRatio(paramMap);
					List OtherFluctuationRatio = userService.getOtherFluctuationRatio(paramMap);
					List MyMaxDrawdown = userService.getMyMaxDrawdown(paramMap);
					List OterMaxDrawdown = userService.getOterMaxDrawdown(paramMap);
					List InvestmentPreference = userService.getInvestmentPreference(paramMap);
					List StylePreference = userService.getStylePreference(paramMap);
					// add new func
					List InfoByKey = userService.getInfoByKey(paramMap);

					Map data = new HashMap();
					data.put("OtherIncomePower", OtherIncomePower);
					// data.put("MyIncomeTrend", MyIncomeTrend);
					data.put("MyIncomeSource", MyIncomeSource);
					data.put("OtherIncomeTrend", OtherIncomeTrend);
					data.put("OtherIncomeSource", OtherIncomeSource);
					data.put("OtherInvestmentAbility", OtherInvestmentAbility);
					data.put("OtherRiskControl", OtherRiskControl);
					data.put("MyFluctuationRatio", MyFluctuationRatio);
					data.put("OtherFluctuationRatio", OtherFluctuationRatio);
					data.put("MyMaxDrawdown", MyMaxDrawdown);
					data.put("OterMaxDrawdown", OterMaxDrawdown);
					data.put("InvestmentPreference", InvestmentPreference);
					data.put("StylePreference", StylePreference);
					data.put("InfoByKey", InfoByKey);
					result.setData(data);
					result.setRet(0);
				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e);
				}
			}
		}
		return result;
	}

	@RequestMapping(value = "/diagnosisSummary", method = RequestMethod.GET)
	@ApiOperation(value = "Get Diagnosis Info", notes = "requires trade_id")
	@ResponseBody
	public Result getUserSummary(@RequestParam(value = "trade_id", required = true) String trade_id,
			@RequestParam(value = "period", required = false, defaultValue = "1") String period,
			@RequestHeader(value = "x-convert-user-info", defaultValue = "") String UserInfo) {

		if (true) {
			if (UserInfo == null || "".equals(UserInfo)) {
				throw new BadRequestException("4001");
			}
			UserParam user = JSON.parseObject(UserInfo, new TypeReference<UserParam>() {
			});
			trade_id = user.getTradeUser().getId();
		}

		Map paramMap = new HashMap();
		Result result = new Result(1, null);
		paramMap.put("period", period);
		paramMap.put("trade_id", trade_id);

		String cust_id = userService.getMyCustId(paramMap);
		if (cust_id == null || cust_id.isEmpty()) {
			// 2找不到客户编号
			result.setRet(2);
		} else {
			paramMap.put("cust_id", cust_id);
			String class_id = userService.getMyClassInfo(paramMap);
			if (class_id == null || class_id.isEmpty()) {
				// 3找不到客户类别
				result.setRet(3);
			} else {
				paramMap.put("class_id", class_id);
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String begindate = "";
				String enddate = format.format(Calendar.getInstance().getTime());
				switch (period) {
				case "1":
					cal.add(Calendar.DAY_OF_MONTH, -32);
					break;
				case "2":
					cal.add(Calendar.DAY_OF_MONTH, -92);
					break;
				case "3":
					cal.add(Calendar.DAY_OF_MONTH, -182);
					break;
				case "4":
					cal.add(Calendar.DAY_OF_MONTH, -367);
					break;
				default:
					cal.add(Calendar.DAY_OF_MONTH, -32);
				}
				begindate = format.format(cal.getTime());
				paramMap.put("begindate", begindate);
				paramMap.put("enddate", enddate);

				try {
					List InfoByKey = userService.getInfoByKey(paramMap);

					Map data = new HashMap();
					data.put("InfoByKey", InfoByKey);
					result.setData(data);
					result.setRet(0);
				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e);
				}
			}
		}
		return result;
	}

	@RequestMapping(value = "/stylePreference", method = RequestMethod.GET)
	@ApiOperation(value = "Get Diagnosis StylePreference", notes = "requires trade_id")
	@ResponseBody
	public Result getUseStylePreference(@RequestParam(value = "trade_id", required = true) String trade_id,
			@RequestParam(value = "period", required = false, defaultValue = "1") String period,
			@RequestHeader(value = "x-convert-user-info", defaultValue = "") String UserInfo) {

		if (true) {
			if (UserInfo == null || "".equals(UserInfo)) {
				throw new BadRequestException("4001");
			}
			UserParam user = JSON.parseObject(UserInfo, new TypeReference<UserParam>() {
			});
			trade_id = user.getTradeUser().getId();
		}

		Map paramMap = new HashMap();
		Result result = new Result(1, null);
		paramMap.put("period", period);
		paramMap.put("trade_id", trade_id);

		String cust_id = userService.getMyCustId(paramMap);
		if (cust_id == null || cust_id.isEmpty()) {
			// 2找不到客户编号
			result.setRet(2);
		} else {
			paramMap.put("cust_id", cust_id);
			String class_id = userService.getMyClassInfo(paramMap);
			if (class_id == null || class_id.isEmpty()) {
				// 3找不到客户类别
				result.setRet(3);
			} else {
				paramMap.put("class_id", class_id);
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String begindate = "";
				String enddate = format.format(Calendar.getInstance().getTime());
				switch (period) {
				case "1":
					cal.add(Calendar.DAY_OF_MONTH, -32);
					break;
				case "2":
					cal.add(Calendar.DAY_OF_MONTH, -92);
					break;
				case "3":
					cal.add(Calendar.DAY_OF_MONTH, -182);
					break;
				case "4":
					cal.add(Calendar.DAY_OF_MONTH, -367);
					break;
				default:
					cal.add(Calendar.DAY_OF_MONTH, -32);
				}
				begindate = format.format(cal.getTime());
				paramMap.put("begindate", begindate);
				paramMap.put("enddate", enddate);

				try {
					List StylePreference = userService.getStylePreference(paramMap);

					Map data = new HashMap();
					data.put("StylePreference", StylePreference);
					result.setData(data);
					result.setRet(0);
				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e);
				}
			}
		}
		return result;
	}

	@RequestMapping(value = "/getPcData", method = RequestMethod.GET)
	@ApiOperation(value = "Get getPcData Info", notes = "requires trade_id")
	@ResponseBody
	public Result getPcData(@RequestParam(value = "trade_id", required = true) String trade_id,
			@RequestParam(value = "period", required = false, defaultValue = "1") String period,
			@RequestParam(value = "x-convert-user-info", defaultValue = "") String UserInfo) {

		Map paramMap = new HashMap();
		Result result = new Result(1, null);
		paramMap.put("period", period);
		paramMap.put("trade_id", trade_id);

		String cust_id = userService.getMyCustId(paramMap);
		if (cust_id == null || cust_id.isEmpty()) {
			// 2找不到客户编号
			result.setRet(2);
		} else {
			paramMap.put("cust_id", cust_id);
			String class_id = userService.getMyClassInfo(paramMap);
			if (class_id == null || class_id.isEmpty()) {
				// 3找不到客户类别
				result.setRet(3);
			} else {
				paramMap.put("class_id", class_id);
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String begindate = "";
				String enddate = format.format(Calendar.getInstance().getTime());
				switch (period) {
				case "1":
					cal.add(Calendar.DAY_OF_MONTH, -32);
					break;
				case "2":
					cal.add(Calendar.DAY_OF_MONTH, -92);
					break;
				case "3":
					cal.add(Calendar.DAY_OF_MONTH, -182);
					break;
				case "4":
					cal.add(Calendar.DAY_OF_MONTH, -367);
					break;
				default:
					cal.add(Calendar.DAY_OF_MONTH, -32);
				}
				begindate = format.format(cal.getTime());
				paramMap.put("begindate", begindate);
				paramMap.put("enddate", enddate);

				try {
					// pc数据整合
					List pcData = userService.getPcData(paramMap);
					// 股票仓位
					List StockRatio = userService.getStockRatio(paramMap);

					Map data = new HashMap();
                    data.put("pcData", pcData);
					data.put("StockRatio", StockRatio);

					result.setData(data);
					result.setRet(0);
				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e);
				}
			}
		}
		return result;
	}

	// 综合能力PC
	@RequestMapping(value = "/getZhnlOnPc", method = RequestMethod.GET)
	@ApiOperation(value = "Get pc Info", notes = "requires trade_id")
	@ResponseBody
	public Result getZhnlOnPc(@RequestParam(value = "trade_id", required = true) String trade_id,
			@RequestParam(value = "period", required = false, defaultValue = "1") String period,
			@RequestParam(value = "x-convert-user-info", defaultValue = "") String UserInfo) {

		Map paramMap = new HashMap();
		Result result = new Result(1, null);
		paramMap.put("period", period);
		paramMap.put("trade_id", trade_id);

		String cust_id = userService.getMyCustId(paramMap);
		if (cust_id == null || cust_id.isEmpty()) {
			// 2找不到客户编号
			result.setRet(2);
		} else {
			paramMap.put("cust_id", cust_id);
			String class_id = userService.getMyClassInfo(paramMap);
			if (class_id == null || class_id.isEmpty()) {
				// 3找不到客户类别
				result.setRet(3);
			} else {
				paramMap.put("class_id", class_id);
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String begindate = "";
				String enddate = format.format(Calendar.getInstance().getTime());
				switch (period) {
				case "1":
					cal.add(Calendar.DAY_OF_MONTH, -32);
					break;
				case "2":
					cal.add(Calendar.DAY_OF_MONTH, -92);
					break;
				case "3":
					cal.add(Calendar.DAY_OF_MONTH, -182);
					break;
				case "4":
					cal.add(Calendar.DAY_OF_MONTH, -367);
					break;
				default:
					cal.add(Calendar.DAY_OF_MONTH, -32);
				}
				begindate = format.format(cal.getTime());
				paramMap.put("begindate", begindate);
				paramMap.put("enddate", enddate);

				try {
					// 综合能力
					List MyIncomePC = userService.getMyIncomePC(paramMap);
					Map data = new HashMap();

					data.put("MyIncomePC", MyIncomePC);
					result.setData(data);
					result.setRet(0);
				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e);
				}
			}
		}
		return result;
	}

	// 我的收益PC
	@RequestMapping(value = "/getMyIncomePowerPC", method = RequestMethod.GET)
	@ApiOperation(value = "Get pc Info", notes = "requires trade_id")
	@ResponseBody
	public Result getMyIncomePowerPC(@RequestParam(value = "trade_id", required = true) String trade_id,
			@RequestParam(value = "period", required = false) String period,
			@RequestParam(value = "x-convert-user-info", defaultValue = "") String UserInfo) {

		Map paramMap = new HashMap();
		Result result = new Result(1, null);
		paramMap.put("period", period);
		paramMap.put("trade_id", trade_id);

		String cust_id = userService.getMyCustId(paramMap);
		if (cust_id == null || cust_id.isEmpty()) {
			// 2找不到客户编号
			result.setRet(2);
		} else {
			paramMap.put("cust_id", cust_id);
			String class_id = userService.getMyClassInfo(paramMap);
			if (class_id == null || class_id.isEmpty()) {
				// 3找不到客户类别
				result.setRet(3);
			} else {
				paramMap.put("class_id", class_id);

				try {
					// 收益能力
					List MyIncomePowerPC = userService.getMyIncomePowerPC(paramMap);
					List getOtherIncomePowerPC = userService.getOtherIncomePowerPC(paramMap);
					List getEarningPower = userService.getEarningPower(paramMap);
					Map data = new HashMap();
					// 如果周期为空,将收益能力排名插入数据。
					if (period == null) {
						data.put("getEarningPower", getEarningPower);
					}
					data.put("MyIncomePowerPC", MyIncomePowerPC);
					data.put("getOtherIncomePowerPC", getOtherIncomePowerPC);
					result.setData(data);
					result.setRet(0);
				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e);
				}
			}
		}
		return result;
	}

	// 收益走势PC
	@RequestMapping(value = "/getOtherIncomeTrendPC", method = RequestMethod.GET)
	@ApiOperation(value = "Get pc Info", notes = "requires trade_id")
	@ResponseBody
	public Result getOtherIncomeTrendPC(@RequestParam(value = "trade_id", required = true) String trade_id,
			@RequestParam(value = "period", required = false, defaultValue = "1") String period,
			@RequestParam(value = "x-convert-user-info", defaultValue = "") String UserInfo) {

		Map paramMap = new HashMap();
		Result result = new Result(1, null);
		paramMap.put("period", period);
		paramMap.put("trade_id", trade_id);

		String cust_id = userService.getMyCustId(paramMap);
		if (cust_id == null || cust_id.isEmpty()) {
			// 2找不到客户编号
			result.setRet(2);
		} else {
			paramMap.put("cust_id", cust_id);
			String class_id = userService.getMyClassInfo(paramMap);
			if (class_id == null || class_id.isEmpty()) {
				// 3找不到客户类别
				result.setRet(3);
			} else {
				paramMap.put("class_id", class_id);
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String begindate = "";
				String enddate = format.format(Calendar.getInstance().getTime());
				switch (period) {
				case "1":
					cal.add(Calendar.DAY_OF_MONTH, -32);
					break;
				case "2":
					cal.add(Calendar.DAY_OF_MONTH, -92);
					break;
				case "3":
					cal.add(Calendar.DAY_OF_MONTH, -182);
					break;
				case "4":
					cal.add(Calendar.DAY_OF_MONTH, -367);
					break;
				default:
					cal.add(Calendar.DAY_OF_MONTH, -32);
				}
				begindate = format.format(cal.getTime());
				paramMap.put("begindate", begindate);
				paramMap.put("enddate", enddate);
				System.out.println(paramMap);
				// System.out.println(enddate);
				try {
					// 收益走势
					List OtherIncomeTrendPC = userService.getOtherIncomeTrendPC(paramMap);
					// List getOtherIncomePowerPC =
					// userService.getOtherIncomePowerPC(paramMap);
					Map data = new HashMap();

					data.put("OtherIncomeTrendPC", OtherIncomeTrendPC);
					result.setData(data);
					result.setRet(0);
				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e);
				}
			}
		}
		return result;
	}

	// 收益来源PC
	@RequestMapping(value = "/getProfitSorcePC", method = RequestMethod.GET)
	@ApiOperation(value = "Get getProfitSorcePC Info", notes = "requires trade_id")
	@ResponseBody
	public Result getProfitSorcePC(@RequestParam(value = "trade_id", required = true) String trade_id,
			@RequestParam(value = "period", required = false, defaultValue = "1") String period,
			@RequestParam(value = "x-convert-user-info", defaultValue = "") String UserInfo) {

		Map paramMap = new HashMap();
		Result result = new Result(1, null);
		paramMap.put("period", period);
		paramMap.put("trade_id", trade_id);

		String cust_id = userService.getMyCustId(paramMap);
		if (cust_id == null || cust_id.isEmpty()) {
			// 2找不到客户编号
			result.setRet(2);
		} else {
			paramMap.put("cust_id", cust_id);
			String class_id = userService.getMyClassInfo(paramMap);
			if (class_id == null || class_id.isEmpty()) {
				// 3找不到客户类别
				result.setRet(3);
			} else {
				paramMap.put("class_id", class_id);
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String begindate = "";
				String enddate = format.format(Calendar.getInstance().getTime());
				switch (period) {
				case "1":
					cal.add(Calendar.DAY_OF_MONTH, -32);
					break;
				case "2":
					cal.add(Calendar.DAY_OF_MONTH, -92);
					break;
				case "3":
					cal.add(Calendar.DAY_OF_MONTH, -182);
					break;
				case "4":
					cal.add(Calendar.DAY_OF_MONTH, -367);
					break;
				default:
					cal.add(Calendar.DAY_OF_MONTH, -32);
				}
				begindate = format.format(cal.getTime());
				paramMap.put("begindate", begindate);
				paramMap.put("enddate", enddate);
				System.out.println(paramMap);
				// System.out.println(enddate);
				try {
					// 收益走势
					List MyIncomeSourcePC = userService.getMyIncomeSourcePC(paramMap);
					List OtherIncomeSourcePC = userService.getOtherIncomeSourcePC(paramMap);
					Map data = new HashMap();

					data.put("MyIncomeSourcePC", MyIncomeSourcePC);
					data.put("OtherIncomeSourcePC", OtherIncomeSourcePC);
					result.setData(data);
					result.setRet(0);
				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e);
				}
			}
		}
		return result;
	}

	// 投资能力PC
	@RequestMapping(value = "/getInvestAbility", method = RequestMethod.GET)
	@ApiOperation(value = "Get getInvestAbility Info", notes = "requires trade_id")
	@ResponseBody
	public Result getInvestAbility(@RequestParam(value = "trade_id", required = true) String trade_id,
			@RequestParam(value = "period", required = false, defaultValue = "1") String period,
			@RequestParam(value = "x-convert-user-info", defaultValue = "") String UserInfo) {

		Map paramMap = new HashMap();
		Result result = new Result(1, null);
		paramMap.put("period", period);
		paramMap.put("trade_id", trade_id);

		String cust_id = userService.getMyCustId(paramMap);
		if (cust_id == null || cust_id.isEmpty()) {
			// 2找不到客户编号
			result.setRet(2);
		} else {
			paramMap.put("cust_id", cust_id);
			String class_id = userService.getMyClassInfo(paramMap);
			if (class_id == null || class_id.isEmpty()) {
				// 3找不到客户类别
				result.setRet(3);
			} else {
				paramMap.put("class_id", class_id);
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String begindate = "";
				String enddate = format.format(Calendar.getInstance().getTime());
				switch (period) {
				case "1":
					cal.add(Calendar.DAY_OF_MONTH, -32);
					break;
				case "2":
					cal.add(Calendar.DAY_OF_MONTH, -92);
					break;
				case "3":
					cal.add(Calendar.DAY_OF_MONTH, -182);
					break;
				case "4":
					cal.add(Calendar.DAY_OF_MONTH, -367);
					break;
				default:
					cal.add(Calendar.DAY_OF_MONTH, -32);
				}
				begindate = format.format(cal.getTime());
				paramMap.put("begindate", begindate);
				paramMap.put("enddate", enddate);
				System.out.println(paramMap);
				// System.out.println(enddate);
				try {
					// 投资能力
					List InvestAbilityPC = userService.getInvestAbilityPC(paramMap);
					List InvestAbilityAvgPC = userService.getInvestAbilityAvgPC(paramMap);
					Map data = new HashMap();

					data.put("InvestAbilityPC", InvestAbilityPC);
					data.put("InvestAbilityAvgPC", InvestAbilityAvgPC);
					result.setData(data);
					result.setRet(0);
				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e);
				}
			}
		}
		return result;
	}

	// 风险收益比PC
	@RequestMapping(value = "/getRiskProfitRatioPC", method = RequestMethod.GET)
	@ApiOperation(value = "Get getRiskProfitRatioPC Info", notes = "requires trade_id")
	@ResponseBody
	public Result getRiskProfitRatioPC(@RequestParam(value = "trade_id", required = true) String trade_id,
			@RequestParam(value = "period", required = false, defaultValue = "1") String period,
			@RequestParam(value = "x-convert-user-info", defaultValue = "") String UserInfo) {

		Map paramMap = new HashMap();
		Result result = new Result(1, null);
		paramMap.put("period", period);
		paramMap.put("trade_id", trade_id);

		String cust_id = userService.getMyCustId(paramMap);
		if (cust_id == null || cust_id.isEmpty()) {
			// 2找不到客户编号
			result.setRet(2);
		} else {
			paramMap.put("cust_id", cust_id);
			String class_id = userService.getMyClassInfo(paramMap);
			if (class_id == null || class_id.isEmpty()) {
				// 3找不到客户类别
				result.setRet(3);
			} else {
				paramMap.put("class_id", class_id);
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String begindate = "";
				String enddate = format.format(Calendar.getInstance().getTime());
				switch (period) {
				case "1":
					cal.add(Calendar.DAY_OF_MONTH, -32);
					break;
				case "2":
					cal.add(Calendar.DAY_OF_MONTH, -92);
					break;
				case "3":
					cal.add(Calendar.DAY_OF_MONTH, -182);
					break;
				case "4":
					cal.add(Calendar.DAY_OF_MONTH, -367);
					break;
				default:
					cal.add(Calendar.DAY_OF_MONTH, -32);
				}
				begindate = format.format(cal.getTime());
				paramMap.put("begindate", begindate);
				paramMap.put("enddate", enddate);
				System.out.println(paramMap);
				// System.out.println(enddate);
				try {
					// 风险收益比
					List RiskProfitRatioPC = userService.getRiskProfitRatioPC(paramMap);
					List RiskProfitRatioAvgPC = userService.getRiskProfitRatioAvgPC(paramMap);
					Map data = new HashMap();

					data.put("RiskProfitRatioPC", RiskProfitRatioPC);
					data.put("RiskProfitRatioAvgPC", RiskProfitRatioAvgPC);
					result.setData(data);
					result.setRet(0);
				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e);
				}
			}
		}
		return result;
	}

	// 波动率PC
	@RequestMapping(value = "/getMyFluctuationRatio", method = RequestMethod.GET)
	@ApiOperation(value = "Get getMyFluctuationRatio Info", notes = "requires trade_id")
	@ResponseBody
	public Result getMyFluctuationRatio(@RequestParam(value = "trade_id", required = true) String trade_id,
			@RequestParam(value = "period", required = false, defaultValue = "1") String period,
			@RequestParam(value = "x-convert-user-info", defaultValue = "") String UserInfo) {

		Map paramMap = new HashMap();
		Result result = new Result(1, null);
		paramMap.put("period", period);
		paramMap.put("trade_id", trade_id);

		String cust_id = userService.getMyCustId(paramMap);
		if (cust_id == null || cust_id.isEmpty()) {
			// 2找不到客户编号
			result.setRet(2);
		} else {
			paramMap.put("cust_id", cust_id);
			String class_id = userService.getMyClassInfo(paramMap);
			if (class_id == null || class_id.isEmpty()) {
				// 3找不到客户类别
				result.setRet(3);
			} else {
				paramMap.put("class_id", class_id);
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String begindate = "";
				String enddate = format.format(Calendar.getInstance().getTime());
				switch (period) {
				case "1":
					cal.add(Calendar.DAY_OF_MONTH, -32);
					break;
				case "2":
					cal.add(Calendar.DAY_OF_MONTH, -92);
					break;
				case "3":
					cal.add(Calendar.DAY_OF_MONTH, -182);
					break;
				case "4":
					cal.add(Calendar.DAY_OF_MONTH, -367);
					break;
				default:
					cal.add(Calendar.DAY_OF_MONTH, -32);
				}
				begindate = format.format(cal.getTime());
				paramMap.put("begindate", begindate);
				paramMap.put("enddate", enddate);
				System.out.println(paramMap);
				// System.out.println(enddate);
				try {
					// 波动率
					List MyFluctuationRatio = userService.getMyFluctuationRatio(paramMap);
					// 波动率(广发/同类客户)
					List OtherFluctuationRatio = userService.getOtherFluctuationRatio(paramMap);
					Map data = new HashMap();

					data.put("MyFluctuationRatio", MyFluctuationRatio);
					data.put("OtherFluctuationRatio", OtherFluctuationRatio);
					result.setData(data);
					result.setRet(0);
				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e);
				}
			}
		}
		return result;
	}

	// 最大回撤PC
	@RequestMapping(value = "/getMaxdrawdownPC", method = RequestMethod.GET)
	@ApiOperation(value = "Get getMaxdrawdownPC Info", notes = "requires trade_id")
	@ResponseBody
	public Result getMaxdrawdownPC(@RequestParam(value = "trade_id", required = true) String trade_id,
			@RequestParam(value = "period", required = false, defaultValue = "1") String period,
			@RequestParam(value = "x-convert-user-info", defaultValue = "") String UserInfo) {

		Map paramMap = new HashMap();
		Result result = new Result(1, null);
		paramMap.put("period", period);
		paramMap.put("trade_id", trade_id);

		String cust_id = userService.getMyCustId(paramMap);
		if (cust_id == null || cust_id.isEmpty()) {
			// 2找不到客户编号
			result.setRet(2);
		} else {
			paramMap.put("cust_id", cust_id);
			String class_id = userService.getMyClassInfo(paramMap);
			if (class_id == null || class_id.isEmpty()) {
				// 3找不到客户类别
				result.setRet(3);
			} else {
				paramMap.put("class_id", class_id);
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String begindate = "";
				String enddate = format.format(Calendar.getInstance().getTime());
				switch (period) {
				case "1":
					cal.add(Calendar.DAY_OF_MONTH, -32);
					break;
				case "2":
					cal.add(Calendar.DAY_OF_MONTH, -92);
					break;
				case "3":
					cal.add(Calendar.DAY_OF_MONTH, -182);
					break;
				case "4":
					cal.add(Calendar.DAY_OF_MONTH, -367);
					break;
				default:
					cal.add(Calendar.DAY_OF_MONTH, -32);
				}
				begindate = format.format(cal.getTime());
				paramMap.put("begindate", begindate);
				paramMap.put("enddate", enddate);
				System.out.println(paramMap);
				// System.out.println(enddate);
				try {
					// 最大回撤
					List MyMaxDrawdown = userService.getMyMaxDrawdown(paramMap);
					// 最大回撤(广发/同类客户)
					List OterMaxDrawdown = userService.getOterMaxDrawdown(paramMap);
					Map data = new HashMap();

					data.put("MyMaxDrawdown", MyMaxDrawdown);
					data.put("OterMaxDrawdown", OterMaxDrawdown);
					System.out.println(data);
					result.setData(data);
					result.setRet(0);
				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e);
				}
			}
		}
		return result;
	}

	// 行业偏好/风格偏好PC
	@RequestMapping(value = "/getInvestmentPreferencePC", method = RequestMethod.GET)
	@ApiOperation(value = "Get getInvestmentPreferencePC Info", notes = "requires trade_id")
	@ResponseBody
	public Result getInvestmentPreferencePC(@RequestParam(value = "trade_id", required = true) String trade_id,
			@RequestParam(value = "period", required = false, defaultValue = "1") String period,
			@RequestParam(value = "x-convert-user-info", defaultValue = "") String UserInfo) {

		Map paramMap = new HashMap();
		Result result = new Result(1, null);
		paramMap.put("period", period);
		paramMap.put("trade_id", trade_id);

		String cust_id = userService.getMyCustId(paramMap);
		if (cust_id == null || cust_id.isEmpty()) {
			// 2找不到客户编号
			result.setRet(2);
		} else {
			paramMap.put("cust_id", cust_id);
			String class_id = userService.getMyClassInfo(paramMap);
			if (class_id == null || class_id.isEmpty()) {
				// 3找不到客户类别
				result.setRet(3);
			} else {
				paramMap.put("class_id", class_id);
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String begindate = "";
				String enddate = format.format(Calendar.getInstance().getTime());
				switch (period) {
				case "1":
					cal.add(Calendar.DAY_OF_MONTH, -32);
					break;
				case "2":
					cal.add(Calendar.DAY_OF_MONTH, -92);
					break;
				case "3":
					cal.add(Calendar.DAY_OF_MONTH, -182);
					break;
				case "4":
					cal.add(Calendar.DAY_OF_MONTH, -367);
					break;
				default:
					cal.add(Calendar.DAY_OF_MONTH, -32);
				}
				begindate = format.format(cal.getTime());
				paramMap.put("begindate", begindate);
				paramMap.put("enddate", enddate);
				System.out.println(paramMap);
				// System.out.println(enddate);
				try {
					// 行业占比
					List InvestmentPreference = userService.getInvestmentPreference(paramMap);
					// 风格偏好
					List StylePreference = userService.getStylePreference(paramMap);
					Map data = new HashMap();

					data.put("InvestmentPreference", InvestmentPreference);
					data.put("StylePreference", StylePreference);
					System.out.println(data);
					result.setData(data);
					result.setRet(0);
				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e);
				}
			}
		}
		return result;
	}

	// 客户筛选
	@RequestMapping(value = "/getUserList", method = RequestMethod.POST, produces = "application/json;charset=utf-8;")
	@ApiOperation(value = "Get getUserList Info", notes = "requires trade_id")
	@ResponseBody
	public Result getUserList(@RequestBody JSONObject info) {

		Map paramMap = new HashMap();
		Result result = new Result(1, null);

		// System.out.println(infoString);
		// JSONObject info = JSONObject.parseObject(infoString);

		System.out.println(info);

		/**************** 基本信息 ***************/
		// 周期
		String period = info.getJSONObject("profitRisk").getString("period");
		if (period == null || period == "") {
			period = "1";
		}
		paramMap.put("period", period);
		// 客户类别
		// 前端角标 0 1 2 3 4 大众 金管家 私银客户 机构 其他
		// 后端 1 2 3 4 9
		List class_id = (List) info.get("customerType");
		for (int i = 0; i < class_id.size(); i++) {
			Integer classidNum = (Integer) class_id.get(i);
			String classid = Integer.toString(classidNum);
			if (classid.equals("0")) {
				class_id.set(i, "1");
			} else if (classid.equals("1")) {
				class_id.set(i, "2");
			} else if (classid.equals("2")) {
				class_id.set(i, "3");
			} else if (classid.equals("3")) {
				class_id.set(i, "4");
			} else if (classid.equals("4")) {
				class_id.set(i, "9");
			}
		}
		paramMap.put("class_id", class_id);
		// 页码
		Float page = info.getFloat("index");
		/*
		 * if(page == null) { page = (float) 1.0; } page = 20*(page-1);
		 */
		paramMap.put("page", page);

		// 每页条数
		String pageSize = info.getString("pageSize");

		/************** 盈利情况 *****************/
		JSONObject profitRisk = info.getJSONObject("profitRisk");
		// 周期收益
		paramMap.put("max_my_profit", profitRisk.getJSONObject("theReturn").getFloat("upperBound"));
		paramMap.put("min_my_profit", profitRisk.getJSONObject("theReturn").getFloat("lowerBound"));
		// 个股查询
		paramMap.put("prd_name", "%" + profitRisk.getString("stockName") + "%");
		// 行业查询
		paramMap.put("industry_prd", profitRisk.getString("industryType"));
		// 大类查询
		paramMap.put("profit_source", profitRisk.getString("categoriesType"));
		// 盈利次数
		paramMap.put("max_profit_num", profitRisk.getJSONObject("earningsNumber").getFloat("upperBound"));
		paramMap.put("min_profit_num", profitRisk.getJSONObject("earningsNumber").getFloat("lowerBound"));
		// 亏损次数
		paramMap.put("max_deficit_num", profitRisk.getJSONObject("lossNumber").getFloat("upperBound"));
		paramMap.put("min_deficit_num", profitRisk.getJSONObject("lossNumber").getFloat("lowerBound"));
		// 盈利金额
		paramMap.put("max_profit_balance", profitRisk.getJSONObject("earningsRange").getFloat("upperBound"));
		paramMap.put("min_profit_balance", profitRisk.getJSONObject("earningsRange").getFloat("lowerBound"));
		// 亏损金额
		paramMap.put("max_deficit_balance", profitRisk.getJSONObject("lossRange").getFloat("upperBound"));
		paramMap.put("min_deficit_balance", profitRisk.getJSONObject("lossRange").getFloat("lowerBound"));

		/**************** 交易偏好及特点 ******************/
		JSONObject trading = info.getJSONObject("trading");
		// 股票仓位
		paramMap.put("max_stock_ratio", trading.getJSONObject("equitiesRange").getFloat("upperBound"));
		paramMap.put("min_stock_ratio", trading.getJSONObject("equitiesRange").getFloat("lowerBound"));
		// 持股集中度(股票只数)
		paramMap.put("max_stock_num", trading.getJSONObject("holdingRange").getFloat("upperBound"));
		paramMap.put("min_stock_num", trading.getJSONObject("holdingRange").getFloat("lowerBound"));
		// 交易次数
		paramMap.put("max_exchange_num", trading.getJSONObject("tradingRange").getFloat("upperBound"));
		paramMap.put("min_exchange_num", trading.getJSONObject("tradingRange").getFloat("lowerBound"));
		// 交易换手率
		paramMap.put("max_exchange_ratio", trading.getJSONObject("resellRange").getFloat("upperBound"));
		paramMap.put("min_exchange_ratio", trading.getJSONObject("resellRange").getFloat("lowerBound"));
		// 平均持仓时长
		paramMap.put("max_stock_day_avg", trading.getJSONObject("longRange").getFloat("upperBound"));
		paramMap.put("min_stock_day_avg", trading.getJSONObject("longRange").getFloat("lowerBound"));
		// 投资偏好（盘型）
		paramMap.put("ptype", trading.getString("discType"));
		// 投资偏好（风格）
		paramMap.put("ftype", trading.getString("styleType"));
		// 行业倾向
		paramMap.put("indu_code", (List) trading.get("tendencyType"));

		/************** 风险控制能力 *****************/
		JSONObject risk = info.getJSONObject("risk");
		// 最大回撤
		paramMap.put("max_max_drawdown", risk.getJSONObject("retracementRange").getFloat("upperBound"));
		paramMap.put("min_max_drawdown", risk.getJSONObject("retracementRange").getFloat("lowerBound"));
		// 下行风险
		paramMap.put("max_downside_risk", risk.getJSONObject("downsideRange").getFloat("upperBound"));
		paramMap.put("min_downside_risk", risk.getJSONObject("downsideRange").getFloat("lowerBound"));
		// 回撤恢复时间
		paramMap.put("max_daynum", risk.getJSONObject("retreatRange").getFloat("upperBound"));
		paramMap.put("min_daynum", risk.getJSONObject("retreatRange").getFloat("lowerBound"));
		// VAR
		paramMap.put("max_var_val", risk.getJSONObject("varRange").getFloat("upperBound"));
		paramMap.put("min_var_val", risk.getJSONObject("varRange").getFloat("lowerBound"));

		/************** 投资管理能力 **************/
		JSONObject manage = info.getJSONObject("manage");
		// 交易胜率
		paramMap.put("max_exchange_winratio", manage.getJSONObject("tradeRange").getFloat("upperBound"));
		paramMap.put("min_exchange_winratio", manage.getJSONObject("tradeRange").getFloat("lowerBound"));
		// 卖出盈利率
		paramMap.put("max_sell_earnings_val", manage.getJSONObject("sellProfitRange").getFloat("upperBound"));
		paramMap.put("min_sell_earnings_val", manage.getJSONObject("sellProfitRange").getFloat("lowerBound"));
		// 净值增长率
		paramMap.put("max_net_worth_uprate", manage.getJSONObject("rateRange").getFloat("upperBound"));
		paramMap.put("min_net_worth_uprate", manage.getJSONObject("rateRange").getFloat("lowerBound"));
		// 净值增长率标准差
		paramMap.put("max_networth_uprate_stdev", manage.getJSONObject("rateStandardRange").getFloat("upperBound"));
		paramMap.put("min_networth_uprate_stdev", manage.getJSONObject("rateStandardRange").getFloat("lowerBound"));
		// 同类客户平均收益率
		paramMap.put("max_my_profit_avg", manage.getJSONObject("averageRange").getFloat("upperBound"));
		paramMap.put("min_my_profit_avg", manage.getJSONObject("averageRange").getFloat("lowerBound"));
		// 超额收益
		paramMap.put("max_ce_profit", manage.getJSONObject("excessRange").getFloat("upperBound"));
		paramMap.put("min_ce_profit", manage.getJSONObject("excessRange").getFloat("lowerBound"));
		// 超额胜率
		paramMap.put("max_cesl_score", manage.getJSONObject("excessOddsRange").getFloat("upperBound"));
		paramMap.put("min_cesl_score", manage.getJSONObject("excessOddsRange").getFloat("lowerBound"));
		// 择时能力
		paramMap.put("max_zsnl_score", manage.getJSONObject("downsideRange").getFloat("upperBound"));
		paramMap.put("min_zsnl_score", manage.getJSONObject("downsideRange").getFloat("lowerBound"));
		// 择股能力
		paramMap.put("max_zgnl_score", manage.getJSONObject("stockRange").getFloat("upperBound"));
		paramMap.put("min_zgnl_score", manage.getJSONObject("stockRange").getFloat("lowerBound"));
		// Alpha指标
		paramMap.put("max_alpha_val", manage.getJSONObject("alphaRange").getFloat("upperBound"));
		paramMap.put("min_alpha_val", manage.getJSONObject("alphaRange").getFloat("lowerBound"));
		// Beta指标
		paramMap.put("max_beta_val", manage.getJSONObject("betaRange").getFloat("upperBound"));
		paramMap.put("min_beta_val", manage.getJSONObject("betaRange").getFloat("lowerBound"));
		// 夏普比率
		paramMap.put("max_sharpe_val", manage.getJSONObject("sharpeRange").getFloat("upperBound"));
		paramMap.put("min_sharpe_val", manage.getJSONObject("sharpeRange").getFloat("lowerBound"));
		// 信息收益比
		paramMap.put("max_info_val", manage.getJSONObject("informationRange").getFloat("upperBound"));
		paramMap.put("min_info_val", manage.getJSONObject("informationRange").getFloat("lowerBound"));
		// 进攻能力
		paramMap.put("max_jgnl_score", manage.getJSONObject("offensiveRange").getFloat("upperBound"));
		paramMap.put("min_jgnl_score", manage.getJSONObject("offensiveRange").getFloat("lowerBound"));
		// 防御能力
		paramMap.put("max_fynl_score", manage.getJSONObject("defenseRange").getFloat("upperBound"));
		paramMap.put("min_fynl_score", manage.getJSONObject("defenseRange").getFloat("lowerBound"));

		// System.out.println(paramMap);
		try {
			System.out.println(paramMap);
			// 用户列表
			List UserList = userService.getUserList(paramMap);
			List listNum = userService.getUserListNum(paramMap);

			Map data = new HashMap();

			Map countMap = (Map) listNum.get(0);
			data.put("totalCount", countMap.get("count"));
			data.put("UserList", UserList);
			data.put("page", info.getFloat("page"));
			data.put("pageSize", 20);

			result.setData(data);
			result.setRet(0);

		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}

		return result;
	}

	// 获取行业
	@RequestMapping(value = "/getInduCode", method = RequestMethod.GET)
	@ApiOperation(value = "Get getInduCode Info", notes = "requires trade_id")
	@ResponseBody
	public Result getInduCode(@RequestParam(value = "trade_id", required = false) String trade_id,
			@RequestParam(value = "period", required = false, defaultValue = "1") String period,
			@RequestParam(value = "x-convert-user-info", defaultValue = "") String UserInfo) {

		Map paramMap = new HashMap();
		Result result = new Result(1, null);
		paramMap.put("period", period);
		paramMap.put("trade_id", trade_id);

		try {
			// 行业List
			List induList = userService.getInduList(paramMap);
			// List induList = new
			Map data = new HashMap();
			data.put("induList", induList);
			result.setData(data);
			result.setRet(0);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}

		return result;
	}

	// 获取交易特点
	@RequestMapping(value = "/getTradeType", method = RequestMethod.POST, produces = "application/json;charset=utf-8;")
	@ApiOperation(value = "Get getTradeType Info", notes = "requires trade_id")
	@ResponseBody
	public Result getTradeType(@RequestBody JSONObject infoJson) {

		Map paramMap = new HashMap();
		Result result = new Result(1, null);

		// System.out.println(infoString);
		// JSONObject info = JSONObject.parseObject(infoString);

		JSONObject info = infoJson.getJSONObject("customers");
		System.out.println(info);

		String typeString = infoJson.getString("type");

		/**************** 基本信息 ***************/
		// 周期
		String period = info.getJSONObject("profitRisk").getString("period");
		if (period == null || period == "") {
			period = "1";
		}
		paramMap.put("period", period);
		// 客户类别
		// 前端角标 0 1 2 3 4 大众 金管家 私银客户 机构 其他
		// 后端 1 2 3 4 9
		List class_id = (List) info.get("customerType");
		for (int i = 0; i < class_id.size(); i++) {
			Integer classidNum = (Integer) class_id.get(i);
			String classid = Integer.toString(classidNum);
			if (classid.equals("0")) {
				class_id.set(i, "1");
			} else if (classid.equals("1")) {
				class_id.set(i, "2");
			} else if (classid.equals("2")) {
				class_id.set(i, "3");
			} else if (classid.equals("3")) {
				class_id.set(i, "4");
			} else if (classid.equals("4")) {
				class_id.set(i, "9");
			}
		}
		paramMap.put("class_id", class_id);
		// 页码
		Float page = info.getFloat("index");

		if (page == null) {
			page = (float) 1.0;
		}
		page = 5 * (page - 1);
		paramMap.put("page", page);

		// 每页条数
		String pageSize = info.getString("pageSize");

		/************** 盈利情况 *****************/
		JSONObject profitRisk = info.getJSONObject("profitRisk");
		// 周期收益
		paramMap.put("max_my_profit", profitRisk.getJSONObject("theReturn").getFloat("upperBound"));
		paramMap.put("min_my_profit", profitRisk.getJSONObject("theReturn").getFloat("lowerBound"));
		// 个股查询
		paramMap.put("prd_name", "%" + profitRisk.getString("stockName") + "%");
		// 行业查询
		paramMap.put("industry_prd", profitRisk.getString("industryType"));
		// 大类查询
		paramMap.put("profit_source", profitRisk.getString("categoriesType"));
		// 盈利次数
		paramMap.put("max_profit_num", profitRisk.getJSONObject("earningsNumber").getFloat("upperBound"));
		paramMap.put("min_profit_num", profitRisk.getJSONObject("earningsNumber").getFloat("lowerBound"));
		// 亏损次数
		paramMap.put("max_deficit_num", profitRisk.getJSONObject("lossNumber").getFloat("upperBound"));
		paramMap.put("min_deficit_num", profitRisk.getJSONObject("lossNumber").getFloat("lowerBound"));
		// 盈利金额
		paramMap.put("max_profit_balance", profitRisk.getJSONObject("earningsRange").getFloat("upperBound"));
		paramMap.put("min_profit_balance", profitRisk.getJSONObject("earningsRange").getFloat("lowerBound"));
		// 亏损金额
		paramMap.put("max_deficit_balance", profitRisk.getJSONObject("lossRange").getFloat("upperBound"));
		paramMap.put("min_deficit_balance", profitRisk.getJSONObject("lossRange").getFloat("lowerBound"));

		/**************** 交易偏好及特点 ******************/
		JSONObject trading = info.getJSONObject("trading");
		// 股票仓位
		paramMap.put("max_stock_ratio", trading.getJSONObject("equitiesRange").getFloat("upperBound"));
		paramMap.put("min_stock_ratio", trading.getJSONObject("equitiesRange").getFloat("lowerBound"));
		// 持股集中度(股票只数)
		paramMap.put("max_stock_num", trading.getJSONObject("holdingRange").getFloat("upperBound"));
		paramMap.put("min_stock_num", trading.getJSONObject("holdingRange").getFloat("lowerBound"));
		// 交易次数
		paramMap.put("max_exchange_num", trading.getJSONObject("tradingRange").getFloat("upperBound"));
		paramMap.put("min_exchange_num", trading.getJSONObject("tradingRange").getFloat("lowerBound"));
		// 交易换手率
		paramMap.put("max_exchange_ratio", trading.getJSONObject("resellRange").getFloat("upperBound"));
		paramMap.put("min_exchange_ratio", trading.getJSONObject("resellRange").getFloat("lowerBound"));
		// 平均持仓时长
		paramMap.put("max_stock_day_avg", trading.getJSONObject("longRange").getFloat("upperBound"));
		paramMap.put("min_stock_day_avg", trading.getJSONObject("longRange").getFloat("lowerBound"));
		// 投资偏好（盘型）
		paramMap.put("ptype", trading.getString("discType"));
		// 投资偏好（风格）
		paramMap.put("ftype", trading.getString("styleType"));
		// 行业倾向
		paramMap.put("indu_code", (List) trading.get("tendencyType"));

		/************** 风险控制能力 *****************/
		JSONObject risk = info.getJSONObject("risk");
		// 最大回撤
		paramMap.put("max_max_drawdown", risk.getJSONObject("retracementRange").getFloat("upperBound"));
		paramMap.put("min_max_drawdown", risk.getJSONObject("retracementRange").getFloat("lowerBound"));
		// 下行风险
		paramMap.put("max_downside_risk", risk.getJSONObject("downsideRange").getFloat("upperBound"));
		paramMap.put("min_downside_risk", risk.getJSONObject("downsideRange").getFloat("lowerBound"));
		// 回撤恢复时间
		paramMap.put("max_daynum", risk.getJSONObject("retreatRange").getFloat("upperBound"));
		paramMap.put("min_daynum", risk.getJSONObject("retreatRange").getFloat("lowerBound"));
		// VAR
		paramMap.put("max_var_val", risk.getJSONObject("varRange").getFloat("upperBound"));
		paramMap.put("min_var_val", risk.getJSONObject("varRange").getFloat("lowerBound"));

		/************** 投资管理能力 **************/
		JSONObject manage = info.getJSONObject("manage");
		// 交易胜率
		paramMap.put("max_exchange_winratio", manage.getJSONObject("tradeRange").getFloat("upperBound"));
		paramMap.put("min_exchange_winratio", manage.getJSONObject("tradeRange").getFloat("lowerBound"));
		// 卖出盈利率
		paramMap.put("max_sell_earnings_val", manage.getJSONObject("sellProfitRange").getFloat("upperBound"));
		paramMap.put("min_sell_earnings_val", manage.getJSONObject("sellProfitRange").getFloat("lowerBound"));
		// 净值增长率
		paramMap.put("max_net_worth_uprate", manage.getJSONObject("rateRange").getFloat("upperBound"));
		paramMap.put("min_net_worth_uprate", manage.getJSONObject("rateRange").getFloat("lowerBound"));
		// 净值增长率标准差
		paramMap.put("max_networth_uprate_stdev", manage.getJSONObject("rateStandardRange").getFloat("upperBound"));
		paramMap.put("min_networth_uprate_stdev", manage.getJSONObject("rateStandardRange").getFloat("lowerBound"));
		// 同类客户平均收益率
		paramMap.put("max_my_profit_avg", manage.getJSONObject("averageRange").getFloat("upperBound"));
		paramMap.put("min_my_profit_avg", manage.getJSONObject("averageRange").getFloat("lowerBound"));
		// 超额收益
		paramMap.put("max_ce_profit", manage.getJSONObject("excessRange").getFloat("upperBound"));
		paramMap.put("min_ce_profit", manage.getJSONObject("excessRange").getFloat("lowerBound"));
		// 超额胜率
		paramMap.put("max_cesl_score", manage.getJSONObject("excessOddsRange").getFloat("upperBound"));
		paramMap.put("min_cesl_score", manage.getJSONObject("excessOddsRange").getFloat("lowerBound"));
		// 择时能力
		paramMap.put("max_zsnl_score", manage.getJSONObject("downsideRange").getFloat("upperBound"));
		paramMap.put("min_zsnl_score", manage.getJSONObject("downsideRange").getFloat("lowerBound"));
		// 择股能力
		paramMap.put("max_zgnl_score", manage.getJSONObject("stockRange").getFloat("upperBound"));
		paramMap.put("min_zgnl_score", manage.getJSONObject("stockRange").getFloat("lowerBound"));
		// Alpha指标
		paramMap.put("max_alpha_val", manage.getJSONObject("alphaRange").getFloat("upperBound"));
		paramMap.put("min_alpha_val", manage.getJSONObject("alphaRange").getFloat("lowerBound"));
		// Beta指标
		paramMap.put("max_beta_val", manage.getJSONObject("betaRange").getFloat("upperBound"));
		paramMap.put("min_beta_val", manage.getJSONObject("betaRange").getFloat("lowerBound"));
		// 夏普比率
		paramMap.put("max_sharpe_val", manage.getJSONObject("sharpeRange").getFloat("upperBound"));
		paramMap.put("min_sharpe_val", manage.getJSONObject("sharpeRange").getFloat("lowerBound"));
		// 信息收益比
		paramMap.put("max_info_val", manage.getJSONObject("informationRange").getFloat("upperBound"));
		paramMap.put("min_info_val", manage.getJSONObject("informationRange").getFloat("lowerBound"));
		// 进攻能力
		paramMap.put("max_jgnl_score", manage.getJSONObject("offensiveRange").getFloat("upperBound"));
		paramMap.put("min_jgnl_score", manage.getJSONObject("offensiveRange").getFloat("lowerBound"));
		// 防御能力
		paramMap.put("max_fynl_score", manage.getJSONObject("defenseRange").getFloat("upperBound"));
		paramMap.put("min_fynl_score", manage.getJSONObject("defenseRange").getFloat("lowerBound"));

		// System.out.println(paramMap);
		try {
			System.out.println(paramMap);
			List dataList = new ArrayList<>();
			if (typeString.equals("1")) {
				dataList = userService.getProfit_num(paramMap);
			} else if (typeString.equals("2")) {
				dataList = userService.getDeficit_num(paramMap);
			} else if (typeString.equals("3")) {
				dataList = userService.getProfit_balance(paramMap);
			} else if (typeString.equals("4")) {
				dataList = userService.getDeficit_balance(paramMap);
			}

			List classList1 = new ArrayList<>();
			List countList1 = new ArrayList<>();
			List classList2 = new ArrayList<>();
			List countList2 = new ArrayList<>();
			List classList3 = new ArrayList<>();
			List countList3 = new ArrayList<>();
			List classList4 = new ArrayList<>();
			List countList4 = new ArrayList<>();

			for (int i = 0; i < dataList.size(); i++) {
				Map json = (Map) dataList.get(i);
				if (json.get("status").equals("1")) {
					classList1.add(json.get("class_id"));
					countList1.add(json.get("count"));
				} else if (json.get("status").equals("2")) {
					classList2.add(json.get("class_id"));
					countList2.add(json.get("count"));
				} else if (json.get("status").equals("3")) {
					classList3.add(json.get("class_id"));
					countList3.add(json.get("count"));
				} else if (json.get("status").equals("4")) {
					classList4.add(json.get("class_id"));
					countList4.add(json.get("count"));
				}
			}

			Map data1 = new HashMap();
			data1.put("count", countList1);
			data1.put("class_id", classList1);

			Map data2 = new HashMap();
			data2.put("count", countList2);
			data2.put("class_id", classList2);

			Map data3 = new HashMap();
			data3.put("count", countList3);
			data3.put("class_id", classList3);

			Map data4 = new HashMap();
			data4.put("count", countList4);
			data4.put("class_id", classList4);

			Map data = new HashMap();
			data.put("LessThanTenTimes", data1);
			data.put("TenToFiftyTimes", data2);
			data.put("FiftyToOneHundredTimes", data3);
			data.put("MoreThanOneHundredTimes", data4);

			result.setData(data);
			result.setRet(0);

		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}

		return result;
	}

}
