package com.atstudio.telegrambot.starterpack.impl;

import com.atstudio.telegrambot.starterpack.api.RootUpdateReceiver;
import com.atstudio.telegrambot.starterpack.api.UpdateProcessingExceptionHandler;
import com.atstudio.telegrambot.starterpack.api.RootUpdateHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
@Slf4j
class UpdateHandlerImpl implements RootUpdateReceiver {

    private final List<RootUpdateHandler> processors;
    private final Optional<UpdateProcessingExceptionHandler> exceptionHandler;

    @Override
    public void acceptTelegramUpdate(Update update) {
        log.info("Received update from telegram: {}", update);
        try {
            for (RootUpdateHandler processor : processors) {

                if (processor.applicableFor(update)) {
                    processor.handle(update);
                    return;
                }
            }
        } catch (Exception e) {
            exceptionHandler.ifPresent(
                    handler -> handler.handleUpdateProcessingException(update, e)
            );
            return;
        }
    }

}
