package data;

import models.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class TxtFileToDatabase {


    String path = "salary.txt";

    private List<Player> players = new ArrayList<Player>();


    public void add_player(Player player) {
        players.add(player);

    }


    private File newFile = new File(path);

    private JdbcTemplate jdbcTemplate;


    public TxtFileToDatabase(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public void read_players_from_file() {

        try (Scanner scanner = new Scanner(newFile)) {

            int counter = 0;

            while (scanner.hasNext() & counter < 488) {

                String line = scanner.nextLine();

                String[] arr = line.split(",");

                String nameWithIdToken = arr[1];
                String delim = "\\\\";
                String[] nameArr = nameWithIdToken.split(delim);

                String name = nameArr[0];
                String tokenId = nameArr[1];

                String team = arr[2];
                String salaryAsString = arr[3];
                String salary = salaryAsString.substring(1);
                BigDecimal finalSalary = new BigDecimal(salary).divide(new BigDecimal(1000000));

                try {
                    Player player = new Player(name, finalSalary);
                    add_player(player);
                } catch (Exception e) {

                }

                counter++;


            }
        } catch (FileNotFoundException e) {
            System.out.println("Whoops can't find that file!");
        }


    }

    public void insert_players_to_database() {

        for (Player player : players) {

            try {
                String sql = "INSERT into nba_salary (name, salary) VALUES (?, ?)";

                jdbcTemplate.update(sql, player.getName(), player.getSalary());

            } catch (Exception e) {

            }
        }


    }


}
