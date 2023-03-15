package com.zzj.springboot.pojo;

import lombok.Data;

/**
 * @author zhaozj37918
 * @date 2023年03月15日 17:18
 */
@Data
public class Lock {
    private String name;
    private String value;

    public Lock(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
