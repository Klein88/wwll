package dragonhope.wwllbackend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;

@Data
@TableName("Orders")
public class Orders {
    private Integer id;
    private Integer modelId;
    private Integer materialId;
    private Integer openCloseId;
    private Integer length;
    private Integer quantity;
    private BigDecimal profitRate;
    private BigDecimal totalPrice;
} 