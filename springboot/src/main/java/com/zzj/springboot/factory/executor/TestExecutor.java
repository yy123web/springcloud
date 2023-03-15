package com.zzj.springboot.factory.executor;

/**
 * @author zhaozj37918
 * @date 2022年03月24日 10:02
 */
public abstract class TestExecutor extends Executor {
    public void execute() {
        super.execute();
    }
    @Override
    public void doExecutor() {
        System.out.println("开始执行子类");
        sendApply();
    }
    protected abstract void sendApply();
}
