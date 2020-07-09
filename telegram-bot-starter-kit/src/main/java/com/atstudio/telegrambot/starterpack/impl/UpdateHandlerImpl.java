package com.atstudio.telegrambot.starterpack.impl;

import com.atstudio.telegrambot.starterpack.api.UpdateHandler;
import com.atstudio.telegrambot.starterpack.api.UpdateProcessingExceptionHandler;
import com.atstudio.telegrambot.starterpack.api.UpdateProcessor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
@Slf4j
class UpdateHandlerImpl implements UpdateHandler {

    private final List<UpdateProcessor> processors;
    private final Optional<UpdateProcessingExceptionHandler> exceptionHandler;

    @Override
    public void handleTelegramUpdate(Update update) {
        log.info("Received update from telegram: {}", update);
        try {
            for (UpdateProcessor processor : processors) {

                if (processor.applicableFor(update)) {
                    processor.processUpdate(update);
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
