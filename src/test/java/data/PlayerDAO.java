package data;

import models.Player;

import java.math.BigDecimal;
import java.util.List;

public interface PlayerDAO {

    public BigDecimal getSalaryByPlayerName(String name);

//    public List<Player> getAllPlayers();
//
//    public List<Player> getPlayersUnderSalary(BigDecimal salary);
//
//    public List<Player> getPlayersOverSalary(BigDecimal salary);
//
//    public List<Player> getTenHighestPaidPlayers();
//
//    public List<Player> getHighestPaidGuards();
//
//    public List<Player> getHighestPaidForwards();
//
//    public List<Player> getHighestPaidCenters();

    // other select statements based on any stats that can eventually be added to the DB through APIs(?);





}
