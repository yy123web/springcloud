package com.zzj.springboot.factory;

/**
 * @author zhaozj37918
 * @date 2022年02月28日 9:58
 */
public class Test1Excutor extends TestExcutor {

    @Override
    public void execute() {
        super.execute();
        System.out.println("子类测试");
    }

    @Override
    public void doExcutor() {
        System.out.println("子类测试");
    }


}
