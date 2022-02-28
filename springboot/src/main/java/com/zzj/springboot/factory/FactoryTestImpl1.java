package com.zzj.springboot.factory;

import org.springframework.stereotype.Service;

/**
 * @author zhaozj37918
 * @date 2022年02月28日 10:57
 */
@Service
public class FactoryTestImpl1 implements FactoryTest {
    @Override
    public String getType() {
        return "type1";
    }
    @Override
    public void parse() {
        System.out.println("type1子类");
    }
}
