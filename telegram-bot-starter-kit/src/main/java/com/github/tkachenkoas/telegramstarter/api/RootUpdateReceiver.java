package com.github.tkachenkoas.telegramstarter.api;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Acts as default entrypoint for any incoming update from Telegram
 */
public interface RootUpdateReceiver {

    void acceptTelegramUpdate(Update update);

}
