import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ClientSocket {
    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int port = 8989;

        String info = "{\"title\": \"булка\", \"date\": \"2022.02.08\", \"sum\": 200}";

        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            out.println(info);
            String response = in.readLine();
            System.out.println(response);

        }
    }
}
