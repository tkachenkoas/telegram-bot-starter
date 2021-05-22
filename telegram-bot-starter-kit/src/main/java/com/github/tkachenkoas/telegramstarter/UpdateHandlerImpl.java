package com.github.tkachenkoas.telegramstarter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
class UpdateHandlerImpl implements RootUpdateReceiver {

    private final List<RootUpdateHandler> processors;
    private final Optional<UpdateProcessingExceptionHandler> exceptionHandler;
    private final Optional<UpdatePreHandler> preHandler;
    private final Optional<UpdatePostHandler> postHandler;

    @Override
    public void acceptTelegramUpdate(Update update) {
        try {
            preHandler.ifPresent(bean -> bean.preHandle(update));

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
        } finally {
            postHandler.ifPresent(bean -> bean.postHandle(update));
        }
    }

}
