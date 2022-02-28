package com.zzj.springboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhaozj37918
 * @date 2022年01月25日 17:52
 */
public class ProtocolInfo implements Serializable {
    /**
     * 协议主键
     */
    @TableId(value = "PROTOCOL_KEY", type = IdType.INPUT)
    private String protocolKey;
    /**
     * 限额key
     */
    private String limitKey;

    public ProtocolInfo(String protocolKey, String limitKey, String payReceiveFlag, String onlineProtocolFlag, BigDecimal newAcctAmt, Integer errorMaxNum, Integer errorContinuedNum, String payChannel, String protocolType, String protocolNo, String outProtocolKey, String protocolClass, String signNo, String signChannel, String sendRecvFlag, String billType, String corpCode, String corpName, String feeItemCode, String sendBankNo, String sendSettleBank, String recvBankNo, String recvSettleBank, String msgId, String busiTypeCode, String busiKindCode, String subsysBusiKind, String subsysBusiType, String payerAcctType, String payerSettleBank, String payerBank, String payerBankName, String payerOpenBank, String payerOpenBankName, String payerAcctAttr, String payerAcct, String payerAcctName, String payerIdType, String payerIdNum, String payerMobileNo, String payeeSettleBank, String payeeBank, String payeeBankName, String payeeOpenBank, String payeeOpenBankName, String payeeAcctAttr, String payeeAcctType, String payeeAcct, String payeeAcctName, String payeeIdType, String payeeIdNum, String payeeMobileNo, String acct, String acctName, String outCustNo, String customerCode, String addr, String payerTelNo, String opponentAcct, String opponentName, BigDecimal singleMinAmt, BigDecimal singleMaxQuota, BigDecimal dayCumulateMax, Integer dayCumulateLimit, BigDecimal monthlyCumulateMax, Integer monthlyCumulateLimit, String currCumulateDate, BigDecimal dayCumulateAmt, Integer dayDrawTimes, BigDecimal monthlyCumulateAmt, Integer monthlyDrawTimes, String effectiveFlag, String effectiveDate, String invalidDate, String branch, String entryTeller, String checkDate, String maintainDate, String maintainTime, String releaseDate, String releaseTime, String pltDate, String pltTime, String productReserve1, String productReserve2, String productReserve3, String extField1, String extField2, String extField3, String extField4, String extField5, String extField6, String createDatetime, String updateDatetime, String limitQuotaFlag, String thirdProtocolNo, String busiExtParams) {
        this.protocolKey = protocolKey;
        this.limitKey = limitKey;
        this.payReceiveFlag = payReceiveFlag;
        this.onlineProtocolFlag = onlineProtocolFlag;
        this.newAcctAmt = newAcctAmt;
        this.errorMaxNum = errorMaxNum;
        this.errorContinuedNum = errorContinuedNum;
        this.payChannel = payChannel;
        this.protocolType = protocolType;
        this.protocolNo = protocolNo;
        this.outProtocolKey = outProtocolKey;
        this.protocolClass = protocolClass;
        this.signNo = signNo;
        this.signChannel = signChannel;
        this.sendRecvFlag = sendRecvFlag;
        this.billType = billType;
        this.corpCode = corpCode;
        this.corpName = corpName;
        this.feeItemCode = feeItemCode;
        this.sendBankNo = sendBankNo;
        this.sendSettleBank = sendSettleBank;
        this.recvBankNo = recvBankNo;
        this.recvSettleBank = recvSettleBank;
        this.msgId = msgId;
        this.busiTypeCode = busiTypeCode;
        this.busiKindCode = busiKindCode;
        this.subsysBusiKind = subsysBusiKind;
        this.subsysBusiType = subsysBusiType;
        this.payerAcctType = payerAcctType;
        this.payerSettleBank = payerSettleBank;
        this.payerBank = payerBank;
        this.payerBankName = payerBankName;
        this.payerOpenBank = payerOpenBank;
        this.payerOpenBankName = payerOpenBankName;
        this.payerAcctAttr = payerAcctAttr;
        this.payerAcct = payerAcct;
        this.payerAcctName = payerAcctName;
        this.payerIdType = payerIdType;
        this.payerIdNum = payerIdNum;
        this.payerMobileNo = payerMobileNo;
        this.payeeSettleBank = payeeSettleBank;
        this.payeeBank = payeeBank;
        this.payeeBankName = payeeBankName;
        this.payeeOpenBank = payeeOpenBank;
        this.payeeOpenBankName = payeeOpenBankName;
        this.payeeAcctAttr = payeeAcctAttr;
        this.payeeAcctType = payeeAcctType;
        this.payeeAcct = payeeAcct;
        this.payeeAcctName = payeeAcctName;
        this.payeeIdType = payeeIdType;
        this.payeeIdNum = payeeIdNum;
        this.payeeMobileNo = payeeMobileNo;
        this.acct = acct;
        this.acctName = acctName;
        this.outCustNo = outCustNo;
        this.customerCode = customerCode;
        this.addr = addr;
        this.payerTelNo = payerTelNo;
        this.opponentAcct = opponentAcct;
        this.opponentName = opponentName;
        this.singleMinAmt = singleMinAmt;
        this.singleMaxQuota = singleMaxQuota;
        this.dayCumulateMax = dayCumulateMax;
        this.dayCumulateLimit = dayCumulateLimit;
        this.monthlyCumulateMax = monthlyCumulateMax;
        this.monthlyCumulateLimit = monthlyCumulateLimit;
        this.currCumulateDate = currCumulateDate;
        this.dayCumulateAmt = dayCumulateAmt;
        this.dayDrawTimes = dayDrawTimes;
        this.monthlyCumulateAmt = monthlyCumulateAmt;
        this.monthlyDrawTimes = monthlyDrawTimes;
        this.effectiveFlag = effectiveFlag;
        this.effectiveDate = effectiveDate;
        this.invalidDate = invalidDate;
        this.branch = branch;
        this.entryTeller = entryTeller;
        this.checkDate = checkDate;
        this.maintainDate = maintainDate;
        this.maintainTime = maintainTime;
        this.releaseDate = releaseDate;
        this.releaseTime = releaseTime;
        this.pltDate = pltDate;
        this.pltTime = pltTime;
        this.productReserve1 = productReserve1;
        this.productReserve2 = productReserve2;
        this.productReserve3 = productReserve3;
        this.extField1 = extField1;
        this.extField2 = extField2;
        this.extField3 = extField3;
        this.extField4 = extField4;
        this.extField5 = extField5;
        this.extField6 = extField6;
        this.createDatetime = createDatetime;
        this.updateDatetime = updateDatetime;
        this.limitQuotaFlag = limitQuotaFlag;
        this.thirdProtocolNo = thirdProtocolNo;
        this.busiExtParams = busiExtParams;
    }

