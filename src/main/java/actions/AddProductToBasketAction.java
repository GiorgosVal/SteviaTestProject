package actions;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import pages.AmazonGiftCardsPage;
import pages.AmazonGiftCardsPage.CardFilter;
import pages.Navigation;

public class AddProductToBasketAction {

    @Autowired
    private Navigation navigation;

    @Autowired
    private AmazonGiftCardsPage amazonGiftCardsPage;

    public void addGiftCardToBasket(List<CardFilter> cardFilters, int numberOfProduct, boolean strict) {

        navigation.clickGiftCards();
        cardFilters.forEach(cardFilter -> amazonGiftCardsPage.filterResults(cardFilter));
        amazonGiftCardsPage.clickOnCard(numberOfProduct, strict);
    }
}
