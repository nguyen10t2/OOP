package getTwitter_signAccount;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.NoSuchElementException;

public class SignTwitter {
    private Account account = new Account();
    private WebDriver driver;
    private HashMap<String, String> map = new HashMap<String, String>();
    private WebDriverWait wait;

    public SignTwitter(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(30));
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
        try {
            driver.get("https://x.com/i/flow/login");
            wait.until(driver -> ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("return document.readyState").equals("complete"));


            WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//input[@name='text']")));
            wait.until(ExpectedConditions.elementToBeClickable(usernameField));
            usernameField.clear();
            usernameField.sendKeys(getElements("text"));
            usernameField.sendKeys(Keys.ENTER);

            WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//input[@name='password']")));
            wait.until(ExpectedConditions.elementToBeClickable(passwordField));
            passwordField.clear();
            passwordField.sendKeys(getElements("password"));
            passwordField.sendKeys(Keys.ENTER);

            try {
                WebElement extraUsernameField = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//input[@name='text']")));
                if (extraUsernameField.isDisplayed()) {
                    wait.until(ExpectedConditions.elementToBeClickable(extraUsernameField));
                    extraUsernameField.clear();
                    extraUsernameField.sendKeys(getElements("text"));
                    extraUsernameField.sendKeys(Keys.ENTER);
                }
            } catch (TimeoutException | NoSuchElementException e) {
                System.out.println("Additional username verification not required");
            }

        } catch (TimeoutException e) {
            System.err.println("Timeout waiting for element: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Error during login process: " + e.getMessage());
            throw e;
        }
    }
}
