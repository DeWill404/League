import java.util.Scanner;

public class Main {
	private static Scanner sc = new Scanner(System.in);
	private static Manage m = new Manage();
	private static Sport sport;
	private static Player player;
	private static Team team, team1, team2;
	private static League league;

	// Function to check for valid integer input
	private static int validIntInput(int max) {
		int num;

		// Check for integer
		if (sc.hasNextInt()) {
			num = sc.nextInt();
			// check if in range of 1 to max
			if (num > 0 && num <= max) {
				sc.nextLine();  // Enter escape line
				return num;
			}
		}
		System.out.print("-> Please enter a valid number <-\n-> ");
		sc.nextLine();  // Enter escape line

		// If invalid, recall function
		return validIntInput(max);
	}

	// Function to check for valid string input
	private static String validStringInput() {
		String s = sc.nextLine();

		// Check if string is empty
		if (s.isBlank()) {
			System.out.print("-> Please enter a valid string <-\n-> ");
			return validStringInput(); // Recursive call, for invalid string
		}

		// If string is valid
		return s;
	}

	// Function to check for valid object
	private static Object validObject(String type) {
		// Input
		System.out.print("Enter the " + type + " name : ");
		String s2 = validStringInput();

		// Check for valid input
		switch (type) {
			case "sport":
				sport = m.isPresentInSport(s2);
				if (sport != null) { return sport; }
				m.listSport();
				break;
			case "player":
				sport = (Sport) validObject("sport");
				player = m.isPresentInPlayer(s2, sport);
				if (player != null) { return player; }
				m.listPlayer();
				break;
			case "team":
				sport = (Sport) validObject("sport");
				team = m.isTeamPresent(s2, sport);
				if (team != null) { return team; }
				m.listAllTeam();
				break;
			case "league":
				sport = (Sport) validObject("sport");
				league = m.isLeaguePresent(s2, sport);
				if (league != null) { return league; }
				m.listAllLeague();
				break;
		}

		// if invalid
		System.out.println("-> Enter valid "+type+" name <-");
		return validObject(type);
	}

