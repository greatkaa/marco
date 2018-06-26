package online.greatlab.lexer;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import static org.junit.Assert.assertEquals;

/**
 * @author a.kotov
 * @since 04.06.2018
 */
public class LexerComplicatedTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void defineVariableWithoutValue() throws Exception {
        String text = "#define TEVO_BLTOUCH";
        List<Token> lexerTokens = Lexer.lex(text);
        List<Token> expTokens = new ArrayList<>();
        expTokens.add(new Token(TokenType.DEFINE, "#define"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.IDENTIFIER, "TEVO_BLTOUCH"));
        assertEquals(expTokens, lexerTokens);
    }

    @Test
    public void defineVariableWithStringValue() throws Exception {
        String text = "#define MACHINE_UUID \"c9548f16-e739-4d1f-a081-b56d39c1ce6c\"";
        List<Token> lexerTokens = Lexer.lex(text);
        List<Token> expTokens = new ArrayList<>();
        expTokens.add(new Token(TokenType.DEFINE, "#define"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.IDENTIFIER, "MACHINE_UUID"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.STRING, "c9548f16-e739-4d1f-a081-b56d39c1ce6c"));
        assertEquals(expTokens, lexerTokens);
    }

    @Test
    public void defineVariableWithFloatValue() throws Exception {
        String text = "#define DEFAULT_ZJERK                  0.2";
        List<Token> lexerTokens = Lexer.lex(text);
        List<Token> expTokens = new ArrayList<>();
        expTokens.add(new Token(TokenType.DEFINE, "#define"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.IDENTIFIER, "DEFAULT_ZJERK"));
        expTokens.add(new Token(TokenType.WHITESPACE, "18"));
        expTokens.add(new Token(TokenType.FLOAT, "0.2"));
        assertEquals(expTokens, lexerTokens);
    }

    @Test
    public void defineVariableWithNegativeIntegerValue() throws Exception {
        String text = "#define Y_PROBE_OFFSET_FROM_EXTRUDER -18";
        List<Token> lexerTokens = Lexer.lex(text);
        List<Token> expTokens = new ArrayList<>();
        expTokens.add(new Token(TokenType.DEFINE, "#define"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.IDENTIFIER, "Y_PROBE_OFFSET_FROM_EXTRUDER"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.INT, "-18"));
        assertEquals(expTokens, lexerTokens);
    }

    @Test
    public void defineVariableWithOctalIntegerValue() throws Exception {
        String text = "#define CONFIGURATION_H_VERSION 010100";
        List<Token> lexerTokens = Lexer.lex(text);
        List<Token> expTokens = new ArrayList<>();
        expTokens.add(new Token(TokenType.DEFINE, "#define"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.IDENTIFIER, "CONFIGURATION_H_VERSION"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.OCTALINT, "010100"));
        assertEquals(expTokens, lexerTokens);
    }

    @Test
    public void defineVariableWithOtherVariablesValue() throws Exception {
        String text = "#define Z_SAFE_HOMING_Y_POINT ((Y_MIN_POS + Y_MAX_POS) / 2)";
        List<Token> lexerTokens = Lexer.lex(text);
        List<Token> expTokens = new ArrayList<>();
        expTokens.add(new Token(TokenType.DEFINE, "#define"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.IDENTIFIER, "Z_SAFE_HOMING_Y_POINT"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.LEFTPAREN, "("));
        expTokens.add(new Token(TokenType.LEFTPAREN, "("));
        expTokens.add(new Token(TokenType.IDENTIFIER, "Y_MIN_POS"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.PLUS, "+"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.IDENTIFIER, "Y_MAX_POS"));
        expTokens.add(new Token(TokenType.RIGHTPAREN, ")"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.DIVIDE, "/"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.INT, "2"));
        expTokens.add(new Token(TokenType.RIGHTPAREN, ")"));
        assertEquals(expTokens, lexerTokens);
    }

    @Test
    public void defineVariableWithBooleanValue() throws Exception {
        String text = "#define min_software_endstops false";
        List<Token> lexerTokens = Lexer.lex(text);
        List<Token> expTokens = new ArrayList<>();
        expTokens.add(new Token(TokenType.DEFINE, "#define"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.IDENTIFIER, "min_software_endstops"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.FALSE, "false"));
        assertEquals(expTokens, lexerTokens);
    }

    @Test
    public void defineVariableWithEvaluationValue() throws Exception {
        String text = "#define HOMING_FEEDRATE_XY (50*60)";
        List<Token> lexerTokens = Lexer.lex(text);
        List<Token> expTokens = new ArrayList<>();
        expTokens.add(new Token(TokenType.DEFINE, "#define"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.IDENTIFIER, "HOMING_FEEDRATE_XY"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.LEFTPAREN, "("));
        expTokens.add(new Token(TokenType.INT, "50"));
        expTokens.add(new Token(TokenType.STAR, "*"));
        expTokens.add(new Token(TokenType.INT, "60"));
        expTokens.add(new Token(TokenType.RIGHTPAREN, ")"));
        assertEquals(expTokens, lexerTokens);
    }

    @Test
    public void defineVariableWithArrayValue() throws Exception {
        String text = "#define MANUAL_FEEDRATE {50*60, 50*60, 4*60, 60}";
        List<Token> lexerTokens = Lexer.lex(text);
        List<Token> expTokens = new ArrayList<>();
        expTokens.add(new Token(TokenType.DEFINE, "#define"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.IDENTIFIER, "MANUAL_FEEDRATE"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.LEFTBRACE, "{"));
        expTokens.add(new Token(TokenType.INT, "50"));
        expTokens.add(new Token(TokenType.STAR, "*"));
        expTokens.add(new Token(TokenType.INT, "60"));
        expTokens.add(new Token(TokenType.COMMA, ","));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.INT, "50"));
        expTokens.add(new Token(TokenType.STAR, "*"));
        expTokens.add(new Token(TokenType.INT, "60"));
        expTokens.add(new Token(TokenType.COMMA, ","));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.INT, "4"));
        expTokens.add(new Token(TokenType.STAR, "*"));
        expTokens.add(new Token(TokenType.INT, "60"));
        expTokens.add(new Token(TokenType.COMMA, ","));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.INT, "60"));
        expTokens.add(new Token(TokenType.RIGHTBRACE, "}"));
        assertEquals(expTokens, lexerTokens);
    }

