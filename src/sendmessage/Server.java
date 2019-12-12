package sendmessage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6960);
            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), Charset.forName("UTF-8")));
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), Charset.forName("UTF-8")))) {

                System.out.println("Новое подключение - " + clientSocket.getInetAddress().toString()+" - установлено");

                String request = in.readLine();

                out.write(request + "\r\n");
                out.flush();

                System.out.println("клиент отсоединился");
        }
    }
}

