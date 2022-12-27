
import com.google.gson.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Main {
    public static File tsvFile = new File("categories.tsv");

    public static void main(String[] args) throws IOException {
        Categories categories = new Categories();
        Statistic statistic = new Statistic();
        String str;
        if (!tsvFile.exists()) return;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(tsvFile))) {
            while ((str = bufferedReader.readLine()) != null) {
                String[] titleCategoryArr = str.split("\t");
                for (int i = 0; i < titleCategoryArr.length; i++) {
                    String title = titleCategoryArr[0];
                    String category = titleCategoryArr[1];
                    /*categories*/statistic.addTitleCategoryMap(title, category);
                    categories.addCategoryMap(category, /*title,*/ 0);
                }
            }
        }

        try (ServerSocket serverSocket = new ServerSocket(8989)) { // стартуем сервер один(!) раз
            while (true) { // в цикле(!) принимаем подключения
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream())
                ) {
                    {
                        String input = in.readLine();
                        System.out.println(input);

                        categories.statistics(input, statistic, categories);

                        Optional<Categories> maxCategories = categories.getMaxCategory();

                        JsonObject maxCategory = new JsonObject();
                        JsonObject jsonObject = new JsonObject();
                        maxCategory.add("category", new JsonPrimitive(maxCategories.get().getCategory()));
                        maxCategory.add("sum", new JsonPrimitive(maxCategories.get().getSum()));
                        jsonObject.add("maxCategory",maxCategory);
                        out.println(jsonObject);
                    }
                }
            }
        }
    }
}