@Test
public void defineVariableWithArrayAndExpressionsValue() throws Exception {
    String text = "#define NOZZLE_PARK_POINT { (X_MIN_POS + 10), (Y_MAX_POS - 10), 20 }";
    List<Token> lexerTokens = Lexer.lex(text);
    List<Token> expTokens = new ArrayList<>();
    expTokens.add(new Token(TokenType.DEFINE, "#define"));
    expTokens.add(new Token(TokenType.WHITESPACE, "1"));
    expTokens.add(new Token(TokenType.IDENTIFIER, "NOZZLE_PARK_POINT"));
    expTokens.add(new Token(TokenType.WHITESPACE, "1"));
    expTokens.add(new Token(TokenType.LEFTBRACE, "{"));
    expTokens.add(new Token(TokenType.WHITESPACE, "1"));
    expTokens.add(new Token(TokenType.LEFTPAREN, "("));
    expTokens.add(new Token(TokenType.IDENTIFIER, "X_MIN_POS"));
    expTokens.add(new Token(TokenType.WHITESPACE, "1"));
    expTokens.add(new Token(TokenType.PLUS, "+"));
    expTokens.add(new Token(TokenType.WHITESPACE, "1"));
    expTokens.add(new Token(TokenType.INT, "10"));
    expTokens.add(new Token(TokenType.RIGHTPAREN, ")"));
    expTokens.add(new Token(TokenType.COMMA, ","));
    expTokens.add(new Token(TokenType.WHITESPACE, "1"));
    expTokens.add(new Token(TokenType.LEFTPAREN, "("));
    expTokens.add(new Token(TokenType.IDENTIFIER, "Y_MAX_POS"));
    expTokens.add(new Token(TokenType.WHITESPACE, "1"));
    expTokens.add(new Token(TokenType.MINUS, "-"));
    expTokens.add(new Token(TokenType.WHITESPACE, "1"));
    expTokens.add(new Token(TokenType.INT, "10"));
    expTokens.add(new Token(TokenType.RIGHTPAREN, ")"));
    expTokens.add(new Token(TokenType.COMMA, ","));
    expTokens.add(new Token(TokenType.WHITESPACE, "1"));
    expTokens.add(new Token(TokenType.INT, "20"));
    expTokens.add(new Token(TokenType.WHITESPACE, "1"));
    expTokens.add(new Token(TokenType.RIGHTBRACE, "}"));
    assertEquals(expTokens, lexerTokens);
}


    @Test
    public void disabledDefineVariableWithoutValue() throws Exception {
        String text = "//#define COREXY";
        List<Token> lexerTokens = Lexer.lex(text);
        List<Token> expTokens = new ArrayList<>();
        expTokens.add(new Token(TokenType.COMMENT, "//"));
        expTokens.add(new Token(TokenType.DEFINE, "#define"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.IDENTIFIER, "COREXY"));
        assertEquals(expTokens, lexerTokens);
    }

    @Test
    public void ifStatementWithOneExpression() throws Exception {
        String text = "#if POWER_SUPPLY > 0";
        List<Token> lexerTokens = Lexer.lex(text);
        List<Token> expTokens = new ArrayList<>();
        expTokens.add(new Token(TokenType.IF, "#if"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.IDENTIFIER, "POWER_SUPPLY"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.GREATER, ">"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.INT, "0"));
        assertEquals(expTokens, lexerTokens);
    }

    @Test
    public void ifdefStatement() throws Exception {
        String text = "#ifdef TEVO_BLTOUCH";
        List<Token> lexerTokens = Lexer.lex(text);
        List<Token> expTokens = new ArrayList<>();
        expTokens.add(new Token(TokenType.IFDEF, "#ifdef"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.IDENTIFIER, "TEVO_BLTOUCH"));
        assertEquals(expTokens, lexerTokens);
    }

    @Test
    public void ifStatementWithTwoExpressions() throws Exception {
        String text = "#if ENABLED(AUTO_BED_LEVELING_LINEAR) || ENABLED(AUTO_BED_LEVELING_BILINEAR)";
        List<Token> lexerTokens = Lexer.lex(text);
        List<Token> expTokens = new ArrayList<>();
        expTokens.add(new Token(TokenType.IF, "#if"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.IDENTIFIER, "ENABLED"));
        expTokens.add(new Token(TokenType.LEFTPAREN, "("));
        expTokens.add(new Token(TokenType.IDENTIFIER, "AUTO_BED_LEVELING_LINEAR"));
        expTokens.add(new Token(TokenType.RIGHTPAREN, ")"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.OROR, "||"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.IDENTIFIER, "ENABLED"));
        expTokens.add(new Token(TokenType.LEFTPAREN, "("));
        expTokens.add(new Token(TokenType.IDENTIFIER, "AUTO_BED_LEVELING_BILINEAR"));
        expTokens.add(new Token(TokenType.RIGHTPAREN, ")"));
        assertEquals(expTokens, lexerTokens);
    }

    @Test
    public void partOfCode() throws Exception {
        String text = "#if ENABLED(SWITCHING_EXTRUDER)\n" +
                "  #define SWITCHING_EXTRUDER_SERVO_NR 0\n" +
                "            #define SWITCHING_EXTRUDER_SERVO_ANGLES { 0, 90 } // Angles for E0, E1\n" +
                "    //#define HOTEND_OFFSET_Z {0.0, 0.0}\n" +
                "#endif";
        List<Token> lexerTokens = Lexer.lex(text);
        List<Token> expTokens = new ArrayList<>();
        expTokens.add(new Token(TokenType.IF, "#if"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.IDENTIFIER, "ENABLED"));
        expTokens.add(new Token(TokenType.LEFTPAREN, "("));
        expTokens.add(new Token(TokenType.IDENTIFIER, "SWITCHING_EXTRUDER"));
        expTokens.add(new Token(TokenType.RIGHTPAREN, ")"));
        expTokens.add(new Token(TokenType.NEWLINE, null));

        expTokens.add(new Token(TokenType.WHITESPACE, "2"));
        expTokens.add(new Token(TokenType.DEFINE, "#define"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.IDENTIFIER, "SWITCHING_EXTRUDER_SERVO_NR"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.INT, "0"));
        expTokens.add(new Token(TokenType.NEWLINE, null));

        expTokens.add(new Token(TokenType.WHITESPACE, "12"));
        expTokens.add(new Token(TokenType.DEFINE, "#define"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.IDENTIFIER, "SWITCHING_EXTRUDER_SERVO_ANGLES"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.LEFTBRACE, "{"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.INT, "0"));
        expTokens.add(new Token(TokenType.COMMA, ","));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.INT, "90"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.RIGHTBRACE, "}"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.COMMENT, "//"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.IDENTIFIER, "Angles"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.IDENTIFIER, "for"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.IDENTIFIER, "E0"));
        expTokens.add(new Token(TokenType.COMMA, ","));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.IDENTIFIER, "E1"));
        expTokens.add(new Token(TokenType.NEWLINE, null));

        expTokens.add(new Token(TokenType.WHITESPACE, "4"));
        expTokens.add(new Token(TokenType.COMMENT, "//"));
        expTokens.add(new Token(TokenType.DEFINE, "#define"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.IDENTIFIER, "HOTEND_OFFSET_Z"));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.LEFTBRACE, "{"));
        expTokens.add(new Token(TokenType.FLOAT, "0.0"));
        expTokens.add(new Token(TokenType.COMMA, ","));
        expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        expTokens.add(new Token(TokenType.FLOAT, "0.0"));
        expTokens.add(new Token(TokenType.RIGHTBRACE, "}"));
        expTokens.add(new Token(TokenType.NEWLINE, null));

        expTokens.add(new Token(TokenType.ENDIF, "#endif"));
        assertEquals(expTokens, lexerTokens);
    }

    private List<Token> convertValuesToListOfTokens(List<String> values, TokenType tokenType) {
        List<Token> expTokens = new ArrayList<>();
        values.forEach(x -> expTokens.add(new Token(tokenType, x)));
        return expTokens;
    }


    //    Управляющие конструкции
//#ifdef TEVO_BLTOUCH
//	#define STRING_CONFIG_H_AUTHOR "(dot_bob, BLTouch)" // Who made the changes.
//            #else
//            #define STRING_CONFIG_H_AUTHOR "(dot_bob, default config)" // Who made the changes.
//            #endif
//
//    Присваивание на основе других переменных
//#define STRING_SPLASH_LINE1 SHORT_BUILD_VERSION
//
//    Управляющая конструкция с функцией и отключенным параметром
//#if ENABLED(SWITCHING_EXTRUDER)
//  #define SWITCHING_EXTRUDER_SERVO_NR 0
//            #define SWITCHING_EXTRUDER_SERVO_ANGLES { 0, 90 } // Angles for E0, E1
//    //#define HOTEND_OFFSET_Z {0.0, 0.0}
//#endif
//#if DISABLED(ENDSTOPPULLUPS)
//#if ENABLED(AUTO_BED_LEVELING_LINEAR) || ENABLED(AUTO_BED_LEVELING_BILINEAR)
//#if PIN_EXISTS(CASE_LIGHT)
//
//    Сравнение
//#if POWER_SUPPLY > 0
//
//    Отключенные
////#define COREXY
////#define COREXZ
////#define COREYZ
//
//            Массивы


}