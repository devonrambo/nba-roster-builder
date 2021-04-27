package data;

import models.Player;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;



public class PlayerJdbcDAO implements PlayerDAO{


    private JdbcTemplate jdbcTemplate;

    public PlayerJdbcDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }



    @Override
    public BigDecimal getSalaryByPlayerName(String name) {

        String sql = "SELECT salary from nba_salary WHERE name ILIKE ?";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, name);

        BigDecimal returnedSalary;

            if (result.next()){

               returnedSalary = new BigDecimal(result.getDouble("salary"));
                String truncatedValue = String.valueOf(returnedSalary).substring(0, 5);
                returnedSalary = new BigDecimal(truncatedValue);
                return returnedSalary;
            }

            return new BigDecimal(0);
    }



//
//    @Override
//    public List<Player> getAllPlayers() {
//        return null;
//    }
//
//    @Override
//    public List<Player> getPlayersUnderSalary(BigDecimal salary) {
//        return null;
//    }
//
//    @Override
//    public List<Player> getPlayersOverSalary(BigDecimal salary) {
//        return null;
//    }
//
//    @Override
//    public List<Player> getTenHighestPaidPlayers() {
//        return null;
//    }
//
//    @Override
//    public List<Player> getHighestPaidGuards() {
//        return null;
//    }
//
//    @Override
//    public List<Player> getHighestPaidForwards() {
//        return null;
//    }
//
//    @Override
//    public List<Player> getHighestPaidCenters() {
//        return null;
//    }
}
