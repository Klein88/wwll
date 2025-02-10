package dragonhope.wwllbackend.controller;

import dragonhope.wwllbackend.common.Result;
import dragonhope.wwllbackend.entity.Models;
import dragonhope.wwllbackend.service.ModelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
public class ModelsController {

    @Autowired
    private ModelsService modelsService;

    @GetMapping
    public Result<List<Models>> list() {
        return Result.success(modelsService.list());
    }

    @GetMapping("/{id}")
    public Result<Models> getById(@PathVariable Integer id) {
        return Result.success(modelsService.getById(id));
    }

    @PostMapping
    public Result<Boolean> save(@RequestBody Models models) {
        return Result.success(modelsService.save(models));
    }

    @PutMapping
    public Result<Boolean> update(@RequestBody Models models) {
        return Result.success(modelsService.updateById(models));
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Integer id) {
        return Result.success(modelsService.removeById(id));
    }
} 