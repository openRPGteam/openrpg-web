package info.openrpg.telegram;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.logging.Logger;

public class Bot extends TelegramLongPollingBot {
    private static final Logger logger = Logger.getLogger("bot");

    private Credentials credentials;

    public Bot(Credentials credentials) {
        this.credentials = credentials;
    }

    public void onUpdateReceived(Update update) {
        if (update.getMessage().getText().equals("/help")) {
            sendMessage(update, "Ты пидор");
        }
        logger.info(update.getMessage().getText());
    }

    private void sendMessage(Update update, String message) {
        try {
            sendMessage(new SendMessage().setChatId(update.getMessage().getChatId()).setText(message).enableHtml(true));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return credentials.getBotName();
    }

    public String getBotToken() {
        return credentials.getToken();
    }
}
