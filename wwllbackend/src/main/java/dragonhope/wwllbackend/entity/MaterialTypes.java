package dragonhope.wwllbackend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("MaterialTypes")
public class MaterialTypes {
    private Integer id;
    private String typeName;
} 