package com.atstudio.telegrambot.starterpack.api;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateHandler {

    void handleTelegramUpdate(Update update);

}
