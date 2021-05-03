# NBA Roster Builder


### Description
This CLI application allows a user to create an NBA roster using every player's real 2020 salary cap information. Easy Mode allows unlimited spending, Hard Mode will stop you from adding new players once you've gone too far past the hard cap. 
Finally, the user will get a cap statement that shows how far over the soft and hard cap the team is as well as their luxury tax owed.

### Motivation
I wanted to build this to get more comfortable with file I/O, PostgreSQL, and DAOs. 

### Methods

A file from basketball-reference with every player's salary started the project. Then a scanner was used to grab that data, and a JdbcTemplate inserted it into a postgreSQL database on my machine. Model classes were made for players and teams. Then I built a command-line front end to grab the info from the user, use the DAO class, and return the relevant information.

### Next Steps & Contributing
I'd like to add a feature that compares the user's cap situation to the rest of the league. Any feedback or contributions are welcome!


