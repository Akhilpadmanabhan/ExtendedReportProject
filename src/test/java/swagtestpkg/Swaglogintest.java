package swagtestpkg;

import org.testng.Assert;
import org.testng.annotations.Test;


import basepkgswag.Swagbase;

public class Swaglogintest extends Swagbase{
	
	@Test(priority =1)
	public void login() throws Exception {
		test = extent.createTest("Loginvalidation");
		swagobj.loginmthd("standard_user","secret_sauce");
		Thread.sleep(2000);
	}
	
	@Test(priority =2)
	public void validation() {
		test = extent.createTest("Loginvalidation");
		String expurl = "https://www.saucedemo.com/inventory.html";
		String acturl = swagobj.urlmethd();
		Assert.assertEquals(expurl, acturl);		
	}
	
	@Test(priority =3)
	public void swagtitleverification()
	{
		test = extent.createTest("swagtitleverification");
		String Exp = "Swag Labs";
		String actual = swagobj.titlemthds();
		System.out.println("actual");
		Assert.assertEquals(Exp, actual);
	}
	
}

