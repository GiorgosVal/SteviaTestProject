package productselection;

import static java.util.Arrays.asList;
import static org.testng.Assert.assertEquals;
import static pages.AmazonGiftCardsPage.CardFilter.PRINT_AT_HOME;
import static pages.AmazonGiftCardsPage.CardFilter.STANDARD_DESIGNS;
import static utils.LogUtils.errorMessage;

import actions.AddProductToBasketAction;
import com.persado.oss.quality.stevia.spring.SteviaTestBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import pages.AmazonCartPage;
import pages.AmazonProductAddedPage;
import pages.AmazonProductPage;

public class AddToBasketTest extends SteviaTestBase {

    @Autowired
    private AddProductToBasketAction addProductToBasketAction;

    @Autowired
    private AmazonProductPage amazonProductPage;

    @Autowired
    private AmazonProductAddedPage amazonProductAddedPage;

    @Autowired
    private AmazonCartPage amazonCartPage;

    @Test(description = "A selected product added to cart should have correct price in the Cart")
    public void testCardAddedToBasketPrice() {

        addProductToBasketAction.addGiftCardToBasket(asList(PRINT_AT_HOME, STANDARD_DESIGNS), 3, false);

        String selectedAmount = amazonProductPage.selectedAmount(true);

        amazonProductPage.addToCart();
        String itemId = amazonProductAddedPage.getItemId();

        amazonProductAddedPage.goToCard();
        String productPriceInCart = amazonCartPage.getProductPrice(itemId, true);

        assertEquals(productPriceInCart, selectedAmount,
                errorMessage(amazonCartPage, "The product price in cart is not equal to the product price of " + amazonProductPage.getClass().getSimpleName()));
    }
}
