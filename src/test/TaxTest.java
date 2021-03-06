package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.Account;
import game.Player;
import slots.TaxData;
import slots.TaxController;

public class TaxTest {
	

	private Account acc;
	private TaxData taxD;
	private TaxController tax;
	private Player player;
	
	@Before
	public void preTest(){

		acc = new Account(0, "Test");
		taxD = new TaxData(1, 2000, 10);
		tax = new TaxController(taxD, acc);
		player = new Player("Test");
	}

	@Test
	public void testFlatTax() {

		tax.pushToGUI(1);
		tax.landOnField(player);
		
	
		assertTrue(player.getAccount().getGold()== 27000 || player.getAccount().getGold()== 28000);
		assertTrue(acc.getGold()==2000 || acc.getGold()==3000);
		

	}
//	
//	@Test
//	public void testPercentageTax

}
