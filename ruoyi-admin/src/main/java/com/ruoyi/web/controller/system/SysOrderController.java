package com.ruoyi.web.controller.system;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysOrder;
import com.ruoyi.system.service.ISysOrderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订单Controller
 * 
 * @author ruoyi
 * @date 2023-03-10
 */
@Api("用户信息管理")
@RestController
@RequestMapping("/system/order")
public class SysOrderController extends BaseController
{
    @Autowired
    private ISysOrderService sysOrderService;

    /**
     * 查询订单列表
     */
    @ApiOperation("查询订单列表")
//    @PreAuthorize("@ss.hasPermi('system:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysOrder sysOrder)
    {
        startPage();
        List<SysOrder> list = sysOrderService.selectSysOrderList(sysOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单列表
     */
    @ApiOperation("导出订单列表")
//    @PreAuthorize("@ss.hasPermi('system:order:export')")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysOrder sysOrder)
    {
        List<SysOrder> list = sysOrderService.selectSysOrderList(sysOrder);
        ExcelUtil<SysOrder> util = new ExcelUtil<SysOrder>(SysOrder.class);
        util.exportExcel(response, list, "订单数据");
    }


    /**
     * 新增订单
     */
    @ApiOperation("新增订单")
//    @PreAuthorize("@ss.hasPermi('system:order:add')")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping("/insertOrder")
    public AjaxResult add(@RequestBody SysOrder sysOrder)
    {
        HashMap<Object, Object> objectObjectHashMap = sysOrderService.insertSysOrder(sysOrder);
        if (objectObjectHashMap != null) {
            return new AjaxResult(200,"操作成功",objectObjectHashMap);
        }
       return  AjaxResult.error("请检查金额是否准确");
    }

    /**
     * 修改订单
     */
    @ApiOperation("修改订单")
//    @PreAuthorize("@ss.hasPermi('system:order:edit')")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping("/updateOrder")
    public AjaxResult edit(@RequestBody SysOrder sysOrder)
    {
        return toAjax(sysOrderService.updateSysOrder(sysOrder));
    }

    /**
     * 删除订单
     */
    @ApiOperation("删除订单")
//    @PreAuthorize("@ss.hasPermi('system:order:remove')")
    @Log(title = "订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable Long[] orderIds)
    {
        return toAjax(sysOrderService.deleteSysOrderByOrderIds(orderIds));
    }



//    备用
    /**
     * 获取订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:order:query')")
    @GetMapping(value = { "/getOrder", "/{orderId}" })
    public AjaxResult getInfo1(@PathVariable(value = "orderId", required = false) Long orderId)
    {
        return success(sysOrderService.selectSysOrderByOrderId(orderId));
    }



    /**
     * 新增订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:add')")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add1(@RequestBody SysOrder sysOrder)
    {
        HashMap<Object, Object> objectObjectHashMap = sysOrderService.insertSysOrder(sysOrder);
        if (objectObjectHashMap != null) {
            return new AjaxResult(200,"操作成功",objectObjectHashMap);
        }
        return  AjaxResult.error("请检查金额是否准确");
    }

    /**
     * 修改订单
     */
//    @PreAuthorize("@ss.hasPermi('system:order:edit')")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit1(@RequestBody SysOrder sysOrder)
    {
        return toAjax(sysOrderService.updateSysOrder(sysOrder));
    }

//    /**
//     * 删除订单
//     */
//    @PreAuthorize("@ss.hasPermi('system:order:remove')")
//    @Log(title = "订单", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{orderIds}")
//    public AjaxResult remove1(@PathVariable Long[] orderIds)
//    {
//        return toAjax(sysOrderService.deleteSysOrderByOrderIds(orderIds));
//    }
}
