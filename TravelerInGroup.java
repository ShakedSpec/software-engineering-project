import java.io.Serializable;
public class TravelerInGroup implements Serializable
{
    private  Traveler traveler;
    private Group group;
    private double matchScore;

    public void setMatchScore(double matchScore) { this.matchScore = matchScore; }
    public double getMatchScore() { return matchScore; }
    public Traveler getTraveler() {
        return traveler;
    }
    public void setTraveler(Traveler traveler_) { this.traveler = traveler_; }
    public Group getGroup() { return group; }
    public void setGroup(Group group_) { this.group = group_; }
    public TravelerInGroup(Traveler traveler_, Group group_, double matchScore_)
    {
        traveler = traveler_;
        group = group_;
        matchScore = matchScore_;
    }
}