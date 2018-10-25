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
	private final String[] HPVARS = {"Name", "Position", "Birthplace"};
	private int sortHPBy;
	
	//constructors
	public HockeyPlayer(String lastName, String position, String birthplace, int... stats){
		this.lastName = lastName;
		this.position = position;
		this.birthplace = birthplace;
		this.stats = stats;
	}
	
	public HockeyPlayer(int sortHPBy){
		this(" ", " ", " ");
		this.sortHPBy = sortHPBy;
		this.setStats(this.stats);
	}
	
	public HockeyPlayer(ArrayList<HockeyPlayer> sk, ArrayList<HockeyPlayer> g){
		this(" ", " ", " ");	
		this.setStats(this.stats);
		roster = new ArrayList<HockeyPlayer>();
		setRoster(sk, g);
	}
	
	public HockeyPlayer(int sortHPBy, ArrayList<HockeyPlayer> sk, ArrayList<HockeyPlayer> g){
		this(" ", " ", " ");
		roster = new ArrayList<HockeyPlayer>();
		this.sortHPBy = sortHPBy;
		setRoster(sk, g);
		setRosterSorted();
	}
	
	public HockeyPlayer(HockeyPlayer hp){
		this(hp.lastName, hp.position, hp.birthplace, hp.stats);
		roster = new ArrayList<HockeyPlayer>();
	}
	
	/**
	* a method that defines how to sort Goalies numerically by a stat
	* @param Player the player to which we are comparing
	*/
	@Override
    	public int compareTo(HockeyPlayer other) {
    	if(this.getStats().length == 3){
		switch(sortHPBy){
		case 1:
			if (this.getStats()[0] < other.getStats()[0]) {
				return -1;
			}
			if (this.getStats()[0] == other.getStats()[0]) { 
				return 0;
			}
			break;
		case 2:
			if (this.getStats()[1] < other.getStats()[1]) {
				return -1;
			}
			if (this.getStats()[1] == other.getStats()[1]) { 
				return 0;
			}
			break;
		case 3:
			if (this.getStats()[2] < other.getStats()[2]) {
				return -1;
			}
			if (this.getStats()[2] == other.getStats()[2]) { 
				return 0;
			}
			break;
		}
    	}
    	else{
	switch(sortHPBy){
		case 1:
			if (this.getStats()[0] < other.getStats()[0]) {
				return -1;
			}
			if (this.getStats()[0] == other.getStats()[0]) { 
				return 0;
			}
			break;
		case 2:
			if (this.getStats()[1] < other.getStats()[1]) {
				return -1;
			}
			if (this.getStats()[1] == other.getStats()[1]) { 
				return 0;
			}
			break;
		case 3:
			if (this.getStats()[2] < other.getStats()[2]) {
				return -1;
			}
			if (this.getStats()[2] == other.getStats()[2]) { 
				return 0;
			}
			break;
		case 4:
			if (this.getStats()[3] < other.getStats()[3]) {
				return -1;
			}
			if (this.getStats()[3] == other.getStats()[3]) { 
				return 0;
			}
			break;
		}
    	}
    	return 1;
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
	
	public void setRoster(ArrayList<HockeyPlayer> sk, ArrayList<HockeyPlayer> g){
		roster.addAll(sk);
		roster.addAll(g);
	}
	
	public void setRosterSorted(){
		rosterSorted = new ArrayList<HockeyPlayer>(getRoster());
		Collections.sort(rosterSorted);
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
	
	public String[] getHPVARS(){
		return HPVARS;	
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
		super(" ", " ", " ");
		setRoster();
		this.sortGoaliesBy = sortGoaliesBy;
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
	}
	
	public void setRosterSorted(){
		rosterSorted = new ArrayList<HockeyPlayer>(getRoster());
		Collections.sort(rosterSorted);
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
		rosterBP.add(getRoster().get(0).getBirthplace());
		for(int i = 1; i< getRoster().size(); i++){
			if(rosterBP.contains(getRoster().get(i).getBirthplace()) == false){
				rosterBP.add(getRoster().get(i).getBirthplace());	
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
		//right = 0
		//left = 1
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
		rosterBP.add(getRoster().get(0).getBirthplace());
		for(int i = 1; i< getRoster().size(); i++){
			if(rosterBP.contains(getRoster().get(i).getBirthplace()) == false){
				rosterBP.add(getRoster().get(i).getBirthplace());	
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
		//right = 0
		//left = 1
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
abstract class Output{
	//gives user option of displaying/sorting/querying stats
	public static int statsWizard(){
		final String [] OPTIONS = {"Display Roster", "Sort Stats", "Query Players"};
		System.out.println("\n**********************************************************************************");
		System.out.println("WELCOME TO 2017-2018 WASHINGTON CAPITALS' (SOME) REGULAR SEASON STATS WIZARD!");
		System.out.println("\nSelect an option: ");
		for(int i = 0; i < OPTIONS.length; i++){
			System.out.println((i+1) + "). " + OPTIONS[i]);	
		}
		System.out.println("\n" + (OPTIONS.length + 1) + "). " + "Exit");
		System.out.println("\n*********************************************");
		
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter selection: ");
			int userChoice = Integer.parseInt(reader.readLine());	
			if(userChoice < (OPTIONS.length + 1)){
				System.out.println("  You selected:  " + OPTIONS[userChoice - 1]);
				return userChoice;
			}
			else if(userChoice == (OPTIONS.length + 1)){
				System.out.print("  You selected: EXIT");
				System.exit(0);
			}
			else{
				Output.statsWizard(); //reload menu bc invalid selection	
			}
		}
		catch(Exception e){
			System.out.println("oh noz, there is an Exception: " + e + "\nTry again!");
			statsWizard();
		}
		return 1;
	}
	
	public static void mainMenu(){
		int choice = statsWizard();
		if(choice == 1){
			Display.printRosterDetails();
			mainMenu();
		}
		else if(choice == 2){
			Sort.userSortOptions();
		}
		else{
			Query.userQueryOptions();
			mainMenu();
		}
	}
	
	public static void printSkaterStats(HockeyPlayer skater){
		System.out.println("Position: " + skater.getPosition() + "\tGoals: " + skater.getStats()[0] + "\tAssists: " + skater.getStats()[1] + "\tPoints: " + skater.getStats()[2] + "\t+/-: " + skater.getStats()[3] + "\t\tName: " + skater.getLastName());	
	}
	
	public static void printGoalieStats(HockeyPlayer goalie){
		System.out.println("Position: " + goalie.getPosition() + "\tShots Against: " + goalie.getStats()[0] + "\tGoals Against: " + goalie.getStats()[1] + "\tSaves: " + goalie.getStats()[2] + "\t\tName: " + goalie.getLastName());	
	}
	
}

///////////////////////////////////////////////////////////////////////////////////////////////////
class Display{
	//ouputs skaters (forward and defense) and goalies and some of their stats in a predetermined order
	public static void printRoster(){
		Forward f = new Forward();
		Defense d = new Defense();
		Skaters sk = new Skaters(f.getRoster(), d.getRoster());
		Goalies g = new Goalies();
		HockeyPlayer hp = new HockeyPlayer(sk.getRoster(), g.getRoster());
		for(HockeyPlayer player : hp.getRoster()){
			if(player.getStats().length == 5){
				Output.printSkaterStats(player);	
			}
			else{
				Output.printGoalieStats(player);
			}
		}
	}
	
	//outputs all players and some of their stats in a predetermined order, then loads main menu of user options
	public static void printRosterDetails(){
		System.out.println("*****************************************************************************");
		System.out.println("THE 2017-2018 REGULAR SEASON ROSTER AND SOME OF THEIR STATS:\n");
		Display display = new Display();
		printRoster();
		Output.mainMenu();
	}	
}

///////////////////////////////////////////////////////////////////////////////////////////////////
class Sort{
	//fields
	private static int userMin;
	private static int userMax;
	private static int rosterMin;
	private static int rosterMax;
	private int userChoice;
	
	//setters
	public void setRosterMin(boolean b, int statChoice){
		if(b == true){
			Goalies g = new Goalies (statChoice);
			switch(statChoice){
				case 1: rosterMin = g.getRosterSorted().get(0).getStats()[0];
					break;
				case 2: rosterMin = g.getRosterSorted().get(0).getStats()[1];
					break;
				case 3: rosterMin = g.getRosterSorted().get(0).getStats()[2];
					break;	
			}	
		}
		else{
			Forward f = new Forward();
			Defense d = new Defense();
			Skaters s = new Skaters(statChoice, f.getRoster(), d.getRoster());
			switch(statChoice){
				case 1: rosterMin = s.getRosterSorted().get(0).getStats()[0];
					break;
				case 2: rosterMin = s.getRosterSorted().get(0).getStats()[1];
					break;
				case 3: rosterMin = s.getRosterSorted().get(0).getStats()[2];
					break;	
				case 4: rosterMin = s.getRosterSorted().get(0).getStats()[3];
					break;		
			}
		}
	}
		
	public void setRosterMax(boolean b, int statChoice){
		if(b == true){
			Goalies g = new Goalies(statChoice);
			switch(statChoice){
				case 1: rosterMax = g.getRosterSorted().get(g.getRosterSorted().size() - 1).getStats()[0];
					break;
				case 2: rosterMax = g.getRosterSorted().get(g.getRosterSorted().size() - 1).getStats()[1];
					break;
				case 3: rosterMax = g.getRosterSorted().get(g.getRosterSorted().size() - 1).getStats()[2];
					break;	
			}
		}
		else{
			Forward f = new Forward();
			Defense d = new Defense();
			Skaters s = new Skaters(statChoice, f.getRoster(), d.getRoster());
			switch(statChoice){
				case 1: rosterMax = s.getRosterSorted().get(s.getRosterSorted().size() - 1).getStats()[0];
					break;
				case 2: rosterMax = s.getRosterSorted().get(s.getRosterSorted().size() - 1).getStats()[1];
					break;
				case 3: rosterMax = s.getRosterSorted().get(s.getRosterSorted().size() - 1).getStats()[2];
					break;	
				case 4: rosterMax = s.getRosterSorted().get(s.getRosterSorted().size() - 1).getStats()[3];
					break;		
			}
		}
	}
	
	public void setUserChoice(){	
		try{
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				userChoice = Integer.parseInt(reader.readLine());	
		}
		catch(Exception e){
				System.out.println("oh noz, there is an Exception: " + e + "\nTry again!");
				setUserChoice();
		}
	}
	
	//getters
	public int getRosterMin(){
		return rosterMin;
	}
	
	public int getRosterMax(){
		return rosterMax;
	}
	
	public int getUserChoice(){
		return userChoice;	
	}
	
	//method to display to user available sort options
	public static ArrayList<String> sortOptions(String... options){
		ArrayList<String> sortOpts = new ArrayList<String>(Arrays.asList(options));
		System.out.println("Select from these options: ");
		for(int i = 0; i<sortOpts.size(); i++){
			System.out.println((i+1) + "). " + sortOpts.get(i));
		}
		return sortOpts;
	}
	
	//method to (i) show user statMin and statMax, and (ii) get sort bounds from user
	public static void showGetMinMax(String type, String stat, int statMin, int statMax){
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
			System.out.println("oh noz, there is an Exception: " + e + "\nTry again!");
			showGetMinMax(type, stat, statMin, statMax);
		}
	}
	
	//method to output user-defined sorted(some/all) goalie stats
	public void outputSortedGoalieStat(int statChoice, ArrayList<Goalies> sortedRoster){
		for(Goalies g : sortedRoster){
			switch(statChoice){
				case 1: if(g.getStats()[0] >= userMin){
					if(g.getStats()[0] <= userMax){
						Output.printGoalieStats(g);	
					}
					}
					break;
				case 2: if(g.getStats()[1] >= userMin){
					if(g.getStats()[1] <= userMax){
						Output.printGoalieStats(g);						
					}
					}
					break;
				case 3:  if(g.getStats()[2] >= userMin){
					if(g.getStats()[2] <= userMax){
						Output.printGoalieStats(g);	
					}
					}
					break;	
			}		
		}	
	}
	
	//method to output user-defined sorted(some/all) skater stats
	public void outputSortedSkaterStat(int statChoice, ArrayList<Skaters> sortedRoster){
		for(Skaters s : sortedRoster){
			switch(statChoice){
				case 1: if(s.getGoals() >= userMin){
					if(s.getGoals() <= userMax){
						Output.printSkaterStats(s);	
					}
					}
					break;
				case 2: if(s.getAssists() >= userMin){
					if(s.getAssists() <= userMax){
						Output.printSkaterStats(s);	
					}
					}
					break;
				case 3:  if(s.getPoints() >= userMin){
					if(s.getPoints() <= userMax){
						Output.printSkaterStats(s);	
					}
					}
					break;	
				case 4:  if(s.getPlusMinus() >= userMin){
					if(s.getPlusMinus() <= userMax){
						Output.printSkaterStats(s);	
					}
					}
					break;	
			}		
		}
	}
	
	public static ArrayList<String> usersSortChoices(String... sortChoices){
		ArrayList<String> sOpt = sortOptions(sortChoices);	
		return sOpt;
	}
	
	public static void sortTypes(String playerType, String... sortChoices){
		System.out.println("  You selected: Sort Washington Capitals' " + playerType.toUpperCase() + " Stats");
		usersSortChoices(sortChoices);
		System.out.print("Enter selection:  ");
	}
	/*
	public static void sortGoalieStats(){
		Sort sort = new Sort();
		sortTypes("goalie", "Shots Against", "Goals Against", "Saves");
		sort.setUserChoice();
		Goalies gl = new Goalies(sort.userChoice);
		sort.setRosterMin(true, sort.userChoice);
		sort.setRosterMax(true, sort.userChoice);
		showGetMinMax( "goalies", usersSortChoices("Shots Against", "Goals Against", "Saves").get(sort.userChoice - 1), rosterMin, rosterMax);
		sort.outputSortedGoalieStat(sort.userChoice, gl.getRosterSorted());
		System.out.println("**************************************************************************************");
	}	
	
	public static void sortSkaterStats(){
		Sort sort = new Sort();
		sortTypes("skater", "Goals", "Assists", "Points", "+/-");
		sort.setUserChoice();
			Forward f = new Forward();
			Defense d = new Defense();
			Skaters sk = new Skaters(sort.userChoice, f.getRoster(), d.getRoster());
			sort.setRosterMin(false, sort.userChoice);
			sort.setRosterMax(false, sort.userChoice);
			showGetMinMax( "skaters", usersSortChoices("Goals", "Assists", "Points", "+/-").get(sort.userChoice - 1), rosterMin, rosterMax);
			sort.outputSortedSkaterStat(sort.userChoice, sk.getRosterSorted());
			System.out.println("**************************************************************************************");
	}
	*/
	//a method that outputs a menu to select type of sorted stats to display on screen
	public static void userSortOptions(){
		Sort sort = new Sort();
		System.out.println();
		System.out.println("**********************************************************************************");
		System.out.println("\nSelect Stats to Sort:\n1.) Goalie \n2.) Skater\n\n3.) Exit");
		System.out.println("\n*********************************************");
		System.out.print("Enter selection: ");
		sort.setUserChoice();
		System.out.println();
		switch(sort.userChoice){
			case 1: System.out.println("Sort Goalie Stats Coming Soon"); 
				//sortGoalieStats();
				break;
			case 2: System.out.println("Sort Skater Stats Coming Soon"); 
				//sortSkaterStats();
				break;
			case 3: System.out.print("  You selected: EXIT");
				System.exit(0);
				break;
			default: userSortOptions(); //reload menu bc invalid selection
				break;	
		}
		Output.mainMenu();
	}
	
}

///////////////////////////////////////////////////////////////////////////////////////////////////
class Query{
	
	//this method returns an array of all of the players' birthplaces
	public static String[] BPQueryOptions(){
		ArrayList<String> bpQueryOptions = new ArrayList<String>();
		Defense d = new Defense();
		Forward f = new Forward();
		Skaters s = new Skaters(f.getRoster(), d.getRoster());
		s.setRosterBP();
		Goalies qg = new Goalies();
		bpQueryOptions = s.getRosterBP();
		for(int i = 0; i<qg.getRosterBP().size(); i++){
			if(bpQueryOptions.contains(qg.getRosterBP().get(i)) == false){
				bpQueryOptions.add(qg.getRosterBP().get(i));	
			}
		}
		Collections.sort(bpQueryOptions);
		String[] bpQO = bpQueryOptions.toArray(new String[0]);
		return bpQO;
	}
	
	//method to display to user available query options
	public static ArrayList<String> queryOptions(String... options){
		ArrayList<String> queryOpts = new ArrayList<String>(Arrays.asList(options));
		System.out.println("Select from these options: ");
		for(int i = 0; i< queryOpts.size(); i++){
			System.out.println((i+1) + "). " + queryOpts.get(i));	
		}
		return queryOpts;
	}
	
	//method to display birthplace query of players by country
	public void outputQueryBP(String country){
		System.out.println("\n***********************************************************************");
		System.out.println("RESULTS:\nPlayers born in " + country + " are:");
						
		int counter = 0;
		Defense de = new Defense();
		Forward fo = new Forward();
		Skaters skate = new Skaters(fo.getRoster(), de.getRoster());
		Goalies goalie = new Goalies();
		HockeyPlayer hp = new HockeyPlayer(skate.getRoster(), goalie.getRoster());
		for(HockeyPlayer player : hp.getRoster()){
			if(player.getBirthplace() == country ){
				System.out.println("\t" + player.getLastName());	
				counter++;
			}
		}
		System.out.println("\nTOTAL: " + counter + " players were born in " + country + ".");
		System.out.println("***********************************************************************");
	}
	
	//method to display shoots query of skaters by left or right
	public void outputQueryShoots(int leftOrRight){
		String leftRT = "";
		if(leftOrRight == 0){
			leftRT = "Right";
		}
		else{
			leftRT = "Left";	
		}
		System.out.println("\n***********************************************************************");
		System.out.println("RESULTS:\nSkaters who shoot " + leftRT + " are:");
		int counter = 0;
		Defense d = new Defense();
		Forward f = new Forward();
		Skaters skate = new Skaters(f.getRoster(), d.getRoster());
		Goalies goalie = new Goalies();
		HockeyPlayer hp = new HockeyPlayer(skate.getRoster(), goalie.getRoster());
			for(HockeyPlayer player : hp.getRoster()){
				if(player.getStats().length == 5){
					if(player.getStats()[4] == leftOrRight){
						System.out.println("\t" + player.getLastName());	
						counter++;
					}
				}
			}
		System.out.println("\nTOTAL: " + counter + " skaters shoot " + leftRT + ".");
		System.out.println("***********************************************************************");
	}
	
	//method to display position type query of forwards (lw/c/rw), defense, goalies
	public void outputQueryPositionType(String forwardPosition){
		System.out.println("\n***********************************************************************");
		System.out.println("RESULTS:\n" + forwardPosition + " are:");
		int counter = 0;
		Defense d = new Defense();
		Forward f = new Forward();
		Skaters skate = new Skaters(f.getRoster(), d.getRoster());
		Goalies goalie = new Goalies();
		HockeyPlayer hp = new HockeyPlayer(skate.getRoster(), goalie.getRoster());
			for(HockeyPlayer player : hp.getRoster()){
				if(player.getPosition().contains(forwardPosition)){
					System.out.println("\t" + player.getLastName());	
					counter++;
				}
			}
		System.out.println("\nTOTAL: " + counter + " " + forwardPosition + ". ");
		System.out.println("***********************************************************************");
	}

	//a method that outputs a menu to select which queried detail to display on screen
	public static void userQueryOptions(){
		System.out.println();
		System.out.println("**********************************************************************************");
		System.out.println("\nSelect a detail to query:\n1.) Birthplace \n2.) Shoots \n3.) Position\n\n4.) Exit");
		System.out.println("\n*********************************************");
		Query query = new Query();
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter selection: ");
			int userChoice = Integer.parseInt(reader.readLine()); /////////////////
			System.out.println();
				switch(userChoice){
					case 1: String[] bpQueryOpts = BPQueryOptions();
						System.out.println("  You selected: Query Player Birthplace");
						ArrayList<String> bp = queryOptions(bpQueryOpts);
						System.out.print("Enter selection:  ");
						int userBPSelection = Integer.parseInt(reader.readLine()); /////////////////
						query.outputQueryBP(bp.get(userBPSelection-1));
						break;
					case 2: System.out.println("  You selected: Query Skater Shoots");
						ArrayList<String> lr = queryOptions("R", "L");
						System.out.print("Enter selection:  ");
						int userShootsSelection = Integer.parseInt(reader.readLine()); /////////////////
						query.outputQueryShoots(userShootsSelection - 1);
						break;
					case 3: System.out.println("  You selected: Query Player Position");
						System.out.println("  Coded Functionality Coming Soon");
						
						queryOptions("Fowards", "Defense", "Goalies");
						System.out.print("Enter selection:  ");
						int userPositionSelection = Integer.parseInt(reader.readLine());/////////////////
						switch(userPositionSelection){
							case 1: System.out.println("  You selected: Query Forwards");
								ArrayList<String> alcr = queryOptions("Forward", "Forward, LW", "Forward, C", "Forward, RW");
								System.out.print("Enter selection:  ");
								int userForwardSelection = Integer.parseInt(reader.readLine());
									query.outputQueryPositionType(alcr.get(userForwardSelection-1));
								break;
							case 2: query.outputQueryPositionType("Defense");
								break;
							case 3: query.outputQueryPositionType("Goalie");
								break;
							default: queryOptions("Fowards", "Defense", "Goalies"); //reload menu bc invalid selection
							break;
						} 
						
						break;
					case 4: System.out.print("  You selected: EXIT");
						System.exit(0);
						break;
					default: userQueryOptions(); //reload menu bc invalid selection
						break;	
				}
				Output.mainMenu();
		}
		catch(Exception e){
			System.out.println("oh noz, there is an Exception: " + e + "\nTry again!");
			userQueryOptions();
		}
	}
	
}

///////////////////////////////////////////////////////////////////////////////////////////////////
public class HockeyDataSetWizardV6{
	//main method
	public static void main(String... args){
		Output.mainMenu();
	}
}