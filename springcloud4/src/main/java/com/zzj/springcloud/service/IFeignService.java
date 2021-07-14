package com.zzj.springcloud.service;


import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "controller/hi")
public interface IFeignService {

    public String hiService(String name);
}