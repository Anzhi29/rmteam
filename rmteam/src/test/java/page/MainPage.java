package page;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.logging.Logger;

import static driver.Web.*;

public class MainPage {
    private static Logger log = Logger.getLogger(MainPage.class.getName());

    public static void clickOnButton(String name){
        log.info(String.format("Нажимаем на кнопку %s", name));
        WebElement button = getWebElement(String.format(".//span[text()='%s']", name));
        waitUntil(button);
        button.click();
    }

    public static void checkScrollToSection(String section) {
        try {
            Thread.sleep(2000);
            Assert.assertTrue(scrollToElements(getWebElement(String.format(".//h2[text()='%s']", section))),
                    String.format("Скролл к разделу \"%s\" не произошел", section));
            log.info(String.format("Произошел скролл к разделу \"%s\"", section));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
