import java.util.*;
import java.io.*;
import java.time.*;

///////////////////////////////////////////////////////////////////////////////////////////////////
class HockeyPlayer implements Comparable<HockeyPlayer>{
	//fields
	private String lastName;
	private String position;
	private String birthplace;
	private int [] stats;
	private ArrayList<HockeyPlayer> roster;
	private ArrayList<HockeyPlayer> rosterSorted;
	private ArrayList<String> rosterBP;
	private final String[] HPVARS = {"Name", "Position", "Birthplace"};
	private static int sortHPBy;
	
	//constructors
	public HockeyPlayer(String lastName, String position, String birthplace, int... stats){
		this.lastName = lastName;
		this.position = position;
		this.birthplace = birthplace;
		this.stats = stats;
	}
	
	public HockeyPlayer(int sortHPBy){
		this(" ", " ", " ");
		setSortHPBy(sortHPBy);
		this.setStats(this.stats);
	}
	
	public HockeyPlayer(ArrayList<HockeyPlayer> sk, ArrayList<HockeyPlayer> g){
		this(" ", " ", " ");	
		this.setStats(this.stats);
		roster = new ArrayList<HockeyPlayer>();
		setRoster(sk, g);
	}
	
	public HockeyPlayer(int sortHPBy, ArrayList<HockeyPlayer> player){
		this(sortHPBy);
		roster = new ArrayList<HockeyPlayer>();
		setRoster(player);
		setRosterSorted();
		setRosterBP();
	}
	
	public HockeyPlayer(int sortHPBy, ArrayList<HockeyPlayer> sk, ArrayList<HockeyPlayer> g){
		this(" ", " ", " ");
		roster = new ArrayList<HockeyPlayer>();
		this.sortHPBy = sortHPBy;
		setRoster(sk, g);
		setRosterSorted();
		setRosterBP();
	}
	
	public HockeyPlayer(HockeyPlayer hp){
		this(hp.lastName, hp.position, hp.birthplace, hp.stats);
		roster = new ArrayList<HockeyPlayer>();
	}
	
	/**
	* a method that defines how to sort HockeyPlayers numerically by a stat
	* @param HockeyPlayer the player to which we are comparing
	* @return int -1 if original player less than other player, 0 if original and other player equal, 1 if original player greater than other player (in reference to a specific stat)
	*/
	@Override
    	public int compareTo(HockeyPlayer other) {
		if(sortHPBy == 1){
			if (this.getStats()[0] < other.getStats()[0]) {
				return -1;
			}
			else if (this.getStats()[0] == other.getStats()[0]) { 
				return 0;
			}
			else{
				return 1;	
			}
		}
		else if(sortHPBy == 2){
			if (this.getStats()[1] < other.getStats()[1]) {
				return -1;
			}
			else if (this.getStats()[1] == other.getStats()[1]) { 
				return 0;
			}
			else{
				return 1;	
			}
		}
		else if(sortHPBy == 3){
			if (this.getStats()[2] < other.getStats()[2]) {
				return -1;
			}
			else if (this.getStats()[2] == other.getStats()[2]) { 
				return 0;
			}
			else{
				return 1;	
			}
		}
		else if(sortHPBy == 4){
			if (this.getStats()[3] < other.getStats()[3]) {
				return -1;
			}
			else if (this.getStats()[3] == other.getStats()[3]) { 
				return 0;
			}
			else{
				return 1;
			}
		}
		else{
			return 1;
		}
    	}
    	
	
	//setters
	public void setLastName(String lastName){
		this.lastName = lastName;	
	}
	
	public void setPosition(String position){
		this.position = position;	
	}
	
	public void setBirthplace(String birthplace){
		this.birthplace = birthplace;	
	}
	
	public void setStats(int... stats){
		this.stats = stats;
	}
	
	public void setRoster(ArrayList<HockeyPlayer> player){
		roster.addAll(player);	
	}
	
	public void setRoster(ArrayList<HockeyPlayer> sk, ArrayList<HockeyPlayer> g){
		roster.addAll(sk);
		roster.addAll(g);
	}
	
