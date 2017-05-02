package info.openrpg;

import info.openrpg.telegram.OpenRpgBot;
import info.openrpg.telegram.Credentials;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger("main");

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        Properties properties = new Properties();
        InputStream input;
        try {
            input = Main.class.getClassLoader().getResourceAsStream("application.properties");
            properties.load(input);
            Credentials botCredentials = Credentials
                    .builder()
                    .botName(properties.getProperty("bot.name"))
                    .token(properties.getProperty("bot.token"))
                    .build();
            telegramBotsApi.registerBot(new OpenRpgBot(botCredentials, properties));
        } catch (TelegramApiRequestException e) {
            logger.warning(e.getMessage());
        } catch (FileNotFoundException e) {
            logger.warning("No file with properties was found");
        } catch (IOException e) {
            logger.warning("Exception while loading properties");
        }

    }
}
