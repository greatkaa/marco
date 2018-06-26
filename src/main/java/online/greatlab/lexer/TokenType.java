package online.greatlab.lexer;

import lombok.Getter;

import static online.greatlab.lexer.TokenCategory.*;


/**
 * @author a.kotov
 * @since 19.06.2018
 */
public enum TokenType {

    CHAR("'.'|'\\\\u\\d{4}'|'\\\\t'", CHARACTER_LITERAL),
    FLOAT("-?\\d+\\.\\d+(e\\d+)?", FLOATING_LITERAL),
    OCTALINT("0[0-7]+", INTEGER_LITERAL),
    INT("-?\\d+", INTEGER_LITERAL),
    STRING("\"[^\"]*\"", STRING_LITERAL),
    TRUE("true", BOOL_LITERAL),
    FALSE("false", BOOL_LITERAL),

    COMMENT("//", WHITE_SPACE),
    COMMENTSTART("/\\*", WHITE_SPACE),
    COMMENTEND("\\*/", WHITE_SPACE),
    WHITESPACE("[ ]+", WHITE_SPACE),

    CONTLINE("\\\\\n", EOF),
    NEWLINE("\n", EOF),


    //Preprocessors
    DEFINE("#define", PREPROCESSOR),
    IFDEF("#ifdef", PREPROCESSOR),
    IFNDEF("#ifndef", PREPROCESSOR),
    IF("#if", PREPROCESSOR),
    ELSE("#else", PREPROCESSOR),
    ELIF("#elif", PREPROCESSOR),
    ENDIF("#endif", PREPROCESSOR),
    ERROR("#error", PREPROCESSOR),
    LINE("#line", PREPROCESSOR),
    PRAGMA("#pragma", PREPROCESSOR),
    UNDEF("#undef", PREPROCESSOR),
    WARNING("#warning", PREPROCESSOR),
    INCLUDE("#include \"\\w+(.|)[\\w\\.]+\"", PREPROCESSOR),
    QHEADER("#include <\\w+(.|)[\\w\\.]+>", PREPROCESSOR),
    HHEADER("#include \\w+(.|)[\\w\\.]+", PREPROCESSOR),

    COMPLALT("compl", OPERATOR),
    NOTEQUALALT("not_eq", OPERATOR),
    NOTALT("not", OPERATOR),
    ORASSIGNALT("or_eq", OPERATOR),
    XORASSIGNALT("xor_eq", OPERATOR),
    ANDALT("bitand", OPERATOR),
    ANDASSIGNALT("and_eq", OPERATOR),
    ORALT("or", OPERATOR),
    XORALT("xor", OPERATOR),

    POUNDPOUNDTRIGRAPH("\\?\\?=\\?\\?=", OPERATOR),
    POUNDPOUNDALT("%:%:", OPERATOR),
    ELLIPSIS("\\.\\.\\.", OPERATOR),
    SHIFTLEFTASSIGN("<<=", OPERATOR),
    SHIFTRIGHTASSIGN(">>=", OPERATOR),
    ORTRIGRAPH("\\?\\?!", OPERATOR),
    XORTRIGRAPH("\\?\\?'", OPERATOR),
    LEFTBRACETRIGRAPH("\\?\\?<", OPERATOR),
    LEFTBRACKETTRIGRAPH("\\?\\?\\(", OPERATOR),
    RIGHTBRACETRIGRAPH("\\?\\?>", OPERATOR),
    RIGHTBRACKETTRIGRAPH("\\?\\?\\)", OPERATOR),
    COMPLTRIGRAPH("\\?\\?-", OPERATOR),
    POUNDTRIGRAPH("\\?\\?=", OPERATOR),

    POUNDALT("%:", OPERATOR),
    LEFTBRACKETALT("<:", OPERATOR),
    RIGHTBRACEALT("%>", OPERATOR),
    RIGHTBRACKETALT(":>", OPERATOR),
    LEFTBRACEALT("<%", OPERATOR),
    POUNDPOUND("##", OPERATOR),
    STARASSIGN("\\*=", OPERATOR),
    SHIFTRIGHT(">>", OPERATOR),
    ANDAND("&&", OPERATOR),
    ANDASSIGN("&=", OPERATOR),
    ORASSIGN("\\|=", OPERATOR),
    XORASSIGN("\\^=", OPERATOR),
    EQUAL("==", OPERATOR),
    DIVIDEASSIGN("/=", OPERATOR),
    DOTSTAR("\\.\\*", OPERATOR),
    GREATEREQUAL(">=", OPERATOR),
    LESSEQUAL("<=", OPERATOR),
    MINUSASSIGN("-=", OPERATOR),
    MINUSMINUS("--", OPERATOR),
    PERCENTASSIGN("%=", OPERATOR),
    NOTEQUAL("!=", OPERATOR),
    OROR("\\|\\|", OPERATOR),
    PLUSASSIGN("\\+=", OPERATOR),
    PLUSPLUS("\\+\\+", OPERATOR),
    ARROWSTAR("->\\*", OPERATOR),
    ARROW("->", OPERATOR),
    COLONCOLON("::", OPERATOR),
    SHIFTLEFT("<<", OPERATOR),

    POUND("#", OPERATOR),
    COMPL("~", OPERATOR),
    STAR("\\*", OPERATOR),
    DIVIDE("/", OPERATOR),
    DOT("\\.", OPERATOR),
    COMMA("\\,", OPERATOR),
    LEFTBRACE("\\{", OPERATOR),
    LEFTPAREN("\\(", OPERATOR),
    LEFTBRACKET("\\[", OPERATOR),
    QUESTIONMARK("\\?", OPERATOR),
    RIGHTBRACE("\\}", OPERATOR),
    RIGHTPAREN("\\)", OPERATOR),
    RIGHTBRACKET("]", OPERATOR),
    SEMICOLON(";", OPERATOR),
    PLUS("\\+", OPERATOR),
    MINUS("-", OPERATOR),
    OR("\\|", OPERATOR),
    COLON(":", OPERATOR),
    AND("&", OPERATOR),
    NOT("!", OPERATOR),
    PERCENT("%", OPERATOR),
    XOR("\\^", OPERATOR),
    LESS("<", OPERATOR),
    GREATER(">", OPERATOR),
    ASSIGN("=", OPERATOR),

    IDENTIFIER("([a-zA-Z]+\\w+)", TokenCategory.IDENTIFIER),;

    @Getter
    private final String pattern;
    @Getter
    private final TokenCategory category;

    TokenType(String pattern, TokenCategory category) {
        this.pattern = pattern;
        this.category = category;
    }

}