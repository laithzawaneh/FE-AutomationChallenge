package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtils {

  private final WebDriver driver;

  public ElementUtils(WebDriver driver) {
    this.driver = driver;
  }

  // For the element to disappear
  public boolean waitForElementToDisappear(By element, long timeInSeconds) {
    try {
      if (timeInSeconds == 0)
        new WebDriverWait(driver, 10)
            .until(ExpectedConditions.invisibilityOfElementLocated(element));
      else
        new WebDriverWait(driver, timeInSeconds)
            .until(ExpectedConditions.invisibilityOfElementLocated(element));

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  // For the element to appear
  public boolean waitForElementToAppear(By element, long timeInSeconds) {
    try {
      if (timeInSeconds == 0)
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(element));
      else
        new WebDriverWait(driver, timeInSeconds)
            .until(ExpectedConditions.visibilityOfElementLocated(element));
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  // For the element to be clickable
  public boolean waitForElementToBeClickable(By element, long timeInSeconds) {
    try {
      if (timeInSeconds == 0)
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(element));
      else
        new WebDriverWait(driver, timeInSeconds)
            .until(ExpectedConditions.elementToBeClickable(element));

      new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(element));

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  // For the element to be selected
  public boolean waitForElementToBeSelected(By element, long timeInSeconds) {
    try {
      if (timeInSeconds == 0)
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeSelected(element));
      else
        new WebDriverWait(driver, timeInSeconds)
            .until(ExpectedConditions.elementToBeSelected(element));
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }
  /***
   ********************************************************************
   */
  // For the element to be disappeared
  public boolean waitForElementToDisappear(WebElement element, long timeInSeconds) {
      try {
          if (timeInSeconds == 0)
              new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOf(element));
          else
              new WebDriverWait(driver, timeInSeconds).until(ExpectedConditions.invisibilityOf(element));

      } catch (Exception e) {
          e.printStackTrace();
          return false;
      }
      return true;
  }

    // For the element to Appear(Web Element)
  public boolean waitForElementToAppear(WebElement webElement, long timeInSeconds) {
    try {
      if (timeInSeconds == 0)
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(webElement));
      else
        new WebDriverWait(driver, timeInSeconds).until(ExpectedConditions.visibilityOf(webElement));

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  // For the element to be selected
  public boolean waitForElementToBeSelected(WebElement webElement, long timeInSeconds) {
    try {
      if (timeInSeconds == 0)
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeSelected(webElement));
      else
        new WebDriverWait(driver, timeInSeconds)
            .until(ExpectedConditions.elementToBeSelected(webElement));
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  // For the element to be clickable
  public boolean waitForElementToBeClickable(WebElement webElement, long timeInSeconds) {
    try {
      new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(webElement));

      if (timeInSeconds == 0)
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(webElement));
      else
        new WebDriverWait(driver, timeInSeconds)
            .until(ExpectedConditions.elementToBeClickable(webElement));
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

}