    public ProtocolInfo() {

    }

    public String getProtocolKey() {
        return protocolKey;
    }

    public void setProtocolKey(String protocolKey) {
        this.protocolKey = protocolKey;
    }

    public String getLimitKey() {
        return limitKey;
    }

    public void setLimitKey(String limitKey) {
        this.limitKey = limitKey;
    }

    public String getPayReceiveFlag() {
        return payReceiveFlag;
    }

    public void setPayReceiveFlag(String payReceiveFlag) {
        this.payReceiveFlag = payReceiveFlag;
    }

    public String getOnlineProtocolFlag() {
        return onlineProtocolFlag;
    }

    public void setOnlineProtocolFlag(String onlineProtocolFlag) {
        this.onlineProtocolFlag = onlineProtocolFlag;
    }

    public BigDecimal getNewAcctAmt() {
        return newAcctAmt;
    }

    public void setNewAcctAmt(BigDecimal newAcctAmt) {
        this.newAcctAmt = newAcctAmt;
    }

    public Integer getErrorMaxNum() {
        return errorMaxNum;
    }

    public void setErrorMaxNum(Integer errorMaxNum) {
        this.errorMaxNum = errorMaxNum;
    }

    public Integer getErrorContinuedNum() {
        return errorContinuedNum;
    }

