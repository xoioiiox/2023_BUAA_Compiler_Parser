package parser.function;

import lexer.LexType;
import lexer.LexerIterator;
import lexer.Token;
import parser.declaration.Btype;
import parser.expression.ConstExp;
import parser.expression.ExpParser;
import parser.statement.Block;
import parser.statement.BlockParser;

import java.util.ArrayList;

public class FuncDefParser {
    LexerIterator iterator;

    public FuncDefParser(LexerIterator iterator) {
        this.iterator = iterator;
    }

    public FuncDef parseFuncDef() {
        ArrayList<FuncFParam> funcFParams = new ArrayList<>();
        FuncType funcType = new FuncType(iterator.read());
        System.out.println("<FuncType>");
        Token Ident = iterator.read();
        iterator.read(); // (
        if (iterator.preRead(1).getLexType() != LexType.RPARENT) {
            funcFParams.add(parseFuncFParam());
            while (iterator.preRead(1).getLexType() == LexType.COMMA) {
                iterator.read(); // ,
                funcFParams.add(parseFuncFParam());
            }
            System.out.println("<FuncFParams>");
        }
        iterator.read(); // )
        BlockParser blockParser = new BlockParser(iterator);
        Block block = blockParser.parseBlock(); //block
        System.out.println("<FuncDef>");
        return new FuncDef(funcType, Ident, funcFParams, block);
    }

    public FuncFParam parseFuncFParam() {
        ConstExp constExp = null;
        iterator.read();
        Btype btype = new Btype("int");
        Token Ident = iterator.read();
        while (iterator.preRead(1).getLexType() == LexType.LBRACK) {
            iterator.read(); // [
            if (iterator.preRead(1).getLexType() != LexType.RBRACK) {
                ExpParser expParser = new ExpParser(iterator);
                constExp = expParser.parseConstExp(); //parseConstExp
            }
            iterator.read(); // ]
        }
        System.out.println("<FuncFParam>");
        return new FuncFParam(btype, Ident, constExp);
    }
}
