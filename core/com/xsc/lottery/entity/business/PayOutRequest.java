package com.xsc.lottery.entity.business;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xsc.lottery.entity.BaseObject;

/** 提款申请 */
@SuppressWarnings("serial")
@Entity
@Table(name = "business_pay_out_request")
@SequenceGenerator(name = "S_PayOutRequest", allocationSize = 1, initialValue = 1, sequenceName = "S_PayOutRequest")
public class PayOutRequest extends BaseObject
{
	    @Id
	    @GeneratedValue
	    private Long id;
	    
	    /***流水号 */
	    @Column(nullable = false)
		private String YURREF;
	    
	    /**主管审批时间*/
	    private Calendar time;
		
		/***付款账号*/
		private String DBTACC;
		
		/***付款方开户地区代码*/
		private String DBTBBK;
		
		/***付款方开户地区名称*/
		private String C_DBTBBK;
		
		/***开户行名称*/
		private String DBTBNK;
		
		/***付款账号用户名*/
		private String DBTNAM;
		
		/***付款公司名称*/
		private String DBTREL;
		
		/***币种代码*/
		private String CCYNBR;
		
		/***结算方式 N普通F快速*/
		private String STLCHN;
		
		/***付款用途*/
		private String NUSAGE;
		
		/***收方开户行*/
		private String CRTBNK;
		
		/***开户城市代码*/
		private String CTYCOD;
		
		/***开户城市名称*/
		private String CRTPVC;
		
		/***记录当前状态*/
		private int State;
		
		/***记录当前状态描述*/
		private String StateDesc;
		
		/***记录当前状态修改时间*/
		private Calendar StateTime;
		
		/**进程标识*/
		private int progressFlag;
		
		/**用户提款申请*/
		@OneToOne
		@JoinColumn(nullable=false)
		private BackMoneyRequest backMoneyRequest;
		
		/**最终打款（扣除手续费后）*/
		private BigDecimal money;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getYURREF() {
			return YURREF;
		}

		public void setYURREF(String yurref) {
			YURREF = yurref;
		}

		public String getDBTACC() {
			return DBTACC;
		}

		public void setDBTACC(String dbtacc) {
			DBTACC = dbtacc;
		}

		public String getDBTBBK() {
			return DBTBBK;
		}

		public void setDBTBBK(String dbtbbk) {
			DBTBBK = dbtbbk;
		}

		public String getC_DBTBBK() {
			return C_DBTBBK;
		}

		public void setC_DBTBBK(String c_dbtbbk) {
			C_DBTBBK = c_dbtbbk;
		}

		public String getDBTBNK() {
			return DBTBNK;
		}

		public void setDBTBNK(String dbtbnk) {
			DBTBNK = dbtbnk;
		}

		public String getDBTNAM() {
			return DBTNAM;
		}

		public void setDBTNAM(String dbtnam) {
			DBTNAM = dbtnam;
		}

		public String getDBTREL() {
			return DBTREL;
		}

		public void setDBTREL(String dbtrel) {
			DBTREL = dbtrel;
		}

		public String getCCYNBR() {
			return CCYNBR;
		}

		public void setCCYNBR(String ccynbr) {
			CCYNBR = ccynbr;
		}

		public String getSTLCHN() {
			return STLCHN;
		}

		public void setSTLCHN(String stlchn) {
			STLCHN = stlchn;
		}

		public String getNUSAGE() {
			return NUSAGE;
		}

		public void setNUSAGE(String nusage) {
			NUSAGE = nusage;
		}

		public String getCRTBNK() {
			return CRTBNK;
		}

		public void setCRTBNK(String crtbnk) {
			CRTBNK = crtbnk;
		}

		public String getCTYCOD() {
			return CTYCOD;
		}

		public void setCTYCOD(String ctycod) {
			CTYCOD = ctycod;
		}

		public String getCRTPVC() {
			return CRTPVC;
		}

		public void setCRTPVC(String crtpvc) {
			CRTPVC = crtpvc;
		}

		public BackMoneyRequest getBackMoneyRequest() {
			return backMoneyRequest;
		}

		public void setBackMoneyRequest(BackMoneyRequest backMoneyRequest) {
			this.backMoneyRequest = backMoneyRequest;
		}

		public int getState() {
			return State;
		}

		public void setState(int state) {
			State = state;
		}

		public String getStateDesc() {
			return StateDesc;
		}

		public void setStateDesc(String stateDesc) {
			StateDesc = stateDesc;
		}

		public Calendar getStateTime() {
			return StateTime;
		}

		public void setStateTime(Calendar stateTime) {
			StateTime = stateTime;
		}

		public Calendar getTime() {
			return time;
		}

		public void setTime(Calendar time) {
			this.time = time;
		}

		public int getProgressFlag() {
			return progressFlag;
		}

		public void setProgressFlag(int progressFlag) {
			this.progressFlag = progressFlag;
		}

		public BigDecimal getMoney() {
			return money;
		}

		public void setMoney(BigDecimal money) {
			this.money = money;
		}
}
