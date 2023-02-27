package UHDBONF.controller;

import UHDBONF.domain.dto.LanguageDto;
import UHDBONF.domain.dto.SealDto;
import UHDBONF.service.SealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hj/seal")
public class SealController {
    @Autowired
    private SealService sealService;

    @PostMapping
    public String registerSeal(@RequestBody SealDto sealDto) {
        return sealService.registerSeal(sealDto);
    }

    @PostMapping("list")
    public String registerSealList(@RequestBody List<SealDto> sealDtoList) {
        return sealService.registerSealList(sealDtoList);
    }

    @GetMapping
    public List<SealDto> findAllSeal(){
        return sealService.findAllSeal();
    }

    @GetMapping("{id}")
    public SealDto findSealByKeyNumber(@PathVariable(name="id") String id){
        return sealService.findSealById(id);
    }
}
