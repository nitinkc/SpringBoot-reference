package com.spring.reference.controller;

import com.spring.reference.dto.RefTableDTO;
import com.spring.reference.model.RefTable;
import com.spring.reference.service.RefTableService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class K_RefTable_Caching_Controller {

    private RefTableService refTableService;

    @PostMapping("/ref")
    public List<RefTableDTO> getRefDataByIds(@RequestBody Map<String, List<Integer>> refIds){

        return refTableService.findByIds(refIds.get("ids"));
    }

    @GetMapping("/ref/{id}")
    public RefTableDTO getRefDataById(@PathVariable Integer id){

        return refTableService.findById(id);
    }


}