	public void setRosterSorted(){
		rosterSorted = new ArrayList<HockeyPlayer>(getRoster());
		Collections.sort(rosterSorted);
	}
	
	public void setRosterBP(){
		rosterBP = new ArrayList<String>();
		Defense d = new Defense();
		Forward f = new Forward();
		Skaters skate = new Skaters(f.getRoster(), d.getRoster());
		skate.setRosterBP();
		Goalies goalie = new Goalies();
		rosterBP= skate.getRosterBP();
		for(int i = 0; i<goalie.getRosterBP().size(); i++){
			if(rosterBP.contains(goalie.getRosterBP().get(i)) == false){
				rosterBP.add(goalie.getRosterBP().get(i));	
			}
		}
		Collections.sort(rosterBP);
	}
	
	public void setSortHPBy(int sortHPBy){
		this.sortHPBy = sortHPBy;	
	}
	
	//getters
	public String getLastName(){
		return lastName;	
	}
	
	public String getPosition(){
		return position;	
	}
	
	public String getBirthplace(){
		return birthplace;	
	}
	
	public int[] getStats(){
		return stats;	
	}
	
	public ArrayList<HockeyPlayer> getRoster(){
		return roster;	
	}
	
	public ArrayList<HockeyPlayer> getRosterSorted(){
		return rosterSorted;	
	}
	
	public ArrayList<String> getRosterBP(){
		return rosterBP;	
	}
	
	public String[] getHPVARS(){
		return HPVARS;	
	}
	
