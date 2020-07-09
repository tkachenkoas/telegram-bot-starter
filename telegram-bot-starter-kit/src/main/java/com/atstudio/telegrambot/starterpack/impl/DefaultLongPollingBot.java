package com.atstudio.telegrambot.starterpack.impl;

import com.atstudio.telegrambot.starterpack.api.LongPollingUpdateTaskExecutor;
import com.atstudio.telegrambot.starterpack.api.UpdateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@ConditionalOnProperty(value = "bot.mode", havingValue = "polling")
@Component
class DefaultLongPollingBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String botUserName;
    @Value("${bot.token}")
    private String botToken;

    private final UpdateHandler handler;
    private final LongPollingUpdateTaskExecutor updateExecutor;

    @Autowired
    public DefaultLongPollingBot(
            UpdateHandler handler,
            LongPollingUpdateTaskExecutor updateExecutor) {
        this.handler = handler;
        this.updateExecutor = updateExecutor;
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        updateExecutor.execute(() -> handler.handleTelegramUpdate(update));
    }

}
