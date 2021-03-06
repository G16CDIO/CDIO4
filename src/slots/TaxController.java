package slots;

import java.awt.Color;

import desktop_resources.GUI;
import game.Account;
import game.Player;
import game.Translator;

public class TaxController extends FieldController {
	private desktop_fields.Tax tax;
	private TaxData taxData;
	private Account parkinglotAccount;
	public TaxController(TaxData data, Account parkinglotAccount)
	{
		super(data);
		taxData = data;
		this.parkinglotAccount = parkinglotAccount;
	}
	@Override
	public void landOnField(Player player) {
		/**
		 * Player lands on Tax and has to pay, either a flat amount or
		 * a percentage of his fortune. 
		 */
		int taxPaid = 0;
		if (taxData.getTaxRate() == 0){
			GUI.getUserButtonPressed(Translator.getString("LANDONTAX"), Integer.toString(taxData.getTaxAmount()));
			taxPaid = taxData.getTaxAmount();
		}
		else {
			tax.displayOnCenter();
			if (GUI.getUserLeftButtonPressed(Translator.getString("LANDONTAX"), Integer.toString(taxData.getTaxRate())+"%" , Integer.toString(taxData.getTaxAmount()))) {
				taxPaid = (int)(((float)taxData.getTaxRate()/100f)*player.getAccount().getGold());
			}
			else {
				taxPaid = taxData.getTaxAmount();
			}
		}
		player.getAccount().transferTo(parkinglotAccount, taxPaid);
	}

	@Override
	public desktop_fields.Field pushToGUI(int position) {
		taxData.setPosition(position);
		tax = new desktop_fields.Tax.Builder().setBgColor(new Color(255f/255, 43f/255, 57f/255)).build();
		tax.setDescription(getDescription());
		tax.setTitle(taxData.getName());
		tax.setSubText(Integer.toString(taxData.getTaxAmount()));
		return tax;
	}
	@Override
	public String getDescription() {
		
		return Translator.getString("TAXDSC");
	}
	
	public String toString(){
		return taxData.toString();
	}
}
