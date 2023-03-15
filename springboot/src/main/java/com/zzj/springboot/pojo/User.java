package com.zzj.springboot.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private Integer id;
    private String name;
    private String password;
    private int currPageNo;
    private int pageSize;
}
