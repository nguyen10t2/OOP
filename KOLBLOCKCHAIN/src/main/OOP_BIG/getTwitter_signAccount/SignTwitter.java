package getTwitter_signAccount;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignTwitter {
    private Account account = new Account();
    private WebDriver driver;
    private HashMap<String, String> map = new HashMap<String, String>();

    public SignTwitter(WebDriver driver) {
        this.driver = driver;
        this.getAccount();
        map.put("text", account.getUserName());
        map.put("email", account.getUserMail());
        map.put("password", account.getUserPass());
    }

    public void getAccount() {
        account.readAccount();
    }

    private String getElements(String text) {
        return map.get(text);
    }

    public void signAccountTwitter() {
        this.driver.get("https://x.com/i/flow/login");
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement usernameField = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='text']")));
        usernameField.sendKeys(getElements("text") + Keys.ENTER);
        WebElement passwordField = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='password']")));
        passwordField.sendKeys(getElements("password") + Keys.ENTER);
        try {
            WebElement userNameField = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='text']")));
            userNameField.sendKeys(getElements("text") + Keys.ENTER);
        } catch (Exception e) {
            System.out.println("No username field");
        }
    }
}
