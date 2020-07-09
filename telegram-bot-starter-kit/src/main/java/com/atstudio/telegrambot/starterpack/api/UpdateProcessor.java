package com.atstudio.telegrambot.starterpack.api;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateProcessor {

    boolean applicableFor(Update update);

    void processUpdate(Update update);

}
