package dragonhope.wwllbackend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("OpenCloseTypes")
public class OpenCloseTypes {
    private Integer id;
    private String type;
    private Integer lengthAdjust;
} 