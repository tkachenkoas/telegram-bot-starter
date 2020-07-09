package com.atstudio.telegrambot.starterpack.api;

import java.util.concurrent.Executor;

public interface LongPollingUpdateTaskExecutor extends Executor {

    @Override
    void execute(Runnable task);

}
