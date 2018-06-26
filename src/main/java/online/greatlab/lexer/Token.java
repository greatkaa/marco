package online.greatlab.lexer;

import lombok.Data;

/**
 * @author a.kotov
 * @since 19.06.2018
 */
@Data
public class Token {
    private TokenType type;
    private String data;

    public Token(TokenType tokenType, String data) {
        this.type = tokenType;
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) (%s)", data, type.name(), type.getCategory());
    }
}

