package info.openrpg.telegram.command;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CommandChooserTest {
    private CommandChooser commandChooser;

    @BeforeMethod
    public void setUp() throws Exception {
        commandChooser = new CommandChooser();
    }

    @Test
    public void testVoidCommand() throws Exception {
        assertEquals(TelegramCommand.VOID, commandChooser.chooseCommand("/"));
    }

    @Test
    public void testVoidCommandWithoutSLash() throws Exception {
        assertEquals(TelegramCommand.VOID, commandChooser.chooseCommand(""));
    }

    @Test
    public void testHelp() throws Exception {
        assertEquals(TelegramCommand.HELP, commandChooser.chooseCommand("/help"));
    }

    @Test
    public void testPlayerInfo() throws Exception {
        assertEquals(TelegramCommand.PLAYER_INFO, commandChooser.chooseCommand("/player_info 123123"));
    }

    @Test
    public void testPlayerInfoWithoutArg() throws Exception {
        assertEquals(TelegramCommand.PLAYER_INFO, commandChooser.chooseCommand("/player_info"));
    }

    @Test
    public void testStart() throws Exception {
        assertEquals(TelegramCommand.START, commandChooser.chooseCommand("/start"));
    }
}