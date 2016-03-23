package java2bash.java2bash.commands.simple;

import static org.junit.Assert.*;

import java.util.EnumSet;

import org.junit.Test;

public class EchoTest {

	@Test
	public void testEchoString() {
		Echo echo = new Echo("HELLO");
		assertTrue(echo.getCode().equals("echo HELLO"));
	}

	@Test
	public void testEchoStringEnumSetOfOptions() {
		Echo echo = new Echo("HELLO", EnumSet.of(Echo.Options.NO_NEW_LINE));
		assertTrue(echo.getCode().equals("echo -n HELLO"));
	}
	
	@Test
	public void testEchoStringEnumOption() {
		Echo echo = new Echo("HELLO", Echo.Options.NO_NEW_LINE);
		assertTrue(echo.getCode().equals("echo -n HELLO"));
	}
	
	
}
