package selections;

import driver.Web;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.logging.Logger;

import static driver.Web.scrollToElements;
import static driver.Web.waitUntil;

public class Products {
    private static Logger log = Logger.getLogger(Products.class.getName());
    private static WebElement portalCard = Web.driver
            .findElement(By.xpath("//div[@class='product-card' and .//h2[contains(text(), 'ГИС-портал ОКЕАН')]]"));
    private static WebElement portalVideo = portalCard
            .findElement(By.xpath("//div[@class='video-container']//video[.//source[contains(@src, '/api/rest/files/ГИС_ПОРТАЛ_ОКЕАН.mp4')]]"));
    private static WebElement portalOverview = portalCard
            .findElement(By.xpath("//div[@class='overview']"));
    private static WebElement smartlinkCard = Web.driver
            .findElement(By.xpath("//div[@class='product-card' and .//h2[contains(text(), 'Smartlink')]]"));
    private static WebElement smartlinkVideo = smartlinkCard
            .findElement(By.xpath("//div[@class='video-container']//video[.//source[contains(@src, '/api/rest/files/Smartlink.mp4')]]"));
    private static WebElement smartlinkOverview = smartlinkCard
            .findElement(By.xpath("//div[@class='overview']"));

    private static WebElement getproductFeatures(WebElement card, String name) {
        return card.findElement(By.xpath(String.format("//p[contains(text(), '%s')]", name)));
    }

    public static void checkVisibleElements() {
        List<String> portalProductFeatures = List.of("Визуализация", "Создание карт",
                "Системность", "Регистрация в Роспатенте");
        List<String> smartlinkProductFeatures = List.of("Конструктор визитки", "Обмен информацией",
                "Регистрация в Роспатенте", "Технологичность");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(scrollToElements(portalCard), "В окне не отображается карта ГИС-портал ОКЕАН");
        softAssert.assertTrue(scrollToElements(portalVideo), "В окне не отображается видео ГИС-портал ОКЕАН");
        softAssert.assertTrue(scrollToElements(portalOverview), "В окне не отображается обзор ГИС-портал ОКЕАН");
        for(String portalProductFeature: portalProductFeatures) {
            softAssert.assertTrue(scrollToElements(getproductFeatures(portalCard, portalProductFeature)),
                    String.format("В окне не отображается характеристика \"%s\"", portalProductFeature));
        }
        if(scrollToElements(smartlinkCard)) {
            softAssert.assertTrue(scrollToElements(smartlinkVideo), "В окне не отображается видео Smartlink");
            softAssert.assertTrue(scrollToElements(smartlinkOverview), "В окне не отображается обзор Smartlink");
            for(String smartlinkProductFeature: smartlinkProductFeatures) {
                softAssert.assertTrue(scrollToElements(getproductFeatures(smartlinkCard, smartlinkProductFeature)),
                        String.format("В окне не отображается характеристика \"%s\"", smartlinkProductFeature));
            }
        }
        softAssert.assertAll();
    }

    public static void clickOnProductFeature() {
        log.info("Нажимаем на кнопку \"Визуализация\"");
        getproductFeatures(portalCard, "Визуализация").click();
    }

    public static void checkVisibleElementsOnPopup() {
        WebElement popup = waitUntil(Web.driver.findElement(By.xpath("//product-feature-dialog")));
        waitUntil(popup.findElement(By.xpath("//span[contains(text(), 'Визуализация')]")));
        waitUntil(popup.findElement(By.xpath("//img[contains(@src, '/api/rest/files/images/logo')]")));
        waitUntil(popup.findElement(By.xpath("//p[@class='description']")));
        log.info("Все элементы попапа \"Визуализация\" отобразились");
    }
}
