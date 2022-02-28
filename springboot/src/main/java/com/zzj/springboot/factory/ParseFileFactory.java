package com.zzj.springboot.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhaozj37918
 * @date 2022年02月28日 10:51
 */
@Service
public class ParseFileFactory {


    @Autowired(required = false)
    private List<FactoryTest> handlers;



    public FactoryTest createInstance(String type) {
        if (handlers != null && !handlers.isEmpty()) {
            for (FactoryTest handler : handlers) {
                if (type.equalsIgnoreCase(handler.getType())) {
                    return handler;
                }
            }
        }
        return null;
    }
}
