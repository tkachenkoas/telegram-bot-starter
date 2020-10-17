package com.atstudio.telegrambot.starterpack.impl;

import com.atstudio.telegrambot.starterpack.api.LongPollingUpdateTaskExecutor;
import com.atstudio.telegrambot.starterpack.api.RootUpdateReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@ConditionalOnProperty(value = "bot.mode", havingValue = "webhook")
@Component
class DefaultWebHookBot extends TelegramWebhookBot {

    @Value("${bot.username}")
    private String botUserName;
    @Value("${bot.token}")
    private String botToken;

    private final RootUpdateReceiver handler;
    private final LongPollingUpdateTaskExecutor updateExecutor;

    @Autowired
    public DefaultWebHookBot(
            RootUpdateReceiver handler,
            LongPollingUpdateTaskExecutor updateExecutor) {
        this.handler = handler;
        this.updateExecutor = updateExecutor;
    }

    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {
        updateExecutor.execute(() -> handler.acceptTelegramUpdate(update));
        return null;
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
    public String getBotPath() {
        return "";
    }


}
