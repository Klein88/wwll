package dragonhope.wwllbackend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import dragonhope.wwllbackend.entity.Orders;

import java.util.List;

public interface OrdersService {
    Orders saveOrder(Orders order);
    
    // 分页查询订单
    IPage<Orders> getOrdersByPage(Page<Orders> page);
    
    // 获取最近的n条订单
    List<Orders> getRecentOrders(int limit);
    
    // 根据ID获取订单详情
    Orders getOrderById(Integer id);
} 