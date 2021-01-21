package com.liuyuan.http.req;


import java.util.Date;
import java.util.TreeMap;


/**
 * ETC运单
 *
 * !! 自动生成的代码，修改前请咨询作者 ！！
 *
 * @Author: ly@kuaihuoyun.com
*/

public class EtcOrderBo {

    /**
    * 主键
    */

    private Long id;


    private String externalId;

    // ignore txnId


    private String serviceProviderCode;

    private String serviceCode;


    private Long companyId;


    private Long vehicleId;


    private String type;


    private String status;


    private String invoiceStatus;


    private String notifyStatus;


    private String sourceAddr;


    private String destAddr;


    private String actualDestAddr;


    private Date startTime;


    private Date predictEndTime;


    private Date endTime;


    private Long fee;

    private TreeMap extJson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getServiceProviderCode() {
        return serviceProviderCode;
    }

    public void setServiceProviderCode(String serviceProviderCode) {
        this.serviceProviderCode = serviceProviderCode;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getNotifyStatus() {
        return notifyStatus;
    }

    public void setNotifyStatus(String notifyStatus) {
        this.notifyStatus = notifyStatus;
    }

    public String getSourceAddr() {
        return sourceAddr;
    }

    public void setSourceAddr(String sourceAddr) {
        this.sourceAddr = sourceAddr;
    }

    public String getDestAddr() {
        return destAddr;
    }

    public void setDestAddr(String destAddr) {
        this.destAddr = destAddr;
    }

    public String getActualDestAddr() {
        return actualDestAddr;
    }

    public void setActualDestAddr(String actualDestAddr) {
        this.actualDestAddr = actualDestAddr;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getPredictEndTime() {
        return predictEndTime;
    }

    public void setPredictEndTime(Date predictEndTime) {
        this.predictEndTime = predictEndTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public TreeMap getExtJson() {
        return extJson;
    }

    public void setExtJson(TreeMap extJson) {
        this.extJson = extJson;
    }

    // ignore createTime

    // ignore updateTime

}
