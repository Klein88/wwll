package dragonhope.wwllbackend.controller;

import dragonhope.wwllbackend.common.Result;
import dragonhope.wwllbackend.entity.ExtraCosts;
import dragonhope.wwllbackend.service.ExtraCostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/extra-costs")
public class ExtraCostsController {

    @Autowired
    private ExtraCostsService extraCostsService;

    @GetMapping
    public Result<List<ExtraCosts>> list() {
        return Result.success(extraCostsService.list());
    }

    @GetMapping("/{id}")
    public Result<ExtraCosts> getById(@PathVariable Integer id) {
        return Result.success(extraCostsService.getById(id));
    }

    @PostMapping
    public Result<ExtraCosts> save(@RequestBody ExtraCosts extraCosts) {
        extraCostsService.save(extraCosts);
        return Result.success(extraCosts);
    }

    @PutMapping("/{id}")
    public Result<ExtraCosts> update(@PathVariable Integer id, @RequestBody ExtraCosts extraCosts) {
        extraCosts.setId(id);
        extraCostsService.updateById(extraCosts);
        return Result.success(extraCostsService.getById(id));
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Integer id) {
        return Result.success(extraCostsService.removeById(id));
    }
} 