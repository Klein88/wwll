package dragonhope.wwllbackend.controller;

import dragonhope.wwllbackend.common.Result;
import dragonhope.wwllbackend.entity.Materials;
import dragonhope.wwllbackend.service.MaterialsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materials")
public class MaterialsController {

    @Autowired
    private MaterialsService materialsService;

    @GetMapping
    public Result<List<Materials>> list(
            @RequestParam(value = "modelId", required = false) Integer modelId,
            @RequestParam(value = "typeId", required = false) Integer typeId,
            @RequestParam(value = "name", required = false) String name) {

        QueryWrapper<Materials> queryWrapper = new QueryWrapper<>();
        if (modelId != null) {
            queryWrapper.eq("model_id", modelId);
        }
        if (typeId != null) {
            queryWrapper.eq("type_id", typeId);
        }
        if (name != null && !name.isEmpty()) {
            queryWrapper.like("name", name);
        }
        return Result.success(materialsService.list(queryWrapper));
    }

    @GetMapping("/{id}")
    public Result<Materials> getById(@PathVariable Integer id) {
        return Result.success(materialsService.getById(id));
    }

    @PostMapping
    public Result<Materials> save(@RequestBody Materials materials) {
        materialsService.save(materials);
        return Result.success(materials);  // 返回保存后的对象（包含新生成的ID）
    }

    @PutMapping("/{id}")
    public Result<Materials> update(@PathVariable Integer id, @RequestBody Materials materials) {
        materials.setId(id);
        materialsService.updateById(materials);
        return Result.success(materialsService.getById(id));  // 返回更新后的对象
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Integer id) {
        return Result.success(materialsService.removeById(id));
    }
} 