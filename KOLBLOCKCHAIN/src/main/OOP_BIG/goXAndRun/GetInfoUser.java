package goXAndRun;

import java.util.List;

import org.openqa.selenium.WebDriver;

import NodeOfGraph.Node;
/*
 * Lớp này dùng để lấy thông tin của từng user
 * Lưu dạng List các Node
 * Node như nào thì vào NodeOfGraph/Node.java và code
 */
public class GetInfoUser {
    private WebDriver driver;
    private List<String> userBlockchain;

    public GetInfoUser(WebDriver driver, List<String> userBlockchain) {
        this.driver = driver;
        this.userBlockchain = userBlockchain;
    }

    private Node getInfoUser(String url) {
        //truy cập vào url của từng user và lấy thông tin
        this.driver.get(url);
        return null;
    }


}
