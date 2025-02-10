package dragonhope.wwllbackend.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import dragonhope.wwllbackend.common.Result;
import dragonhope.wwllbackend.entity.Orders;
import dragonhope.wwllbackend.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping("/page")
    public Result<IPage<Orders>> getOrdersByPage(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size) {
        Page<Orders> page = new Page<>(current, size);
        return Result.success(ordersService.getOrdersByPage(page));
    }

    @GetMapping("/recent")
    public Result<List<Orders>> getRecentOrders(
            @RequestParam(defaultValue = "5") Integer limit) {
        return Result.success(ordersService.getRecentOrders(limit));
    }

    @GetMapping("/{id}")
    public Result<Orders> getOrderById(@PathVariable Integer id) {
        return Result.success(ordersService.getOrderById(id));
    }
} 