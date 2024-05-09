package com.ruoyi.system.service;

import java.util.HashMap;
import java.util.List;
import com.ruoyi.system.domain.SysOrder;

/**
 * 订单Service接口
 * 
 * @author ruoyi
 * @date 2023-03-12
 */
public interface ISysOrderService 
{
    /**
     * 查询订单
     * 
     * @param orderId 订单主键
     * @return 订单
     */
    public SysOrder selectSysOrderByOrderId(Long orderId);

    /**
     * 查询订单列表
     * 
     * @param sysOrder 订单
     * @return 订单集合
     */
    public List<SysOrder> selectSysOrderList(SysOrder sysOrder);

    /**
     * 新增订单
     * 
     * @param sysOrder 订单
     * @return 结果
     */
    public HashMap<Object, Object> insertSysOrder(SysOrder sysOrder);

    /**
     * 修改订单
     * 
     * @param sysOrder 订单
     * @return 结果
     */
    public int updateSysOrder(SysOrder sysOrder);

    /**
     * 批量删除订单
     * 
     * @param orderIds 需要删除的订单主键集合
     * @return 结果
     */
    public int deleteSysOrderByOrderIds(Long[] orderIds);

    /**
     * 删除订单信息
     * 
     * @param orderId 订单主键
     * @return 结果
     */
    public int deleteSysOrderByOrderId(Long orderId);
}
