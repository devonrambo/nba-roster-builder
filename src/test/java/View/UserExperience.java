package View;

import data.PlayerJdbcDAO;
import models.Player;
import models.Taxable;
import models.Team;
import org.apache.commons.dbcp.BasicDataSource;

import java.math.BigDecimal;
import java.util.Scanner;

public class UserExperience {

    public static void main(String[] args) {

        // hooking up to the sql database
        BasicDataSource ds = new BasicDataSource();

        ds.setUrl("jdbc:postgresql://localhost:5432/nba");
        ds.setUsername("postgres");
        ds.setPassword("postgres1");
//
//        // reading info from the file
//        TxtFileToDatabase maker = new TxtFileToDatabase(ds);
//        maker.read_players_from_file();
//
//        // using info read from file and shooting it to the database
//        maker.insert_players_to_database();

        PlayerJdbcDAO playerJdbcDAO = new PlayerJdbcDAO(ds);
        BigDecimal sampleValue = playerJdbcDAO.getSalaryByPlayerName("Stephen Curry");

        System.out.println(sampleValue);

        System.out.println("Welcome to the 2020-21 NBA Roster Builder!");

        System.out.println("\nPlease enter the city your team will be based out of: ");

        Scanner input = new Scanner(System.in);
        String teamCity = input.nextLine();

        System.out.println("\nPlease enter your team's nickname (Cavaliers, Bulls, Koalas, etc): ");
        String teamNickname = input.nextLine();

        Team yourTeam = new Team(teamNickname, teamCity);


        // First the user will be prompted to fill out a starting 5 roster
        // Each player will be given a name, position, and salary


        System.out.println("Our app has two modes: Easy and Hard");
        System.out.println("Easy Mode allows you to sign any 5 starters. You will then be presented with your *likely expensive* cap situation");
        System.out.println("Hard Mode stops you from going over the tax line (hard cap). This provides a challenge to build a championship roster");

        boolean typeChosen = false;
        boolean easyMode = false;

        do {
            System.out.println("Please enter: E for Easy Mode | H for Hard Mode | Q to Quit ");
            String difficultyVar = input.nextLine();

            if (difficultyVar.equals("E")) {
                easyMode = true;
                typeChosen = true;
            } else if (difficultyVar.equals("H")) {
                easyMode = false;
                typeChosen = true;
            } else if (difficultyVar.equals("Q")) {
                typeChosen = true;
            } else {
                System.out.println("Invalid entry");
                difficultyVar = input.nextLine();
            }
        } while (typeChosen == false);


        if (easyMode == true) {

            ConsoleService consoleService = new ConsoleService();
            consoleService.handleEasyMode(input, yourTeam, playerJdbcDAO);

        } else if (easyMode == false) {

            ConsoleService consoleService = new ConsoleService();
            consoleService.handleHardMode(input, yourTeam, playerJdbcDAO);
        }
    }
}
