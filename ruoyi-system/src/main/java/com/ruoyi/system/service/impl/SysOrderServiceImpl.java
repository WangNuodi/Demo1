package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysOrderMapper;
import com.ruoyi.system.domain.SysOrder;
import com.ruoyi.system.service.ISysOrderService;

/**
 * 订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-12
 */
@Service
public class SysOrderServiceImpl implements ISysOrderService 
{
    @Autowired
    private SysOrderMapper sysOrderMapper;

    /**
     * 查询订单
     * 
     * @param orderId 订单主键
     * @return 订单
     */
    @Override
    public SysOrder selectSysOrderByOrderId(Long orderId)
    {
        return sysOrderMapper.selectSysOrderByOrderId(orderId);
    }

    /**
     * 查询订单列表
     * 
     * @param sysOrder 订单
     * @return 订单
     */
    @Override
    public List<SysOrder> selectSysOrderList(SysOrder sysOrder)
    {
        List<SysOrder> sysOrders = sysOrderMapper.selectSysOrderList(sysOrder);
        if (sysOrders.size()==1 && sysOrder != null && sysOrder.getTradePasswordYou()!=null && sysOrder.getTradePasswordYou()!="") {
            if (sysOrders.get(0).getTradePasswordYou().equals(sysOrder.getTradePasswordYou())) {
                return sysOrders;
            }else {
                return null;
            }
        }
        return sysOrders;
    }

    /**
     * 新增订单
     * 
     * @param sysOrder 订单
     * @return 结果
     */
    @Override
    public HashMap<Object, Object> insertSysOrder(SysOrder sysOrder)
    {
        sysOrder.setCreateTime(DateUtils.getNowDate());
        //生成11位随机交易码
        //生成七位数
        Random random = new Random();
        ArrayList arrayList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            int num = random.nextInt(10);
            if (arrayList.size() ==0) {
                arrayList.add(num);
            }
//            else if (arrayList.contains(num)) {
//                i=i-1;
//            }
            else {
                arrayList.add(num);
            }
        }
        String nums1="";
        for (int i = 0; i <arrayList.size(); i++) {
            nums1=nums1+arrayList.get(i);
        }
//        System.out.println("七位数字"+nums1);
        //生成3个字母
        String code="";
        for (int i = 0; i <3 ; i++) {
            char n = (char) (new Random().nextInt(26)+97);
            code=code+n;
        }
//        System.out.println("三个字母"+code);
        //生成七位数
        Random random1 = new Random();
//        ArrayList arrayList1 = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            int num = random1.nextInt(10);
//            if (arrayList1.size() ==0) {
//                arrayList1.add(num);
//            }
//            else {
//                arrayList1.add(num);
//            }
//        }
//        String nums2="";
//        for (int i = 0; i <arrayList1.size(); i++) {
//            nums2=nums2+arrayList1.get(i);
//        }
//        System.out.println("三位数字"+nums2);
        String tradeCode="";
        tradeCode=nums1+code+random1.nextInt(10);
//        System.out.println("交易码"+tradeCode);
        sysOrder.setTradeCode(tradeCode);
        String tradeUrl="www.jiaoyibao361.com?tradeCode="+tradeCode;
        sysOrder.setTradeUrl(tradeUrl);
        if (sysOrder.getTradeMoney() < 10) {
            return null;
        }
        else if (sysOrder.getTradeMoney()>0 && sysOrder.getTradeMoney()+sysOrder.getTradeMoneyService() == sysOrder.getTradeMoneyMe()+sysOrder.getTradeMoneyYou()) {
            sysOrderMapper.insertSysOrder(sysOrder);
            HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
            objectObjectHashMap.put("tradeCode",tradeCode);
            objectObjectHashMap.put("tradeUrl",tradeUrl);
            return objectObjectHashMap;
        }
        return null;
    }

    /**
     * 修改订单
     * 
     * @param sysOrder 订单
     * @return 结果
     */
    @Override
    public int updateSysOrder(SysOrder sysOrder)
    {
        sysOrder.setUpdateTime(DateUtils.getNowDate());
        return sysOrderMapper.updateSysOrder(sysOrder);
    }

    /**
     * 批量删除订单
     * 
     * @param orderIds 需要删除的订单主键
     * @return 结果
     */
    @Override
    public int deleteSysOrderByOrderIds(Long[] orderIds)
    {
        return sysOrderMapper.deleteSysOrderByOrderIds(orderIds);
    }

    /**
     * 删除订单信息
     * 
     * @param orderId 订单主键
     * @return 结果
     */
    @Override
    public int deleteSysOrderByOrderId(Long orderId)
    {
        return sysOrderMapper.deleteSysOrderByOrderId(orderId);
    }
}
