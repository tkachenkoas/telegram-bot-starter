package com.github.tkachenkoas.telegramstarter.impl;

import com.github.tkachenkoas.telegramstarter.api.RootUpdateReceiver;
import com.github.tkachenkoas.telegramstarter.api.UpdateProcessingExceptionHandler;
import com.github.tkachenkoas.telegramstarter.api.RootUpdateHandler;
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
