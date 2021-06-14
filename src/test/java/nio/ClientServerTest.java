package nio;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
class ClientServerTest {

	ClientServer client = null;
	
	@BeforeEach
	public void setUp() throws IOException, InterruptedException {
		client = ClientServer.getInstance();
		AsynchronousServer.start();
	}
	
	@Test
	@Order(1)
	void testSendMessage() throws IOException, InterruptedException {
		String response = client.sendMessage("123");
		String expected = "Echo 123";
		assertThat("testSendMessage", response, equalTo(expected));
	}
	
	@Test
	@Order(2)
	void testSendMessage_when_passing_exit_message() throws IOException, InterruptedException {
		String response = client.sendMessage("exit");
		String expected = "Closing connection at";
		assertThat("testSendMessage", response , containsString(expected));
	}
}
