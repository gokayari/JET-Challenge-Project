import Pages.BasePage;
import Pages.CareersHomePage;
import Pages.SearchPageForSelectedCategory;
import Pages.SearchPageForEnteredJob;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;


public class SearchTest {

    WebDriver driver;
    BasePage basePage;
    CareersHomePage careersHomePage;
    SearchPageForEnteredJob searchPageForEnteredJob;
    SearchPageForSelectedCategory searchPageForSelectedCategory;

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://careers.justeattakeaway.com/global/en/home");
        basePage = new BasePage(driver);
        careersHomePage = new CareersHomePage(driver);
    }

    @Test
    public void TestCase1(){
        searchPageForEnteredJob = new SearchPageForEnteredJob(driver);

        //Search for the Job Title “Test”:
        careersHomePage.inputJobTitle("Test");
        careersHomePage.searchButtonClick();

        //Verify that the search contains results from multiple locations:
        searchPageForEnteredJob.verifyMultiplyLocations();

        //Then Refine your search from the left panel to the Country “Netherlands”:
        searchPageForEnteredJob.countrySelectMethod("Netherlands");

        //Verify that now the search results’ location is the Netherlands only:
        searchPageForEnteredJob.verifyExpectedOneCountry("Netherlands");
    }


    @Test
    public void TestCase2(){
        searchPageForSelectedCategory = new SearchPageForSelectedCategory(driver);

        //Click on “Search for Job Title” and select “Sales” among Job Categories:
        careersHomePage.jobTitleSearchBarClick();
        careersHomePage.wishedJobCategoryClick("Sales");

        //Scroll to “Refine your search”:
        searchPageForSelectedCategory.scrollToRefineYourSearch();

        //Verify Category “Sales” is selected and the search results number is matching:
        searchPageForSelectedCategory.checkboxIsCheckedAndNumberIsMatching("Sales");

        //Then Refine your search from the left panel to the Country “Germany”
        searchPageForSelectedCategory.countrySelectMethod("Germany");

        // Verify the number of the search results is matching and category is “Sales” on all results:
        searchPageForSelectedCategory.countrySearchNumberIsMatching("Germany");
        searchPageForSelectedCategory.verifyAllCategoryResultsSame("Sales");

    }

    @AfterEach
    public void tearDown(){
        driver.close();
    }

    @AfterClass
    public void tearDownClass(){
        driver.quit();
    }


}
