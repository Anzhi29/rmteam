import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import static driver.Web.closePage;
import static driver.Web.getPage;

import static page.MainPage.*;
import static selections.Contacts.*;
import static selections.Products.*;

public class Tests {

    @Test
    public void checkProductsButton() {
        String section = "Продукты";
        getPage();
        clickOnButton(section);
        checkScrollToSection(section);
        checkVisibleElements();
    }

    @Test
    public void viewProductInformation() {
        String section = "Продукты";
        getPage();
        clickOnButton(section);
        checkScrollToSection(section);
        clickOnProductFeature();
        checkVisibleElementsOnPopup();
    }

    @Test
    public void checkFeedbackForm() {
        String section = "Контакты";
        getPage();
        clickOnButton(section);
        checkScrollToSection(section);
        clickOnFeedback();
        checkPopupOpened();
        fillFields();
        checkButtonActivity();
    }

    @AfterTest
    public void close() {
        closePage();
    }
}
