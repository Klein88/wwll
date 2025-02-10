package dragonhope.wwllbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dragonhope.wwllbackend.entity.Models;
import dragonhope.wwllbackend.mapper.ModelsMapper;
import dragonhope.wwllbackend.service.ModelsService;
import org.springframework.stereotype.Service;

@Service
public class ModelsServiceImpl extends ServiceImpl<ModelsMapper, Models> implements ModelsService {
} 