package info.openrpg.telegram.command.action;

import info.openrpg.telegram.OpenRpgBot;
import org.hibernate.Session;
import org.telegram.telegrambots.api.objects.Update;

public class HelpCommand implements ExecutableCommand {

    @Override
    public void execute(Session session, Update update, OpenRpgBot bot) {
        bot.sendMessage(update, "Ты пидор!");
    }

    @Override
    public void handleCrash(RuntimeException e, Update update, OpenRpgBot bot) {

    }
}
