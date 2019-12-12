package increment;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 6960);
             InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()) {
            int response = 1;
            outputStream.write(response);
            System.out.println("отправлено серверу: " + response);

            while ((response = inputStream.read()) != -1) {
                System.out.println("прислал сервер: " + response);
                if (response >= 10) {
                    break;
                }
                outputStream.write(response++);
                System.out.println("отправлено серверу: " + response);
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}