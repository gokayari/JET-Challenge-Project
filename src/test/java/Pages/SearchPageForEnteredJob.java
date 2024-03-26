package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class SearchPageForEnteredJob extends BasePage {

    By jobListItem = By.cssSelector(".jobs-list-item");
    By jobLocation = By.cssSelector(".job-location");
    By countryAccordion = By.id("CountryAccordion");
    By countries = By.cssSelector("#CountryBody ul [role=\"presentation\"] .result-text");
    By loaderEnding = By.xpath("//div[@class='show-loader au-target aurelia-hide']");
    By jobLocations = By.className("job-location");
    By arrowButton = By.xpath("//span[@class='icon icon-arrow-right']");

    public SearchPageForEnteredJob(WebDriver driver) {
        super(driver);
    }

    public void verifyMultiplyLocations() {
        wait.until(ExpectedConditions.presenceOfElementLocated(jobListItem));
        List<WebElement> JobItems = driver.findElements(jobListItem);
        Set<String> locations = new HashSet<>();
        for (WebElement jobItem : JobItems) {
            WebElement JobLocation = jobItem.findElement(jobLocation);
            String locationText = JobLocation.getText().trim();
            locations.add(locationText);
        }
        int uniqueLocations = locations.size();
        Assert.assertTrue(uniqueLocations > 1, "The search result doesn't contain multiple locations");
    }

    public void countrySelectMethod(String selectedCountry) {
        driver.findElement(countryAccordion).click();

        List<WebElement> Countries = driver.findElements(countries);
        for (WebElement country : Countries) {
            if (country.getText().contains(selectedCountry)){
                country.click();
                break;
            }
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(loaderEnding));
    }

    public void verifyExpectedOneCountry(String wishedCountry) {
        while (true) {
            List<WebElement> Jobs = driver.findElements(jobListItem);
            for (WebElement job : Jobs) {
                WebElement JobLocation = job.findElement(jobLocations);
                String locationText = JobLocation.getText();
                Assert.assertTrue(locationText.contains(wishedCountry), "Location is not "+ wishedCountry +" for job");
            }
            WebElement nextPageButton = driver.findElement(arrowButton);
            if (nextPageButton.isDisplayed()) {
                nextPageButton.click();
            }else{
                break;
            }
        }

    }
}
