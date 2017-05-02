package info.openrpg.telegram.command.action;

import info.openrpg.db.player.Player;
import info.openrpg.telegram.OpenRpgBot;
import org.hibernate.Session;
import org.telegram.telegrambots.api.objects.Update;

import java.util.List;

public class PlayerInfoCommand implements ExecutableCommand {
    @Override
    public void execute(Session session, Update update, OpenRpgBot bot) {
        List<Player> result = session.createQuery("select p from player p where p.id = :id", Player.class)
                .setParameter("id",  Integer.parseInt(update.getMessage().getText().split(" ")[1]))
                .list();
        for (Player event : result) {
            bot.sendMessage(update, "Пидора зовут: " + event.getFirstName() + " " + event.getLastName());
        }
    }

    @Override
    public void handleCrash(RuntimeException e, Update update, OpenRpgBot bot) {

    }
}
