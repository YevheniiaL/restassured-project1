package com.cydeo.tests.practice;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoogleSearch {

    @DisplayName("Test google search(ui) with csv file")
    @ParameterizedTest
    @CsvFileSource(resources = "/google_search.csv", numLinesToSkip = 1)
    public void testGoogleSearch(String article, String expectedTitle) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys(article);
        Thread.sleep(3000);
        driver.findElement(By.name("btnK")).click();
        Thread.sleep(3000);


        String actualTitle = driver.getTitle();
        System.out.println("expectedTitle = " + expectedTitle);
        System.out.println("actualTitle = " + actualTitle);

        assertEquals(expectedTitle, actualTitle);

        driver.quit();


    }


}
