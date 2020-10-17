package com.github.tkachenkoas.telegramstarter.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.PostConstruct;

@ConditionalOnProperty(value = "bot.mode", havingValue = "webhook")
@RequiredArgsConstructor
@RestController
class WebHookController {

    @Value("${bot.host}")
    private String botHost;
    @Value("${webhook.path}")
    private String webHookPath;
    @Value("${certificate.path:}")
    private String certificatePath;

    private final TelegramWebhookBot telegramBot;

    @SneakyThrows
    @PostConstruct
    public void setWebHook() {
        telegramBot.setWebhook(botHost + "/" + webHookPath, certificatePath);
    }

    @PostMapping("${webhook.path}")
    public ResponseEntity handleWebhook(@RequestBody Update update) {
        telegramBot.onWebhookUpdateReceived(update);
        return ResponseEntity.ok().build();
    }

}
