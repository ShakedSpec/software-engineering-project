import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Track implements Serializable
{
    private String trackCode;
    private String location;
    private double trackLength;
    private List<GroupOnTrack> groupsOnTrack;
    private Difficulty difficulty;

    public String getTrackCode() { return trackCode; }
    public void setTrackCode(String trackCode_) { this.trackCode = trackCode_; }
    public String getLocation() { return location; }
    public void setLocation(String location_) { this.location = location_; }
    public double getTrackLength() { return trackLength ;}
    public void setTrackLength(double trackLength_) { this.trackLength = trackLength_; }
    public List<GroupOnTrack> getGroupsOnTrack() { return groupsOnTrack; }
    public void setGroupsOnTrack(List<GroupOnTrack> groupsOnTrack_) { this.groupsOnTrack = groupsOnTrack_;}
    public Difficulty getDifficulty() { return difficulty;}
    public void setDifficulty(Difficulty difficulty_) { this.difficulty = difficulty_; }

    public Track(String trackCode, String location, double trackLength, Difficulty difficulty)
{
    this.trackCode = trackCode;
    this.location = location;
    this.trackLength = trackLength;
    this.groupsOnTrack = new ArrayList<GroupOnTrack>();
    this.difficulty = difficulty;
}

    public String toString()
    {
        return "Track Code: " + trackCode + "\nLocation: " + location + "\nTrack length: " + trackLength + "\ndifficulty: " + difficulty + "\n";
    }

    public void displayTrackInfo()
    {
        System.out.println("Track Info:");
        System.out.println(this);
    }

 public int calcNumOfTravelersInTrack()
 {
     int totalNumOfTravelers = 0;
     for( GroupOnTrack got : groupsOnTrack)
            totalNumOfTravelers += got.getGroup().getTravelerInGroup().size();
        return totalNumOfTravelers;
 }

    public void addGroupToTrack(GroupOnTrack got) { groupsOnTrack.add(got); }
}