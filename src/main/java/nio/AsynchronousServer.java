package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsynchronousServer {
	
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open();
		server.bind(new InetSocketAddress("127.0.0.1", 8888));
		Future<AsynchronousSocketChannel> acceptFuture = server.accept();
		AsynchronousSocketChannel clientChannel = acceptFuture.get();
		if((clientChannel != null) && (clientChannel.isOpen())) {
			while(true) {
				ByteBuffer buffer = ByteBuffer.allocate(32);
				Future<Integer> readResult = clientChannel.read(buffer);
				readResult.get();
				String content = byteBufferToString(buffer, Charset.defaultCharset());
				if(content.contains("0")) {
					System.out.println("Interrupting server.");
					break;
				}
				buffer.flip();
				Future<Integer> writeResult = clientChannel.write(buffer);
				writeResult.get();
				buffer.clear();
			}
			clientChannel.close();
			server.close();
		}
	}
	
	public static ByteBuffer stringToByteBuffer(String msg, Charset charset){
	    return ByteBuffer.wrap(msg.getBytes(charset));
	}

	public static String byteBufferToString(ByteBuffer buffer, Charset charset){
	    byte[] bytes;
	    if(buffer.hasArray()) {
	        bytes = buffer.array();
	    } else {
	        bytes = new byte[buffer.remaining()];
	        buffer.get(bytes);
	    }
	    return new String(bytes, charset);
	}
}
