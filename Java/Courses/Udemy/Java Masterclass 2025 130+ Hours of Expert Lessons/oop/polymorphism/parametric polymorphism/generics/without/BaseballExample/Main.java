class Main {

    public static void main(String[] args) {
        BaseballTeam phillies = new BaseballTeam("Philadelphia Phillies");
        BaseballTeam astros = new BaseballTeam("Huston Astros");
        scoreResult(phillies, 3, astros, 5);

        BaseballPlayer harper = new BaseballPlayer("B. Harper", "RF");
        BaseballPlayer jensen = new BaseballPlayer("T. Jensen", "SS");
        phillies.addTeamMember(harper);
        phillies.addTeamMember(jensen);
        phillies.listTeamMembers();
    }

    private static void scoreResult(BaseballTeam team1, int t1_score,
                                    BaseballTeam team2, int t2_score) {

        String message = team1.setScore(t1_score, t2_score);
        team2.setScore(t2_score, t1_score);
        System.out.printf("%s %s  %s %n", team1, message, team2);
    }

}


/*
 * The baseball small application was implemented, but what if a football team
 * needs to also be added?
 * */
