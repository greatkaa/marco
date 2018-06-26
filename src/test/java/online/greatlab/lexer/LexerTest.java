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
public class LexerTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void textContainsOfAndAndSymbol_returnANDANDToken() throws Exception {
        String text = "&&";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.ANDAND, text), lex.get(0));
    }

    @Test
    public void textContainsOfAndSymbol_returnANDToken() throws Exception {
        String text = "&";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.AND, text), lex.get(0));
    }

    @Test
    public void textContainsOfAssignSymbol_returnASSIGNToken() throws Exception {
        String text = "=";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.ASSIGN, text), lex.get(0));
    }

    @Test
    public void textContainsOfAndAssignSymbol_returnANDASSIGNToken() throws Exception {
        String text = "&=";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.ANDASSIGN, text), lex.get(0));
    }

    @Test
    public void textContainsOfOrSymbol_returnORToken() throws Exception {
        String text = "|";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.OR, text), lex.get(0));
    }

    @Test
    public void textContainsOfOrAssignSymbol_returnORASSIGNToken() throws Exception {
        String text = "|=";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.ORASSIGN, text), lex.get(0));
    }

    @Test
    public void textContainsOfXorSymbol_returnXORToken() throws Exception {
        String text = "^";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.XOR, text), lex.get(0));
    }

    @Test
    public void textContainsOfXorAssignSymbol_returnXORASSIGNToken() throws Exception {
        String text = "^=";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.XORASSIGN, text), lex.get(0));
    }

    @Test
    public void textContainsOfCommaSymbol_returnCOMMAToken() throws Exception {
        String text = ",";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.COMMA, text), lex.get(0));
    }

    @Test
    public void textContainsOfChar_returnCHARTokens() throws Exception {
        List<String> values = Arrays.asList(
                "'f'",
                "'3'",
                "'\\u3456'",
                "'\t'"
        );
        String text = createTextFromList(values);
        List<Token> lexerTokens = Lexer.lex(text);
        assertEquals(convertValuesToListOfTokens(values, TokenType.CHAR), lexerTokens);
    }

    @Test
    public void textContainsOfFloatValues_returnFLOATTokens() throws Exception {
        List<String> values = Arrays.asList(
                "3.1",
                "-3.1",
                "3.223",
                "-3.223",
                "223.32e39",
                "-223.32e39");
        String text = createTextFromList(values);
        List<Token> lexerTokens = Lexer.lex(text);
        assertEquals(convertValuesToListOfTokens(values, TokenType.FLOAT), lexerTokens);
    }

    @Test
    public void textContainsOfIntegerValues_returnINTEGERTokens() throws Exception {
        List<String> values = Arrays.asList(
                "3",
                "3123234",
                "-13223",
                "0",
                "-1"
        );
        String text = createTextFromList(values);
        List<Token> lexerTokens = Lexer.lex(text);
        assertEquals(convertValuesToListOfTokens(values, TokenType.INT), lexerTokens);
    }

    @Test
    public void textContainsOfOctalIntValue_returnOCTALINTToken() throws Exception {
        String text = "01001000";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.OCTALINT, text), lex.get(0));
    }



    @Test
    public void textContainsOfStringValues_returnSTRINGTokens() throws Exception {
        List<String> values = Arrays.asList(
                "\"String\"",
                "\"String1234\"",
                "\"1234\"",
                "\"\"",
                "\"c9548f16-e739-4d1f-a081-b56d39c1ce6c\""
        );
        String text = createTextFromList(values);
        List<Token> lexerTokens = Lexer.lex(text);
        assertEquals(convertValuesToListOfTokens(values, TokenType.STRING), lexerTokens);
    }

    @Test
    public void textContainsOfTrueValue_returnTRUEToken() throws Exception {
        String text = "true";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.TRUE, text), lex.get(0));
    }

    @Test
    public void textContainsOfFalseValue_returnFALSEToken() throws Exception {
        String text = "false";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.FALSE, text), lex.get(0));
    }

    @Test
    public void textContainsOfCommentValue_returnCOMMENTToken() throws Exception {
        String text = "//";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.COMMENT, text), lex.get(0));
    }

    @Test
    public void textContainsOfCommentStartValue_returnCOMMENTSTARTToken() throws Exception {
        String text = "/*";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.COMMENTSTART, text), lex.get(0));
    }

    @Test
    public void textContainsOfCommentEndValue_returnCOMMENTENDToken() throws Exception {
        String text = "*/";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.COMMENTEND, text), lex.get(0));
    }

    @Test
    public void textContainsOfContlineValue_returnCONTLINEToken() throws Exception {
        String text = "\\\\\n";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.CONTLINE, null), lex.get(0));
    }

    @Test
    public void textContainsOfNewlineValue_returnNEWLINEToken() throws Exception {
        String text = "\n";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.NEWLINE, null), lex.get(0));
    }

    @Test
    public void textContainsOfDefineValue_returnDEFINEToken() throws Exception {
        String text = "#define";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.DEFINE, text), lex.get(0));
    }

    @Test
    public void textContainsOfIfValue_returnIFToken() throws Exception {
        String text = "#if";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.IF, text), lex.get(0));
    }

    @Test
    public void textContainsOfIfdefValue_returnIFDEFToken() throws Exception {
        String text = "#ifdef";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.IFDEF, text), lex.get(0));
    }

    @Test
    public void textContainsOfElifValue_returnELIFToken() throws Exception {
        String text = "#elif";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.ELIF, text), lex.get(0));
    }

    @Test
    public void textContainsOfIfndefValue_returnIFNDEFToken() throws Exception {
        String text = "#ifndef";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.IFNDEF, text), lex.get(0));
    }

    @Test
    public void textContainsOfElseValue_returnELSEToken() throws Exception {
        String text = "#else";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.ELSE, text), lex.get(0));
    }

    @Test
    public void textContainsOfEndifValue_returnENDIFToken() throws Exception {
        String text = "#endif";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.ENDIF, text), lex.get(0));
    }

    @Test
    public void textContainsOfErrorValue_returnERRORToken() throws Exception {
        String text = "#error";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.ERROR, text), lex.get(0));
    }

    @Test
    public void textContainsOfLineValue_returnLINEToken() throws Exception {
        String text = "#line";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.LINE, text), lex.get(0));
    }

    @Test
    public void textContainsOfPragmaValue_returnPRAGMAToken() throws Exception {
        String text = "#pragma";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.PRAGMA, text), lex.get(0));
    }

    @Test
    public void textContainsOfUndefValue_returnUNDEFToken() throws Exception {
        String text = "#undef";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.UNDEF, text), lex.get(0));
    }

    @Test
    public void textContainsOfWarningValue_returnWARNINGToken() throws Exception {
        String text = "#warning";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.WARNING, text), lex.get(0));
    }

    @Test
    public void textContainsOfIncludeValue_returnINCLUDEToken() throws Exception {
        String text = "#include \"stdout.h\"";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.INCLUDE, text), lex.get(0));
    }

    @Test
    public void textContainsOfQHeaderValue_returnQHEADERToken() throws Exception {
        String text = "#include <stdout.h>";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.QHEADER, text), lex.get(0));
    }

    @Test
    public void textContainsOfHHeaderValue_returnHHEADERToken() throws Exception {
        String text = "#include stdout.h";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.HHEADER, text), lex.get(0));
    }

    @Test
    public void textContainsOfIdentifierValue_returnIDENTIFIERToken() throws Exception {
        List<String> values = Arrays.asList(
                "String",
                "String1234",
                "fg234"
        );
        String text = createTextFromList(values);
        List<Token> lexerTokens = Lexer.lex(text);
        assertEquals(convertValuesToListOfTokens(values, TokenType.IDENTIFIER), lexerTokens);
    }

    @Test
    public void textContainsOfOpenBracerValue_returnOPENBRACERToken() throws Exception {
        String text = "(";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.LEFTPAREN, text), lex.get(0));
    }

    @Test
    public void textContainsOfCloseBracerValue_returnCLOSEBRACERToken() throws Exception {
        String text = ")";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.RIGHTPAREN, text), lex.get(0));
    }

    @Test
    public void textContainsOfOpenFigBracerValue_returnOPENFIGBRACERToken() throws Exception {
        String text = "{";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.LEFTBRACE, text), lex.get(0));
    }

    @Test
    public void textContainsOfCloseFigBracerValue_returnCLOSEFIGBRACERToken() throws Exception {
        String text = "}";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.RIGHTBRACE, text), lex.get(0));
    }

    @Test
    public void textContainsOfColonValue_returnCOLONToken() throws Exception {
        String text = ":";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.COLON, text), lex.get(0));
    }

    @Test
    public void textContainsOfDivideValue_returnDIVIDEToken() throws Exception {
        String text = "/";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.DIVIDE, text), lex.get(0));
    }

    @Test
    public void textContainsOfDivideAssignValue_returnDIVIDEASSIGNToken() throws Exception {
        String text = "/=";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.DIVIDEASSIGN, text), lex.get(0));
    }

    @Test
    public void textContainsOfDotValue_returnDOTToken() throws Exception {
        String text = ".";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.DOT, text), lex.get(0));
    }

    @Test
    public void textContainsOfDotStarValue_returnDOTSTARToken() throws Exception {
        String text = ".*";
        List<Token> lex = Lexer.lex(text);
        assertEquals(1, lex.size());
        assertEquals(new Token(TokenType.DOTSTAR, text), lex.get(0));
    }

    @Test
    public void textContainsOfEllipsisValue_returnELLIPSISToken() throws Exception {
        String text = "...";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.ELLIPSIS, text), lex.get(0));
    }

    @Test
    public void textContainsOfEqualValue_returnEQUALToken() throws Exception {
        String text = "==";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.EQUAL, text), lex.get(0));
    }

    @Test
    public void textContainsOfGreaterEqualValue_returnGREATEREQUALToken() throws Exception {
        String text = ">=";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.GREATEREQUAL, text), lex.get(0));
    }

    @Test
    public void textContainsOfLessValue_returnLESSToken() throws Exception {
        String text = "<";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.LESS, text), lex.get(0));
    }

    @Test
    public void textContainsOfLessEqualValue_returnLESSEQUALToken() throws Exception {
        String text = "<=";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.LESSEQUAL, text), lex.get(0));
    }

    @Test
    public void textContainsOfLeftBracketValue_returnLEFTBRACKETToken() throws Exception {
        String text = "[";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.LEFTBRACKET, text), lex.get(0));
    }

    @Test
    public void textContainsOfMinusValue_returnMINUSToken() throws Exception {
        String text = "-";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.MINUS, text), lex.get(0));
    }

    @Test
    public void textContainsOfMinusAssignValue_returnMINUSASSIGNToken() throws Exception {
        String text = "-=";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.MINUSASSIGN, text), lex.get(0));
    }

    @Test
    public void textContainsOfMinusMinusValue_returnMINUSMINUSToken() throws Exception {
        String text = "--";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.MINUSMINUS, text), lex.get(0));
    }

    @Test
    public void textContainsOfPercentValue_returnPERCENTToken() throws Exception {
        String text = "%";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.PERCENT, text), lex.get(0));
    }

    @Test
    public void textContainsOfPercentAssignValue_returnPERCENTASSIGNToken() throws Exception {
        String text = "%=";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.PERCENTASSIGN, text), lex.get(0));
    }

    @Test
    public void textContainsOfNotValue_returnNOTToken() throws Exception {
        String text = "!";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.NOT, text), lex.get(0));
    }

    @Test
    public void textContainsOfNotEqualValue_returnNOTEQUALToken() throws Exception {
        String text = "!=";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.NOTEQUAL, text), lex.get(0));
    }

    @Test
    public void textContainsOfOrOrValue_returnORORToken() throws Exception {
        String text = "||";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.OROR, text), lex.get(0));
    }

    @Test
    public void textContainsOfPlusValue_returnPLUSToken() throws Exception {
        String text = "+";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.PLUS, text), lex.get(0));
    }

    @Test
    public void textContainsOfPlusPlusValue_returnPLUSPLUSToken() throws Exception {
        String text = "++";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.PLUSPLUS, text), lex.get(0));
    }

    @Test
    public void textContainsOfPlusAssignValue_returnPLUSASSIGNToken() throws Exception {
        String text = "+=";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.PLUSASSIGN, text), lex.get(0));
    }

    @Test
    public void textContainsOfArrowValue_returnARROWToken() throws Exception {
        String text = "->";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.ARROW, text), lex.get(0));
    }

    @Test
    public void textContainsOfArrowStarValue_returnARROWSTARToken() throws Exception {
        String text = "->*";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.ARROWSTAR, text), lex.get(0));
    }

    @Test
    public void textContainsOfQuestionMarkValue_returnQUESTIONMARKToken() throws Exception {
        String text = "?";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.QUESTIONMARK, text), lex.get(0));
    }

    @Test
    public void textContainsOfRightBracketValue_returnRIGHTBRACKETToken() throws Exception {
        String text = "]";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.RIGHTBRACKET, text), lex.get(0));
    }

    @Test
    public void textContainsOfColonColonValue_returnCOLONCOLONToken() throws Exception {
        String text = "::";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.COLONCOLON, text), lex.get(0));
    }

    @Test
    public void textContainsOfSemicolonValue_returnSEMICOLONToken() throws Exception {
        String text = ";";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.SEMICOLON, text), lex.get(0));
    }

    @Test
    public void textContainsOfShiftLeftValue_returnSHIFTLEFTToken() throws Exception {
        String text = "<<";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.SHIFTLEFT, text), lex.get(0));
    }

    @Test
    public void textContainsOfShiftLeftAssignValue_returnSHIFTLEFTASSIGNToken() throws Exception {
        String text = "<<=";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.SHIFTLEFTASSIGN, text), lex.get(0));
    }

    @Test
    public void textContainsOfGreaterValue_returnGREATERToken() throws Exception {
        String text = ">";
        List<Token> lex = Lexer.lex(text);
        assertEquals(1, lex.size());
        assertEquals(new Token(TokenType.GREATER, text), lex.get(0));
    }

    @Test
    public void textContainsOfShiftRightValue_returnSHIFTRIGHTToken() throws Exception {
        String text = ">>";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.SHIFTRIGHT, text), lex.get(0));
    }

    @Test
    public void textContainsOfShiftRightAssignValue_returnSHIFTRIGHTASSIGNToken() throws Exception {
        String text = ">>=";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.SHIFTRIGHTASSIGN, text), lex.get(0));
    }

    @Test
    public void textContainsOfStarValue_returnSTARToken() throws Exception {
        String text = "*";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.STAR, text), lex.get(0));
    }

    @Test
    public void textContainsOfComplValue_returnCOMPLToken() throws Exception {
        String text = "~";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.COMPL, text), lex.get(0));
    }

    @Test
    public void textContainsOfStarAssignValue_returnSTARASSIGNToken() throws Exception {
        String text = "*=";
        List<Token> lex = Lexer.lex(text);
        assertEquals(1, lex.size());
        assertEquals(new Token(TokenType.STARASSIGN, text), lex.get(0));
    }

    @Test
    public void textContainsOfPoundPoundValue_returnPOUNDPOUNDToken() throws Exception {
        String text = "##";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.POUNDPOUND, text), lex.get(0));
    }

    @Test
    public void textContainsOfPoundValue_returnPOUNDToken() throws Exception {
        String text = "#";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.POUND, text), lex.get(0));
    }

    @Test
    public void textContainsOfAndAltValue_returnANDALTToken() throws Exception {
        String text = "bitand";
        List<Token> lex = Lexer.lex(text);
        assertEquals(1, lex.size());
        assertEquals(new Token(TokenType.ANDALT, text), lex.get(0));
    }

    @Test
    public void textContainsOfAndAssignAltValue_returnANDASSIGNALTToken() throws Exception {
        String text = "and_eq";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.ANDASSIGNALT, text), lex.get(0));
    }

    @Test
    public void textContainsOfAndOrAltValue_returnORALTToken() throws Exception {
        String text = "or";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.ORALT, text), lex.get(0));
    }

    @Test
    public void textContainsOfOrAssignAltValue_returnORASSIGNALTToken() throws Exception {
        String text = "or_eq";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.ORASSIGNALT, text), lex.get(0));
    }

    @Test
    public void textContainsOfXorAltValue_returnXORALTToken() throws Exception {
        String text = "xor";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.XORALT, text), lex.get(0));
    }

    @Test
    public void textContainsOfXorAssignAltValue_returnXORASSIGNALTToken() throws Exception {
        String text = "xor_eq";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.XORASSIGNALT, text), lex.get(0));
    }

    @Test
    public void textContainsOfNotAltValue_returnNOTALTToken() throws Exception {
        String text = "not";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.NOTALT, text), lex.get(0));
    }

    @Test
    public void textContainsOfNotEqualAltValue_returnNOTEQUALALTToken() throws Exception {
        String text = "not_eq";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.NOTEQUALALT, text), lex.get(0));
    }

    @Test
    public void textContainsOfComplAltValue_returnCOMPLALTToken() throws Exception {
        String text = "compl";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.COMPLALT, text), lex.get(0));
    }

    @Test
    public void textContainsOfLeftBraceAltValue_returnLEFTBRACEALTToken() throws Exception {
        String text = "<%";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.LEFTBRACEALT, text), lex.get(0));
    }

    @Test
    public void textContainsOfLeftBracketAltValue_returnLEFTBRACKETALTToken() throws Exception {
        String text = "<:";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.LEFTBRACKETALT, text), lex.get(0));
    }

    @Test
    public void textContainsOfRightBraceAltValue_returnRIGHTBRACEALTToken() throws Exception {
        String text = "%>";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.RIGHTBRACEALT, text), lex.get(0));
    }

    @Test
    public void textContainsOfRightBrackeTaltValue_returnRIGHTBRACKETALTToken() throws Exception {
        String text = ":>";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.RIGHTBRACKETALT, text), lex.get(0));
    }

    @Test
    public void textContainsOfPoundPoundAltValue_returnPOUNDPOUNDALTToken() throws Exception {
        String text = "%:%:";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.POUNDPOUNDALT, text), lex.get(0));
    }

    @Test
    public void textContainsOfPoundAltValue_returnPOUNDALTToken() throws Exception {
        String text = "%:";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.POUNDALT, text), lex.get(0));
    }

    @Test
    public void textContainsOfOrTrigraphValue_returnORTRIGRAPHToken() throws Exception {
        String text = "??!";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.ORTRIGRAPH, text), lex.get(0));
    }

    @Test
    public void textContainsOfXorTrigraphValue_returnXORTRIGRAPHToken() throws Exception {
        String text = "??'";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.XORTRIGRAPH, text), lex.get(0));
    }

    @Test
    public void textContainsOfLeftBraceTrigraphValue_returnLEFTBRACETRIGRAPHToken() throws Exception {
        String text = "??<";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.LEFTBRACETRIGRAPH, text), lex.get(0));
    }

    @Test
    public void textContainsOfLeftBracketTrigraphValue_returnLEFTBRACKETTRIGRAPHToken() throws Exception {
        String text = "??(";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.LEFTBRACKETTRIGRAPH, text), lex.get(0));
    }

    @Test
    public void textContainsOfRightBraceTrigraphValue_returnRIGHTBRACETRIGRAPHToken() throws Exception {
        String text = "??>";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.RIGHTBRACETRIGRAPH, text), lex.get(0));
    }

    @Test
    public void textContainsOfRightBracketTrigraphValue_returnRIGHTBRACKETTRIGRAPHToken() throws Exception {
        String text = "??)";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.RIGHTBRACKETTRIGRAPH, text), lex.get(0));
    }

    @Test
    public void textContainsOfComplTrigraphValue_returnCOMPLTRIGRAPHToken() throws Exception {
        String text = "??-";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.COMPLTRIGRAPH, text), lex.get(0));
    }

    @Test
    public void textContainsOfPoundPoundTrigraphValue_returnPOUNDPOUNDTRIGRAPHToken() throws Exception {
        String text = "??=??=";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.POUNDPOUNDTRIGRAPH, text), lex.get(0));
    }

    @Test
    public void textContainsOfPoundTrigraphValue_returnPOUNDTRIGRAPHToken() throws Exception {
        String text = "??=";
        List<Token> lex = Lexer.lex(text);
        assertEquals(new Token(TokenType.POUNDTRIGRAPH, text), lex.get(0));
    }

    private String createTextFromList(List<String> values) {
        StringJoiner stringJoiner = new StringJoiner(" ", "", "");
        values.forEach(stringJoiner::add);
        return stringJoiner.toString();
    }

    private List<Token> convertValuesToListOfTokens(List<String> values, TokenType tokenType) {
        List<Token> expTokens = new ArrayList<>();
        if (tokenType.equals(TokenType.STRING)) {
            values.replaceAll(x -> x.substring(1, x.length() - 1));
        }
        values.forEach(x -> {
            expTokens.add(new Token(tokenType, x));
            expTokens.add(new Token(TokenType.WHITESPACE, "1"));
        });
        expTokens.remove(expTokens.size() - 1);
        return expTokens;
    }

}