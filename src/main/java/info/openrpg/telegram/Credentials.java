package info.openrpg.telegram;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Credentials {
    OpenRPGBot("OpenRPGBot", "360419088:AAFuT9do0fG68jBEpW4ETCVdXYiN5f_I46k"),
    corovaner_bot("corovaner_bot", "334320004:AAEKbNEWJgKBHWWvc6W_OuUtTqCxucG7Vsg");

    private String botName;
    private String token;
}
