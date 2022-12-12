
import com.google.gson.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static File tsvFile = new File("categories.tsv");

    public static void main(String[] args) throws IOException {
        Map<String, Categories> titleCategoryMap = new HashMap<>();
        Map<String, Categories> categoryMap = new HashMap<>();
        String str;
        if (!tsvFile.exists()) return;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(tsvFile))) {
            while ((str = bufferedReader.readLine()) != null) {
                String[] titleCategoryArr = str.split("\t");
                for (int i = 0; i < titleCategoryArr.length; i++) {
                    String title = titleCategoryArr[0];
                    String category = titleCategoryArr[1];
                    titleCategoryMap.put(title, new Categories(title,category,0));
                    categoryMap.put(category, new Categories(title, category, 0));
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
                        JsonElement element =  JsonParser.parseString(input).getAsJsonObject().get("title");
                        JsonElement sum = JsonParser.parseString(input).getAsJsonObject().get("sum");
                        if (titleCategoryMap.containsKey(element.getAsString())) {
                            for (Categories item : titleCategoryMap.values()) {
                                if (item.getTitle().equals(element.getAsString())) {
                                    String cat = item.getCategory();
                                    categoryMap.get(cat).addSum(sum.getAsInt());
                                    titleCategoryMap.get(element.getAsString()).addSum(sum.getAsInt());
                                    break;
                                }
                            }
                        }else {
                            titleCategoryMap.put(element.getAsString(), new Categories(element.getAsString(), "другое", sum.getAsInt()));
                            if (categoryMap.containsKey("другое")) {
                                categoryMap.get("другое").addSum(sum.getAsInt());
                            }else {
                                categoryMap.put("другое",new Categories(element.getAsString(), "другое",sum.getAsInt()));
                            }
                        }
                        Optional<Categories> maxCategories = categoryMap.values().stream().max(Comparator.comparingInt(Categories::getSum));
                        maxCategories.ifPresent(Categories::getCategory);
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
