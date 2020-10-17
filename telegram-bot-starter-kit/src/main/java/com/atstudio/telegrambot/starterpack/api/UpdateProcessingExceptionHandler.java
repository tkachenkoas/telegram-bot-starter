package com.atstudio.telegrambot.starterpack.api;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 *  Any uncaught exception when executing RootUpdateHandler#handle method
 *  will be passed to implementation of this interface (if exists)
 */
public interface UpdateProcessingExceptionHandler {

    void handleUpdateProcessingException(Update update, Exception e);

}
