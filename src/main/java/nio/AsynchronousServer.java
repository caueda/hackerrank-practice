package nio;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AsynchronousServer {
	
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		new AsynchronousServer().initServer();
	}

	private void initServer() throws IOException, InterruptedException, ExecutionException {
		log.debug("Server started");
		AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open();
		server.bind(new InetSocketAddress("127.0.0.1", 8888));
		Future<AsynchronousSocketChannel> acceptFuture = server.accept();
		AsynchronousSocketChannel clientChannel = acceptFuture.get();
		if((clientChannel != null) && (clientChannel.isOpen())) {
			while(true) {
				ByteBuffer buffer = ByteBuffer.allocate(256);
				Future<Integer> readResult = clientChannel.read(buffer);
				readResult.get();
				String message = new String(buffer.array()).trim();
				
				if(message.toLowerCase().contains("exit")) {
					String closingMsg = "Closing connection at " + new Date();
					System.out.println(closingMsg);
					buffer = ByteBuffer.wrap(closingMsg.getBytes());
					Future<Integer> writeResult = clientChannel.write(buffer);
					writeResult.get();
					break;
				}
				String msg = "Echo " + message;
				buffer.clear();
				buffer = ByteBuffer.wrap(msg.getBytes());
				Future<Integer> writeResult = null;
				if(buffer.hasRemaining()) {
					writeResult = clientChannel.write(buffer);
				}
				writeResult.get();
				buffer.flip();
				buffer.clear();
			}
			clientChannel.close();
			server.close();
		}
	}

	public static Process start() throws IOException, InterruptedException {
		String javaHome = System.getProperty("java.home");
		String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
		String classpath = System.getProperty("java.class.path");
		String className = AsynchronousServer.class.getCanonicalName();
		ProcessBuilder builder = new ProcessBuilder(javaBin, "-cp", classpath, className);
		return builder.start();
	}
}
