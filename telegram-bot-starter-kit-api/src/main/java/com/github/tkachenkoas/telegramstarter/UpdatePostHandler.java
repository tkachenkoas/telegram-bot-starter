package com.github.tkachenkoas.telegramstarter;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 *  Will be executed as last operation after matching {@link RootUpdateHandler}
 *  and (if applicable) after exception handling by {@link UpdateProcessingExceptionHandler}
 */
public interface UpdatePostHandler {

    void postHandle(Update update);

}
