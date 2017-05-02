package info.openrpg.telegram.command.action;

import info.openrpg.db.player.Player;
import info.openrpg.telegram.OpenRpgBot;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;

import javax.persistence.PersistenceException;

public class StartCommand implements ExecutableCommand {

    public static final String ALREADY_REGISTERED_MESSAGE = "Ты уже зарегистрирован";
    public static final String FIRST_MESSAGE = "Спасибо за регистрацию";

    @Override
    public void execute(Session session, Update update, OpenRpgBot bot) {
        User user = update.getMessage().getFrom();
        Player player = Player.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userName(user.getUserName())
                .build();
        session.save(player);
        bot.sendMessage(update, FIRST_MESSAGE);
    }

    @Override
    public void handleCrash(RuntimeException e, Update update, OpenRpgBot bot) {
        if (e instanceof PersistenceException) {
            if (e.getCause() instanceof ConstraintViolationException) {
                bot.sendMessage(update, ALREADY_REGISTERED_MESSAGE);
            }
        }
    }
}
