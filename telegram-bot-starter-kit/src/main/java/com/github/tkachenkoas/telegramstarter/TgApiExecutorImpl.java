package com.github.tkachenkoas.telegramstarter;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import java.io.Serializable;

@Service
@Slf4j
class TgApiExecutorImpl implements TgApiExecutor {

    private final DefaultAbsSender delegate;

    @Autowired
    public TgApiExecutorImpl(@Lazy DefaultAbsSender delegate) {
        this.delegate = delegate;
    }

    @Override
    @SneakyThrows
    public <Result extends Serializable, Method extends BotApiMethod<Result>> Result execute(Method method) {
        return delegate.execute(method);
    }
}
