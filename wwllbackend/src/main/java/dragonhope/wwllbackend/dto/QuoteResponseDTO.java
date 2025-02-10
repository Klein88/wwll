package dragonhope.wwllbackend.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class QuoteResponseDTO {
    private Integer orderId;        // 新增订单ID字段
    private BigDecimal unitPrice;   // 单价（元/cm）
    private BigDecimal totalPrice;  // 总价（元）
    private BigDecimal materialCost;// 材料成本
    private BigDecimal extraCost;   // 附加成本
    private BigDecimal profit;      // 利润
    private Integer adjustedLength; // 调整后的长度
} 