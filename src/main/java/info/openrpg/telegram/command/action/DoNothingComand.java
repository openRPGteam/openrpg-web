package info.openrpg.telegram.command.action;

import info.openrpg.telegram.OpenRpgBot;
import org.hibernate.Session;
import org.telegram.telegrambots.api.objects.Update;

public class DoNothingComand implements ExecutableCommand {

    @Override
    public void execute(Session session, Update update, OpenRpgBot bot) {

    }

    @Override
    public void handleCrash(RuntimeException e, Update update, OpenRpgBot bot) {

    }
}
