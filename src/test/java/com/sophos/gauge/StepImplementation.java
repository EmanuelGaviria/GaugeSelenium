package com.sophos.gauge;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;


public class StepImplementation {

    private WebDriver driver;

    @Step("Open the web browser Chrome.")
    public void setBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Step("The word <word> has results in <website>.")
    public void verifySingleWordResults(String word, String website) {
        driver.get(website);
        driver.findElement(By.name("q")).sendKeys(word);
        driver.findElement(By.name("btnK")).submit();
        WebElement element = driver.findElement(By.xpath("//div[@id='search']"));
        assertTrue("Búsqueda sin resultados", element.getSize().getHeight() > 0);
    }

    @Step("Almost all words have results in <website> <wordsTable>")
    public void verifyMultipleWordResults(String website, Table wordsTable) {
        driver.get(website);
        for (TableRow row : wordsTable.getTableRows()) {
            String word = row.getCell("Word");
            String actual = "";
            driver.findElement(By.name("q")).sendKeys(word);
            driver.findElement(By.name("btnK")).submit();
            try {
                WebElement element = driver.findElement(By.xpath("//h1[contains(text(),'Resultados de búsqueda')]"));
                actual = "ok";
                driver.get(website);
            } catch (Exception e) {
                actual = "no";
                driver.get(website);
            }
            assertThat("Unexpected result", actual, equalTo(row.getCell("Result")));
        }
    }

    @Step("Close the web browser")
    public void closeBrowser() {
        driver.quit();
    }
}
