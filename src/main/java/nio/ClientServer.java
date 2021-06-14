package nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientServer {
	private AsynchronousSocketChannel client;
	private Future<Void> future;
	private static ClientServer instance;
	
	private ClientServer() {
        try {
            client = AsynchronousSocketChannel.open();
            InetSocketAddress hostAddress = new InetSocketAddress("localhost", 8888);
            future = client.connect(hostAddress);
            start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static ClientServer getInstance() {
        if (instance == null)
            instance = new ClientServer();
        return instance;
    }

	private void start() {
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
	
	public void stop() {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public String sendMessage(String message) {
        byte[] byteMsg = message.getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(byteMsg);
        Future<Integer> writeResult = client.write(buffer);

        try {
            writeResult.get();
            buffer.flip();
        } catch (Exception e) {
            e.printStackTrace();
        }
        buffer = ByteBuffer.allocate(256);
        Future<Integer> readResult = client.read(buffer);
        try {
            readResult.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String echo = new String(buffer.array()).trim();
        buffer.clear();
        return echo;
    }
	
	public static void main(String[] args) throws Exception {
		
		AsynchronousServer.start();
		log.info("Client started.");
		ClientServer client = ClientServer.getInstance();
        client.start();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.print("Message to server: ");
        while ((line = br.readLine()) != null) {
            String response = client.sendMessage(line);
            System.out.println("response from server: " + response);
            if(!response.contains("Closing connection at ")) {
            	System.out.print("Message to server:");
            } else  {
            	client.stop();
            }
        }
    }
}
