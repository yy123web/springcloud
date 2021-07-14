package com.zzj.springcloud.controller;


import com.zzj.springcloud.service.IFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {

    @Autowired
    private IFeignService iFeignService;

    @RequestMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return iFeignService.hiService(name);
    }
}
