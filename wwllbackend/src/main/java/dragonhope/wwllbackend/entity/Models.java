package dragonhope.wwllbackend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Models")
public class Models {
    private Integer id;
    private String name;
} 