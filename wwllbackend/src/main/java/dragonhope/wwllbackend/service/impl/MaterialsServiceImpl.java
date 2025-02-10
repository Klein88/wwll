package dragonhope.wwllbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dragonhope.wwllbackend.entity.Materials;
import dragonhope.wwllbackend.mapper.MaterialsMapper;
import dragonhope.wwllbackend.service.MaterialsService;
import org.springframework.stereotype.Service;

@Service
public class MaterialsServiceImpl extends ServiceImpl<MaterialsMapper, Materials> implements MaterialsService {
} 