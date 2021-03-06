package Chancecards;

import game.Player;
import game.Account;

public class ChanceCardBuildingTaxController extends ChanceCardController{
	private ChanceCardBuildingTaxData chanceCardData;
	private Account parkinglotAccount;
	
	public ChanceCardBuildingTaxController(ChanceCardBuildingTaxData chanceCardData, Account parkinglotAccount) {
		super(chanceCardData);
		this.chanceCardData = chanceCardData;
		this.parkinglotAccount = parkinglotAccount;
	}

	@Override
	public boolean onDrawn(Player player) {
		int hu = player.getProperty().getTotalHouseCount();
		int ho = player.getProperty().getTotalHotelCount();
		int housecount = chanceCardData.getHouseTax();
		int hotelcount = chanceCardData.getHotelTax();
		
		if(hu+ho > 0) {
			player.getAccount().transferTo(parkinglotAccount, housecount*hu);
			player.getAccount().transferTo(parkinglotAccount, hotelcount*ho);
			return false;
		} else {
			return true;
		}
	}

	public String toString(){
		return chanceCardData.toString() + ", parkinglotAccount=" + parkinglotAccount;
	}

}
