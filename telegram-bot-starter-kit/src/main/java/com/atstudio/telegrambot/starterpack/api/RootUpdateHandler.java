package com.atstudio.telegrambot.starterpack.api;

import org.springframework.core.Ordered;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Processing telegram updates happens in chain of responsibility - like
 * manner. Implementations of this interface will be iterated until applicable
 * handler is found. No further processing will be executed once applicable
 * handler is found
 */
public interface RootUpdateHandler extends Ordered {

    boolean applicableFor(Update update);

    void handle(Update update);

}
