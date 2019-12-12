package increment;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6960);
        try (Socket clientSocket = serverSocket.accept();
             InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream()) {

            System.out.println("Новое соединение с " + clientSocket.getInetAddress().toString() + " - установлено");

            int request;
            while ((request = inputStream.read()) != -1) {
                System.out.println("Клиент прислал сообщение: " + request);
                outputStream.write(++request);
                System.out.println("Клиенту отправлено сообщение: " + request);
                outputStream.flush();
            }
            System.out.println("клиент отключился");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

