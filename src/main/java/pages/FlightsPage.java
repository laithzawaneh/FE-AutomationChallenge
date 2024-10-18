package pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FlightsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//span[@class='ts-rmsg__hdrcnt']")
    private WebElement verifySitCountry;

    @FindBy(xpath = "//span[@class='summary-curr-only']")
    private WebElement lowestPriceText;

    @FindBy(xpath = "//div[@class='ts-fbr-flight-list outbound-list']//a[@class='ts-fbr-flight-sort-filter__sortby__item-core' and ./@data-value='price']")
    private WebElement priceFromToFilter;


    @FindBy(xpath = "//div[@class='ts-fbr-flight-list outbound-list']//td[@class='ts-fbr-brand-table__cell ts-fbr-brand-table__cell--price ts-fbr-brand-table__cell--status-default ts-fbr-brand-table__cell--status-checked'][1]//strong")
    private WebElement priceFromToTextSelected;

    @FindBy(xpath = "//div[@class='ts-fbr-flight-list inbound-list']//td[@class='ts-fbr-brand-table__cell ts-fbr-brand-table__cell--price ts-fbr-brand-table__cell--status-default ts-fbr-brand-table__cell--status-checked'][1]//strong")
    private WebElement priceToFromTextSelected;

    @FindBy(xpath = "//div[@class='ts-fbr-flight-list inbound-list']//a[@class='ts-fbr-flight-sort-filter__sortby__item-core' and ./@data-value='price']")
    private WebElement priceToFromFilter;

    @FindBy(xpath = "//div[@class='ts-fbr-option ts-fbr-option--2 ts-fbr-option--business fbclass-j upgbusiness' and ./@data-target='option-0-1-0']")
    private WebElement lowestPriceFlightFromTo;

    @FindBy(xpath = "//div[@class='ts-fbr-option ts-fbr-option--2 ts-fbr-option--business fbclass-j upgbusiness' and ./@data-target='option-1-1-0']")
    private WebElement lowestPriceFlightToFrom;

    @FindBy(xpath = "//td[@class='ts-fbr-brand-table__cell ts-fbr-brand-table__cell--select ts-fbr-brand-table__cell--status-default ts-fbr-brand-table__cell--status-checked'][1]//a[@class='ts-fbr-brand-table__select-btn tc-fbr-cursor-style']")
    private WebElement selectLowestPriceServices;

    @FindBy(xpath = "//div[@class='ts-pa__right' and ./a[contains(text(),'Continue to Passengers')]]")
    private WebElement continueToPassengersBtn;


    public FlightsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public String getSiteCountryText() {
        wait.until(ExpectedConditions.visibilityOf(verifySitCountry));
//        System.out.println(verifySitCountry.getText());
        return verifySitCountry.getText();
    }

    public int getTotalLowestPriceIntegerValue() {
        wait.until(ExpectedConditions.visibilityOf(lowestPriceText));
//        System.out.println(lowestPriceText.getText());
        return Integer.parseInt(lowestPriceText.getText().replaceAll("[^\\d]", ""));
    }

    public void clickOnPriceFilterFromToBtn() {
        wait.until(ExpectedConditions.visibilityOf(priceFromToFilter));
        priceFromToFilter.click();
    }

    public void clickOnPriceFilterToFromBtn() {
        wait.until(ExpectedConditions.visibilityOf(priceToFromFilter));
        priceToFromFilter.click();
    }

    public void clickOnLowestPriceFromToBtn() {
        retryingFindClick(lowestPriceFlightFromTo);
    }

    private void retryingFindClick(WebElement element) {
        int attempts = 0;
        while (attempts < 3) {
            try {
//                wait.until(ExpectedConditions.visibilityOf(element));
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                break;
            } catch (StaleElementReferenceException e) {
                attempts++;
                if (attempts == 3) {
                    throw e;
                }
            }
        }
    }

    public void clickOnLowestPriceToFromBtn() {
        retryingFindClick(lowestPriceFlightToFrom);
    }

    public void clickOnSelectLowestPriceServicesBtn() {
        wait.until(ExpectedConditions.visibilityOf(selectLowestPriceServices));
        selectLowestPriceServices.click();
    }

    public int getPriceFromToSelectedIntegerValue() {
        wait.until(ExpectedConditions.visibilityOf(priceFromToTextSelected));
        return Integer.parseInt(priceFromToTextSelected.getText().replaceAll("[^\\d]", ""));
    }

    public int getPriceToFromSelectedIntegerValue() {
        wait.until(ExpectedConditions.visibilityOf(priceToFromTextSelected));
        return Integer.parseInt(priceToFromTextSelected.getText().replaceAll("[^\\d]", ""));
    }

    public void clickOnContinueToPassengersBtn() {
        wait.until(ExpectedConditions.visibilityOf(continueToPassengersBtn));
        continueToPassengersBtn.click();
    }
}
