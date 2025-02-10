package dragonhope.wwllbackend.controller;

import dragonhope.wwllbackend.common.Result;
import dragonhope.wwllbackend.entity.OpenCloseTypes;
import dragonhope.wwllbackend.service.OpenCloseTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/open-close-types")
public class OpenCloseTypesController {

    @Autowired
    private OpenCloseTypesService openCloseTypesService;

    @GetMapping
    public Result<List<OpenCloseTypes>> list() {
        return Result.success(openCloseTypesService.list());
    }

    @GetMapping("/{id}")
    public Result<OpenCloseTypes> getById(@PathVariable Integer id) {
        return Result.success(openCloseTypesService.getById(id));
    }

    @PostMapping
    public Result<OpenCloseTypes> save(@RequestBody OpenCloseTypes openCloseTypes) {
        openCloseTypesService.save(openCloseTypes);
        return Result.success(openCloseTypes);
    }

    @PutMapping("/{id}")
    public Result<OpenCloseTypes> update(@PathVariable Integer id, @RequestBody OpenCloseTypes openCloseTypes) {
        openCloseTypes.setId(id);
        openCloseTypesService.updateById(openCloseTypes);
        return Result.success(openCloseTypesService.getById(id));
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Integer id) {
        return Result.success(openCloseTypesService.removeById(id));
    }
} 