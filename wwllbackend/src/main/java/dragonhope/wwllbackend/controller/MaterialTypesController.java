package dragonhope.wwllbackend.controller;

import dragonhope.wwllbackend.common.Result;
import dragonhope.wwllbackend.entity.MaterialTypes;
import dragonhope.wwllbackend.service.MaterialTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/material-types")
public class MaterialTypesController {

    @Autowired
    private MaterialTypesService materialTypesService;

    @GetMapping
    public Result<List<MaterialTypes>> list() {
        return Result.success(materialTypesService.list());
    }

    @GetMapping("/{id}")
    public Result<MaterialTypes> getById(@PathVariable Integer id) {
        return Result.success(materialTypesService.getById(id));
    }

    @PostMapping
    public Result<MaterialTypes> save(@RequestBody MaterialTypes materialTypes) {
        materialTypesService.save(materialTypes);
        return Result.success(materialTypes);
    }

    @PutMapping("/{id}")
    public Result<MaterialTypes> update(@PathVariable Integer id, @RequestBody MaterialTypes materialTypes) {
        materialTypes.setId(id);
        materialTypesService.updateById(materialTypes);
        return Result.success(materialTypesService.getById(id));
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Integer id) {
        return Result.success(materialTypesService.removeById(id));
    }
} 