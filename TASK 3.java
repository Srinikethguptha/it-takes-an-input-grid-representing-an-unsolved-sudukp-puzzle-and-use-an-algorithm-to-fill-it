import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductScraper {
    public static void main(String[] args) throws IOException {
        String url = "https://www.example-ecommerce-website.com/products"; // Replace with your target URL

        Document doc = Jsoup.connect(url).get();

        // Find product elements (adjust selector as needed)
        Elements productElements = doc.select(".product-item");

        List<Product> products = new ArrayList<>();

        for (Element productElement : productElements) {
            String name = productElement.select(".product-name").text();
            String price = productElement.select(".product-price").text();
            String rating = productElement.select(".product-rating").text();

            Product product = new Product(name, price, rating);
            products.add(product);
        }

        // Write to CSV file
        File csvFile = new File("products.csv");
        FileWriter writer = new FileWriter(csvFile);

        writer.append("Name,Price,Rating\n");
        for (Product product : products) {
            writer.append(product.name + "," + product.price + "," + product.rating + "\n");
        }

        writer.flush();
        writer.close();

        System.out.println("Product information extracted and saved to products.csv");
    }

    static class Product {
        String name;
        String price;
        String rating;

        public Product(String name, String price, String rating) {
            this.name = name;
            this.price = price;
            this.rating = rating;
        }
    }
}