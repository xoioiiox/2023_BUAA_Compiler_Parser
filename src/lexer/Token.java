package lexer;

public class Token {
    private LexType lexType;
    private String val;

    public Token(LexType lexType, String val) {
        this.lexType = lexType;
        this.val = val;
    }

    public LexType getLexType() {
        return lexType;
    }

    public String getVal() {
        return val;
    }
}
