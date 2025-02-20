package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setUpTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void createNewProductAndVerifyInList(ChromeDriver driver) {
        driver.get(baseUrl + "/product/create");

        // Verify Title of the Page
        String pageTitle = driver.getTitle();
        assertEquals("Create New Product", pageTitle);

        WebElement nameInput = driver.findElement(By.id("nameInput"));
        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        WebElement submitButton = driver.findElement(By.tagName("button"));

        nameInput.sendKeys("Sample Product");
        quantityInput.sendKeys("100");

        // Submit the form
        submitButton.click();

        // Verify Redirect to Product List Page
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/product/list"), "Expected to be redirected to Product List page");

        // Verify the newly created product is displayed in the product list
        WebElement productTable = driver.findElement(By.tagName("table"));
        String pageText = productTable.getText();

        assertTrue(pageText.contains("Sample Product"), "Product name not found in list");
        assertTrue(pageText.contains("100"), "Product quantity not found in list");
    }
}