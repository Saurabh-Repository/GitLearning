
package Amazon.Amazon;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Amazon.PageObjects.SigninPage;

public class AmazonOriginal {

	@Test
	public void Amazon() throws InterruptedException {
		// TODO Auto-generated method stub
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("Please enter your mobile number or email ID: ");
//		String id = scanner.next();
//		System.out.println("Please enter your Password: ");
//		String password = scanner.next();
//		System.out.println("Please enter the Product you want to search: ");
//		String product = scanner.next();
		
		String id = "7020951227";
		String password = "Saurabh@16";
		String product = "Books";
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		SigninPage signinPage = new SigninPage(driver);
		Actions actions = new Actions(driver);
		WebElement signing = driver.findElement(By.xpath("//a[@id='nav-link-accountList']"));
		actions.moveToElement(signing).perform();
		driver.findElement(By.xpath("//span[@class='nav-action-inner']")).click();
		driver.findElement(By.xpath("//input[@id='ap_email']")).sendKeys(id);
		driver.findElement(By.xpath("//input[@id='continue']")).click();
		driver.findElement(By.xpath("//input[@autocomplete='current-password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='signInSubmit']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search Amazon.in']")).sendKeys(product);
		driver.findElement(By.xpath("(//input[@type='submit'])[1]")).click();
		driver.findElement(By.xpath("(//span[@class='a-price-whole'])[1]")).click();
		driver.findElement(By.xpath("(//span[@class='a-price-whole'])[2]")).click();
		driver.findElement(By.xpath("(//span[@class='a-price-whole'])[3]")).click();

		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		String parentId = it.next();
		String childId1 = it.next();
		String childId2 = it.next();
		String childId3 = it.next();

		// 1st window
		driver.switchTo().window(childId1);
		System.out.println(driver.findElement(By.xpath("//span[@id='productTitle']")).getText());
		driver.findElement(By.xpath("//input[@name='submit.add-to-cart']")).click();

		// 2nd Window
		driver.switchTo().window(childId2);
		System.out.println(driver.findElement(By.xpath("//span[@id='productTitle']")).getText());
		driver.findElement(By.xpath("//input[@name='submit.add-to-cart']")).click();
		// 3rd Window
		driver.switchTo().window(childId3);
		System.out.println(driver.findElement(By.xpath("//span[@id='productTitle']")).getText());
		driver.findElement(By.xpath("//input[@name='submit.add-to-cart']")).click();

		driver.switchTo().window(parentId);
		System.out.println(driver.getTitle());
		
		driver.findElement(By.xpath("//a[@id='nav-cart']")).click();
		
		
		List<WebElement> prices = driver.findElements(By.xpath(
				"//div[@id='sc-active-cart']//div[@class='a-section a-spacing-mini']"));
		double allPrice=0.00;
		for (int i = 0 ; i<prices.size(); i++)
		{
			String price = prices.get(i).getText(); 
			System.out.println(price);
			price = price.replaceAll("₹","");
			double priceF = Double.parseDouble(price);
			allPrice = allPrice+priceF;
			System.out.println("Prices of each items are: "+allPrice);
		}
		
		//Comparing displayed value with actual value
		String totalPrice= driver.findElement(By.xpath("//div[@id='sc-buy-box']//span[@id='sc-subtotal-amount-buybox']")).getText();
		System.out.println("The total displayed price is "+totalPrice);
		double totalF = Double.parseDouble(totalPrice);
		
		Assert.assertEquals(allPrice, totalF);
		System.out.println("Assertion passed the total actual value is equal to total displayed value " +allPrice+"="+totalF);
		
//		Thread.sleep(2000);
//	    List<WebElement> trashes = driver.findElements(By.cssSelector(".a-icon.a-icon-small-trash"));
//	    for (int j =0; j<trashes.size(); j++) {
//	    	
//	    	WebElement trash = trashes.get(j);
//	    	trashes = driver.findElements(By.cssSelector(".a-icon.a-icon-small-trash"));
//	    	trash.click();
//	    }
		Thread.sleep(10000);
		driver.quit();

	}

}
