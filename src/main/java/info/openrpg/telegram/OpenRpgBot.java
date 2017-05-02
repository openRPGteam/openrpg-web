package info.openrpg.telegram;

import info.openrpg.db.player.Player;
import info.openrpg.telegram.command.CommandChooser;
import info.openrpg.telegram.command.action.ExecutableCommand;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.Optional;
import java.util.Properties;
import java.util.logging.Logger;

public class OpenRpgBot extends TelegramLongPollingBot {
    private static final Logger logger = Logger.getLogger("bot");

    private Credentials credentials;
    private SessionFactory sessionFactory;
    private CommandChooser commandChooser;

    public OpenRpgBot(Credentials credentials, Properties properties) {
        this.credentials = credentials;
        this.sessionFactory = new Configuration()
                .addPackage("db.player")
                .addProperties(properties)
                .addAnnotatedClass(Player.class)
                .buildSessionFactory();
        commandChooser = new CommandChooser();
    }

    public void onUpdateReceived(Update update) {
        Optional.of(update)
                .map(Update::getMessage)
                .map(Message::getText)
                .ifPresent(text -> {
                            logger.info(text);
                            Session session = sessionFactory.openSession();
                            session.beginTransaction();
                            ExecutableCommand executableCommand = commandChooser
                                    .chooseCommand(text).getExecutableCommand();
                            try {
                                executableCommand.execute(session, update, this);
                                session.getTransaction().commit();
                            } catch (RuntimeException e) {
                                session.getTransaction().rollback();
                                executableCommand.handleCrash(e, update, this);
                            }
                            session.close();
                        }
                );
    }

    public void sendMessage(Update update, String message) {
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
