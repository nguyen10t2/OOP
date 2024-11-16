package getTwitter_signAccount;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class SearchingTopic {
    private WebDriver driver;
    private String topic = "";

    public SearchingTopic(WebDriver driver) {
        this.driver = driver;
        this.readTopic();
    }

    public void readTopic() {
        try(Scanner scanner = new Scanner(new File(getResourcePath()))) {
            topic = scanner.nextLine();
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getTopic() {
        return topic;
    }

    private String getResourcePath() {
        ClassLoader classLoader = getClass().getClassLoader();
        return classLoader.getResource("Topic.txt").getFile();
    }

    public void searchTopic() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search']")));
        searchField.sendKeys(getTopic() + Keys.ENTER);
        WebElement peopleTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='People']")));
        peopleTab.click();
    }
}
