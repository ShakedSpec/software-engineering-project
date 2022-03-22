import java.io.Serializable;
import java.util.GregorianCalendar;

public class GroupOnTrack implements Serializable
{
    private Group group;
    private Track track;
    GregorianCalendar dateAndTime;

    public Group getGroup() { return group; }
    public void setGroup(Group group) { this.group = group; }
    public Track getTrack() { return track; }
    public void setTrack(Track track) { this.track = track; }
    public void setDateAndTime(GregorianCalendar dat) { this.dateAndTime = dat; }
    public GregorianCalendar getDateAndTime() { return dateAndTime; }

    public GroupOnTrack(GregorianCalendar date, Group g, Track t)
    {
        dateAndTime = date;
        group = g;
        track = t;
    }
}