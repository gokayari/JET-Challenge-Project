package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class CareersHomePage extends BasePage {

    By jobTitleSearchBar = By.id("keywordSearch");
    By searchButton = By.id("ph-search-backdrop");
    By firstJobCategory = By.xpath("//div[@class='phs-search-categories']/ul[1]/li[@class='au-target job-container-category_0']");
    By jobCategories = By.cssSelector(".phs-search-suggestions li span");

    public CareersHomePage(WebDriver driver) {
            super(driver);
    }

    public void inputJobTitle (String jobTitle){
        wait.until(ExpectedConditions.presenceOfElementLocated(jobTitleSearchBar));
        driver.findElement(jobTitleSearchBar).sendKeys(jobTitle);
    }

    public void searchButtonClick (){
        driver.findElement(searchButton).click();
    }

    public void jobTitleSearchBarClick (){
        wait.until(ExpectedConditions.presenceOfElementLocated(jobTitleSearchBar));
        driver.findElement(jobTitleSearchBar).click();
    }

    public void wishedJobCategoryClick(String wishedJobCategory){
        wait.until(ExpectedConditions.presenceOfElementLocated(firstJobCategory));

        List<WebElement> JobCategories = driver.findElements(jobCategories);
        for (WebElement jobCategory : JobCategories) {
            if (jobCategory.getText().contains(wishedJobCategory)) {
                jobCategory.click();
                break;
            }
        }

    }








}