	// DRIVER CODE
	public static void main(String[] args) {
		boolean flagA = true, flagB, flagC;
		int switch_var, t1, t2;
		String stringTemp;



		while (flagA) {
			// Menu and input and reset
			flagB = true;
			System.out.print("Enter\n\t1. Manage Sports \n\t2. Manage Players \n\t3. Manage Team \n\t4. Manage League \n\t5. Exit \n-> ");
			switch_var = validIntInput(5);

			switch (switch_var) {
				// To manage Sports
				case 1:
					while (flagB) {
						m.listSport();
						System.out.print("Enter\n\t1. Add \n\t2. Remove \n\t3. Back \n\t4. Exit \n-> ");
						switch_var = validIntInput(4);
						switch (switch_var) {
							// To add new sport
							case 1:
								System.out.print("Enter name of sport : ");
								stringTemp = validStringInput();
								if (m.addSport(stringTemp))
									System.out.println(stringTemp + " is successfully add to list.");
								else
									System.out.println(stringTemp + " is already present in list.");
								break;
							// To delete delete
							case 2:
								System.out.print("Enter name of sport : ");
								stringTemp = validStringInput();
								if (m.deleteSport(stringTemp))
									System.out.println(stringTemp + " is successfully deleted to list.");
								else
									System.out.println(stringTemp + " is not present in list.");
								break;
							// To Go back
							case 4:
								flagA = false;
								// To Exit
							case 3:
								flagB = false;
								break;
						}
					}

				// To Manage Players
				case 2:
					if (m.isSportEmpty()) {
						System.out.println("Please enter some sports first....");
						flagB = false;
					}
					while (flagB) {
						// Menu and input and reset
						System.out.print("Enter\n\t1. Add Players \n\t2. Remove Players \n\t3. List \n\t4. Back \n\t5. Exit \n-> ");
						switch_var = validIntInput(5);

						switch (switch_var) {
							// To add new Players
							case 1:
								System.out.print("Enter the player name : ");
								stringTemp = validStringInput();
								sport = (Sport) validObject("sport");
								if (m.addPlayer(stringTemp, sport)) {
									System.out.println("Player "+stringTemp+" is successfully register for sport "+sport.getName());
								} else {
									System.out.println("Player "+stringTemp+" is already registered.");
								}
								break;
							// To delete players
							case 2:
								System.out.print("Enter the player name : ");
								stringTemp = validStringInput();
								sport = (Sport) validObject("sport");
								if (m.deletePlayer(stringTemp, sport)) {
									System.out.println("Player "+stringTemp+" is removed from entry.");
								} else {
									System.out.println("Player "+stringTemp+" is not present in entry.");
								}
								break;
							// To list all players
							case 3:
								m.listPlayer();
								break;
							// To Go back
							case 5:
								flagA = false;
							// To Exit
							case 4:
								flagB = false;
								break;
						}
					}
					break;

				// To Manage Teams
				case 3:
					if (m.isPlayerEmpty()) {
						System.out.println("Please enter some players first....");
						flagB = false;
					}
					while (flagB) {
						// Menu and input and reset
						flagC = true;
						System.out.print("Enter\n\t1. Add Teams \n\t2. Remove Teams \n\t3. List Teams \n\t4. Manage \n\t5. Back \n\t6. Exit \n-> ");
						switch_var = validIntInput(6);

						switch (switch_var) {
							// To add new team
							case 1:
								System.out.print("Enter the name of team : ");
								stringTemp = validStringInput();
								sport = (Sport) validObject("sport");
								if (m.addNewTeam(stringTemp, sport)) {
									System.out.println("Team "+stringTemp+" is add with sport "+sport.getName());
								} else {
									System.out.println("Team "+stringTemp+" is already registered.");
								}
								break;
							// To delete team
							case 2:
								System.out.println("Enter the name of team : ");
								stringTemp = validStringInput();
								sport = (Sport) validObject("sport");
								if (m.deletePrevTeam(stringTemp, sport)) {
									System.out.println("Team "+stringTemp+" is successfully deleted.");
								} else {
									System.out.println("Team "+stringTemp+" is not present.");
								}
								break;
							// To list team
							case 3:
								m.listAllTeam();
								break;

							// To Manage Players in team
							case 4:
								if (m.isNoTeam()) {
									System.out.println("Please enter some Teams first....");
									flagC = false;
								} else {
									team = (Team) validObject("team");
								}
								while (flagC) {
									// Menu and input
									System.out.print("Enter\n\t1. Add Player \n\t2. Remove Player \n\t3. List Player \n\t4. Back \n\t5. Exit \n-> ");
									switch_var = validIntInput(5);

									switch (switch_var) {
										// Add players in team
										case 1:
											player = (Player) validObject("player");
											if (m.addPlayerInTeam(team, player)) {
												System.out.println("Player is successfully added.");
											} else {
												System.out.println("Player is already present.");
											}
											break;
										// Remove Player from team
										case 2:
											player = (Player) validObject("player");
											if (m.deletePlayerFromTeam(team, player)) {
												System.out.println("Player successfully removed.");
											} else {
												System.out.println("PLayer is not present.");
											}
											break;
										// List all player from team
										case 3:
											m.listPlayerInTeam(team);
											break;
										// Back
										case 5:
											flagA = flagB = false;
										// Exit
										case 4:
											flagC = false;
											break;
									}
								}
								break;

							// To Go back
							case 6:
								flagA = false;
								// To Exit
							case 5:
								flagB = false;
								break;
						}
					}
					break;

				// To Manage League
				case 4:
					if(m.isNoTeam()) {
						System.out.println("Please enter some teams first...");
						flagB = false;
					}
					while (flagB) {
						// Menu and input and reset
						flagC = true;
						System.out.print("Enter\n\t1. Add League \n\t2. Delete League \n\t3. List League \n\t4. Manage \n\t5. Back \n\t6. Exit \n-> ");
						switch_var = validIntInput(6);

						switch (switch_var) {
							// To add new League
							case 1:
								System.out.print("Enter the name of League : ");
								stringTemp = validStringInput();
								sport = (Sport) validObject("sport");
								if (m.addNewLeague(stringTemp, sport)) {
									System.out.println("League "+stringTemp+" is add with sport "+sport.getName());
								} else {
									System.out.println("League "+stringTemp+" is already registered.");
								}
								break;
							// To delete League
							case 2:
								System.out.println("Enter the name of League : ");
								stringTemp = validStringInput();
								sport = (Sport) validObject("sport");
								if (m.deletePrevLeague(stringTemp, sport)) {
									System.out.println("League "+stringTemp+" is successfully deleted.");
								} else {
									System.out.println("League "+stringTemp+" is not present.");
								}
								break;
							// To list League
							case 3:
								m.listAllLeague();
								break;

							// Manage League
							case 4:
								if (m.isNoLeague()) {
									System.out.println("Please enter some League first....");
									flagC = false;
								} else {
									league = (League) validObject("league");
								}
								while (flagC) {
									// Menu and input
									System.out.print("Enter\n\t1. Add Team \n\t2. Remove Team \n\t3. Ranking \n\t4. Match \n\t5. Back \n\t6. Exit \n-> ");
									switch_var = validIntInput(6);

									switch (switch_var) {
										// Add team in league
										case 1:
											team = (Team) validObject("team");
											if (m.addTeamInLeague(league, team)) {
												System.out.println("Team is successfully added.");
											} else {
												System.out.println("Team is already present.");
											}
											break;
										// Remove team from league
										case 2:
											team = (Team) validObject("team");
											if (m.deleteTeamFromLeague(league, team)) {
												System.out.println("Team successfully removed.");
											} else {
												System.out.println("Team is not present.");
											}
											break;
										// List all team from league
										case 3:
											m.listTeamInLeague(league);
											break;
										// Make match
										case 4:
											team1 = (Team) validObject("team");
											System.out.print("Enter score of "+team1.getTeamName()+": ");
											t1 = validIntInput(100);
											team2 = (Team) validObject("team");
											System.out.print("Enter score of "+ team2.getTeamName()+": ");
											t2 = validIntInput(100);
											int temp = m.makeMatch(league, team1, team2, t1, t2);
											if (temp == 1) {
												System.out.println(team1.getTeamName()+" won match against "+ team2.getTeamName());
											} else if (temp == -1) {
												System.out.println(team1.getTeamName()+" lost match against "+ team2.getTeamName());
											} else {
												System.out.println("Match between "+team1.getTeamName()+" and "+ team2.getTeamName()+" is tied.");
											}
											break;
										// Back
										case 6:
											flagA = flagB = false;
											// Exit
										case 5:
											flagC = false;
											break;
									}
								}
								break;

							// Exit code
							case 6:
								flagA = false;
							// Go to previous menu
							case 5:
								flagB = false;
								break;
						}
					}
					break;

			    // Exit Case
				case 5:
					flagA = false;
					break;
			}

		}
	}
}
