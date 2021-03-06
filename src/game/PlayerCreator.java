package game;

import java.awt.Color;
import java.util.regex.Pattern;

import desktop_codebehind.Car;
import desktop_resources.GUI;
import utilities.ShuffleBag;

public class PlayerCreator {
	private final int PLAYERSTARTINGCASH = 30000;
	private ShuffleBag<Color> availableCarColors = new ShuffleBag<Color>(new Color[]{Color.BLUE, Color.YELLOW, new Color(0, 107f/255, 15f/255), Color.PINK, Color.RED, Color.MAGENTA});
	private Player[] players;
	
	
	public Player createPlayer(String name)
	{
		Player newPlayer = new Player(name);
		Color color = Color.white;
		try {
			color = availableCarColors.getNext();
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * Giving players a random colored car, as well as setting up the player on the GUI
		 * with starting cash, name and their newly given car.
		 */
		Car car;
		int result = (int)((Math.random())*3+1);
		if(result == 1)
		{
			car = new Car.Builder().primaryColor(color).secondaryColor(Color.black).patternZebra().build();

		}
		else if(result == 2)
		{
			car = new Car.Builder().primaryColor(color).secondaryColor(Color.black).patternDotted().build();
		}
		else
		{
			car = new Car.Builder().primaryColor(color).secondaryColor(Color.black).patternCheckered().build();
		}
		System.out.println(car + " " + name);
		GUI.addPlayer(name, PLAYERSTARTINGCASH, car);
	
		return newPlayer;
	}
	private boolean verifyName(String s)
	{
		if(s.isEmpty())
		{
			return false;
		}
		
			//Checks if the string contains a whitespace character
			Pattern pattern = Pattern.compile("\\s");
			java.util.regex.Matcher m = pattern.matcher(s);
			
			return (!m.find());
		
	}
	private Player setupPlayer(String user){
		if (!verifyName(user) || user.length() > 15)
		{
			return null;
		}
		for(Player i : players) {
			if (i != null && i.getName().equals(user)){
				return null;
			}
		}
		 
		return createPlayer(user);
	}

	public Player[] setupPlayers() {
		int amount = GUI.getUserInteger(Translator.getString("NUMBEROFPLAYERS"));
		final int PLAYERAMOUNTMIN = 2;
		final int PLAYERAMOUNTMAX = 6;
		while(amount < PLAYERAMOUNTMIN || amount > PLAYERAMOUNTMAX){
			GUI.showMessage(Translator.getString("NUMBEROFPLAYERSERROR",PLAYERAMOUNTMIN,PLAYERAMOUNTMAX));
			
			amount = GUI.getUserInteger(Translator.getString("NUMBEROFPLAYERS"));	
		}
		/*
		 * adds each player to the players[] array. Used to keep track of running players in the game
		 * and whose turn it is. Also checks if each player are fulfilling the conditions, for making a name.
		 */
		players = new Player[amount];
		for(int j = 0; j < amount; j++) {
			String user;
			if(j == 0)
			{
				 user = GUI.getUserString(Translator.getString("ENTERNAME1"));
			}
			else
			{
				user = GUI.getUserString(Translator.getString("ENTERNAME2"));
			}
		Player newPlayer = null;
		while((newPlayer = setupPlayer(user)) == null) {
			user = GUI.getUserString(Translator.getString("NAMEERROR"));
		}
		players[j] = newPlayer;
		}
		desktop_board.Board.getInstance().updatePlayers();
		return players;

	}

}
