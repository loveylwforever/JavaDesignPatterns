import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

// OCP 开闭原则
public class OpenClosedPrincipleDemo {
    public static void main(String[] args) {
        Product apple = new Product("Apple", Color.RED, Size.SMALL);
        Product tree = new Product("Tree", Color.GREEN, Size.MEDIUM);
        Product house = new Product("House", Color.BLUE, Size.LARGE);

        List<Product> products = Arrays.asList(apple, tree, house);

        ProductFilter productFilter = new ProductFilter();
        productFilter.filterByColor(products, Color.BLUE).forEach(p -> System.out.println(p.name + " is blue"));

        productFilter.filterBySize(products, Size.SMALL).forEach(p -> System.out.println(p.name + " is small"));

        productFilter.filterByColorAndSize(products, Color.GREEN, Size.MEDIUM).forEach(p -> System.out.println(p.name + " is green and medium"));
    }
}

enum Color{
    RED, GREEN, BLUE
}

enum Size{
    SMALL, MEDIUM, LARGE
}

class Product{
    public String name;
    public Color color;
    public Size size;

    public Product(String name, Color color, Size size) {
        this.name = name;
        this.color = color;
        this.size = size;
    }
}

class ProductFilter{
    public Stream<Product> filterByColor(List<Product> products, Color color){
        return products.stream().filter(p -> p.color == color);
    }

    public Stream<Product> filterBySize(List<Product> products, Size size){
        return products.stream().filter(p -> p.size == size);
    }

    public Stream<Product> filterByColorAndSize(List<Product> products,
                                                Color color,
                                                Size size){
        return products.stream().filter(p -> p.color == color
                                            && p.size == size);
    }
}
