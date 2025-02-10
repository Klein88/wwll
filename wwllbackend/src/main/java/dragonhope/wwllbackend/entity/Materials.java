package dragonhope.wwllbackend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("Materials")
public class Materials {
    private Integer id;
    private String name;
    private Integer typeId;
    private Integer modelId;
    private BigDecimal rollPrice;
    private Integer rollLength;
    private Integer wasteLength;
} 