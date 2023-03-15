package com.zzj.springboot.factory.executor;

/**
 * @author zhaozj37918
 * @date 2022年03月24日 10:10
 */
public class Executor1 extends TestExecutor {
    public void execute() {
        super.execute();
    }

    @Override
    protected void sendApply() {
        System.out.println("子类执行");
    }
}
