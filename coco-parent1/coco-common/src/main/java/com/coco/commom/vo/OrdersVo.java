package com.coco.commom.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by geely
 */
public class OrdersVo {

    private Long orderNo;

    private BigDecimal payment;

    private Integer payType;

    private BigDecimal postage;

    private Integer status;

    private Date payTime;

    private Date sendTime;

    private Date endTime;

    private Date createTime;

    private Date updateTime;

    //订单明细

    private List<OrdersInfoVo> ordersInfoVosList;

    private String pic;


    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public BigDecimal getPostage() {
        return postage;
    }

    public void setPostage(BigDecimal postage) {
        this.postage = postage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<OrdersInfoVo> getOrdersInfoVosList() {
        return ordersInfoVosList;
    }

    public void setOrdersInfoVosList(List<OrdersInfoVo> ordersInfoVosList) {
        this.ordersInfoVosList = ordersInfoVosList;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public AddressVo getAddressVo() {
        return addressVo;
    }

    public void setAddressVo(AddressVo addressVo) {
        this.addressVo = addressVo;
    }

    private Integer addressId;
    private String receiverName;

    private AddressVo addressVo;

    @Override
    public String toString() {
        return "OrdersVo{" +
                "orderNo=" + orderNo +
                ", payment=" + payment +
                ", payType=" + payType +
                ", postage=" + postage +
                ", status=" + status +
                ", payTime=" + payTime +
                ", sendTime=" + sendTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", ordersInfoVosList=" + ordersInfoVosList +
                ", pic='" + pic + '\'' +
                ", addressId=" + addressId +
                ", receiverName='" + receiverName + '\'' +
                ", addressVo=" + addressVo +
                '}';
    }
}
