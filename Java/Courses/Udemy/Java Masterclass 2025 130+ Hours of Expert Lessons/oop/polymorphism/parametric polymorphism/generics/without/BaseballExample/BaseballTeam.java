import java.util.List;
import java.util.ArrayList;

class BaseballTeam {

    private String name;
    private List<BaseballPlayer> teamMembers;
    private int totalWins = 0;
    private int totalLosses = 0;
    private int totalTies = 0;

    public BaseballTeam(String name) {
        this.name = name;
        this.teamMembers = new ArrayList<>();
    }

    public BaseballTeam(String name, List<BaseballPlayer> teamMembers) {
        this.name = name;
        this.teamMembers = teamMembers;
    }

    public void addTeamMember(BaseballPlayer player) {
        if (!teamMembers.contains(player)) {
            this.teamMembers.add(player);
        }
    }

    public void listTeamMembers() {
        System.out.println(name + " Roster:");
        System.out.println(teamMembers);
    }

    private int ranking() {
        return (totalLosses * 2) + totalTies + 1;
    }

    public String setScore(int ourScore, int theirScore) {
        String message = "lost to";
        if (ourScore > theirScore) {
            this.totalWins++;
            message = "beat";
        } else if (ourScore == theirScore) {
            this.totalTies++;
            message = "tied";
        } else {
            totalLosses++;
        }

        return message;
    }

    @Override
    public String toString() {
        return name + " (Ranked " + ranking() + ")";
    }
}
