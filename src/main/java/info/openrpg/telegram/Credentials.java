package info.openrpg.telegram;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Credentials {
    private String botName;
    private String token;
}
