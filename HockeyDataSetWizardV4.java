import java.util.*;
import java.io.*;
import java.time.*;

///////////////////////////////////////////////////////////////////////////////////////////////////
class HockeyPlayer{
	//fields
	private String lastName;
	private String position;
	private String birthplace;
	private final String[] HPVARS = {"Name", "Position", "Birthplace"};
	
	//constructors
	public HockeyPlayer(String lastName, String position, String birthplace){
		this.lastName = lastName;
		this.position = position;
		this.birthplace = birthplace;
	}
	
	public HockeyPlayer(HockeyPlayer hp){
		this(hp.lastName, hp.position, hp.birthplace);
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
	
	public String[] getHPVARS(){
		return HPVARS;	
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////
class Goalies extends HockeyPlayer implements Comparable<Goalies>{
	//fields 
	private int shotsAgainst;
	private int goalsAgainst;
	private int saves;
	private final String[] GOALIEVARS = {"Shots Against", "Goals Against", "Saves"};
	private ArrayList<Goalies> goaliesRoster;
	private ArrayList<Goalies> goaliesSorted;
	private ArrayList<String> queryGoaliesBP;
	private static int sortGoaliesBy;
	
	//constructors
	public Goalies(){
		super(" ", " ", " ");
		setGoaliesRoster();	
		setQueryGoaliesBP();
	}
	
	public Goalies(int sortGoaliesBy){
		super(" ", " ", " ");
		setGoaliesRoster();
		this.sortGoaliesBy = sortGoaliesBy;
		setGoaliesSorted();
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
	
	/**
	* a method that defines how to sort Goalies numerically by a stat
	* @param Player the player to which we are comparing
	*/
	@Override
    	public int compareTo(Goalies other) {
    	switch(sortGoaliesBy){
    	case 1:
    		if (this.getShotsAgainst() < other.getShotsAgainst()) {
    			return -1;
    		}
    		if (this.getShotsAgainst() == other.getShotsAgainst()) { 
    			return 0;
    		}
    		break;
   	case 2:
    		if (this.getGoalsAgainst() < other.getGoalsAgainst()) {
    			return -1;
    		}
    		if (this.getGoalsAgainst() == other.getGoalsAgainst()) { 
    			return 0;
    		}
    		break;
    	case 3:
    		if (this.getSaves() < other.getSaves()) {
    			return -1;
    		}
    		if (this.getSaves() == other.getSaves()) { 
    			return 0;
    		}
    		break;
    	}
    	return 1;
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
	
	public void setGoaliesRoster(){
		goaliesRoster = new ArrayList<Goalies>();
		goaliesRoster.add(new Goalies("Holtby", "Goalie", "Canada", 1648, 153, 1495));
		goaliesRoster.add(new Goalies("Grubauer", "Goalie", "Germany", 953, 73, 880));
	}
	
	public void setGoaliesSorted(){
		goaliesSorted = new ArrayList<Goalies>(getGoaliesRoster());
		Collections.sort(goaliesSorted);
	}
	
	public void setQueryGoaliesBP(){
		queryGoaliesBP = new ArrayList<String>();
		queryGoaliesBP.add(getGoaliesRoster().get(0).getBirthplace());
		for(int i = 1; i< getGoaliesRoster().size(); i++){
			if(queryGoaliesBP.contains(getGoaliesRoster().get(i).getBirthplace()) == false){
				queryGoaliesBP.add(getGoaliesRoster().get(i).getBirthplace());	
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
	
	public ArrayList<Goalies> getGoaliesRoster(){
		return goaliesRoster;	
	}
	
	public ArrayList<Goalies> getGoaliesSorted(){
		return goaliesSorted;	
	}
	
	public ArrayList<String> getQueryGoaliesBP(){
		return queryGoaliesBP;	
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////
class Skaters extends HockeyPlayer implements Comparable<Skaters>{
	//fields
	private int goals;
	private int assists;
	private int points;
	private int plusMinus;
	private String shoots;
	private final String[] SKATERVARS = {"Goals", "Assists", "Points", "+/-", "Shoots"};
	private ArrayList<Skaters> skatersRoster;
	private ArrayList<Skaters> skatersSorted;
	private ArrayList<String> querySkatersBP;
	private static int sortSkatersBy;
	
	//constructors
	public Skaters(){
		super(" ", " ", " ");	
		skatersRoster = new ArrayList<Skaters>();
	}
	
	public Skaters(ArrayList<Skaters> f, ArrayList<Skaters> d){
		super(" ", " ", " ");	
		skatersRoster = new ArrayList<Skaters>();
		setSkatersRoster(f, d);
	}
	
	public Skaters(int sortSkatersBy, ArrayList<Skaters> f, ArrayList<Skaters> d){
		super(" ", " ", " ");
		skatersRoster = new ArrayList<Skaters>();
		this.sortSkatersBy = sortSkatersBy;
		setSkatersRoster(f, d);
		setSkatersSorted();
	}
	
	public Skaters(String lastName, String position, String birthplace, int goals, int assists, int points, int plusMinus, String shoots){
		super(lastName, position, birthplace);
		this.goals = goals;
		this.assists = assists;
		this.points = points;
		this.plusMinus = plusMinus;
		this.shoots = shoots;
	}
	
	public Skaters(HockeyPlayer hp, int goals, int assists, int points, int plusMinus, String shoots){
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
	
	/**
	* a method that defines how to sort Skaters numerically by a stat
	* @param Skater the skater to which we are comparing
	*/
	@Override
    	public int compareTo(Skaters other) {
    	switch(sortSkatersBy){
    	case 1:
    		if (this.getGoals() < other.getGoals()) {
    			return -1;
    		}
    		if (this.getGoals() == other.getGoals()) { 
    			return 0;
    		}
    		break;
   	case 2:
    		if (this.getAssists() < other.getAssists()) {
    			return -1;
    		}
    		if (this.getAssists() == other.getAssists()) { 
    			return 0;
    		}
    		break;
    	case 3:
    		if (this.getPoints() < other.getPoints()) {
    			return -1;
    		}
    		if (this.getPoints() == other.getPoints()) { 
    			return 0;
    		}
    		break;
    	case 4:
    		if (this.getPlusMinus() < other.getPlusMinus()) {
    			return -1;
    		}
    		if (this.getPlusMinus() == other.getPlusMinus()) { 
    			return 0;
    		}
    		break;
    	}
    	return 1;
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
	
	public void setShoots(String shoots){
		this.shoots = shoots;
	}
	
	public void setSkatersRoster(ArrayList<Skaters> d, ArrayList<Skaters> f){
		skatersRoster.addAll(d);
		skatersRoster.addAll(f);
	}
	
	public void setSkatersSorted(){
		skatersSorted = new ArrayList<Skaters>(getSkatersRoster());
		Collections.sort(skatersSorted);
	}
	
	public void setQuerySkatersBP(){
		querySkatersBP = new ArrayList<String>();
		querySkatersBP.add(getSkatersRoster().get(0).getBirthplace());
		for(int i = 1; i< getSkatersRoster().size(); i++){
			if(querySkatersBP.contains(getSkatersRoster().get(i).getBirthplace()) == false){
				querySkatersBP.add(getSkatersRoster().get(i).getBirthplace());	
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
	
	public String getShoots(){
		return shoots;	
	}
	
	public String[] getSKATERVARS(){
		return SKATERVARS;	
	}
	
	public ArrayList<Skaters> getSkatersRoster(){
		return skatersRoster;	
	}
	
	public ArrayList<Skaters> getSkatersSorted(){
		return skatersSorted;	
	}
	
	public ArrayList<String> getQuerySkatersBP(){
		return querySkatersBP;	
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////
class Defense extends Skaters{
	//fields
	private ArrayList<Skaters> defenseRoster;
	private ArrayList<String> queryDefenseBP;
	
	//constructors
	public Defense(){
		setDefenseRoster();
		setQueryDefenseBP();
	}
	
	public Defense(String lastName, String position, String birthplace, int goals, int assists, int points, int plusMinus, String shoots){
		super(lastName, position, birthplace, goals, assists, points, plusMinus, shoots);
	}
	
	public Defense(Skaters skater){
		super(skater);	
	}
	
	//setters
	public void setDefenseRoster(){
		defenseRoster = new ArrayList<Skaters>();
		defenseRoster.add(new Skaters("Carlson", "Defense", "USA", 15, 53, 68, 0, "R"));
		defenseRoster.add(new Skaters("Orlov", "Defense", "Russia", 10, 21, 31, 10, "L"));
		defenseRoster.add(new Skaters("Niskanen", "Defense", "USA", 7, 22, 29, 24, "R"));
		defenseRoster.add(new Skaters("Djoos", "Defense", "Sweden", 3, 11, 14, 13, "L"));
		defenseRoster.add(new Skaters("Bowey", "Defense", "Canada", 0, 12, 12, -3, "R"));
		defenseRoster.add(new Skaters("Orpik", "Defense", "USA", 0, 10, 10, -9, "L"));
		defenseRoster.add(new Skaters("Chorney", "Defense", "Canada", 1, 3, 4, 8, "L"));
		defenseRoster.add(new Skaters("Jerabek", "Defense", "Czech Republic", 1, 3, 4, -1, "L"));
		defenseRoster.add(new Skaters("Kempny", "Defense", "Czech Republic", 2, 1, 3, 1, "L"));
		defenseRoster.add(new Skaters("Ness", "Defense", "USA", 0, 1, 1, 2, "L"));
	}
	
	public void setQueryDefenseBP(){
		queryDefenseBP = new ArrayList<String>();
		queryDefenseBP.add(getDefenseRoster().get(0).getBirthplace());
		for(int i = 1; i< getDefenseRoster().size(); i++){
			if(queryDefenseBP.contains(getDefenseRoster().get(i).getBirthplace()) == false){
				queryDefenseBP.add(getDefenseRoster().get(i).getBirthplace());	
			}
		}
	}
	
	//getters
	public ArrayList<Skaters> getDefenseRoster(){
		return defenseRoster;	
	}
	
	public ArrayList<String> getQueryDefenseBP(){
		return queryDefenseBP;	
	}
	
}

///////////////////////////////////////////////////////////////////////////////////////////////////
class Forward extends Skaters{
	//fields
	String forwardPosition;
	private final String[] FORWARDVARS = {"Forward Position"};
	private ArrayList<Skaters> forwardRoster;
	private ArrayList<String> queryForwardBP;
	
	//constructors
	public Forward(){
		setForwardRoster();
		setQueryForwardBP();
	}
	
	public Forward(String lastName, String position, String birthplace, int goals, int assists, int points, int plusMinus, String shoots, String forwardPosition){
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
	
	public void setForwardRoster(){
		forwardRoster = new ArrayList<Skaters>();
		forwardRoster.add(new Skaters("Ovechkin", "Forward, LW", "Russia", 49, 38, 87, 3, "R"));
		forwardRoster.add(new Skaters("Vrana", "Forward, LW", "Czech Republic", 13, 14, 27, 2, "L"));
		forwardRoster.add(new Skaters("Gersich", "Forward, LW", "USA", 0, 1, 1, -1, "L"));
		forwardRoster.add(new Skaters("Walker", "Forward, LW", "Wales", 1, 0, 1, 1, "L"));
		forwardRoster.add(new Skaters("Burakovsky", "Forward, LW", "Austria", 12, 13, 25, 3, "L"));
		forwardRoster.add(new Skaters("Kuznetsov", "Forward, C", "Russia", 27, 56, 83, 3, "L"));
		forwardRoster.add(new Skaters("Backstrom", "Forward, C", "Sweden", 21, 50, 71, 5, "L"));
		forwardRoster.add(new Skaters("Graovac", "Forward, C", "Canada", 0, 0, 0, -3, "L"));
		forwardRoster.add(new Skaters("Boyd", "Forward, C", "USA", 0, 1, 1, 2, "R"));
		forwardRoster.add(new Skaters("O'Brien", "Forward, C", "Canada", 0, 0, 0, 0, "L"));
		forwardRoster.add(new Skaters("Eller", "Forward, C", "Denmark", 18, 20, 38, -6, "L"));
		forwardRoster.add(new Skaters("Stephenson", "Forward, C", "Canada", 6, 12, 18, 13, "L"));
		forwardRoster.add(new Skaters("Beagle", "Forward, C", "Canada", 7, 15, 22, 3, "R"));
		forwardRoster.add(new Skaters("Oshie", "Forward, RW", "USA", 18, 29, 47, 2, "R"));
		forwardRoster.add(new Skaters("Wilson", "Forward, RW", "Canada", 14, 21, 35, 10, "R"));
		forwardRoster.add(new Skaters("Connolly", "Forward, RW", "Canada", 15, 12, 27, -6, "R"));
		forwardRoster.add(new Skaters("Peluso", "Forward, RW", "Canada", 0, 0, 0, 0, "R"));
		forwardRoster.add(new Skaters("Smith-Pelly", "Forward, RW", "Canada", 7, 9, 16, -6, "R"));
		forwardRoster.add(new Skaters("Chiasson", "Forward, RW", "Canada", 9, 9, 18, 1, "R"));
	}
	
	public void setQueryForwardBP(){
		queryForwardBP = new ArrayList<String>();
		queryForwardBP.add(getForwardRoster().get(0).getBirthplace());
		for(int i = 1; i< getForwardRoster().size(); i++){
			if(queryForwardBP.contains(getForwardRoster().get(i).getBirthplace()) == false){
				queryForwardBP.add(getForwardRoster().get(i).getBirthplace());	
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
	
	public ArrayList<Skaters> getForwardRoster(){
		return forwardRoster;	
	}
	
	public ArrayList<String> getQueryForwardBP(){
		return queryForwardBP;	
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
	
	public static void printSkaterStats(Skaters skater){
		System.out.println("Position: " + skater.getPosition() + "\tGoals: " + skater.getGoals() + "\tAssists: " + skater.getAssists() + "\tPoints: " + skater.getPoints() + "\t+/-: " + skater.getPlusMinus() + "\t\tName: " + skater.getLastName());	
	}
	
	public static void printGoalieStats(Goalies goalie){
		System.out.println("Position: " + goalie.getPosition() + "\tShots Against: " + goalie.getShotsAgainst() + "\tGoals Against: " + goalie.getGoalsAgainst() + "\tSaves: " + goalie.getSaves() + "\t\tName: " + goalie.getLastName());	

	}
	
}

///////////////////////////////////////////////////////////////////////////////////////////////////
class Display{
	//ouputs skaters (forward and defense) and some of their stats in a predetermined order
	public void printSkaterRosterDetails(){
		Forward f = new Forward();
		Defense d = new Defense();
		Skaters sk = new Skaters(f.getForwardRoster(), d.getDefenseRoster());
		for(Skaters s : sk.getSkatersRoster()){
			Output.printSkaterStats(s);	
		}
	}
	
	//outputs goalies and some of their stats in a predetermined order
	public void printGoalieRosterDetails(){
		Goalies goalies = new Goalies();
		System.out.println();
		for(Goalies g : goalies.getGoaliesRoster()){
			Output.printGoalieStats(g);	
		}
	}
	
	//outputs all players and some of their stats in a predetermined order, then loads main menu of user options
	public static void printRosterDetails(){
		System.out.println("*****************************************************************************");
		System.out.println("THE 2017-2018 REGULAR SEASON ROSTER AND SOME OF THEIR STATS:\n");
		Display display = new Display();
		display.printSkaterRosterDetails();
		display.printGoalieRosterDetails();
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
	
	//setters
	public void setRosterMin(boolean b, int statChoice){
		if(b == true){
			Goalies g = new Goalies (statChoice);
			switch(statChoice){
				case 1: rosterMin = g.getGoaliesSorted().get(0).getShotsAgainst();
					break;
				case 2: rosterMin = g.getGoaliesSorted().get(0).getGoalsAgainst();
					break;
				case 3: rosterMin = g.getGoaliesSorted().get(0).getSaves();
					break;	
			}	
		}
		else{
			Forward f = new Forward();
			Defense d = new Defense();
			Skaters s = new Skaters(statChoice, f.getForwardRoster(), d.getDefenseRoster());
			switch(statChoice){
				case 1: rosterMin = s.getSkatersSorted().get(0).getGoals();
					break;
				case 2: rosterMin = s.getSkatersSorted().get(0).getAssists();
					break;
				case 3: rosterMin = s.getSkatersSorted().get(0).getPoints();
					break;	
				case 4: rosterMin = s.getSkatersSorted().get(0).getPlusMinus();
					break;		
			}
		}
	}
		
	public void setRosterMax(boolean b, int statChoice){
		if(b == true){
			Goalies g = new Goalies(statChoice);
			switch(statChoice){
				case 1: rosterMax = g.getGoaliesSorted().get(g.getGoaliesSorted().size() - 1).getShotsAgainst();
					break;
				case 2: rosterMax = g.getGoaliesSorted().get(g.getGoaliesSorted().size() - 1).getGoalsAgainst();
					break;
				case 3: rosterMax = g.getGoaliesSorted().get(g.getGoaliesSorted().size() - 1).getSaves();
					break;	
			}
		}
		else{
			Forward f = new Forward();
			Defense d = new Defense();
			Skaters s = new Skaters(statChoice, f.getForwardRoster(), d.getDefenseRoster());
			switch(statChoice){
				case 1: rosterMax = s.getSkatersSorted().get(s.getSkatersSorted().size() - 1).getGoals();
					break;
				case 2: rosterMax = s.getSkatersSorted().get(s.getSkatersSorted().size() - 1).getAssists();
					break;
				case 3: rosterMax = s.getSkatersSorted().get(s.getSkatersSorted().size() - 1).getPoints();
					break;	
				case 4: rosterMax = s.getSkatersSorted().get(s.getSkatersSorted().size() - 1).getPlusMinus();
					break;		
			}
		}
	}
	
	//getters
	public int getRosterMin(){
		return rosterMin;
	}
	
	public int getRosterMax(){
		return rosterMax;
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
	public void outputSortedGoalieStat(int statChoice, ArrayList<Goalies> sortedGoalies){
		for(Goalies g : sortedGoalies){
			switch(statChoice){
				case 1: if(g.getShotsAgainst() >= userMin){
					if(g.getShotsAgainst() <= userMax){
						Output.printGoalieStats(g);	
					}
					}
					break;
				case 2: if(g.getGoalsAgainst() >= userMin){
					if(g.getGoalsAgainst() <= userMax){
						Output.printGoalieStats(g);						
					}
					}
					break;
				case 3:  if(g.getSaves() >= userMin){
					if(g.getSaves() <= userMax){
						Output.printGoalieStats(g);	
					}
					}
					break;	
			}		
		}	
	}
	
	//method to output user-defined sorted(some/all) skater stats
	public void outputSortedSkaterStat(int statChoice, ArrayList<Skaters> sortedSkaters){
		for(Skaters s : sortedSkaters){
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
	//a method that outputs a menu to select type of sorted stats to display on screen
	public static void userSortOptions(){
		Sort sort = new Sort();
		System.out.println();
		System.out.println("**********************************************************************************");
		System.out.println("\nSelect Stats to Sort:\n1.) Goalie \n2.) Skater\n\n3.) Exit");
		System.out.println("\n*********************************************");
		
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter selection: ");
			int userChoice = Integer.parseInt(reader.readLine());	
			System.out.println();
				switch(userChoice){
					case 1: System.out.println("  You selected: Sort Washington Capitals' GOALIE Stats");
						ArrayList<String> sgs = sortOptions("Shots Against", "Goals Against", "Saves");
						System.out.print("Enter selection:  ");
						int userGoalieStatSelection = Integer.parseInt(reader.readLine());
						Goalies gl = new Goalies(userGoalieStatSelection);
						sort.setRosterMin(true, userGoalieStatSelection);
						sort.setRosterMax(true, userGoalieStatSelection);
						showGetMinMax( "goalies", sgs.get(userGoalieStatSelection - 1), rosterMin, rosterMax);
						sort.outputSortedGoalieStat(userGoalieStatSelection, gl.getGoaliesSorted());
						System.out.println("**************************************************************************************");
						break;
					case 2: System.out.println("  You selected: Sort Washington Capitals' SKATER Stats");
						ArrayList<String> gapp = sortOptions("Goals", "Assists", "Points", "+/-");
						System.out.print("Enter selection:  ");
						int userSkaterStatSelection = Integer.parseInt(reader.readLine());
						Forward f = new Forward();
						Defense d = new Defense();
						Skaters sk = new Skaters(userSkaterStatSelection, f.getForwardRoster(), d.getDefenseRoster());
						sort.setRosterMin(false, userSkaterStatSelection);
						sort.setRosterMax(false, userSkaterStatSelection);
						showGetMinMax( "skaters", gapp.get(userSkaterStatSelection - 1), rosterMin, rosterMax);
						sort.outputSortedSkaterStat(userSkaterStatSelection, sk.getSkatersSorted());
						System.out.println("**************************************************************************************");
						break;
					case 3: System.out.print("  You selected: EXIT");
						System.exit(0);
						break;
					default: userSortOptions(); //reload menu bc invalid selection
						break;	
				}
				Output.mainMenu();
		}
		catch(Exception e){
			System.out.println("oh noz, there is an Exception: " + e + "\nTry again!");
		userSortOptions();
		}
	}
	
}

///////////////////////////////////////////////////////////////////////////////////////////////////
class Query{
	
	//this method returns an array of all of the players' birthplaces
	public static String[] BPQueryOptions(){
		ArrayList<String> bpQueryOptions = new ArrayList<String>();
		Defense d = new Defense();
		Forward f = new Forward();
		Skaters s = new Skaters(f.getForwardRoster(), d.getDefenseRoster());
		s.setQuerySkatersBP();
		Goalies qg = new Goalies();
		bpQueryOptions = s.getQuerySkatersBP();
		for(int i = 0; i<qg.getQueryGoaliesBP().size(); i++){
			if(bpQueryOptions.contains(qg.getQueryGoaliesBP().get(i)) == false){
				bpQueryOptions.add(qg.getQueryGoaliesBP().get(i));	
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
	
	
	//a method that outputs a menu to select which queried detail to display on screen
	public static void userQueryOptions(){
		System.out.println();
		System.out.println("**********************************************************************************");
		System.out.println("\nSelect a detail to query:\n1.) Birthplace \n2.) Shoots \n3.) Position\n\n4.) Exit");
		System.out.println("\n*********************************************");
		
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter selection: ");
			int userChoice = Integer.parseInt(reader.readLine());	
			System.out.println();
				switch(userChoice){
					case 1: System.out.println("  You selected: Query Player Birthplace");
						String[] bpQueryOpts = BPQueryOptions();
						ArrayList<String> bp = queryOptions(bpQueryOpts);
						System.out.print("Enter selection:  ");
						int userBPSelection = Integer.parseInt(reader.readLine());
						System.out.println("\n***********************************************************************");
						System.out.println("RESULTS:\nPlayers born in " + bp.get(userBPSelection-1) + " are:");
						
						int counter = 0;
						Defense de = new Defense();
						Forward fo = new Forward();
						Skaters skate = new Skaters(fo.getForwardRoster(), de.getDefenseRoster());
						for(Skaters sk : skate.getSkatersRoster()){
							if(sk.getBirthplace() == bp.get(userBPSelection-1) ){
								System.out.println("\t" + sk.getLastName());	
								counter++;
							}
						}
						Goalies goal = new Goalies();
						for(Goalies go : goal.getGoaliesRoster()){
							if(go.getBirthplace() == bp.get(userBPSelection-1)){
								System.out.println("\t" + go.getLastName());
								counter++;
							}
						}
						System.out.println("\nTOTAL: " + counter + " players were born in " + bp.get(userBPSelection-1) + ".");
						System.out.println("***********************************************************************");
						
						break;
					case 2: System.out.println("  You selected: Query Skater Shoots");
						ArrayList<String> lr = queryOptions("L", "R");
						System.out.print("Enter selection:  ");
						int userShootsSelection = Integer.parseInt(reader.readLine());
						System.out.println("\n***********************************************************************");
						System.out.println("RESULTS:\nSkaters who shoot " + lr.get(userShootsSelection-1) + " are:");
						int counter1 = 0;
						Defense de1 = new Defense();
						Forward fo1 = new Forward();
						Skaters skate1 = new Skaters(fo1.getForwardRoster(), de1.getDefenseRoster());
						for(Skaters sk : skate1.getSkatersRoster()){
							if(sk.getShoots() == lr.get(userShootsSelection-1)){
								System.out.println("\t" + sk.getLastName());	
								counter1++;
							}
						}
						System.out.println("\nTOTAL: " + counter1 + " skaters shoot " + lr.get(userShootsSelection-1) + ".");
						System.out.println("***********************************************************************");
						break;
					case 3: System.out.println("  You selected: Query Player Position");
						queryOptions("Fowards", "Defense", "Goalies");
						System.out.print("Enter selection:  ");
						int userPositionSelection = Integer.parseInt(reader.readLine());
						switch(userPositionSelection){
							case 1: System.out.println("  You selected: Query Forwards");
								ArrayList<String> alcr = queryOptions("All", "LW", "C", "RW");
								int userForwardSelection = Integer.parseInt(reader.readLine());
								if(userForwardSelection == 1){
									System.out.println("RESULTS:\nFORWARDS:");
									Forward forward = new Forward();
									for(Skaters f : forward.getForwardRoster()){
										System.out.println("\t" + f.getLastName());	
									}
									System.out.println("\nTOTAL: " + forward.getForwardRoster().size() + " FORWARDS.");
									System.out.println("***********************************************************************");	
								}
								else{
									System.out.println("\n***********************************************************************");
									System.out.println("RESULTS:\n" + alcr.get(userForwardSelection-1) + " FORWARDS are:");
									int counterF = 0;
									Forward forward = new Forward();
									for(Skaters f : forward.getForwardRoster()){
										if(f.getPosition().indexOf(alcr.get(userForwardSelection-1)) != -1){
											System.out.println("\t" + f.getLastName());	
											counterF++;
										}
									}
									System.out.println("\nTOTAL: " + counterF + " " + alcr.get(userForwardSelection - 1) + " FORWARDS. ");
									System.out.println("***********************************************************************");
								}
								break;
							case 2: System.out.println("\n***********************************************************************");
								System.out.println("RESULTS:\nDEFENSE:");
								Defense defense = new Defense();
								for(Skaters d : defense.getDefenseRoster()){
									System.out.println("\t" + d.getLastName());	
									}
								System.out.println("\nTOTAL: " + defense.getDefenseRoster().size() + " DEFENSEMEN. ");
								System.out.println("***********************************************************************");
								break;
							case 3: System.out.println("\n***********************************************************************");
								System.out.println("RESULTS:\nGOALIES:");
								Goalies goalies = new Goalies();
								for(Goalies g : goalies.getGoaliesRoster()){
									System.out.println("\t" + g.getLastName());	
									}
								System.out.println("\nTOTAL: " + goalies.getGoaliesRoster().size() + " GOALIES. ");
								System.out.println("***********************************************************************");
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
public class HockeyDataSetWizardV4{
	//main method
	public static void main(String... args){
		Output.mainMenu();
	}
}