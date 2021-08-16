package utils;

import static java.util.Objects.nonNull;

import java.util.List;
import org.openqa.selenium.WebElement;

public class ProductSelectionUtils {

    private ProductSelectionUtils(){}

    /**
     * Selects a product according to the number given. If the number given is grater than the existing products, selects the last product.
     *
     * @param products The list of products to search in
     * @param number The number of product to select (starting from 1)
     * @return The n-th or last product found, or null in case the products list is empty.
     */
    public static WebElement selectNthOrLastProduct(List<WebElement> products, int number) {

        WebElement product = null;
        boolean isInputValid = nonNull(products) && !products.isEmpty() && number > 0;
        if (isInputValid) {
            if (products.size() == number) {
                product = products.get(number - 1);
            } else {
                product = products.get(products.size() - 1);
            }
        }
        return product;
    }
}
