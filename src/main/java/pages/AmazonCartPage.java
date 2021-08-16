package pages;

import com.persado.oss.quality.stevia.selenium.core.WebComponent;
import utils.PricesUtils;

public class AmazonCartPage extends WebComponent {

    private Locators locators;

    private static final class Locators {

        private String productPrice;

        private Locators() {

        }

        private void setProductPrice(String itemId) {

            this.productPrice = String.format("//form[@id='activeCartViewForm']//div[@data-itemid='%s']//span[contains(@class, 'sc-product-price')]", itemId);
        }
    }

    private Locators locators() {

        if (this.locators == null) {
            this.locators = new Locators();
        }
        return this.locators;
    }

    private Locators locators(String itemId) {

        this.locators = locators();
        this.locators.setProductPrice(itemId);
        return this.locators;
    }

    private String getProductPrice(String itemId) {

        return controller().getText(locators(itemId).productPrice);
    }

    public String getProductPrice(String itemId, boolean normalize) {

        return normalize ? PricesUtils.normalizedPrice(getProductPrice(itemId)) : getProductPrice(itemId);
    }
}