    public void setErrorContinuedNum(Integer errorContinuedNum) {
        this.errorContinuedNum = errorContinuedNum;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public String getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(String protocolType) {
        this.protocolType = protocolType;
    }

    public String getProtocolNo() {
        return protocolNo;
    }

    public void setProtocolNo(String protocolNo) {
        this.protocolNo = protocolNo;
    }

    public String getOutProtocolKey() {
        return outProtocolKey;
    }

    public void setOutProtocolKey(String outProtocolKey) {
        this.outProtocolKey = outProtocolKey;
    }

    public String getProtocolClass() {
        return protocolClass;
    }

    public void setProtocolClass(String protocolClass) {
        this.protocolClass = protocolClass;
    }

    public String getSignNo() {
        return signNo;
    }

    public void setSignNo(String signNo) {
        this.signNo = signNo;
    }

    public String getSignChannel() {
        return signChannel;
    }

    public void setSignChannel(String signChannel) {
        this.signChannel = signChannel;
    }

    public String getSendRecvFlag() {
        return sendRecvFlag;
    }

    public void setSendRecvFlag(String sendRecvFlag) {
        this.sendRecvFlag = sendRecvFlag;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getFeeItemCode() {
        return feeItemCode;
    }

    public void setFeeItemCode(String feeItemCode) {
        this.feeItemCode = feeItemCode;
    }

    public String getSendBankNo() {
        return sendBankNo;
    }

    public void setSendBankNo(String sendBankNo) {
        this.sendBankNo = sendBankNo;
    }

    public String getSendSettleBank() {
        return sendSettleBank;
    }

    public void setSendSettleBank(String sendSettleBank) {
        this.sendSettleBank = sendSettleBank;
    }

    public String getRecvBankNo() {
        return recvBankNo;
    }

    public void setRecvBankNo(String recvBankNo) {
        this.recvBankNo = recvBankNo;
    }

    public String getRecvSettleBank() {
        return recvSettleBank;
    }

    public void setRecvSettleBank(String recvSettleBank) {
        this.recvSettleBank = recvSettleBank;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getBusiTypeCode() {
        return busiTypeCode;
    }

    public void setBusiTypeCode(String busiTypeCode) {
        this.busiTypeCode = busiTypeCode;
    }

    public String getBusiKindCode() {
        return busiKindCode;
    }

    public void setBusiKindCode(String busiKindCode) {
        this.busiKindCode = busiKindCode;
    }

    public String getSubsysBusiKind() {
        return subsysBusiKind;
    }

    public void setSubsysBusiKind(String subsysBusiKind) {
        this.subsysBusiKind = subsysBusiKind;
    }

    public String getSubsysBusiType() {
        return subsysBusiType;
    }

    public void setSubsysBusiType(String subsysBusiType) {
        this.subsysBusiType = subsysBusiType;
    }

    public String getPayerAcctType() {
        return payerAcctType;
    }

    public void setPayerAcctType(String payerAcctType) {
        this.payerAcctType = payerAcctType;
    }

    public String getPayerSettleBank() {
        return payerSettleBank;
    }

    public void setPayerSettleBank(String payerSettleBank) {
        this.payerSettleBank = payerSettleBank;
    }

    public String getPayerBank() {
        return payerBank;
    }

    public void setPayerBank(String payerBank) {
        this.payerBank = payerBank;
    }

    public String getPayerBankName() {
        return payerBankName;
    }

    public void setPayerBankName(String payerBankName) {
        this.payerBankName = payerBankName;
    }

    public String getPayerOpenBank() {
        return payerOpenBank;
    }

    public void setPayerOpenBank(String payerOpenBank) {
        this.payerOpenBank = payerOpenBank;
    }

    public String getPayerOpenBankName() {
        return payerOpenBankName;
    }

    public void setPayerOpenBankName(String payerOpenBankName) {
        this.payerOpenBankName = payerOpenBankName;
    }

    public String getPayerAcctAttr() {
        return payerAcctAttr;
    }

    public void setPayerAcctAttr(String payerAcctAttr) {
        this.payerAcctAttr = payerAcctAttr;
    }

    public String getPayerAcct() {
        return payerAcct;
    }

    public void setPayerAcct(String payerAcct) {
        this.payerAcct = payerAcct;
    }

    public String getPayerAcctName() {
        return payerAcctName;
    }

    public void setPayerAcctName(String payerAcctName) {
        this.payerAcctName = payerAcctName;
    }

    public String getPayerIdType() {
        return payerIdType;
    }

    public void setPayerIdType(String payerIdType) {
        this.payerIdType = payerIdType;
    }

    public String getPayerIdNum() {
        return payerIdNum;
    }

    public void setPayerIdNum(String payerIdNum) {
        this.payerIdNum = payerIdNum;
    }

    public String getPayerMobileNo() {
        return payerMobileNo;
    }

    public void setPayerMobileNo(String payerMobileNo) {
        this.payerMobileNo = payerMobileNo;
    }

    public String getPayeeSettleBank() {
        return payeeSettleBank;
    }

    public void setPayeeSettleBank(String payeeSettleBank) {
        this.payeeSettleBank = payeeSettleBank;
    }

    public String getPayeeBank() {
        return payeeBank;
    }

    public void setPayeeBank(String payeeBank) {
        this.payeeBank = payeeBank;
    }

    public String getPayeeBankName() {
        return payeeBankName;
    }

    public void setPayeeBankName(String payeeBankName) {
        this.payeeBankName = payeeBankName;
    }

    public String getPayeeOpenBank() {
        return payeeOpenBank;
    }

    public void setPayeeOpenBank(String payeeOpenBank) {
        this.payeeOpenBank = payeeOpenBank;
    }

    public String getPayeeOpenBankName() {
        return payeeOpenBankName;
    }

    public void setPayeeOpenBankName(String payeeOpenBankName) {
        this.payeeOpenBankName = payeeOpenBankName;
    }

    public String getPayeeAcctAttr() {
        return payeeAcctAttr;
    }

    public void setPayeeAcctAttr(String payeeAcctAttr) {
        this.payeeAcctAttr = payeeAcctAttr;
    }

    public String getPayeeAcctType() {
        return payeeAcctType;
    }

    public void setPayeeAcctType(String payeeAcctType) {
        this.payeeAcctType = payeeAcctType;
    }

    public String getPayeeAcct() {
        return payeeAcct;
    }

    public void setPayeeAcct(String payeeAcct) {
        this.payeeAcct = payeeAcct;
    }

    public String getPayeeAcctName() {
        return payeeAcctName;
    }

    public void setPayeeAcctName(String payeeAcctName) {
        this.payeeAcctName = payeeAcctName;
    }

    public String getPayeeIdType() {
        return payeeIdType;
    }

    public void setPayeeIdType(String payeeIdType) {
        this.payeeIdType = payeeIdType;
    }

    public String getPayeeIdNum() {
        return payeeIdNum;
    }

    public void setPayeeIdNum(String payeeIdNum) {
        this.payeeIdNum = payeeIdNum;
    }

    public String getPayeeMobileNo() {
        return payeeMobileNo;
    }

    public void setPayeeMobileNo(String payeeMobileNo) {
        this.payeeMobileNo = payeeMobileNo;
    }

    public String getAcct() {
        return acct;
    }

    public void setAcct(String acct) {
        this.acct = acct;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public String getOutCustNo() {
        return outCustNo;
    }

    public void setOutCustNo(String outCustNo) {
        this.outCustNo = outCustNo;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPayerTelNo() {
        return payerTelNo;
    }

    public void setPayerTelNo(String payerTelNo) {
        this.payerTelNo = payerTelNo;
    }

    public String getOpponentAcct() {
        return opponentAcct;
    }

    public void setOpponentAcct(String opponentAcct) {
        this.opponentAcct = opponentAcct;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public BigDecimal getSingleMinAmt() {
        return singleMinAmt;
    }

    public void setSingleMinAmt(BigDecimal singleMinAmt) {
        this.singleMinAmt = singleMinAmt;
    }

    public BigDecimal getSingleMaxQuota() {
        return singleMaxQuota;
    }

    public void setSingleMaxQuota(BigDecimal singleMaxQuota) {
        this.singleMaxQuota = singleMaxQuota;
    }

    public BigDecimal getDayCumulateMax() {
        return dayCumulateMax;
    }

    public void setDayCumulateMax(BigDecimal dayCumulateMax) {
        this.dayCumulateMax = dayCumulateMax;
    }

    public Integer getDayCumulateLimit() {
        return dayCumulateLimit;
    }

    public void setDayCumulateLimit(Integer dayCumulateLimit) {
        this.dayCumulateLimit = dayCumulateLimit;
    }

    public BigDecimal getMonthlyCumulateMax() {
        return monthlyCumulateMax;
    }

    public void setMonthlyCumulateMax(BigDecimal monthlyCumulateMax) {
        this.monthlyCumulateMax = monthlyCumulateMax;
    }

    public Integer getMonthlyCumulateLimit() {
        return monthlyCumulateLimit;
    }

    public void setMonthlyCumulateLimit(Integer monthlyCumulateLimit) {
        this.monthlyCumulateLimit = monthlyCumulateLimit;
    }

    public String getCurrCumulateDate() {
        return currCumulateDate;
    }

    public void setCurrCumulateDate(String currCumulateDate) {
        this.currCumulateDate = currCumulateDate;
    }

    public BigDecimal getDayCumulateAmt() {
        return dayCumulateAmt;
    }

    public void setDayCumulateAmt(BigDecimal dayCumulateAmt) {
        this.dayCumulateAmt = dayCumulateAmt;
    }

    public Integer getDayDrawTimes() {
        return dayDrawTimes;
    }

    public void setDayDrawTimes(Integer dayDrawTimes) {
        this.dayDrawTimes = dayDrawTimes;
    }

    public BigDecimal getMonthlyCumulateAmt() {
        return monthlyCumulateAmt;
    }

    public void setMonthlyCumulateAmt(BigDecimal monthlyCumulateAmt) {
        this.monthlyCumulateAmt = monthlyCumulateAmt;
    }

    public Integer getMonthlyDrawTimes() {
        return monthlyDrawTimes;
    }

    public void setMonthlyDrawTimes(Integer monthlyDrawTimes) {
        this.monthlyDrawTimes = monthlyDrawTimes;
    }

    public String getEffectiveFlag() {
        return effectiveFlag;
    }

    public void setEffectiveFlag(String effectiveFlag) {
        this.effectiveFlag = effectiveFlag;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(String invalidDate) {
        this.invalidDate = invalidDate;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getEntryTeller() {
        return entryTeller;
    }

    public void setEntryTeller(String entryTeller) {
        this.entryTeller = entryTeller;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getMaintainDate() {
        return maintainDate;
    }

    public void setMaintainDate(String maintainDate) {
        this.maintainDate = maintainDate;
    }

    public String getMaintainTime() {
        return maintainTime;
    }

    public void setMaintainTime(String maintainTime) {
        this.maintainTime = maintainTime;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getPltDate() {
        return pltDate;
    }

    public void setPltDate(String pltDate) {
        this.pltDate = pltDate;
    }

    public String getPltTime() {
        return pltTime;
    }

    public void setPltTime(String pltTime) {
        this.pltTime = pltTime;
    }

    public String getProductReserve1() {
        return productReserve1;
    }

    public void setProductReserve1(String productReserve1) {
        this.productReserve1 = productReserve1;
    }

    public String getProductReserve2() {
        return productReserve2;
    }

    public void setProductReserve2(String productReserve2) {
        this.productReserve2 = productReserve2;
    }

    public String getProductReserve3() {
        return productReserve3;
    }

    public void setProductReserve3(String productReserve3) {
        this.productReserve3 = productReserve3;
    }

    public String getExtField1() {
        return extField1;
    }

    public void setExtField1(String extField1) {
        this.extField1 = extField1;
    }

    public String getExtField2() {
        return extField2;
    }

    public void setExtField2(String extField2) {
        this.extField2 = extField2;
    }

    public String getExtField3() {
        return extField3;
    }

    public void setExtField3(String extField3) {
        this.extField3 = extField3;
    }

    public String getExtField4() {
        return extField4;
    }

    public void setExtField4(String extField4) {
        this.extField4 = extField4;
    }

    public String getExtField5() {
        return extField5;
    }

    public void setExtField5(String extField5) {
        this.extField5 = extField5;
    }

    public String getExtField6() {
        return extField6;
    }

    public void setExtField6(String extField6) {
        this.extField6 = extField6;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(String updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getLimitQuotaFlag() {
        return limitQuotaFlag;
    }

    public void setLimitQuotaFlag(String limitQuotaFlag) {
        this.limitQuotaFlag = limitQuotaFlag;
    }

    public String getThirdProtocolNo() {
        return thirdProtocolNo;
    }

    public void setThirdProtocolNo(String thirdProtocolNo) {
        this.thirdProtocolNo = thirdProtocolNo;
    }

    public String getBusiExtParams() {
        return busiExtParams;
    }

    public void setBusiExtParams(String busiExtParams) {
        this.busiExtParams = busiExtParams;
    }

    /**
     * D001: 付款 (行内用于付款)
     D002: 收款 (行内用于收款)
     */
    private String payReceiveFlag;

    /**
     * 线上协议标志
     */
    private String onlineProtocolFlag;

    /**
     * 开户时打款验证金额
     */
    private BigDecimal newAcctAmt;

    /**
     * 打款金额最大校验次数
     */
    private Integer errorMaxNum;

    /**
     * 打款金额已校验次数
     */
    private Integer errorContinuedNum;

    /**
     * 支付通道
     */
    private String payChannel;

    /**
     * CT00：代收协议
     CT01：代付协议
     */
    private String protocolType;

    /**
     * 协议号
     */
    private String protocolNo;

    /**
     * 外部协议主键
     */
    private String outProtocolKey;

    /**
     * 00  代收付协议
     10  定期贷记协议
     11  定期借记协议
     21  公用事业扣款协议  (四川同城)
     22  非公用事业扣款协议  (四川同城)
     23  小额快速扣款协议  (四川同城)
     24  个人扣款协议  (四川同城)
     */
    private String protocolClass;

    /**
     * 签约编号
     */
    private String signNo;

    /**
     * 签约渠道代码
     */
    private String signChannel;

    /**
     * 往来账标志
     */
    private String sendRecvFlag;

    /**
     * 00  代收付协议
     10  定期贷记协议
     11  定期借记协议
     */
    private String billType;

    /**
     * 企业代码
     */
    private String corpCode;

    /**
     * 企业名称/单位名称
     */
    private String corpName;

    /**
     * 费项代码
     */
    private String feeItemCode;

    /**
     * 发起行行号
     */
    private String sendBankNo;

    /**
     * 发起清算行行号
     */
    private String sendSettleBank;

    /**
     * 接收行行号/收款行号
     */
    private String recvBankNo;

    /**
     * 接收清算行行号
     */
    private String recvSettleBank;

    /**
     * 报文标识号
     */
    private String msgId;

    /**
     * 00  代收付协议
     10  定期贷记协议
     11  定期借记协议
     */
    private String busiTypeCode;

    /**
     * 业务种类
     */
    private String busiKindCode;

    /**
     * 子系统业务种类编码
     */
    private String subsysBusiKind;

    /**
     * 子系统业务种类编码
     */
    private String subsysBusiType;

    /**
     * 交易限定付款账户类型
     */
    private String payerAcctType;

    /**
     * 直接发起机构
     */
    private String payerSettleBank;

    /**
     * 付款行行号/承付行行号
     */
    private String payerBank;

    /**
     * 付款行名称
     */
    private String payerBankName;

    /**
     * 付款人开户行行号
     */
    private String payerOpenBank;

    /**
     * 付款人开户行行号
     */
    private String payerOpenBankName;

    /**
     * 付款人账户性质
     */
    private String payerAcctAttr;

    /**
     * 付方账号
     */
    private String payerAcct;

    /**
     * 付款人名称
     */
    private String payerAcctName;

    /**
     * 付款证件类型
     */
    private String payerIdType;

    /**
     * 付款人证件号码
     */
    private String payerIdNum;

    /**
     * 付款人手机号码
     */
    private String payerMobileNo;

    /**
     * 直接发起机构
     */
    private String payeeSettleBank;

    /**
     * 收款行行号
     */
    private String payeeBank;

    /**
     * 收款行行名
     */
    private String payeeBankName;

    /**
     * 收款人开户行号
     */
    private String payeeOpenBank;

    /**
     * 收款人开户行名
     */
    private String payeeOpenBankName;

    /**
     * 收款人账户性质
     */
    private String payeeAcctAttr;

    /**
     * 收款账户类型
     */
    private String payeeAcctType;

    /**
     * 收款人账号
     */
    private String payeeAcct;

    /**
     * 收款人名称
     */
    private String payeeAcctName;

    /**
     * 收款证件类型
     */
    private String payeeIdType;

    /**
     * 收款人证件号码
     */
    private String payeeIdNum;

    /**
     * 收款人手机号码
     */
    private String payeeMobileNo;


    /**
     * 存本行账号和客户签约账号
     */
    private String acct;

    /**
     * 本行户名或者客户签约户名
     */
    private String acctName;

    /**
     * 第三方客户号
     */
    private String outCustNo;

    /**
     * 存本行账号和客户签约账号
     */
    private String customerCode;

    /**
     * 地址
     */
    private String addr;

    /**
     * 付款人电话
     */
    private String payerTelNo;

    /**
     * 他行账号
     */
    private String opponentAcct;

    /**
     * 他行户名
     */
    private String opponentName;

    /**
     * 单笔金额下限
     */
    private BigDecimal singleMinAmt;

    /**
     * 单笔最高额度
     */
    private BigDecimal singleMaxQuota;

    /**
     * 日累计额度
     */
    private BigDecimal dayCumulateMax;

    /**
     * 日累计笔数上限
     */
    private Integer dayCumulateLimit;

    /**
     * 月累计额度上限
     */
    private BigDecimal monthlyCumulateMax;

    /**
     * 月累计笔数上限
     */
    private Integer monthlyCumulateLimit;

    /**
     * 当前累计日期
     */
    private String currCumulateDate;

    /**
     * 日累计金额
     */
    private BigDecimal dayCumulateAmt;

    /**
     * 日取款次数
     */
    private Integer dayDrawTimes;

    /**
     * 月累计金额
     */
    private BigDecimal monthlyCumulateAmt;

    /**
     * 月取款次数
     */
    private Integer monthlyDrawTimes;

    /**
     * 0 - 待生效
     1 - 生效
     9 - 失效
     */
    private String effectiveFlag;

    /**
     * 生效日期
     */
    private String effectiveDate;

    /**
     * 作废日期
     */
    private String invalidDate;

    /**
     * 交易机构代码
     */
    private String branch;

    /**
     * 录入柜员
     */
    private String entryTeller;

    /**
     * 对账日期、核对日期
     */
    private String checkDate;

    /**
     * 录入日期
     */
    private String maintainDate;

    /**
     * 录入时间
     */
    private String maintainTime;

    /**
     * 机器日期
     */
    private String releaseDate;

    /**
     * 机器时间
     */
    private String releaseTime;

    /**
     * 机器日期
     */
    private String pltDate;

    /**
     * 机器时间
     */
    private String pltTime;

    /**
     * 产品保留字段1
     */
    private String productReserve1;

    /**
     * 产品保留字段2
     */
    private String productReserve2;

    /**
     * 产品保留字段3
     */
    private String productReserve3;

    /**
     * 扩展字段1
     */
    private String extField1;

    /**
     * 扩展字段2
     */
    private String extField2;

    /**
     * 扩展字段3
     */
    private String extField3;

    /**
     * 扩展字段4
     */
    private String extField4;

    /**
     * 扩展字段5
     */
    private String extField5;

    /**
     * 扩展字段6
     */
    private String extField6;

    /**
     * 创建时间
     */
    private String createDatetime;

    /**
     * 更新时间
     */
    private String updateDatetime;

    /**
     * 是否校验限额，为空或1都校验，只有0不校验
     */
    private String limitQuotaFlag;

    /**
     * 三方协议号
     */
    private String thirdProtocolNo;

    /**
     * 业务辅助要素集
     */
    private String busiExtParams;

}
