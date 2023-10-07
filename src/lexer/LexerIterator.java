package lexer;

import java.util.ArrayList;

public class LexerIterator {
    ArrayList<Token> tokens;
    int pos;
    boolean printOrNot;

    public LexerIterator(ArrayList<Token> tokens) {
        pos = 0;
        this.tokens = tokens;
        this.printOrNot = true;
    }

    public void setPrintOrNot(boolean printOrNot) {
        this.printOrNot = printOrNot;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public Token read() {
        if (pos < tokens.size()) {
            Token token = tokens.get(pos);
            if (printOrNot) {
                System.out.println(token.getLexType() + " " + token.getVal()); //print?
            }
            pos++;
            return token;
        }
        else {
            return null;
        }
    }

    public Token preRead(int cnt) {
        int prePos = pos + cnt - 1;
        if (prePos < tokens.size()) {
            return tokens.get(prePos);
        }
        else {
            return null;
        }
    }

    public boolean hasNext() {
        return pos < tokens.size();
    }
}
