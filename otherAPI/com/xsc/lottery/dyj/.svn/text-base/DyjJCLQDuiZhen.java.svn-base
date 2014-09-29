package com.xsc.lottery.dyj;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.MatchArrange;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.RaceStatus;
import com.xsc.lottery.java.common.CommonScheduledThreadPoolExecutor;
import com.xsc.lottery.service.business.LotteryTermService;
import com.xsc.lottery.service.business.MatchArrangeService;
import com.xsc.lottery.task.jclq.JclqTaskExcutor;
import com.xsc.lottery.util.DateUtil;
import com.xsc.lottery.util.NetWorkUtil;
import com.xsc.lottery.util.StringUtil;

import edu.emory.mathcs.backport.java.util.Collections;

@Component
public class DyjJCLQDuiZhen implements ApplicationListener {
	private static String urlString = "http://dc.zs310.com/dz/jclq/";

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MatchArrangeService matchArrangeService;

	@Autowired
	private JclqTaskExcutor jclqTaskExcutor;

	@Autowired
	private LotteryTermService lotteryTermService;

	protected boolean start = false;

	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof ContextRefreshedEvent && !start) {
			logger.info("一彩票获取竞彩篮球对阵服务启动...");
			CommonScheduledThreadPoolExecutor.getInstance().execute(
					createJCLQTask());
			start = true;
		}
	}

	private Runnable createJCLQTask() {
		return new Runnable() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(100000);
						getJCLQDZ();
						Thread.sleep(500000);

					} catch (Exception e) {
						String description = "获取竞彩篮球球对阵异常,请查看日志";
						logger.info(description);
					}
				}
			}
		};
	}

	/** 获取竞彩篮球对阵 */
	private String getresult(String date) {
		if (date.equals("")) {
			Date d = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			date = format.format(d);
		}
		logger.info(urlString + date + ".html");
		String result = NetWorkUtil.getHttpUrl(urlString + date + ".html", "",
				"");
		return result;
	}

	public void getJCLQDZ() {
		logger.info("开始获取竞彩篮球对阵............");
		System.out.println("获取对阵开始...............");
		List<String> dates = new ArrayList<String>();
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = format.format(date); // 今天
		dates.add(date1);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		Date cal = calendar.getTime();
		String date2 = format.format(cal); // 昨天
		dates.add(date2);

		calendar.add(Calendar.DATE, -1);
		Date cal1 = calendar.getTime();
		String date3 = format.format(cal1); // 前天
		dates.add(date3);

		calendar.add(Calendar.DATE, -1);
		Date cal2 = calendar.getTime();
		String date4 = format.format(cal2); // 大前天
		dates.add(date4);

		Calendar calendar1 = Calendar.getInstance();
		for (int i = 0; i < 17; i++) {

			calendar1.add(Calendar.DATE, 1);
			Date cal_add = calendar1.getTime();
			dates.add(format.format(cal_add));
		}
		Collections.sort(dates);
	
		try {
			for (String dateStr : dates) {
				getJCLQDZBYDate(dateStr); // 获取某个日期的对阵
				Thread.sleep(50000);
			}
		} catch (Exception e) {
			logger.info("getdzfail---------", e);
		}

		System.out.println("获取对阵结束......................");
	}

	/*
	 * 解析篮球对阵
	 */
	public void getJCLQDZBYDate(String date) {

		String result = getresult(date);
		JSONArray jsonArray = JSONArray.fromObject(result);
		JSONObject jsonObject;
		for (int i = 0; i < jsonArray.size(); i++) {
			String wholeScore = "";
			String halfScore = "";
			String rfsfdg = "";
			String rfsfgg = "";
			String sfdg = "";
			String sfgg = "";
			String sfcdg = "";
			String sfcgg = "";
			String dxfdg = "";
			String dxfgg = "";
			String sfdgRatios = "";
			String sfggRatios = "";
			String rfsfdgRatios = "";
			String rfsfggRatios = "";
			String sfcdgRatios = "";
			String sfcggRatios = "";
			String dxfdgRatios = "";
			String dxfggRatios = "";
			String currentRatios = "";
			String rangfen = "0";
			String rangfensaiguo = "";
			String quanchangsaiguo = "";

			jsonObject = jsonArray.getJSONObject(i);
			MatchArrange match = new MatchArrange();
			match.setBoutIndex(jsonObject.getString("matchkey").substring(2,
					jsonObject.getString("matchkey").length()));
			match.setMatchName(jsonObject.getString("name"));
			match.setMatchTime(DateUtil.parse(jsonObject.getString("dt"),
					"yy-MM-dd HH:mm:ss"));
			String sop = jsonObject.getString("oh");
			String fop = jsonObject.getString("oa");
			match.setSop(sop);
			match.setFop(fop);

			match.setDanguanRangFen(jsonObject.getString("fu_rfsf_num"));
			match.setDuoguanRangFen(jsonObject.getString("rfsf_num"));

			match.setDanguanDaXiaoQiu(jsonObject.getString("fu_dxf_num"));
			match.setDuoguanDaXiaoQiu(jsonObject.getString("dxf_num"));

			NumberFormat nf = NumberFormat.getNumberInstance();
			nf.setMaximumFractionDigits(2);

			if (sop.isEmpty() || fop.isEmpty()) {
				logger.info("empty sopfop");
			} else {
				Double fm = Double.parseDouble(sop) * Double.parseDouble(fop);

				String stzb = nf.format(Double.parseDouble(fop) / fm);
				String ftzb = nf.format(Double.parseDouble(sop) / fm);
				nf.setMaximumFractionDigits(1);
				match.setStzb(nf.format(Double.valueOf(stzb) * 100));
				match.setFtzb(nf.format(Double.valueOf(ftzb) * 100));
			}

			// 问题：全场比分 赛果区别 matchResult这个字段为空
			wholeScore = jsonObject.getString("score");
			String score_1 = jsonObject.getString("score_1");
			String score_2 = jsonObject.getString("score_2");
			if (!StringUtil.isEmpty(score_1) && !StringUtil.isEmpty(score_2)) {
				Integer halfHomeScore = Integer.parseInt(score_1.split(":")[0])
						+ Integer.parseInt(score_2.split(":")[0]);
				Integer halfAwayScore = Integer.parseInt(score_1.split(":")[1])
						+ Integer.parseInt(score_2.split(":")[1]);
				halfScore = halfHomeScore + ":" + halfAwayScore;
			}
			if (!wholeScore.equals("")) {
				wholeScore = wholeScore.split(":")[1]+":"+wholeScore.split(":")[0];
				match.setWholeScore(wholeScore);
				match.setHalfScore(halfScore);
				rangfen = jsonObject.getString("fu_rfsf_num");
				if (Integer.parseInt(wholeScore.split(":")[0])
						+ Double.parseDouble(rangfen) > Integer
						.parseInt(wholeScore.split(":")[1])) {
					rangfensaiguo = "3";
				}
				if (Integer.parseInt(wholeScore.split(":")[0])
						+ Double.parseDouble(rangfen) < Integer
						.parseInt(wholeScore.split(":")[1])) {
					rangfensaiguo = "0";
				}
				match.setRfsfResult(rangfensaiguo);

				if (Integer.parseInt(wholeScore.split(":")[0]) > Integer
						.parseInt(wholeScore.split(":")[1])) {
					quanchangsaiguo = "3";
				}
				if (Integer.parseInt(wholeScore.split(":")[0]) < Integer
						.parseInt(wholeScore.split(":")[1])) {
					quanchangsaiguo = "0";
				}
				match.setMatchResult(quanchangsaiguo);
				match.setSfResult(quanchangsaiguo);
				Integer totalScore = Integer.parseInt(wholeScore.split(":")[0])
						+ Integer.parseInt(wholeScore.split(":")[1]);
				if (totalScore > Double.parseDouble(jsonObject
						.getString("fu_dxf_num"))) {
					match.setDxfResult("3");
				}
				if (totalScore < Double.parseDouble(jsonObject
						.getString("fu_dxf_num"))) {
					match.setDxfResult("0");
				}
			}

			match.setHomeTeam(jsonObject.getString("home"));
			match.setAwaryTeam(jsonObject.getString("awary"));

			// 问题：状态对应不上
			String status = jsonObject.getString("st");
			if (status.equals("0")) {

			}

			// 篮球格式： 胜平负单关;胜平负多关 | 进球数单关;进球数多关 | 猜比分单关;猜比分多关 | 半全场单关;半全场多关
			// (胜平负单关内部以英文逗号","隔开)
			// 例如：1,3;2,4|3,5;5,7|
			// 浮动单关 固定多关

			// 让球胜平负单关
			if (jsonObject.getString("sf_fu").equals("1")) {
				sfdg = "SGCSF";
				// 胜平负单关
				if (jsonObject.getString("fu_sf_sp3").equals("")
						|| jsonObject.getString("fu_sf_sp3").equals("0")) {
					sfdgRatios += "0000" + ",";
				} else {
					sfdgRatios += jsonObject.getString("fu_sf_sp3") + ",";
				}
				if (jsonObject.getString("fu_sf_sp0").equals("")
						|| jsonObject.getString("fu_sf_sp0").equals("0")) {
					sfdgRatios += "0000";
				} else {
					sfdgRatios += jsonObject.getString("fu_sf_sp0");
				}

			} else {
				sfdgRatios = "-1";
			}
			// 胜平负多关
			if (jsonObject.getString("sf_gd").equals("1")) {
				sfgg = "MGCSF";
				if (jsonObject.getString("sf_sp3").equals("")
						|| jsonObject.getString("sf_sp3").equals("0")) {
					sfggRatios += "0000" + ",";
				} else {
					sfggRatios += jsonObject.getString("sf_sp3") + ",";
				}

				if (jsonObject.getString("sf_sp0").equals("")
						|| jsonObject.getString("sf_sp0").equals("0")) {
					sfggRatios += "0000";
				} else {
					sfggRatios += jsonObject.getString("sf_sp0");
				}
			} else {
				sfggRatios = "-1";
			}
			if (jsonObject.getString("rfsf_fu").equals("1")) {
				rfsfdg = "SGRFSF";
				if (jsonObject.getString("fu_rfsf_sp3").equals("")
						|| jsonObject.getString("fu_rfsf_sp3").equals("0")) {
					rfsfdgRatios += "0000" + ",";
				} else {
					rfsfdgRatios += jsonObject.getString("fu_rfsf_sp3") + ",";
				}
				if (jsonObject.getString("fu_rfsf_sp0").equals("")
						|| jsonObject.getString("fu_rfsf_sp0").equals("0")) {
					rfsfdgRatios += "0000";
				} else {
					rfsfdgRatios += jsonObject.getString("fu_rfsf_sp0");
				}
			} else {
				rfsfdgRatios = "-1";
			}
			if (jsonObject.getString("rfsf_gd").equals("1")) {
				rfsfgg = "MGRFSF";
				// 让球胜平负多关
				if (jsonObject.getString("rfsf_sp3").equals("")
						|| jsonObject.getString("rfsf_sp3").equals("0")) {
					rfsfggRatios += "0000" + ",";
				} else {
					rfsfggRatios += jsonObject.getString("rfsf_sp3") + ",";
				}

				if (jsonObject.getString("rfsf_sp0").equals("")
						|| jsonObject.getString("rfsf_sp0").equals("0")) {
					rfsfggRatios += "0000";
				} else {
					rfsfggRatios += jsonObject.getString("rfsf_sp0");
				}
			} else {
				rfsfggRatios = "-1";
			}
			if (jsonObject.getString("sfc_gd").equals("1")) {
				sfcdg = "SGSFC";
				sfcgg = "MGSFC";
				// 胜分差单关
				if (jsonObject.getString("sfc_sp01").equals("")
						|| jsonObject.getString("sfc_sp01").equals("0")) {
					sfcdgRatios += "0000" + ",";
				} else {
					sfcdgRatios += jsonObject.getString("sfc_sp01") + ",";
				}
				if (jsonObject.getString("sfc_sp02").equals("")
						|| jsonObject.getString("sfc_sp02").equals("0")) {
					sfcdgRatios += "0000" + ",";
				} else {
					sfcdgRatios += jsonObject.getString("sfc_sp02") + ",";
				}
				if (jsonObject.getString("sfc_sp03").equals("")
						|| jsonObject.getString("sfc_sp03").equals("0")) {
					sfcdgRatios += "0000" + ",";
				} else {
					sfcdgRatios += jsonObject.getString("sfc_sp03") + ",";
				}
				if (jsonObject.getString("sfc_sp04").equals("")
						|| jsonObject.getString("sfc_sp04").equals("0")) {
					sfcdgRatios += "0000" + ",";
				} else {
					sfcdgRatios += jsonObject.getString("sfc_sp04") + ",";
				}
				if (jsonObject.getString("sfc_sp05").equals("")
						|| jsonObject.getString("sfc_sp05").equals("0")) {
					sfcdgRatios += "0000" + ",";
				} else {
					sfcdgRatios += jsonObject.getString("sfc_sp05") + ",";
				}
				if (jsonObject.getString("sfc_sp06").equals("")
						|| jsonObject.getString("sfc_sp06").equals("0")) {
					sfcdgRatios += "0000" + ",";
				} else {
					sfcdgRatios += jsonObject.getString("sfc_sp06") + ",";
				}
				if (jsonObject.getString("sfc_sp11").equals("")
						|| jsonObject.getString("sfc_sp11").equals("0")) {
					sfcdgRatios += "0000" + ",";
				} else {
					sfcdgRatios += jsonObject.getString("sfc_sp11") + ",";
				}
				if (jsonObject.getString("sfc_sp12").equals("")
						|| jsonObject.getString("sfc_sp12").equals("0")) {
					sfcdgRatios += "0000" + ",";
				} else {
					sfcdgRatios += jsonObject.getString("sfc_sp12") + ",";
				}
				if (jsonObject.getString("sfc_sp13").equals("")
						|| jsonObject.getString("sfc_sp13").equals("0")) {
					sfcdgRatios += "0000" + ",";
				} else {
					sfcdgRatios += jsonObject.getString("sfc_sp13") + ",";
				}
				if (jsonObject.getString("sfc_sp14").equals("")
						|| jsonObject.getString("sfc_sp14").equals("0")) {
					sfcdgRatios += "0000" + ",";
				} else {
					sfcdgRatios += jsonObject.getString("sfc_sp14") + ",";
				}
				if (jsonObject.getString("sfc_sp15").equals("")
						|| jsonObject.getString("sfc_sp15").equals("0")) {
					sfcdgRatios += "0000" + ",";
				} else {
					sfcdgRatios += jsonObject.getString("sfc_sp15") + ",";
				}
				if (jsonObject.getString("sfc_sp16").equals("")
						|| jsonObject.getString("sfc_sp16").equals("0")) {
					sfcdgRatios += "0000";
				} else {
					sfcdgRatios += jsonObject.getString("sfc_sp16");
				}
				sfcggRatios = sfcdgRatios;
			} else {
				sfcdg = "-1";
				sfcgg = "-1";
			}
			if (jsonObject.getString("dxf_fu").equals("1")) {
				dxfdg = "SGDXF";
				// 大小分单关
				if (jsonObject.getString("fu_dxf_sp0").equals("")
						|| jsonObject.getString("fu_dxf_sp0").equals("0")) {
					dxfdgRatios += "0000" + ",";
				} else {
					dxfdgRatios += jsonObject.getString("dxf_sp0") + ",";
				}
				if (jsonObject.getString("fu_dxf_sp0").equals("")
						|| jsonObject.getString("fu_dxf_sp0").equals("0")) {
					dxfdgRatios += "0000";
				} else {
					dxfdgRatios += jsonObject.getString("fu_dxf_sp0");
				}
			} else {
				dxfdgRatios = "-1";
			}

			if (jsonObject.getString("dxf_gd").equals("1")) {
				dxfgg = "MGDXF";
				// 大小分多关
				if (jsonObject.getString("dxf_sp0").equals("")
						|| jsonObject.getString("dxf_sp0").equals("0")) {
					dxfggRatios += "0000" + ",";
				} else {
					dxfggRatios += jsonObject.getString("dxf_sp0") + ",";
				}
				if (jsonObject.getString("dxf_sp3").equals("")
						|| jsonObject.getString("dxf_sp3").equals("0")) {
					dxfggRatios += "0000";
				} else {
					dxfggRatios += jsonObject.getString("dxf_sp3");
				}
			} else {
				dxfggRatios = "-1";
			}
			String playTypes = sfdg + "|" + sfgg + "|" + rfsfdg + "|" + rfsfgg
					+ "|" + sfcdg + "|" + sfcgg + "|" + dxfdg + "|" + dxfgg;
			match.setPlayTypes(playTypes);
			// 篮球格式：胜负单关;胜负多关| 让分胜负单关;让分胜负多关 | 胜分差单关;胜分差多关 | 大小分单关;大小分多关
			// (胜负单关内部以英文逗号","隔开)
			currentRatios = sfdgRatios + ";" + sfggRatios + "|" + rfsfdgRatios
					+ ";" + rfsfggRatios + "|" + sfcdgRatios + ";"
					+ sfcggRatios + "|" + dxfdgRatios + ";" + dxfggRatios;
			match.setCurrentRatios(currentRatios);

			match.setSaleDate(Calendar.getInstance());
			Calendar stopTime = Calendar.getInstance();
			stopTime.setTime(match.getMatchTime().getTime());
			stopTime = getStopTime(stopTime);
			match.setStopSaleTime(stopTime);

			Calendar openPrizeTime = Calendar.getInstance();
			openPrizeTime.setTime(match.getMatchTime().getTime());
			openPrizeTime.add(Calendar.DATE, 1);
			match.setOpenPrizeTime(openPrizeTime);

			if (match.getStopSaleTime().before(Calendar.getInstance())) {
				match.setStatus(RaceStatus.已停售);
			} else {
				match.setStatus(RaceStatus.销售中);
			}

			if (!jsonObject.get("fu_sf_kjrs").equals("")
					&& !jsonObject.get("fu_sf_kjrs").equals("0")) {

				match.setSpSf(String.valueOf(Float.parseFloat(jsonObject
						.getString("fu_sf_kjrs")) / 2));
			}
			if (!jsonObject.get("fu_rfsf_kjrs").equals("")
					&& !jsonObject.get("fu_rfsf_kjrs").equals("0")) {
				match.setSpRangfenSf(String.valueOf(Float.parseFloat(jsonObject
						.getString("fu_rfsf_kjrs")) / 2));
			}
			if (!jsonObject.get("fu_dxf_kjrs").equals("")
					&& !jsonObject.get("fu_dxf_kjrs").equals("0")) {
				match.setSpDxf(String.valueOf(Float.parseFloat(jsonObject
						.getString("fu_dxf_kjrs")) / 2));
			}

			String mathNo = match.getBoutIndex();
			MatchArrange m = matchArrangeService
					.getUniqueMatchByMatchNo(mathNo);
			if (m != null) {
				if ((m.getStatus() != RaceStatus.已停售)) {
					Calendar oldstopTime = Calendar.getInstance();
					Calendar newstopTime = (Calendar) stopTime.clone();
					oldstopTime.setTime(m.getStopSaleTime().getTime());
					m.setBoutIndex(match.getBoutIndex());
					m.setMatchName(match.getMatchName());
					m.setMatchTime(match.getMatchTime());
					m.setHomeTeam(match.getHomeTeam());
					m.setAwaryTeam(match.getAwaryTeam());
					if (match.getSop().length() > 2)
						m.setSop(match.getSop());
					if (match.getFop().length() > 2)
						m.setFop(match.getFop());
					m.setStzb(match.getStzb());
					m.setFtzb(match.getFtzb());
					m.setCurrentRatios(match.getCurrentRatios());
					m.setDanguanRangFen(match.getDanguanRangFen());
					m.setDuoguanRangFen(match.getDuoguanRangFen());
					m.setDanguanDaXiaoQiu(match.getDanguanDaXiaoQiu());
					m.setDuoguanDaXiaoQiu(match.getDuoguanDaXiaoQiu());
					m.setSaleDate(Calendar.getInstance());
					m.setStopSaleTime(match.getStopSaleTime());
					m.setOpenPrizeTime(match.getOpenPrizeTime());
					if ((m.getStatus() != RaceStatus.已停售)
							&& (m.getStatus() != RaceStatus.已开奖)
							&& (m.getStatus() != RaceStatus.已兑奖)) {
						m.setStatus(match.getStatus());
					}
					newstopTime.add(Calendar.MINUTE, -2);
					if (newstopTime.after(oldstopTime)) { // time changed, add
															// again
						jclqTaskExcutor.addNewMatch(m);
					}

					newstopTime.add(Calendar.MINUTE, 4);
					if (newstopTime.before(oldstopTime)) { // time changed, add
															// again
						jclqTaskExcutor.addNewMatch(m);
					}
				} else {
					m.setWholeScore(match.getWholeScore());
					m.setHalfScore(match.getHalfScore());
					m.setMatchResult(match.getMatchResult());
					m.setHalfResult(match.getHalfResult());
					m.setSfResult(match.getSfResult());
					m.setRfsfResult(match.getRfsfResult());
					m.setDxfResult(match.getDxfResult());
					m.setSpSf(match.getSpSf());
					m.setSpRangfenSf(match.getSpRangfenSf());
					m.setSpDxf(match.getSpDxf());
				}

			} else {
				m = match;
				jclqTaskExcutor.addNewMatch(m);

			}

			LotteryTerm term = lotteryTermService
					.getCurrentTerm(LotteryType.竞彩篮球);
			if (null != m) {

				m.setLotteryType(LotteryType.竞彩篮球);
				m.setTerm(term);
				matchArrangeService.save(m);
			} else {
				logger.info("notfoundterm------");
			}

		}

	}

	private static Calendar getStopTime(Calendar matchtime) {
		Calendar stopTime;
		Calendar matchtime2 = Calendar.getInstance();
		matchtime2 = (Calendar) matchtime.clone();
		matchtime2.add(Calendar.MINUTE, -25);

		Calendar c = Calendar.getInstance();
		Calendar c1 = Calendar.getInstance();

		Calendar c2 = Calendar.getInstance();

		Calendar c3 = Calendar.getInstance();
		Calendar c4 = Calendar.getInstance();
		c.set(matchtime.get(Calendar.YEAR), matchtime.get(Calendar.MONTH),
				matchtime.get(Calendar.DAY_OF_MONTH), 0, 0);
		c1.set(matchtime.get(Calendar.YEAR), matchtime.get(Calendar.MONTH),
				matchtime.get(Calendar.DAY_OF_MONTH), 9, 30);
		c2.set(matchtime.get(Calendar.YEAR), matchtime.get(Calendar.MONTH),
				matchtime.get(Calendar.DAY_OF_MONTH), 23, 25);
		c3.set(matchtime.get(Calendar.YEAR), matchtime.get(Calendar.MONTH),
				matchtime.get(Calendar.DAY_OF_MONTH), 0, 25);

		c4.set(matchtime.get(Calendar.YEAR), matchtime.get(Calendar.MONTH),
				matchtime.get(Calendar.DAY_OF_MONTH), 24, 00);
		if (c.before(matchtime) && c1.after(matchtime)) {
			if (matchtime.get(Calendar.DAY_OF_WEEK) == 1
					|| matchtime.get(Calendar.DAY_OF_WEEK) == 2) {
				stopTime = matchtime2.compareTo(c3) > 0 ? c3 : matchtime2;
			} else {
				c2.set(matchtime.get(Calendar.YEAR), matchtime
						.get(Calendar.MONTH), matchtime
						.get(Calendar.DAY_OF_MONTH) - 1, 22, 40);
				stopTime = matchtime2.compareTo(c2) > 0 ? c2 : matchtime2;
			}
			return stopTime;
		}

		if (c2.before(matchtime) && c4.after(matchtime)) {
			if (matchtime.get(Calendar.DAY_OF_WEEK) != 7
					&& matchtime.get(Calendar.DAY_OF_WEEK) != 1) {
				stopTime = matchtime2.compareTo(c2) > 0 ? c2 : matchtime2;
				return stopTime;
			}
		}
		return matchtime2;
	}

	public static void main(String[] args) {
		String s = "0.32";
		s = String.valueOf(Double.valueOf(s) * 100);
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = format.format(date);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		Date cal = calendar.getTime();
		String date2 = format.format(cal);
		System.out.println(date2 + "|" + date1);

	}
}
