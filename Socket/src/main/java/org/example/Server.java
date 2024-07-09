package org.example;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            ByteBuffer buffer = ByteBuffer.allocate(256);
            socketChannel.read(buffer);
            buffer.flip();

            String url = StandardCharsets.UTF_8.decode(buffer).toString().trim();
            System.out.println(url);
            String content = fetchContentFromUrl(url);

            int totalChars = content.length();
            int chineseChars = (int) content.chars().filter(ch -> ch >= '\u4E00' && ch <= '\u9FFF').count();
            int englishChars = (int) content.chars().filter(ch -> (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')).count();
            int punctuationChars = (int) content.chars().filter(ch -> String.valueOf((char) ch).matches("\\p{Punct}")).count();

            String result = String.format("Total Characters: %d, Chinese Characters: %d, English Characters: %d, Punctuation Characters: %d",
                    totalChars, chineseChars, englishChars, punctuationChars);

            buffer.clear();
            buffer.put(result.getBytes(StandardCharsets.UTF_8));
            buffer.flip();
            socketChannel.write(buffer);

            socketChannel.close();
        }
    }

    private static String fetchContentFromUrl(String urlString) throws Exception {
        URL url = new URL(urlString);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), Charsets.UTF_8))) {
            return CharStreams.toString(reader);
        }
    }
}
