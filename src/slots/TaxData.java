package slots;

import java.awt.Color;

import desktop_resources.GUI;
import game.Player;
import game.Translator;

public class TaxData extends FieldData{

	private int taxAmount;
	private int taxRate = -1;

	
	public TaxData(int i, int price, int taxPercentage) {
		super(i);
		this.taxAmount = price;
		taxRate = taxPercentage;
	}

	
	@Override
	public String toString() {
		return "Tax [taxAmount=" + taxAmount + ", tax=" + tax + ", position=" + position
				+ ", getName()=" + getName() + ", getPosition()= " + getPosition() + "]";
	}
	
}