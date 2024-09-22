package driver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class Web {
    public static WebDriver driver = new ChromeDriver();
    private static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    private static Logger log = Logger.getLogger(Web.class.getName());

    public static void getPage() {
        log.info("Открываем страницу");
        System.setProperty("webdriver.chrome.driver",
                "C:\\Program Files (x86)\\Google\\Driver\\114.0.5735.90\\chromedriver.exe");
        driver.get("https://www.rmteam.ru/routing/public");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Русская морская команда']")));
        log.info("Страница загрузилась");
    }

    public static void closePage() {
        driver.close();
    }

    public static WebElement getWebElement(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public static WebElement waitUntil(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement;
    }

    public static void waitClickable(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static void waitNotClickable(WebElement webElement) {
        wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(webElement)));
    }

    public static boolean scrollToElements(WebElement webElement) {
        Boolean b = (Boolean) ((JavascriptExecutor) driver).executeScript(
                "var elem = arguments[0],                 " +
                        "  box = elem.getBoundingClientRect(),    " +
                        "  cx = box.left + box.width / 2,         " +
                        "  cy = box.top + box.height - 0.001,     " +// ???
                        "  e = document.elementFromPoint(cx, cy); " +
                        "for (; e; e = e.parentElement) {         " +
                        "  if (e === elem)                        " +
                        "    return true;                         " +
                        "}                                        " +
                        "return false;                            "
                , webElement);
        return b;
    }
}
