package online.greatlab.lexer;

/**
 * @author a.kotov
 * @since 19.06.2018
 */
public enum TokenCategory {
    PREPROCESSOR,
    OPERATOR,
    CHARACTER_LITERAL,
    FLOATING_LITERAL,
    INTEGER_LITERAL,
    BOOL_LITERAL,
    STRING_LITERAL,
    IDENTIFIER,
    WHITE_SPACE,
    EOF,
}