package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单对象 sys_order
 *
 * @author ruoyi
 * @date 2023-03-12
 */
public class SysOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单id */
    private Long orderId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商品类别 */
    @Excel(name = "商品类别")
    private Long goodsKind;

    /** 放款规则 */
    @Excel(name = "放款规则")
    private String tradeRule;

    /** 收款地址 */
    @Excel(name = "收款地址")
    private String tradeAddress;

    /** 收款金额 */
    @Excel(name = "收款金额")
    private double tradeMoney;

    /** 担保服务费 */
    @Excel(name = "担保服务费")
    private double tradeMoneyService;

    /** 我方支付金额 */
    @Excel(name = "我方支付金额")
    private double tradeMoneyMe;

    /** 对方支付金额 */
    @Excel(name = "对方支付金额")
    private double tradeMoneyYou;

    /** 收款密码 */
    @Excel(name = "收款密码")
    private String tradePassword;

    /** 收款码地址 */
    @Excel(name = "收款码地址")
    private String tradePasswordYou;

    /** 交易码 */
    @Excel(name = "交易码")
    private String tradeCode;

    /** 交易网址 */
    @Excel(name = "交易网址")
    private String tradeUrl;

    /** 状态：0.正常 1.关闭 */
    @Excel(name = "状态：" +
            "0.交易进行中\n" +
            "1.商家已确认收款\n" +
            "2.收款方已发货\n" +
            "3.付款方已收货，交易完成\n" +
            "4 .取消交易")
    private String status;

    /** 是否收到 ： 0.收到 1.未收到 */
    @Excel(name = "是否收到 ： 0.收到 1.未收到")
    private String recive;

    /** 是否发货 ： 0.已发货 1.未发货 */
    @Excel(name = "是否发货 ： 0.已发货 1.未发货")
    private String send;

    /**  0.确认收货 1.拒绝收货 */
    @Excel(name = " 0.确认收货 1.拒绝收货")
    private String define;

    public void setSend(String send)
    {
        this.send = send;
    }

    public String getSend()
    {
        return send;
    }
    public void setDefine(String define)
    {
        this.define = define;
    }

    public String getDefine()
    {
        return define;
    }

    public void setRecive(String recive)
    {
        this.recive = recive;
    }

    public String getRecive()
    {
        return recive;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Long getGoodsKind() {
        return goodsKind;
    }

    public void setGoodsKind(Long goodsKind) {
        this.goodsKind = goodsKind;
    }

    public String getTradeRule() {
        return tradeRule;
    }

    public void setTradeRule(String tradeRule) {
        this.tradeRule = tradeRule;
    }

    public String getTradeAddress() {
        return tradeAddress;
    }

    public void setTradeAddress(String tradeAddress) {
        this.tradeAddress = tradeAddress;
    }

    public double getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(double tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    public double getTradeMoneyService() {
        return tradeMoneyService;
    }

    public void setTradeMoneyService(double tradeMoneyService) {
        this.tradeMoneyService = tradeMoneyService;
    }

    public double getTradeMoneyMe() {
        return tradeMoneyMe;
    }

    public void setTradeMoneyMe(double tradeMoneyMe) {
        this.tradeMoneyMe = tradeMoneyMe;
    }

    public double getTradeMoneyYou() {
        return tradeMoneyYou;
    }

    public void setTradeMoneyYou(double tradeMoneyYou) {
        this.tradeMoneyYou = tradeMoneyYou;
    }

    public String getTradePassword() {
        return tradePassword;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword;
    }

    public String getTradePasswordYou() {
        return tradePasswordYou;
    }

    public void setTradePasswordYou(String tradePasswordYou) {
        this.tradePasswordYou = tradePasswordYou;
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

    public String getTradeUrl() {
        return tradeUrl;
    }

    public void setTradeUrl(String tradeUrl) {
        this.tradeUrl = tradeUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orderId", getOrderId())
            .append("userId", getUserId())
            .append("goodsName", getGoodsName())
            .append("goodsKind", getGoodsKind())
            .append("tradeRule", getTradeRule())
            .append("tradeAddress", getTradeAddress())
            .append("tradeMoney", getTradeMoney())
            .append("tradeMoneyService", getTradeMoneyService())
            .append("tradeMoneyMe", getTradeMoneyMe())
            .append("tradeMoneyYou", getTradeMoneyYou())
            .append("tradePassword", getTradePassword())
            .append("tradePasswordYou", getTradePasswordYou())
            .append("tradeCode", getTradeCode())
            .append("tradeUrl", getTradeUrl())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .append("recive", getRecive())
            .append("send", getSend())
            .append("define", getDefine())
            .toString();
    }
}
