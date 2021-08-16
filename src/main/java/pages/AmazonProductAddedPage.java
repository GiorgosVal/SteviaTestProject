package pages;

import com.persado.oss.quality.stevia.selenium.core.WebComponent;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;

public class AmazonProductAddedPage extends WebComponent {

    @Autowired
    private Navigation navigation;

    private enum Locators {
        VIEW_CART("//span[@id='hlb-view-cart']"),
        ITEM_ID("//div[contains(@id,'huc-v2-order-row-item-')]");

        final String locator;

        Locators(String locator) {

            this.locator = locator;
        }

        private String get() {

            return this.locator;
        }
    }

    /**
     * Will randomly navigate to the Cart page by choosing to click either the Navigation button, or the button of AmazonProductAddedPage.
     */
    public void goToCard() {

        int zeroToOne = new Random().nextInt(2);
        if (zeroToOne == 0) {
            navigation.goToCart();
        } else {
            controller().click(Locators.VIEW_CART.get());
        }
    }

    public String getItemId() {

        return controller().getAttributeValue(Locators.ITEM_ID.get(), "data-itemid");
    }
}
