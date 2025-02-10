package dragonhope.wwllbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dragonhope.wwllbackend.entity.Orders;
import dragonhope.wwllbackend.mapper.OrdersMapper;
import dragonhope.wwllbackend.service.OrdersService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {
    
    @Override
    public Orders saveOrder(Orders order) {
        save(order);
        return order;
    }

    @Override
    public IPage<Orders> getOrdersByPage(Page<Orders> page) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id"); // 按ID降序排序，最新的在前面
        return page(page, queryWrapper);
    }

    @Override
    public List<Orders> getRecentOrders(int limit) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id")
                   .last("LIMIT " + limit); // 限制返回条数
        return list(queryWrapper);
    }

    @Override
    public Orders getOrderById(Integer id) {
        return getById(id);
    }
} 