	public int getSortHPBy(){
		return sortHPBy;	
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////
class Goalies extends HockeyPlayer{
	//fields 
	private int shotsAgainst;
	private int goalsAgainst;
	private int saves;
	private final String[] GOALIEVARS = {"Shots Against", "Goals Against", "Saves"};
	private ArrayList<HockeyPlayer> roster;
	private ArrayList<HockeyPlayer> rosterSorted;
	private ArrayList<String> rosterBP;
	private static int sortGoaliesBy;
	
	//constructors
	public Goalies(){
		super(" ", " ", " ");
		setRoster();	
		setRosterBP();
	}
	
	public Goalies(int sortGoaliesBy){
		super(sortGoaliesBy);
		this.sortGoaliesBy = sortGoaliesBy;
		setRoster();
		setRosterSorted();
	}
	
	public Goalies(String lastName, String position, String birthplace, int shotsAgainst, int goalsAgainst, int saves){
		super(lastName, position, birthplace);
		this.shotsAgainst = shotsAgainst;
		this.goalsAgainst = goalsAgainst;
		this.saves = saves;
	}
	
	public Goalies(HockeyPlayer hp, int shotsAgainst, int goalsAgainst, int saves){
		super(hp);
		this.shotsAgainst = shotsAgainst;
		this.goalsAgainst = goalsAgainst;
		this.saves = saves;	
	}
	
	public Goalies(Goalies goalie){
		this(goalie.getLastName(), goalie.getPosition(), goalie.getBirthplace(), goalie.shotsAgainst, goalie.goalsAgainst, goalie.saves);	
	}
	
	//setters
	public void setShotsAgainst(int shotsAgainst){
		this.shotsAgainst = shotsAgainst;	
	}
	
	public void setGoalsAgainst(int goalsAgainst){
		this.goalsAgainst = goalsAgainst;	
	}
	
	public void setSaves(int saves){
		this.saves = saves;	
	}
	
	public void setRoster(){
		roster = new ArrayList<HockeyPlayer>();
		roster.add(new HockeyPlayer("Holtby", "Goalie", "Canada", 1648, 153, 1495));
		roster.add(new HockeyPlayer("Grubauer", "Goalie", "Germany", 953, 73, 880));
		//roster.add(new HockeyPlayer("TEST", "Goalie", "USA", 200, 120, 2000));
	}
	
	public void setRosterSorted(){
		rosterSorted = new ArrayList<HockeyPlayer>(getRoster());
		Collections.sort(rosterSorted);
	}
	
	public void setRosterBP(){
		rosterBP = new ArrayList<String>();
		rosterBP.add(roster.get(0).getBirthplace());
		for(int i = 1; i< roster.size(); i++){
			if(rosterBP.contains(roster.get(i).getBirthplace()) == false){
				rosterBP.add(roster.get(i).getBirthplace());	
			}
		}
	}
	
	//getters
	public int getShotsAgainst(){
		return shotsAgainst;	
	}
	
	public int getGoalsAgainst(){
		return goalsAgainst;	
	}
	
	public int getSaves(){
		return saves;	
	}
	
	public String[] getGOALIEVARS(){
		return GOALIEVARS;	
	}
	
	public ArrayList<HockeyPlayer> getRoster(){
		return roster;	
	}
	
	public ArrayList<HockeyPlayer> getRosterSorted(){
		return rosterSorted;	
	}
	
	public ArrayList<String> getRosterBP(){
		return rosterBP;	
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////
class Skaters extends HockeyPlayer{
	//fields
	private int goals;
	private int assists;
	private int points;
	private int plusMinus;
	private int shoots;
	private final String[] SKATERVARS = {"Goals", "Assists", "Points", "+/-", "Shoots"};
	private ArrayList<HockeyPlayer> roster;
	private ArrayList<HockeyPlayer> rosterSorted;
	private ArrayList<String> rosterBP;
	private static int sortSkatersBy;
	
	//constructors
	public Skaters(){
		super(" ", " ", " ");
		super.setStats(goals, assists, points, plusMinus, shoots);	
		roster = new ArrayList<HockeyPlayer>();
	}
	
	public Skaters(ArrayList<HockeyPlayer> f, ArrayList<HockeyPlayer> d){
		super(" ", " ", " ");
		super.setStats(goals, assists, points, plusMinus, shoots);	
		roster = new ArrayList<HockeyPlayer>();
		setRoster(f, d);
	}
	
	public Skaters(int sortSkatersBy, ArrayList<HockeyPlayer> f, ArrayList<HockeyPlayer> d){
		super(" ", " ", " ");
		super.setStats(goals, assists, points, plusMinus, shoots);
		roster = new ArrayList<HockeyPlayer>();
		this.sortSkatersBy = sortSkatersBy;
		setRoster(f, d);
		setRosterSorted();
	}
	
	public Skaters(String lastName, String position, String birthplace, int goals, int assists, int points, int plusMinus, int shoots){
		super(lastName, position, birthplace, goals, assists, points, plusMinus, shoots);
		this.goals = goals;
		this.assists = assists;
		this.points = points;
		this.plusMinus = plusMinus;
		this.shoots = shoots;
	}
	
	public Skaters(HockeyPlayer hp, int goals, int assists, int points, int plusMinus, int shoots){
		super(hp);
		this.goals = goals;
		this.assists = assists;
		this.points = points;
		this.plusMinus = plusMinus;
		this.shoots = shoots;
	}
	
	public Skaters(Skaters skater){
		this(skater.getLastName(), skater.getPosition(), skater.getBirthplace(), skater.goals, skater.assists, skater.points, skater.plusMinus, skater.shoots);	
	}
	
	//setters
	public void setGoals(int goals){
		this.goals = goals;
	}
	
	public void setAssists(int assists){
		this.assists = assists;	
	}
	
	public void setPoints(int points){
		this.points = points;	
	}
	
	public void setPoints(int goals, int assists){
		points = goals + assists;	
	}
	
	public void setPoints(){
		points = goals + assists;	
	}
	
	public void setPlusMinus(int plusMinus){
		this.plusMinus = plusMinus;	
	}
	
	public void setShoots(int shoots){
		this.shoots = shoots;
	}
	
	public void setRoster(ArrayList<HockeyPlayer> d, ArrayList<HockeyPlayer> f){
		roster.addAll(d);
		roster.addAll(f);
	}
	
	public void setRosterSorted(){
		rosterSorted = new ArrayList<HockeyPlayer>(getRoster());
		Collections.sort(rosterSorted);
	}
	
	public void setRosterBP(){
		rosterBP = new ArrayList<String>();
		rosterBP.add(roster.get(0).getBirthplace());
		for(int i = 1; i< roster.size(); i++){
			if(rosterBP.contains(roster.get(i).getBirthplace()) == false){
				rosterBP.add(roster.get(i).getBirthplace());	
			}
		}
	}
	
	//getters
	public int getGoals(){
		return goals;	
	}
	
	public int getAssists(){
		return assists;
	}	
	
	public int getPoints(){
		return points;	
	}
	
	public int getPlusMinus(){
		return plusMinus;	
	}
	
	public int getShoots(){
		return shoots;	
	}
	
	public String[] getSKATERVARS(){
		return SKATERVARS;	
	}
	
	public ArrayList<HockeyPlayer> getRoster(){
		return roster;	
	}
	
	public ArrayList<HockeyPlayer> getRosterSorted(){
		return rosterSorted;	
	}
	
	public ArrayList<String> getRosterBP(){
		return rosterBP;	
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////
class Defense extends Skaters{
	//fields
	private ArrayList<HockeyPlayer> roster;
	private ArrayList<String> rosterBP;
	
	//constructors
	public Defense(){
		setRoster();
		setRosterBP();
	}
	
	public Defense(String lastName, String position, String birthplace, int goals, int assists, int points, int plusMinus, int shoots){
		super(lastName, position, birthplace, goals, assists, points, plusMinus, shoots);
	}
	
	public Defense(Skaters skater){
		super(skater);	
	}
	
	//setters
	public void setRoster(){
		//shoots right = 0
		//shoots left = 1
		roster = new ArrayList<HockeyPlayer>();
		roster.add(new Skaters("Carlson", "Defense", "USA", 15, 53, 68, 0, 0));
		roster.add(new Skaters("Orlov", "Defense", "Russia", 10, 21, 31, 10, 1));
		roster.add(new Skaters("Niskanen", "Defense", "USA", 7, 22, 29, 24, 0));
		roster.add(new Skaters("Djoos", "Defense", "Sweden", 3, 11, 14, 13, 1));
		roster.add(new Skaters("Bowey", "Defense", "Canada", 0, 12, 12, -3, 0));
		roster.add(new Skaters("Orpik", "Defense", "USA", 0, 10, 10, -9, 1));
		roster.add(new Skaters("Chorney", "Defense", "Canada", 1, 3, 4, 8, 1));
		roster.add(new Skaters("Jerabek", "Defense", "Czech Republic", 1, 3, 4, -1, 1));
		roster.add(new Skaters("Kempny", "Defense", "Czech Republic", 2, 1, 3, 1, 1));
		roster.add(new Skaters("Ness", "Defense", "USA", 0, 1, 1, 2, 1));
	}
	
	public void setRosterBP(){
		rosterBP = new ArrayList<String>();
		rosterBP.add(roster.get(0).getBirthplace());
		for(int i = 1; i< roster.size(); i++){
			if(rosterBP.contains(roster.get(i).getBirthplace()) == false){
				rosterBP.add(roster.get(i).getBirthplace());	
			}
		}
	}
	
	//getters
	public ArrayList<HockeyPlayer> getRoster(){
		return roster;	
	}
	
	public ArrayList<String> getRosterBP(){
		return rosterBP;	
	}
	
}

///////////////////////////////////////////////////////////////////////////////////////////////////
class Forward extends Skaters{
	//fields
	String forwardPosition;
	private final String[] FORWARDVARS = {"Forward Position"};
	private ArrayList<HockeyPlayer> roster;
	private ArrayList<String> rosterBP;
	
	//constructors
	public Forward(){
		setRoster();
		setRosterBP();
	}
	
	public Forward(String lastName, String position, String birthplace, int goals, int assists, int points, int plusMinus, int shoots, String forwardPosition){
		super(lastName, position, birthplace, goals, assists, points, plusMinus, shoots);
		this.forwardPosition = forwardPosition;
	}
	
	public Forward(Skaters skater, String forwardPosition){
		super(skater);	
		this.forwardPosition = forwardPosition;
	}
	
	//setters
	public void setForwardPosition(String forwardPosition){
		this.forwardPosition = forwardPosition;	
	}
	
	public void setRoster(){
		//shoots right = 0
		//shoots left = 1
		roster = new ArrayList<HockeyPlayer>();
		roster.add(new Skaters("Ovechkin", "Forward, LW", "Russia", 49, 38, 87, 3, 0));
		roster.add(new Skaters("Vrana", "Forward, LW", "Czech Republic", 13, 14, 27, 2, 1));
		roster.add(new Skaters("Gersich", "Forward, LW", "USA", 0, 1, 1, -1, 1));
		roster.add(new Skaters("Walker", "Forward, LW", "Wales", 1, 0, 1, 1, 1));
		roster.add(new Skaters("Burakovsky", "Forward, LW", "Austria", 12, 13, 25, 3, 1));
		roster.add(new Skaters("Kuznetsov", "Forward, C", "Russia", 27, 56, 83, 3, 1));
		roster.add(new Skaters("Backstrom", "Forward, C", "Sweden", 21, 50, 71, 5, 1));
		roster.add(new Skaters("Graovac", "Forward, C", "Canada", 0, 0, 0, -3, 1));
		roster.add(new Skaters("Boyd", "Forward, C", "USA", 0, 1, 1, 2, 0));
		roster.add(new Skaters("O'Brien", "Forward, C", "Canada", 0, 0, 0, 0, 1));
		roster.add(new Skaters("Eller", "Forward, C", "Denmark", 18, 20, 38, -6, 1));
		roster.add(new Skaters("Stephenson", "Forward, C", "Canada", 6, 12, 18, 13, 1));
		roster.add(new Skaters("Beagle", "Forward, C", "Canada", 7, 15, 22, 3, 0));
		roster.add(new Skaters("Oshie", "Forward, RW", "USA", 18, 29, 47, 2, 0));
		roster.add(new Skaters("Wilson", "Forward, RW", "Canada", 14, 21, 35, 10, 0));
		roster.add(new Skaters("Connolly", "Forward, RW", "Canada", 15, 12, 27, -6, 0));
		roster.add(new Skaters("Peluso", "Forward, RW", "Canada", 0, 0, 0, 0, 0));
		roster.add(new Skaters("Smith-Pelly", "Forward, RW", "Canada", 7, 9, 16, -6, 0));
		roster.add(new Skaters("Chiasson", "Forward, RW", "Canada", 9, 9, 18, 1, 0));
	}
	
	public void setRosterBP(){
		rosterBP = new ArrayList<String>();
		rosterBP.add(getRoster().get(0).getBirthplace());
		for(int i = 1; i< getRoster().size(); i++){
			if(rosterBP.contains(getRoster().get(i).getBirthplace()) == false){
				rosterBP.add(getRoster().get(i).getBirthplace());	
			}
		}
	}
	
	//getters
	public String getForwardPosition(){
		return forwardPosition;	
	}
	
	public String[] getFORWARDVARS(){
		return FORWARDVARS;	
	}
	
	public ArrayList<HockeyPlayer> getRoster(){
		return roster;	
	}
	
	public ArrayList<String> getRosterBP(){
		return rosterBP;	
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
class Output{
	//fields
	private BufferedReader reader;
	
	//constuctor
	public Output(){
		reader = new BufferedReader(new InputStreamReader(System.in));	
	}
	
	//setter
	public void setReader(){
		reader = new BufferedReader(new InputStreamReader(System.in));		
	}
	
	//getter
	public BufferedReader getReader(){
		return reader;
	}	
	
	public int userChoice(){
		int userChoice = 0;
		try{
			System.out.print("Enter selection: ");
			userChoice = Integer.parseInt(reader.readLine());	
		}
		catch(Exception e){
			System.out.println("Exception in userChoice method: " + e + "\nTry again!");
			userChoice();
		}
		return userChoice;
	}
	
	public static void mainTitle(){
		System.out.println("\n**********************************************************************************");
		System.out.println("WELCOME TO 2017-2018 WASHINGTON CAPITALS' (SOME) REGULAR SEASON STATS WIZARD!");
	}
	
	public void mainMenu(){
		mainTitle();
		Output output = new Output();
		int choice = output.userOptions("Display Roster", "Sort Stats", "Query Players");
		if(choice == 1){
			Display display = new Display();
			display.printRosterDetails();
			mainMenu();
		}
		else if(choice == 2){
			Sort sort = new Sort();
			sort.userSortOptions();
		}
		else{
			Query query = new Query();
			query.userQueryOptions();
			mainMenu();
		}
	}
	
	public void printSkaterStats(HockeyPlayer skater){
		System.out.println("Position: " + skater.getPosition() + "\tGoals: " + skater.getStats()[0] + "\tAssists: " + skater.getStats()[1] + "\tPoints: " + skater.getStats()[2] + "\t+/-: " + skater.getStats()[3] + "\t\tName: " + skater.getLastName());	
	}
	
	public void printGoalieStats(HockeyPlayer goalie){
		System.out.println("Position: " + goalie.getPosition() + "\tShots Against: " + goalie.getStats()[0] + "\tGoals Against: " + goalie.getStats()[1] + "\tSaves: " + goalie.getStats()[2] + "\t\tName: " + goalie.getLastName());	
	}
	
	//method to determine which option (from given options), user selects
	public int userOptions(String... OPTIONS){
		int userChc = 0;
		System.out.println("\n**********************************************************************************");
		System.out.println("\nSelect an option: ");
		for(int i = 0; i < OPTIONS.length; i++){
			System.out.println((i+1) + "). " + OPTIONS[i]);	
		}
		System.out.println("\n" + (OPTIONS.length + 1) + "). " + "Exit");
		System.out.println("\n*********************************************");
		userChc = userChoice();
			if(userChc < (OPTIONS.length + 1)){
				System.out.println("  You selected:  " + OPTIONS[userChc - 1]);
				return userChc;
			}
			else if(userChc == (OPTIONS.length + 1)){
				System.out.print("  You selected: EXIT");
				System.exit(0);
			}
		return 1;
	}
	
}

///////////////////////////////////////////////////////////////////////////////////////////////////
class Display extends Output{
	//field
	HockeyPlayer hp;
	
	//no-argument constructor
	public Display(){
		Forward f = new Forward();
		Defense d = new Defense();
		Skaters sk = new Skaters(f.getRoster(), d.getRoster());
		Goalies g = new Goalies();
		hp = new HockeyPlayer(sk.getRoster(), g.getRoster());	
	}
	
	//setter
	public void setHP(HockeyPlayer hp){
		this.hp = hp;
	}
	
	//getter
	public HockeyPlayer getHP(){
		return hp;	
	}
	
	//ouputs skaters (forward and defense) and goalies and some of their stats in a predetermined order
	public void printRoster(){
		for(HockeyPlayer player : hp.getRoster()){
			if(player.getStats().length == 5){
				printSkaterStats(player);	
			}
			else{
				printGoalieStats(player);
			}
		}
	}
	
	//outputs all players and some of their stats in a predetermined order, then loads main menu of user options
	public void printRosterDetails(){
		System.out.println("*****************************************************************************");
		System.out.println("THE 2017-2018 REGULAR SEASON ROSTER AND SOME OF THEIR STATS:\n");
		Display display = new Display();
		display.printRoster();
		mainMenu();
	}	
}

///////////////////////////////////////////////////////////////////////////////////////////////////
class Sort extends Output{
	//fields
	private int userMin;
	private int userMax;
	private int userChoice;
	
	//constructors
	public Sort(){
	}	

	//setters
	public void setUserMin(int userMin){
		this.userMin = userMin;
	}
	
	public void setUserMax(int userMax){
		this.userMax = userMax;
	}
	
	public void setUserChoice(int userChoice){
		this.userChoice = userChoice;
	}
	
	//getters
	public int getUserMin(){
		return userMin;	
	}
	
	public int getUserMax(){
		return userMax;	
	}
	
	public int getUserChoice(){
		return userChoice;	
	}

	//method to (i) show user statMin and statMax, and (ii) get sort bounds from user
	public void showGetMinMax(String type, String stat, int statMin, int statMax){
		System.out.println("**************************************************************************************");
		System.out.println("As of " + LocalDate.now() + " " + stat + " MIN = " + statMin);
		System.out.println("As of " + LocalDate.now() + " " + stat + " MAX = " + statMax);
		System.out.print("Enter MIN " + stat + " to include in sort (this number WILL be included): ");
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			userMin = Integer.parseInt(reader.readLine());
			System.out.print("Enter MAX " + stat + " to include in sort (this number WILL be included): ");
			userMax = Integer.parseInt(reader.readLine());
			System.out.println("\nCAPS " + type.toUpperCase()+ " with " + userMin + " <= " + stat + " <= " + userMax + " are:");
		}
		catch(Exception e){
			System.out.println("Exception in showGetMinMax() method: " + e + "\nTry again!");
			showGetMinMax(type, stat, statMin, statMax);
		}
	}
	
	//method to output user-defined sorted(some/all) goalie stats
	public void outputSortedGoalieStat(ArrayList<HockeyPlayer> sortedRoster){
		for(HockeyPlayer g : sortedRoster){
			switch(userChoice){
				case 1: if(g.getStats()[0] >= userMin){
					if(g.getStats()[0] <= userMax){
						printGoalieStats(g);	
					}
					}
					break;
				case 2: if(g.getStats()[1] >= userMin){
					if(g.getStats()[1] <= userMax){
						printGoalieStats(g);						
					}
					}
					break;
				case 3:  if(g.getStats()[2] >= userMin){
					if(g.getStats()[2] <= userMax){
						printGoalieStats(g);	
					}
					}
					break;	
			}		
		}	
	}
	
	//method to output user-defined sorted(some/all) skater stats
	public void outputSortedSkaterStat(ArrayList<HockeyPlayer> sortedRoster){
		for(HockeyPlayer s : sortedRoster){
			switch(userChoice){
				case 1: if(s.getStats()[0] >= userMin){
					if(s.getStats()[0] <= userMax){
						printSkaterStats(s);	
					}
					}
					break;
				case 2: if(s.getStats()[1] >= userMin){
					if(s.getStats()[1] <= userMax){
						printSkaterStats(s);	
					}
					}
					break;
				case 3:  if(s.getStats()[2] >= userMin){
					if(s.getStats()[2] <= userMax){
						printSkaterStats(s);	
					}
					}
					break;	
				case 4:  if(s.getStats()[3] >= userMin){
					if(s.getStats()[3] <= userMax){
						printSkaterStats(s);	
					}
					}
					break;	
			}		
		}
	}

	public void outputGoaliesSorted(){
		Goalies goalies = new Goalies();
		HockeyPlayer h = new HockeyPlayer(userChoice, goalies.getRoster());
		int rosterMin = h.getRosterSorted().get(0).getStats()[userChoice - 1];
		int rosterMax = h.getRosterSorted().get(h.getRosterSorted().size() - 1).getStats()[userChoice-1];
		showGetMinMax("goalies", goalies.getGOALIEVARS()[userChoice-1], rosterMin, rosterMax);
		outputSortedGoalieStat(h.getRosterSorted());
	}
	
	public void outputSkatersSorted(){
		HockeyPlayer f = new Forward();
		HockeyPlayer d = new Defense();
		Skaters s = new Skaters(f.getRoster(), d.getRoster());
		HockeyPlayer h = new HockeyPlayer(userChoice, s.getRoster());
		int rosterMin = h.getRosterSorted().get(0).getStats()[userChoice - 1];
		int rosterMax = h.getRosterSorted().get(h.getRosterSorted().size() - 1).getStats()[userChoice-1];
		showGetMinMax("skaters", s.getSKATERVARS()[userChoice-1], rosterMin, rosterMax);
		outputSortedSkaterStat(h.getRosterSorted());
	}
	
	//a method that outputs a menu to select type of sorted stats to display on screen
	public void userSortOptions(){
		int sortGoalieOrSkater = userOptions("Goalie", "Skater");
		if(sortGoalieOrSkater == 1){ 
			userChoice = userOptions("Shots Against", "Goals Against", "Saves");
			outputGoaliesSorted();
		}
		else{ 
			userChoice = userOptions("Goals", "Assists", "Points", "+/-");
			outputSkatersSorted();
		}
		mainMenu();
	}
	
}

///////////////////////////////////////////////////////////////////////////////////////////////////
class Query extends Output{
	//fields
	private HockeyPlayer hp;
	private final String [] queryMainMenu = {"Birthplace", "Shoots", "Position"};
	private String [] queryBP;
	private final String [] queryShoots = {"R", "L"};
	private final String [] queryPositions = {"Forward", "Defense", "Goalie"}; 
	private final String [] queryForwardPos = {"Forward", "Forward, LW", "Forward, C", "Forward, RW"};
	
	//no-argument constructor
	public Query(){
		setHP();
		setQueryBP();
	}
	
	//setters
	public void setHP(){
		Defense d = new Defense();
		Forward f = new Forward();
		Skaters skate = new Skaters(f.getRoster(), d.getRoster());
		skate.setRosterBP();
		Goalies goalie = new Goalies();
		hp = new HockeyPlayer(skate.getRoster(), goalie.getRoster());
		hp.setRosterBP();	
	}
	
	public void setHP(HockeyPlayer hp){
		this.hp = hp;	
	}
	
	public void setQueryBP(){
		queryBP = hp.getRosterBP().toArray(new String[0]);
	}
	
	//getters
	public HockeyPlayer getHP(){
		return hp;	
	}
	
	public String[] getQueryMainMenu(){
		return queryMainMenu;	
	}
	
	public String[] getQueryBP(){
		return queryBP;
	}
	
	public String[] getQueryShoots(){
		return queryShoots;	
	}
	
	public String[] getQueryPositions(){
		return queryPositions;	
	}
	
	public String[] getQueryForwardPos(){
		return queryForwardPos;	
	}
	
	/**
	generalized method to display query results -- parameters tailor method to a specific value in the queryMainMenu final class var
	*@param queryType -- depends on userChoice from querySubMenu selection
	*@param message -- words specific to current query (e.g., insert 'born' when displaying birthplace results)
	*@param choice -- depends on userChoice from queryMainMenu selection
	*@param queryOpts -- these depend on the Query class array fields
	*/
	public void outputQuery(int queryType, String message, int choice, String... queryOpts){
		System.out.println("\n***********************************************************************");
		System.out.println("RESULTS:\n" + message + " " + queryOpts[queryType] + " are:");	
		int counter = 0;
		for(HockeyPlayer player : hp.getRoster()){
		boolean conditionMet = false;
			switch(choice){
			case 1: if(player.getBirthplace() == queryOpts[queryType]){
					conditionMet = true;	
				}
				break;
			case 2: if(player.getStats().length == 5){
					if(player.getStats()[4] == queryType){
						conditionMet = true;	
					}
				}
				break;
			case 3: if(player.getPosition().contains(queryOpts[queryType])){
					conditionMet = true;
				}
				break;
			}	
			if(conditionMet){
				System.out.println("\t" + player.getLastName());	
				counter++;
			}
		}
		System.out.println("\nTOTAL: " + counter + " " + message + " " + queryOpts[queryType] + ".");
		System.out.println("***********************************************************************");
	}

	//method that runs a Query 'subMenu' to the main program menu
	public void userQueryOptions(){
		int userChcMain = userOptions(queryMainMenu);	
		int userChc = userChcMain;
		Query query = new Query();
		switch(userChc){ //query sub menu
			case 1: userChc = userOptions(queryBP);
				query.outputQuery(userChc-1, "players born in ", userChcMain, queryBP);
				break;
			case 2: userChc = userOptions(queryShoots);
				query.outputQuery(userChc-1, "skaters who shoot ", userChcMain, queryShoots);
				break;
			case 3: userChc = userOptions(queryPositions);
				if(userChc == 1){
					userChc = userOptions(queryForwardPos);
					query.outputQuery(userChc-1, "", userChcMain, queryForwardPos);
				}
				else{
					query.outputQuery(userChc-1, "", userChcMain, queryPositions);
				}
				break;
			case 4: System.out.print("  You selected: EXIT");
				System.exit(0);
				break;
			default: userQueryOptions(); //reload menu bc invalid selection
				break;	
		}
	}

}

///////////////////////////////////////////////////////////////////////////////////////////////////
public class HockeyDataSetWizardV8{
	//main method
	public static void main(String... args){
		Output output = new Output();
		output.mainMenu();
	}
}