package accumulate;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class Accumulate
{
	public static <T, R> List<R> accumulate(List<T> input, Function<T, R> rule) {
		if ( input == null || rule == null ) {
			throw new NullPointerException();
		}
		
		List<R> result = new LinkedList<R>();

		for ( int i = 0 ; i < input.size( ) ; i++ ) {
			result.add( rule.apply( input.get( i ) ) );
		}
		
		return result;
	}
}
