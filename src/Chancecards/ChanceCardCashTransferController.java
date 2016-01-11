package Chancecards;

import game.Player;

public class ChanceCardCashTransferController extends ChanceCardController{
	private ChanceCardCashData chanceCardData;
	private Player[] players;
	
	public ChanceCardCashTransferController(ChanceCardCashData chanceCardData, Player[] players) {
		this.chanceCardData = chanceCardData;
		this.players = players;
	}
	
	@Override
	public boolean onDrawn(Player player) {
		for(Player p : players)
		{
			if(p != player) {
			p.getAccount().transferTo(player.getAccount(), chanceCardData.getMoney());
			}
		}
		return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
