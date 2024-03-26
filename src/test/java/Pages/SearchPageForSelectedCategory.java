package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SearchPageForSelectedCategory extends BasePage{

    By refineYourSearchText = By.xpath("//h2[@class='panel-title']");
    By categoryAccordion = By.id("CategoryAccordion");
    By jobCategories = By.cssSelector("#CategoryBody li");
    By categoryCheckbox = By.cssSelector("#CategoryBody li [role=\"checkbox\"]");
    By selectedCategoryAmount = By.cssSelector("#CategoryBody li .result-jobs-count");
    By searchResultsAmount = By.cssSelector(".result-count");
    By countryAccordion = By.id("CountryAccordion");
    By countryItems = By.cssSelector("#CountryBody li .result-text");
    By countries = By.cssSelector("#CountryBody li");
    By loaderEnding = By.xpath("//div[@class='show-loader au-target aurelia-hide']");
    By selectedCountryAmount = By.cssSelector("#CountryBody li .result-jobs-count");
    By jobListItem = By.cssSelector(".jobs-list-item");
    By jobCategory = By.xpath("//span[@class='au-target category']");
    By arrowButton = By.xpath("//span[@class='icon icon-arrow-right']");

    public SearchPageForSelectedCategory(WebDriver driver) {
        super(driver);
    }

    public void scrollToRefineYourSearch(){
        wait.until(ExpectedConditions.presenceOfElementLocated(refineYourSearchText));
        WebElement RefineYourSearch = driver.findElement(refineYourSearchText);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView()", RefineYourSearch);
    }


    public void checkboxIsCheckedAndNumberIsMatching(String wishedJobCategory){
        wait.until(ExpectedConditions.presenceOfElementLocated(categoryAccordion));

        WebElement SelectedCategoryAmount = null;

        List<WebElement> JobCategories = driver.findElements(jobCategories);
        for (WebElement jobCategory : JobCategories) {
            if (jobCategory.getText().contains(wishedJobCategory)){
                boolean checkBoxIsSelected = jobCategory.findElement(categoryCheckbox).isSelected();
                SelectedCategoryAmount = jobCategory.findElement(selectedCategoryAmount);
                Assert.assertTrue(checkBoxIsSelected, "Checkbox is not selected!");
                break;
            }
        }

        WebElement SearchResultsAmount = driver.findElement(searchResultsAmount);

        assert SelectedCategoryAmount != null;
        String selectedCategoryAmountText = SelectedCategoryAmount.getText();
        String searchResultsAmountText = SearchResultsAmount.getText();

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(selectedCategoryAmountText);
        int selectedCategoryAmount = 0;
        if (matcher.find()) {
            String numberString = matcher.group();
            selectedCategoryAmount = Integer.parseInt(numberString);
        }
        int searchResultsAmount = Integer.parseInt(searchResultsAmountText);

        Assert.assertEquals(selectedCategoryAmount, searchResultsAmount);
    }


    public void countrySelectMethod(String wishedCountry) {
        driver.findElement(countryAccordion).click();

        List<WebElement> CountryItems = driver.findElements(countryItems);
        for (WebElement countryItem : CountryItems) {
            if (countryItem.getText().contains(wishedCountry)){
                countryItem.click();
                break;
            }
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(loaderEnding));
    }


    public void countrySearchNumberIsMatching(String wishedCountry){

        WebElement SelectedCountryAmount = null;

        List<WebElement> Countries = driver.findElements(countries);
        for (WebElement country : Countries) {
            if (country.getText().contains(wishedCountry)){
                SelectedCountryAmount = country.findElement(selectedCountryAmount);
                break;
            }
        }

        WebElement SearchResultsAmount = driver.findElement(searchResultsAmount);

        assert SelectedCountryAmount != null;
        String selectedCountryAmountText = SelectedCountryAmount.getText();
        String searchResultsAmountText = SearchResultsAmount.getText();

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(selectedCountryAmountText);

        int selectedCountryAmount = 0;
        if (matcher.find()) {
            String numberString = matcher.group();
            selectedCountryAmount = Integer.parseInt(numberString);
        }
        int searchResultsAmount = Integer.parseInt(searchResultsAmountText);

        Assert.assertEquals(selectedCountryAmount, searchResultsAmount);
    }


    public void verifyAllCategoryResultsSame(String wishedCategory) {
        while (true) {
            List<WebElement> Jobs = driver.findElements(jobListItem);
            for (WebElement job : Jobs) {
                WebElement JobCategory = job.findElement(jobCategory);
                String categoryText = JobCategory.getText();
                Assert.assertTrue(categoryText.contains(wishedCategory), "Category is not"+ wishedCategory +" for job");
            }
            WebElement nextPageButton = driver.findElement(arrowButton);
            if (nextPageButton.isDisplayed()) {
                nextPageButton.click();
            } else {
                break;
            }
        }
    }

}

