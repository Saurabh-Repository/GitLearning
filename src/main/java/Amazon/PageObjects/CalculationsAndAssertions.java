package Amazon.PageObjects;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CalculationsAndAssertions {
	WebDriver driver;

	public CalculationsAndAssertions(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@id='sc-active-cart']//div[@class='a-section a-spacing-mini']")
	public List<WebElement> prices;
	@FindBy(xpath = "//div[@id='sc-buy-box']//span[@id='sc-subtotal-amount-buybox']")
	WebElement displayedAmount;

	double allPrice = 0.00;

	public void Calculations() {

		for (int i = 0; i < prices.size(); i++) {
			String price = prices.get(i).getText();
			System.out.println(price);
			price = price.replaceAll("₹", "");
			double priceF = Double.parseDouble(price);
			allPrice = allPrice + priceF;
			System.out.println("Prices of each items are: " + allPrice);
		}
	}

	public void Assertions() {
		// Comparing displayed value with actual value
		String totalPrice = displayedAmount.getText();
		System.out.println("The total displayed price is " + totalPrice);
		double totalF = Double.parseDouble(totalPrice);

		Assert.assertEquals(allPrice, totalF);
		System.out.println(
				"Assertion passed the total actual value is equal to total displayed value " + allPrice + "=" + totalF);
	}

}
