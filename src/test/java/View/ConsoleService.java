package View;

import data.PlayerJdbcDAO;
import models.Player;
import models.Taxable;
import models.Team;

import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleService {

    public void handleEasyMode(Scanner input, Team yourTeam, PlayerJdbcDAO playerJdbcDAO) {


        System.out.println("You have 132.6 million in cap space before the luxury tax kicks in - but this is Easy Mode so spend to your heart's desire! \n");
        System.out.println("Time to sign some players! \n");

        for (int i = 0; i < 5; i++) {
            System.out.println("Enter the players name | (Firstname Lastname): ");
            String playerName = input.nextLine();
            BigDecimal playerSal = playerJdbcDAO.getSalaryByPlayerName(playerName);

            if (playerSal.compareTo(new BigDecimal(0)) == 0) {
                System.out.println("Our database doesn't have any salary info on " + playerName + ". Please enter his salary in millions | i.e. 13.2 for 13.2 million: ");
                try {
                    playerSal = new BigDecimal(input.nextLine());
                } catch (Exception e) {
                    System.out.println("Looks like you entered something that isn't numerical!");
                }
            } else {
                System.out.println("\n" + playerName + "'s salary sits at $" + playerSal + " million for the season. It's been added to the payroll.");
            }

            System.out.println("Enter " + playerName + "'s position | G for guard, F for forward, C for Center: ");
            String playerPos = input.nextLine();
//                System.out.println("Enter the players salary in millions | 24.5 for 24.5 million");


            Taxable playerAdded = new Player(playerName, playerPos, playerSal);

            yourTeam.addPlayer(playerAdded);

            if (playerAdded.getPosition().toUpperCase().equals("G")) {
                yourTeam.addToGuardList(playerAdded);
            } else if (playerAdded.getPosition().toUpperCase().equals("F")) {
                yourTeam.addToForwardsList(playerAdded);
            } else if (playerAdded.getPosition().toUpperCase().equals("C")) {
                yourTeam.addToCentersList(playerAdded);
            }

        }
        System.out.println(yourTeam.toString());


    }

    public void handleHardMode(Scanner input, Team yourTeam, PlayerJdbcDAO playerJdbcDAO) {

        System.out.println("You have 132.6 million in cap space before the luxury tax kicks in. \nAfter you go over the luxury tax you will be forced to sign veteran minimum players. Money is tight!\n");
        System.out.println("Time to sign some players! \n");

        int counter = 0;

        do {
            counter++;

            System.out.println("Enter the players name | (Firstname, Lastname): ");
            String playerName = input.nextLine();

            BigDecimal playerSal = playerJdbcDAO.getSalaryByPlayerName(playerName);

            if (playerSal.compareTo(new BigDecimal(0)) == 0) {
                System.out.println("Our database doesn't have any salary info on " + playerName + ". Please enter his salary in millions | i.e. 13.2 for 13.2 million: ");
                try {
                    playerSal = new BigDecimal(input.nextLine());
                } catch (Exception e) {
                    System.out.println("Looks like you entered something that isn't numerical!");
                }
            } else {
                System.out.println("\n" + playerName + "'s salary sits at $" + playerSal + " million for the season. It's been added to the payroll.");
            }

            System.out.println("Enter " + playerName + "'s position | G for guard, F for forward, C for Center: ");
            String playerPos = input.nextLine();
//                System.out.println("Enter the players salary in millions | 24.5 for 24.5 million");


            Taxable playerAdded = new Player(playerName, playerPos, playerSal);

            yourTeam.addPlayer(playerAdded);

            if (playerAdded.getPosition().toUpperCase().equals("G")) {
                yourTeam.addToGuardList(playerAdded);
            } else if (playerAdded.getPosition().toUpperCase().equals("F")) {
                yourTeam.addToForwardsList(playerAdded);
            } else if (playerAdded.getPosition().toUpperCase().equals("C")) {
                yourTeam.addToCentersList(playerAdded);
            }


        }
        while (yourTeam.getOverspentPayroll().compareTo(new BigDecimal(0)) <= 0 && counter < 5);


        if (counter == 5) {
            System.out.println(yourTeam.toString());
        } else if (counter < 5) {
            System.out.println("You've went over the luxury tax! The rest of your roster will be filled out with the veterans on a minimum salary");
            while (counter < 5) {

                Player vet = new Player("Veteran Player", "G", new BigDecimal("2.6"));
                yourTeam.addPlayer(vet);
                yourTeam.addToGuardList(vet);
                counter++;
            }
            // add in logic to figure out which position to make the generated players

            System.out.println(yourTeam.toString());

        }


    }
}
