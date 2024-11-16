package getTwitter_signAccount;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import goXAndRun.GetInfoUser;
import goXAndRun.GetUserBlockchain;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CustomWebDriver {
    private WebDriver driver;

    public CustomWebDriver() {
        WebDriverManager.edgedriver().setup();
        this.driver = new EdgeDriver();
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void closeDriver() {
        this.driver.quit();
    }
    public void runWebDriver() {
        SignTwitter signTwitter = new SignTwitter(this.driver);
        signTwitter.signAccountTwitter();
        searchingTopic();
        GetUserBlockchain getUserBlockchain = getUserBlock();
        List<String> userBlockchain = getUserBlockchain.getUser();
        GetInfoUser getInfoUser = new GetInfoUser(this.driver, userBlockchain);
        // truyền thông điệp cho getInfoUser làm tiếp 
    }

    private void searchingTopic() {
        SearchingTopic topic = new SearchingTopic(this.driver);
        topic.searchTopic();
    }

    private GetUserBlockchain getUserBlock() {
        GetUserBlockchain getUserBlockchain = new GetUserBlockchain(this.driver);
        getUserBlockchain.runGetUser();
        return getUserBlockchain;
    }
}
