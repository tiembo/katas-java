import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class StringCalculatorTest {

    @Test
    public void testAdd() throws Exception {

        // step 1
        assertEquals(0, StringCalculator.add(""));
        assertEquals(0, StringCalculator.add("0"));
        assertEquals(1, StringCalculator.add("1"));
        assertEquals(10, StringCalculator.add("10"));
        assertEquals(0, StringCalculator.add("0,0"));
        assertEquals(3, StringCalculator.add("1,2"));
        assertEquals(11, StringCalculator.add("5,6"));
        assertEquals(46, StringCalculator.add("12,34"));

        // step 2
        assertEquals(6, StringCalculator.add("1,2,3"));

        // step 3
        assertEquals(6, StringCalculator.add("1\n2,3"));
        assertEquals(6, StringCalculator.add("1,2\n3"));
        assertEquals(6, StringCalculator.add("1\n2\n3"));

        // step 4
        assertEquals(3, StringCalculator.add("//;\n1;2"));

        // step 5 below - shouldNotAllowNegativeNumbers();

        // step 6
        assertEquals(2, StringCalculator.add("2,1001"));
        assertEquals(1002, StringCalculator.add("2,1000"));
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldNotAllowNegativeNumbers() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Negative numbers not allowed: -1, -3");
        StringCalculator.add("-1,2,-3");
    }
}