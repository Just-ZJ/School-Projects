import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.statement.Statement;
import components.utilities.Tokenizer;

/**
 * JUnit test fixture for {@code Statement}'s constructor and kernel methods.
 *
 * @author Zheng Ji Tan(tan.955) & Beiwen Wu(wu.4742) & ZheHui Li(li.8908)
 *
 */
public abstract class StatementTest {

    /**
     * The name of a file containing a sequence of BL statements.
     */
    private static final String FILE_NAME_1 = "test/Statement/statement_Valid_Example.bl",
            VALID_CALL = "test/Statement/statement_Valid_Call.bl",
            VALID_IF = "test/Statement/statement_Valid_If.bl",
            VALID_IF_ELSE = "test/Statement/statement_Valid_IfElse.bl",
            VALID_WHILE = "test/Statement/statement_Valid_While.bl",
            VALID_PARSE2 = "test/Statement/statement_Valid_Parse2.bl";

    /**
     * The name of a file containing a sequence of invalid BL statements.
     */
    private static final String FILE_NAME_2 = "test/Statement/"
            + "statement_Invalid_Example.bl",
            INVALID_IF_CONDITION = "test/Statement/statement_Invalid_IfCondition.bl",
            INVALID_IF_MISSING_THEN = "test/Statement/statement_Invalid_IfMissingTHEN.bl",
            INVALID_IF_MISSING_END = "test/Statement/statement_Invalid_IfMissingEND.bl",
            INVALID_IF_MISSING_ENDING_IF = "test/Statement/"
                    + "statement_Invalid_IfMissingEndingIF.bl",
            INVALID_WHILE_CONDITION = "test/Statement/"
                    + "statement_Invalid_WhileCondition.bl",
            INVALID_WHILE_MISSING_DO = "test/Statement/"
                    + "statement_Invalid_WhileMissingDO.bl",
            INVALID_WHILE_MISSING_END = "test/Statement/"
                    + "statement_Invalid_WhileMissingEND.bl",
            INVALID_WHILE_MISSING_ENDING_WHILE = "test/Statement/"
                    + "statement_Invalid_WhileMissingEndingWHILE.bl";

    /**
     * Invokes the {@code Statement} constructor for the implementation under
     * test and returns the result.
     *
     * @return the new statement
     * @ensures constructorTest = compose((BLOCK, ?, ?), <>)
     */
    protected abstract Statement constructorTest();

    /**
     * Invokes the {@code Statement} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new statement
     * @ensures constructorRef = compose((BLOCK, ?, ?), <>)
     */
    protected abstract Statement constructorRef();

    /**
     * Test of parse on syntactically valid input.
     */
    @Test
    public final void testParseValidExample() {
        /*
         * Setup
         */
        Statement sRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_1);
        Queue<String> tokens = Tokenizer.tokens(file);
        sRef.parse(tokens);
        file.close();
        Statement sTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_1);
        tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        sTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
    }

    @Test
    public final void testParseValidCall() {
        /*
         * Setup
         */
        Statement sRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(VALID_CALL);
        Queue<String> tokens = Tokenizer.tokens(file);
        sRef.parse(tokens);
        file.close();
        Statement sTest = this.constructorTest();
        file = new SimpleReader1L(VALID_CALL);
        tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        sTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
    }

    @Test
    public final void testParseValidIf() {
        /*
         * Setup
         */
        Statement sRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(VALID_IF);
        Queue<String> tokens = Tokenizer.tokens(file);
        sRef.parse(tokens);
        file.close();
        Statement sTest = this.constructorTest();
        file = new SimpleReader1L(VALID_IF);
        tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        sTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
    }

    @Test
    public final void testParseValidIfElse() {
        /*
         * Setup
         */
        Statement sRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(VALID_IF_ELSE);
        Queue<String> tokens = Tokenizer.tokens(file);
        sRef.parse(tokens);
        file.close();
        Statement sTest = this.constructorTest();
        file = new SimpleReader1L(VALID_IF_ELSE);
        tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        sTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
    }

    @Test
    public final void testParseValidWhile() {
        /*
         * Setup
         */
        Statement sRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(VALID_WHILE);
        Queue<String> tokens = Tokenizer.tokens(file);
        sRef.parse(tokens);
        file.close();
        Statement sTest = this.constructorTest();
        file = new SimpleReader1L(VALID_WHILE);
        tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        sTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
    }

    @Test
    public final void testParseValidParse2() {
        /*
         * Setup
         */
        Statement sRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(VALID_PARSE2);
        Queue<String> tokens = Tokenizer.tokens(file);
        sRef.parse(tokens);
        file.close();
        Statement sTest = this.constructorTest();
        file = new SimpleReader1L(VALID_PARSE2);
        tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        sTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
    }

    /**
     ********* Test of parse on syntactically invalid input. *********
     */
    @Test(expected = RuntimeException.class)
    public final void testParseErrorExample() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_2);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorIfCondition() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(INVALID_IF_CONDITION);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorIfMissingTHEN() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(INVALID_IF_MISSING_THEN);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorIfMissingEND() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(INVALID_IF_MISSING_END);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorIfMissingEndingIf() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(INVALID_IF_MISSING_ENDING_IF);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorWhileCondition() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(INVALID_WHILE_CONDITION);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorWhileMissingDO() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(INVALID_WHILE_MISSING_DO);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorWhileMissingEND() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(INVALID_WHILE_MISSING_END);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorWhileMissingEndingWHILE() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                INVALID_WHILE_MISSING_ENDING_WHILE);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    // TODO - add more test cases for valid inputs for both parse and parseBlock
    // TODO - add more test cases for as many distinct syntax errors as possible
    //        for both parse and parseBlock

}
