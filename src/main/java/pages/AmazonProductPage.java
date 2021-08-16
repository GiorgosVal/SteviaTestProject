package pages;

import com.persado.oss.quality.stevia.selenium.core.WebComponent;
import utils.PricesUtils;

public class AmazonProductPage extends WebComponent {

    private enum Locators {

        AMOUNT_SELECTED("//ul[@id='gc-amount-mini-picker']//span[contains(@class, 'a-button-selected')]//button"),
        ADD_TO_CART("gc-buy-box-atc");

        private final String locator;

        Locators(String locator) {

            this.locator = locator;
        }

        private String get() {

            return this.locator;
        }
    }

    private String selectedAmount() {

        return controller().getText(Locators.AMOUNT_SELECTED.get());
    }

    public String selectedAmount(boolean normalize) {

        return normalize ? PricesUtils.normalizedPrice(selectedAmount()) : selectedAmount();
    }

    public void addToCart() {

        controller().click(Locators.ADD_TO_CART.get());
    }
}
