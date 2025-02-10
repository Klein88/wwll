package dragonhope.wwllbackend.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class QuoteRequestDTO {
    private Integer modelId;        // 型号ID
    private Integer materialId;     // 材料ID
    private Integer openCloseId;    // 开口闭口类型ID
    private Integer length;         // 拉链长度(cm)
    private Integer quantity;       // 数量
    private BigDecimal profitRate;  // 利润率（如0.15表示15%）
} 