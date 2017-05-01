package info.openrpg;

import info.openrpg.telegram.Bot;
import info.openrpg.telegram.Credentials;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger("main");

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot(Credentials.corovaner_bot));
        } catch (TelegramApiRequestException e) {
            logger.warning(e.getMessage());
        }

    }
}
