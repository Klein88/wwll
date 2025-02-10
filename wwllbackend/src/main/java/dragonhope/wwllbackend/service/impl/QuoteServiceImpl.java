package dragonhope.wwllbackend.service.impl;

import dragonhope.wwllbackend.dto.QuoteRequestDTO;
import dragonhope.wwllbackend.dto.QuoteResponseDTO;
import dragonhope.wwllbackend.entity.ExtraCosts;
import dragonhope.wwllbackend.entity.Materials;
import dragonhope.wwllbackend.entity.OpenCloseTypes;
import dragonhope.wwllbackend.entity.Orders;
import dragonhope.wwllbackend.service.*;
import dragonhope.wwllbackend.utils.PriceCalculationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private MaterialsService materialsService;

    @Autowired
    private OpenCloseTypesService openCloseTypesService;

    @Autowired
    private ExtraCostsService extraCostsService;

    @Autowired
    private OrdersService ordersService;

    @Override
    public QuoteResponseDTO calculateQuote(QuoteRequestDTO request) {
        // 原有的计算方法，计算完后直接保存订单到数据库
        return calculate(request, true);
    }

    @Override
    public QuoteResponseDTO calculateQuoteWithoutSaving(QuoteRequestDTO request) {
        // 仅计算，不保存订单
        return calculate(request, false);
    }

    public QuoteResponseDTO calculate(QuoteRequestDTO request, boolean saveOrder) {
        // 1. 获取必要数据
        Materials material = materialsService.getById(request.getMaterialId());
        OpenCloseTypes openCloseType = openCloseTypesService.getById(request.getOpenCloseId());
        List<ExtraCosts> extraCosts = extraCostsService.list();

        // 2. 计算调整后的长度
        int adjustedLength = request.getLength() + openCloseType.getLengthAdjust();

        // 3. 计算每厘米的材料成本
        BigDecimal materialUnitPrice = PriceCalculationUtil.calculate(
                material.getRollPrice()
                        .divide(BigDecimal.valueOf(material.getRollLength()),
                                PriceCalculationUtil.CALCULATION_SCALE, RoundingMode.DOWN)
        );

        // 4. 计算材料成本
        BigDecimal materialCost = PriceCalculationUtil.calculate(
                materialUnitPrice.multiply(BigDecimal.valueOf(adjustedLength))
        );

        // 5. 计算额外费用
        BigDecimal totalExtraCost = extraCosts.stream()
                .map(ExtraCosts::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 6. 计算总成本
        BigDecimal totalCost = PriceCalculationUtil.calculate(
                materialCost.add(totalExtraCost)
        );

        // 7. 计算利润
        BigDecimal profit = PriceCalculationUtil.calculate(
                totalCost.multiply(request.getProfitRate())
        );

        // 8. 计算最终单价
        BigDecimal finalCost = PriceCalculationUtil.calculate(totalCost.add(profit));
        BigDecimal unitPrice = PriceCalculationUtil.calculate(
                finalCost.divide(BigDecimal.valueOf(request.getLength()),
                        PriceCalculationUtil.CALCULATION_SCALE, RoundingMode.DOWN)
        );

        // 9. 计算总价
        BigDecimal totalPrice = PriceCalculationUtil.calculate(
                unitPrice
                        .multiply(BigDecimal.valueOf(request.getLength()))
                        .multiply(BigDecimal.valueOf(request.getQuantity()))
        );

        // 如果需要保存订单
        Orders order = null;
        if (saveOrder) {
            order = new Orders();
            order.setModelId(request.getModelId());
            order.setMaterialId(request.getMaterialId());
            order.setOpenCloseId(request.getOpenCloseId());
            order.setLength(request.getLength());
            order.setQuantity(request.getQuantity());
            order.setProfitRate(request.getProfitRate());
            order.setTotalPrice(totalPrice);
            ordersService.saveOrder(order); // 保存订单
        }

        // 10. 封装响应
        QuoteResponseDTO response = new QuoteResponseDTO();
        response.setUnitPrice(PriceCalculationUtil.display(unitPrice));
        response.setTotalPrice(PriceCalculationUtil.display(totalPrice));
        response.setMaterialCost(PriceCalculationUtil.display(materialCost));
        response.setExtraCost(PriceCalculationUtil.display(totalExtraCost));
        response.setProfit(PriceCalculationUtil.display(profit));
        response.setAdjustedLength(adjustedLength);

        // 如果保存了订单，返回订单ID
        if (saveOrder && order != null) {
            response.setOrderId(order.getId());
        }

        return response;
    }
}
//public class QuoteServiceImpl implements QuoteService {
//
//    @Autowired
//    private MaterialsService materialsService;
//
//    @Autowired
//    private OpenCloseTypesService openCloseTypesService;
//
//    @Autowired
//    private ExtraCostsService extraCostsService;
//
//    @Autowired
//    private OrdersService ordersService;
//
//    @Override
//    public QuoteResponseDTO calculateQuote(QuoteRequestDTO request) {
//        // 1. 获取必要数据
//        Materials material = materialsService.getById(request.getMaterialId());
//        OpenCloseTypes openCloseType = openCloseTypesService.getById(request.getOpenCloseId());
//        List<ExtraCosts> extraCosts = extraCostsService.list();
//
//        // 2. 计算调整后的长度
//        int adjustedLength = request.getLength() + openCloseType.getLengthAdjust();
//
//        // 3. 计算每厘米的材料成本
//        BigDecimal materialUnitPrice = PriceCalculationUtil.calculate(
//            material.getRollPrice()
//                .divide(BigDecimal.valueOf(material.getRollLength()),
//                    PriceCalculationUtil.CALCULATION_SCALE, RoundingMode.DOWN)
//        );
//
//        // 4. 计算材料成本
//        BigDecimal materialCost = PriceCalculationUtil.calculate(
//            materialUnitPrice.multiply(BigDecimal.valueOf(adjustedLength))
//        );
//
//        // 5. 计算额外费用
//        BigDecimal totalExtraCost = extraCosts.stream()
//                .map(ExtraCosts::getCost)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//        // 6. 计算总成本
//        BigDecimal totalCost = PriceCalculationUtil.calculate(
//            materialCost.add(totalExtraCost)
//        );
//
//        // 7. 计算利润
//        BigDecimal profit = PriceCalculationUtil.calculate(
//            totalCost.multiply(request.getProfitRate())
//        );
//
//        // 8. 计算最终单价
//        BigDecimal finalCost = PriceCalculationUtil.calculate(totalCost.add(profit));
//        BigDecimal unitPrice = PriceCalculationUtil.calculate(
//            finalCost.divide(BigDecimal.valueOf(request.getLength()),
//                PriceCalculationUtil.CALCULATION_SCALE, RoundingMode.DOWN)
//        );
//
//        // 9. 计算总价
//        BigDecimal totalPrice = PriceCalculationUtil.calculate(
//            unitPrice
//                .multiply(BigDecimal.valueOf(request.getLength()))
//                .multiply(BigDecimal.valueOf(request.getQuantity()))
//        );
//
//        // 保存订单
//        Orders order = new Orders();
//        order.setModelId(request.getModelId());
//        order.setMaterialId(request.getMaterialId());
//        order.setOpenCloseId(request.getOpenCloseId());
//        order.setLength(request.getLength());
//        order.setQuantity(request.getQuantity());
//        order.setProfitRate(request.getProfitRate());
//        order.setTotalPrice(totalPrice);
//
//        ordersService.saveOrder(order);
//
//        // 10. 封装响应
//        QuoteResponseDTO response = new QuoteResponseDTO();
//        response.setUnitPrice(PriceCalculationUtil.display(unitPrice));
//        response.setTotalPrice(PriceCalculationUtil.display(totalPrice));
//        response.setMaterialCost(PriceCalculationUtil.display(materialCost));
//        response.setExtraCost(PriceCalculationUtil.display(totalExtraCost));
//        response.setProfit(PriceCalculationUtil.display(profit));
//        response.setAdjustedLength(adjustedLength);
//        response.setOrderId(order.getId());
//
//        return response;
//    }
//}