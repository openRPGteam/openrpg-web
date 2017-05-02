package info.openrpg.telegram.command;

import info.openrpg.telegram.command.action.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TelegramCommand {
    VOID(new DoNothingComand()),
    START(new StartCommand()),
    HELP(new HelpCommand()),
    PLAYER_INFO(new PlayerInfoCommand()),;

    private ExecutableCommand executableCommand;
}
