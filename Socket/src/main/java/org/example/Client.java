package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author 93253
 */
public class Client {
    public static void main(String[] args) throws Exception {
        BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter URL: ");
        String url = userInputReader.readLine();

        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 8080));

        ByteBuffer buffer = ByteBuffer.allocate(256);
        buffer.put(url.getBytes(StandardCharsets.UTF_8));
        buffer.flip();
        socketChannel.write(buffer);

        buffer.clear();
        socketChannel.read(buffer);
        buffer.flip();

        String response = StandardCharsets.UTF_8.decode(buffer).toString();
        System.out.println("Response from server: " + response);

        socketChannel.close();
    }
}
