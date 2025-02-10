package dragonhope.wwllbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dragonhope.wwllbackend.entity.MaterialTypes;
import dragonhope.wwllbackend.mapper.MaterialTypesMapper;
import dragonhope.wwllbackend.service.MaterialTypesService;
import org.springframework.stereotype.Service;

@Service
public class MaterialTypesServiceImpl extends ServiceImpl<MaterialTypesMapper, MaterialTypes> implements MaterialTypesService {
} 