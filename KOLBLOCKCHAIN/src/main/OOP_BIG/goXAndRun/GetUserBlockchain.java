package goXAndRun;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.io.IOException;

public class GetUserBlockchain {

    private static final int MAX_USER = 50;
    private static final int SCROLL_DELAY_MS = 1000;
    private WebDriver driver;
    private Set<String> userSet;

    public GetUserBlockchain(WebDriver driver) {
        this.driver = driver;
        this.userSet = new HashSet<>();
    }

    public void runGetUser() {
        JavascriptExecutor js = (JavascriptExecutor) this.driver;

        try {
            while (userSet.size() < MAX_USER) {
                List<WebElement> userElements = driver.findElements(By.xpath("//div[@data-testid='cellInnerDiv']"));

                processUserElements(userElements);

                if (userSet.size() >= MAX_USER)
                    break;

                scrollAndWait(js);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processUserElements(List<WebElement> userElements) throws IOException {
        for (WebElement userElement : userElements) {

            WebElement userLink = userElement.findElement(By.xpath(".//a"));

            String userUrl = userLink.getAttribute("href");

            userSet.add(userUrl);
        }

    }

    private void scrollAndWait(JavascriptExecutor js) {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        try {
            Thread.sleep(SCROLL_DELAY_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public List<String> getUser() {
        return new ArrayList<String>(userSet);
    }
}
