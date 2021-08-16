package pages;

import com.persado.oss.quality.stevia.selenium.core.WebComponent;
import java.util.List;
import org.openqa.selenium.WebElement;
import utils.ProductSelectionUtils;

public class AmazonGiftCardsPage extends WebComponent {

    private enum Locators {
        FILTERS_PRINT_AT_HOME("//div[@id='s-refinements']//span[normalize-space(text())='Print at Home']"),
        FILTERS_STANDARD_DESIGNS("//div[@id='s-refinements']//span[normalize-space(text())='Standard Designs']"),
        RESULTS_LIST("//*[@id='search']//div[@data-component-type='s-search-result']");

        private final String locator;

        Locators(String locator) {

            this.locator = locator;
        }

        private String get() {

            return this.locator;
        }
    }

    public enum CardFilter {
        PRINT_AT_HOME, STANDARD_DESIGNS
    }

    private WebElement selectProduct(int number, boolean strict) {

        List<WebElement> products = controller().findElements(Locators.RESULTS_LIST.get());
        return strict ? products.get(number - 1) : ProductSelectionUtils.selectNthOrLastProduct(products, number);
    }

    private void filterResult(Locators locators) {

        controller().click(locators.get());
    }

    /**
     * Clicks on the n-th card (strict = true) or if not found clicks the last card found (strict = false)
     *
     * @param number The number of card to click (starting from 1)
     * @param strict Selection mode strict (select exactly the n-th card) or not (flexible selection)
     */
    public void clickOnCard(int number, boolean strict) {

        WebElement product = selectProduct(number, strict);
        product.click();
    }

    public void filterResults(CardFilter cardFilter) {

        switch (cardFilter) {
            case PRINT_AT_HOME:
                filterResult(Locators.FILTERS_PRINT_AT_HOME);
                break;
            case STANDARD_DESIGNS:
                filterResult(Locators.FILTERS_STANDARD_DESIGNS);
                break;
            default:
                break;
        }
    }
}
