package com.zzj.springboot.factory;

/**
 * @author zhaozj37918
 * @date 2022年02月28日 9:56
 */
public abstract class TestExcutor {


    public void execute() {
        this.doExcutor();
        System.out.println("父类测试");
    }




    protected abstract void  doExcutor();

}
