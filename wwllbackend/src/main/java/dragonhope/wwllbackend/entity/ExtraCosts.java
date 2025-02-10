package dragonhope.wwllbackend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("ExtraCosts")
public class ExtraCosts {
    private Integer id;
    private String description;
    private BigDecimal cost;
} 