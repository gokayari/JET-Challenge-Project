import Pages.BasePage;
import Pages.CareersHomePage;
import Pages.SearchPageForSelectedCategory;
import Pages.SearchPageForEnteredJob;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;


public class SearchTest {

    WebDriver driver;
    BasePage basePage;
    CareersHomePage careersHomePage;
    SearchPageForEnteredJob searchPageForEnteredJob;
    SearchPageForSelectedCategory searchPageForSelectedCategory;

    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(ConfigurationReader.getProperty("baseUrl"));
        basePage = new BasePage(driver);
        careersHomePage = new CareersHomePage(driver);
    }

    @Test
    public void TestCase1(){
        searchPageForEnteredJob = new SearchPageForEnteredJob(driver);

        //Search for the Job Title “Test”:
        careersHomePage.inputJobTitle(ConfigurationReader.getProperty("jobTitle"));
        careersHomePage.searchButtonClick();

        //Verify that the search contains results from multiple locations:
        searchPageForEnteredJob.verifyMultiplyLocations();

        //Then Refine your search from the left panel to the Country “Netherlands”:
        searchPageForEnteredJob.countrySelectMethod(ConfigurationReader.getProperty("country1"));

        //Verify that now the search results’ location is the Netherlands only:
        searchPageForEnteredJob.verifyExpectedOneCountry(ConfigurationReader.getProperty("country1"));
    }


    @Test
    public void TestCase2(){
        searchPageForSelectedCategory = new SearchPageForSelectedCategory(driver);

        //Click on “Search for Job Title” and select “Sales” among Job Categories:
        careersHomePage.jobTitleSearchBarClick();
        careersHomePage.wishedJobCategoryClick(ConfigurationReader.getProperty("jobCategory"));

        //Scroll to “Refine your search”:
        searchPageForSelectedCategory.scrollToRefineYourSearch();

        //Verify Category “Sales” is selected and the search results number is matching:
        searchPageForSelectedCategory.checkboxIsCheckedAndNumberIsMatching(ConfigurationReader.getProperty("jobCategory"));

        //Then Refine your search from the left panel to the Country “Germany”
        searchPageForSelectedCategory.countrySelectMethod(ConfigurationReader.getProperty("country2"));

        // Verify the number of the search results is matching and category is “Sales” on all results:
        searchPageForSelectedCategory.countrySearchNumberIsMatching(ConfigurationReader.getProperty("country2"));
        searchPageForSelectedCategory.verifyAllCategoryResultsSame(ConfigurationReader.getProperty("jobCategory"));

    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }

    @AfterClass
    public void tearDownClass(){
        driver.quit();
    }


}
