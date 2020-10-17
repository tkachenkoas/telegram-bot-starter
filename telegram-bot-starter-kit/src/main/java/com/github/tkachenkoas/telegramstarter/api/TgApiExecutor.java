package com.github.tkachenkoas.telegramstarter.api;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import java.io.Serializable;

/**
 * Component that allows to execute telegram api methods
 */
public interface TgApiExecutor {

    <Result extends Serializable, Method extends BotApiMethod<Result>> Result execute(Method method);

}