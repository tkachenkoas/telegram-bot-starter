package com.atstudio.telegrambot.starterpack.api;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateProcessingExceptionHandler {

    void handleUpdateProcessingException(Update update, Exception e);

}
