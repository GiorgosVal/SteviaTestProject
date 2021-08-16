package pages;

import com.persado.oss.quality.stevia.selenium.core.WebComponent;

public class Navigation extends WebComponent {

    enum Locators {
        NAV_BAR_GIFT_CARDS("//div[@id='nav-xshop']/a[@data-csa-c-slot-id='nav_cs_2']"),
        TOASTER_DISMISS_BUTTON("//div[@id='glow-toaster-body']//following-sibling::div//span/input[@data-action-type='DISMISS']"),
        NAV_CART("nav-cart");

        private final String locator;

        Locators(String locator) {

            this.locator = locator;
        }

        private String get() {

            return locator;
        }
    }

    public void clickGiftCards() {

        dismissToasterIfPresent();
        controller().click(Locators.NAV_BAR_GIFT_CARDS.get());
    }

    void goToCart() {

        controller().click(Locators.NAV_CART.get());
    }

    private void dismissToasterIfPresent() {

        if (isToasterPresent()) {
            controller().click(Locators.TOASTER_DISMISS_BUTTON.get());
        }
    }

    private boolean isToasterPresent() {

        return controller().isComponentPresent(Locators.TOASTER_DISMISS_BUTTON.get());
    }
}
