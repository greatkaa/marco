package online.greatlab.lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author a.kotov
 * @since 26.05.2018
 */
public class Lexer {

    public static List<Token> lex(String input) {
        List<Token> tokens = new ArrayList<>();

        StringBuilder tokenPatternsBuffer = new StringBuilder();
        for (TokenType tokenType : TokenType.values())
            tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.getPattern()));
        Pattern tokenPatterns = Pattern.compile(tokenPatternsBuffer.substring(1));

        Matcher matcher = tokenPatterns.matcher(input);
        while (matcher.find()) {
            if (matcher.group(TokenType.WHITESPACE.name()) != null) {
                tokens.add(new Token(TokenType.WHITESPACE, String.valueOf(matcher.group(TokenType.WHITESPACE.name()).length())));
                continue;
            } else if (matcher.group(TokenType.NEWLINE.name()) != null) {
                tokens.add(new Token(TokenType.NEWLINE, null));
                continue;
            } else if (matcher.group(TokenType.CONTLINE.name()) != null) {
                tokens.add(new Token(TokenType.CONTLINE, null));
                continue;
            } else if (matcher.group(TokenType.STRING.name()) != null) {
                String string = matcher.group(TokenType.STRING.name());
                tokens.add(new Token(TokenType.STRING, string.substring(1, string.length() - 1)));
                continue;
            }
            for (TokenType tokenType : TokenType.values()) {
                if (matcher.group(tokenType.name()) != null) {
                    tokens.add(new Token(tokenType, matcher.group(tokenType.name())));
                }
            }
        }
        return tokens;
    }
}
