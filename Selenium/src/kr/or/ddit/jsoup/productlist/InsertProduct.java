package kr.or.ddit.jsoup.productlist;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.jsoup.SqlMapClientFactory;

public class InsertProduct {
	
	static SqlMapClient smc = SqlMapClientFactory.getClient();
	
	public static void main(String[] args) throws IOException {
		
		
		String WEB_DRIVER_ID = "webdriver.chrome.driver";
		String WEB_DRIVER_PATH = "C:\\chromedriver.exe";
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		
		WebDriver driver = new ChromeDriver();
		driver.get("http://corners.gmarket.co.kr/Bestsellers?viewType=C&largeCategoryCode=100000017");
		
		System.out.println("페이지 타이틀 : " + driver.getTitle());
		waitForLoad(driver);
		
		String category = driver.findElement(By.className("current")).getText().trim();
		System.out.println("카테고리 : " + category);
		
		int cl_index = 0;
		try {
			cl_index = (int)smc.queryForObject("selenium.getCategoryIndex", category);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(cl_index);
		
		List<WebElement> itemnames = driver.findElements(By.className("itemname"));
		List<WebElement> prices = driver.findElements(By.className("s-price"));
		List<WebElement> images = driver.findElements(By.className("lazy"));
		
		List<ProductVO> list = new ArrayList<>();
		
		for(WebElement itemname : itemnames) {
			String title = itemname.getText().trim();
			if(title != "" && title != null && title.length() != 0) {
				System.out.println("제품명 : " + title);
				ProductVO vo = new ProductVO();
				vo.setCl_index(26);
				vo.setPl_name(title);
				list.add(vo);
			}
		}
		for(int i = 0; i < 100; i++) {
			String cost = prices.get(i).getText().trim();
			if(cost != "" && cost != null && cost.length() != 0) {
				cost = cost.substring(0, cost.indexOf("원"));
				cost = cost.replace(",", "");
				System.out.println("가격 : " + cost);
				list.get(i).setPl_price(Integer.parseInt(cost));
			}
			
		}
		for(int i = 0; i < images.size(); i++) {
			String img = images.get(i).getAttribute("src");
			System.out.println(img);
			list.get(i).setPl_image(img);
		}
		
		driver.quit();
		
		for(ProductVO vo1 : list) {
			try {
				smc.insert("selenium.insertProduct", vo1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(vo1.toString());
		}
//		#cl_index#,
//		#pl_name#,
//		#pl_price#,
//		#pl_image#,
		
	}
	
	static void waitForLoad(WebDriver driver) {
		new WebDriverWait(driver, 120).until((ExpectedCondition<Boolean>) wd ->
				((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
	}
}

