package com.zzj.demoprovider.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author zhaozj37918
 * @date 2022年03月02日 10:45
 */
@Data
public class User implements Serializable {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 密码
     */
    private String password;

}
