package sendmessage;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 6960);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("UTF-8")))) {
            Scanner sc = new Scanner(System.in);
            String response = sc.nextLine();
            out.write(response + "\r\n");
            System.out.println("Серверу отправлено -  " + response);
            out.flush();
            System.out.println("Сервер сказал - "+in.readLine());
        }   
    }
}
