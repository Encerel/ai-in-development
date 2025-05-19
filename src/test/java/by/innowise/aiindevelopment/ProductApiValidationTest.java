package by.innowise.aiindevelopment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductApiValidationTest {

    private static final String API_URL = "https://fakestoreapi.com/products";

    @Test
    void testApiDataValidation() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        // Send GET request
        ResponseEntity<String> response = restTemplate.getForEntity(API_URL, String.class);
        assertEquals(200, response.getStatusCodeValue(), "Expected status code 200");

        // Parse JSON
        ObjectMapper mapper = new ObjectMapper();
        Product[] products = mapper.readValue(response.getBody(), Product[].class);

        List<Product> defectiveProducts = new ArrayList<>();

        for (Product product : products) {
            boolean hasDefect = false;

            if (product.getTitle() == null || product.getTitle().trim().isEmpty()) {
                hasDefect = true;
            }

            if (product.getPrice() < 0) {
                hasDefect = true;
            }

            if (product.getRating() != null && product.getRating().getRate() > 5) {
                hasDefect = true;
            }

            if (hasDefect) {
                defectiveProducts.add(product);
            }
        }

        // Output defective products
        if (!defectiveProducts.isEmpty()) {
            System.out.println("Defective products found:");
            defectiveProducts.forEach(System.out::println);
        } else {
            System.out.println("No defective products found.");
        }


        assertTrue(defectiveProducts.isEmpty(), "Found defective products!");
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Product {
        private String title;
        private double price;
        private Rating rating;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public Rating getRating() {
            return rating;
        }

        public void setRating(Rating rating) {
            this.rating = rating;
        }

        @Override
        public String toString() {
            return "Product{" +
                   "title='" + title + '\'' +
                   ", price=" + price +
                   ", rating=" + rating +
                   '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Rating {
        private double rate;

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        @Override
        public String toString() {
            return "Rating{rate=" + rate + '}';
        }
    }
}