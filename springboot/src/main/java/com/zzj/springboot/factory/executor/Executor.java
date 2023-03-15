package com.zzj.springboot.factory.executor;

/**
 * @author zhaozj37918
 * @date 2022年03月24日 10:01
 */
public abstract class Executor {

    public void execute() {
        this.doExecutor();
    }

    protected abstract void doExecutor();
}
