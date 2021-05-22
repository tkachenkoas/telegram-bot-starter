package com.github.tkachenkoas.telegramstarter;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 *  Update will be passed to this component before chain of {@link RootUpdateHandler}s
 */
public interface UpdatePreHandler {

    void preHandle(Update update);

}
