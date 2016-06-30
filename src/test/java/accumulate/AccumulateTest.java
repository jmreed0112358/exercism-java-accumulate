package accumulate;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AccumulateTest {

	@Test
	public void test_accumulate_InvalidInput_NullList_ThrowsException( ) {
		try {
			List<Integer> list = null;
			Accumulate.accumulate( list, x -> x * x );
			fail ("Supposed to catch NullPointerException");
		} catch ( NullPointerException npe ) {
			
		} catch ( Exception e ) {
			fail ("Expected to catch NullPointerException");
		}
	}
	
	@Test
	public void test_accumulate_InvalidInput_NullLambda_ThrowsException( ) {
		try {
			List<Integer> list = new LinkedList<Integer>();
			Accumulate.accumulate( list, null );
			fail ("Supposed to catch NullPointerException");
		} catch ( NullPointerException npe ) {
			
		} catch ( Exception e ) {
			fail ("Expected to catch NullPointerException");
		}
	}
	
    @Test
    public void emptyAccumulateProducesEmptyAccumulation() {
        List<Integer> input = new LinkedList<>();
        List<Integer> expectedOutput = new LinkedList<>();
        assertEquals(expectedOutput, Accumulate.accumulate(input, x -> x * x));
    }

    @Test
    public void accumulateSquares() {
        List<Integer> input = Arrays.asList(1, 2, 3);
        List<Integer> expectedOutput = Arrays.asList(1, 4, 9);
        assertEquals(expectedOutput, Accumulate.accumulate(input, x -> x * x));
    }

    @Test
    public void accumulateUpperCases() {
        List<String> input = Arrays.asList("hello", "world");
        List<String> expectedOutput = Arrays.asList("HELLO", "WORLD");
        assertEquals(expectedOutput, Accumulate.accumulate(input, x -> x.toUpperCase()));
    }

    @Test
    public void accumulateReversedStrings() {
        List<String> input = Arrays.asList("the quick brown fox etc".split(" "));
        List<String> expectedOutput = Arrays.asList("eht kciuq nworb xof cte".split(" "));
        assertEquals(expectedOutput, Accumulate.accumulate(input, this::reverse));
    }

    private String reverse(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    @Test
    public void accumulateWithinAccumulate() {
        List<String> input1 = Arrays.asList("a", "b", "c");
        List<String> input2 = Arrays.asList("1", "2", "3");
        List<String> expectedOutput = Arrays.asList("a1 a2 a3", "b1 b2 b3", "c1 c2 c3");
        assertEquals(expectedOutput, Accumulate.accumulate(
                input1, c ->
                        String.join(" ", Accumulate.accumulate(input2, d -> c + d))
        ));
    }
}
