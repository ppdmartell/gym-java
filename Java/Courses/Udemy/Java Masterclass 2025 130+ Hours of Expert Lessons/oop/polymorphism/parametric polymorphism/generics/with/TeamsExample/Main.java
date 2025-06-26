class Main {

    public static void main(String[] args) {
        Team<BaseballPlayer, Affiliation> phillies = new Team<>("Philadelphia Phillies");
        Team<BaseballPlayer, Affiliation> astros = new Team<>("Huston Astros");
        scoreResult(phillies, 3, astros, 5);

        BaseballPlayer harper = new BaseballPlayer("B. Harper", "RF");
        BaseballPlayer jensen = new BaseballPlayer("T. Jensen", "SS");
        phillies.addTeamMember(harper);
        phillies.addTeamMember(jensen);
        phillies.listTeamMembers();
		
        Affiliation fcbAfi = new Affiliation("city", "UNICEF", "Spain");
		Team<FootballPlayer, Affiliation> fcb = new Team<>("FC Barcelona", fcbAfi);   // Affiliation object passed
        Team<FootballPlayer, String> rma = new Team<>("Real Madrid", "Not affiliated, but working on it.");   // String passed Team<FootballPlayer, String>
        scoreResult(fcb, 2, rma, 2);
		
		FootballPlayer messi = new FootballPlayer("L. Messi", "RS");
        FootballPlayer cr7 = new FootballPlayer("CR7", "MS");
        fcb.addTeamMember(messi);
        rma.addTeamMember(cr7);

        fcb.listTeamMembers();
        rma.listTeamMembers();

		
		// phillies.addTeamMember(messi);    error: incompatible types: FootballPlayer cannot be converted to BaseballPlayer
		// not even casted. And this was allowed with an interface. Hence generics is better in this case.
    }


    // Don't do scoreResult(Team<? extends Player> team1, ...) in the parameters because you could then
    // mix scoreResult between a BaseballPlayer team and a FootballPlayer team.
    // Please note wildcards (?) are only accepted in method parameters and returns
    // And also note <T extends Player>, no reference to S in the type parameter declaration
    public static <T extends Player> void scoreResult(Team<T, ?> team1, int t1_score,
                                   Team<T, ?> team2, int t2_score) {

        String message = team1.setScore(t1_score, t2_score);
        team2.setScore(t2_score, t1_score);
        System.out.printf("%s %s %s %n", team1, message, team2);
    }

}


/*
 * The baseball small application was implemented, but what if a football team
 * needs to also be added?
 * */
