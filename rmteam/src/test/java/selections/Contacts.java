package selections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

import static driver.Web.*;

public class Contacts {
    private static Logger log = Logger.getLogger(Contacts.class.getName());
    private static WebElement fio;
    private static WebElement email;
    private static WebElement phone;
    private static WebElement code;
    private static WebElement buttonGetCode;
    private static WebElement acceptanceProcessing;
    private static WebElement acceptanceConfidential;
    private static WebElement acceptanceUserAgreement;
    private static WebElement buttonFeedback;

    public static void clickOnFeedback() {
        log.info("Нажимаем на кнопку \"Визуализация\"");
        WebElement button = waitUntil(getWebElement(".//span[contains(text(), 'Связаться с нами')]"));
        button.click();
    }

    public static void checkPopupOpened() {
        log.info("Проверяем наличие элементов в попапе \"Связаться с нами\"");
        WebElement popup = waitUntil(getWebElement(".//mat-dialog-container"));
        fio = waitUntil(popup.findElement(By.xpath(".//input[@formcontrolname='fio']")));
        email = waitUntil(popup.findElement(By.xpath(".//input[@formcontrolname='email']")));
        phone = waitUntil(popup.findElement(By.xpath(".//input[@formcontrolname='phone']")));
        code = waitUntil(popup.findElement(By.xpath(".//input[@formcontrolname='code']")));
        buttonGetCode = waitUntil(popup.findElement(By.xpath(".//button[.//span[contains(text(), 'Получить код подтверждения')]]")));
        waitNotClickable(buttonGetCode);
        acceptanceProcessing = waitUntil(popup.findElement(
                By.xpath(".//mat-checkbox[@formcontrolname='agreement1']//div[@class='mdc-checkbox']")));//div[@class='mdc-checkbox__background']
        acceptanceConfidential = waitUntil(popup.findElement(
                By.xpath(".//mat-checkbox[@formcontrolname='agreement2']//div[@class='mdc-checkbox']")));
        acceptanceUserAgreement = waitUntil(popup.findElement(
                By.xpath(".//mat-checkbox[@formcontrolname='agreement3']//div[@class='mdc-checkbox']")));
        buttonFeedback = waitUntil(popup.findElement(By.xpath(".//button[.//span[contains(text(), 'Свяжитесь со мной')]]")));
        waitNotClickable(buttonFeedback);
    }

    public static void fillFields() {
        log.info("Заполняем поля");
        fio.sendKeys("Test");
        email.sendKeys("test@mail.ru");
        phone.sendKeys("79" + ThreadLocalRandom.current().nextLong(100_000_000, 1_000_000_000));
        waitClickable(buttonGetCode);
        code.sendKeys(String.valueOf(ThreadLocalRandom.current().nextLong(1_000, 10_000)));
        acceptanceProcessing.click();
        acceptanceConfidential.click();
        acceptanceUserAgreement.click();
    }

    public static void checkButtonActivity() {
        waitClickable(buttonFeedback);
    }
}
