import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Group implements Serializable
{
    private String groupId;
    private List<Track> listOfTracks;
    private List<TravelerInGroup> tig;
    private Guide guideInGroup;
    private int maxAge;
    private int minAge;
    private int acceptingRate;


    public void setGroupId(String groupId) { this.groupId = groupId; }
    public String getGroupId() { return groupId; }
    public void setListOfTracks(List<Track> listOfTracks) { this.listOfTracks = listOfTracks; }
    public List<Track> getListOfTracks() { return listOfTracks; }
    public void setTravelerInGroup(List<TravelerInGroup> tig) { this.tig = tig;}
    public List<TravelerInGroup> getTravelerInGroup() { return tig;}
    public void setAcceptingRate(int acceptingRate_)
    {
        if (acceptingRate_ < 1 && acceptingRate_ > 10)
            throw  new IllegalArgumentException(" invalid accepting rate");

        this.acceptingRate = acceptingRate_;
    }
    public int getAcceptingRate() { return acceptingRate; }
    public void setMinAge(int minAge) { this.minAge = minAge; }
    public int getMinAge() { return minAge; }
    public void setMaxAge(int maxAge) { this.maxAge = maxAge; }
    public int getMaxAge() { return maxAge; }

    public Group(String groupId_, int acceptingRate_, int min_Age, int max_Age, Guide guideInGroup)
    {
        this.groupId = groupId_;
        this.listOfTracks = new ArrayList<Track>();
        this.acceptingRate = acceptingRate_;
        this.minAge = min_Age;
        this.maxAge = max_Age;
        this.tig = new ArrayList<TravelerInGroup>();
        this.guideInGroup = guideInGroup;
    }

    public Boolean addTravelerToGroup(Traveler t)
    {
        double matchScore = getMatchScore(t);
        if (t.getAge() >= minAge && t.getAge() <= maxAge)
        {
            if (Math.floor(matchScore) >= acceptingRate)
            {
                TravelerInGroup travelerInGroup = new TravelerInGroup(t, this, matchScore);
                tig.add(travelerInGroup);
                return true;
            }
        }
        return false;
    }

    public double getMatchScore(Traveler t)
    {
        double ageScore = 1 - (t.getAge() - minAge) / (maxAge - minAge);
        double healthScore = 0;
        switch (t.getHealthCondition())
        {
            case EXCELLENT:
                healthScore = 1;
                break;
            case HIGH:
                healthScore = 0.75;
                break;
            case MEDIUM:
                healthScore = 0.5;
                break;
            case LOW:
                healthScore = 0.25;
                break;
            default:
                System.out.println("Not a valid health condition");
                break;
        }
        return (ageScore * 0.5 + healthScore * 0.5) * 10;
    }

    public float getAverageAge() {
        float sum = 0, avg_age = 0;
        if(tig.size() == 0) return 0;
        for (TravelerInGroup tig : tig)
            sum += tig.getTraveler().getAge();

        avg_age = sum / tig.size();
        return avg_age;
    }

    public String toString()
    {
        return "Id: " + groupId + "\nAverage age: " + getAverageAge() + "\nAccepting rate: " + acceptingRate + "\n";
    }

    public void displayGroupInfo()
    {
        System.out.println("Group Info:\n");
        System.out.println("Guide: " + guideInGroup.getName() +", phone number: " + guideInGroup.getPhoneNumber());
        System.out.println(this);
    }

    public void createGroupOnTrack(GregorianCalendar date, Track t)
    {
        Group g = this;
        new Thread(() ->
        {
            if((acceptingRate ==1 ||  acceptingRate == 2  &&  t.getDifficulty().ordinal() == 0) ||
               (acceptingRate ==3 ||  acceptingRate == 4  &&  t.getDifficulty().ordinal() <= 1) ||
               (acceptingRate ==5 ||  acceptingRate == 6  &&  t.getDifficulty().ordinal() <= 2) ||
               (acceptingRate ==7 ||  acceptingRate == 8  &&  t.getDifficulty().ordinal() <= 3) ||
               (acceptingRate ==9 ||  acceptingRate == 10  &&  t.getDifficulty().ordinal() <= 4))
            {

//                f(1) = 0- > +0.5*2
//                f(2) = 0 -> +1 *2
//                f(3) = 1 - > +0.5*2
//                f(4) = 1 -> +1 *2
//                f(5) = 2 - > +0.5 *2
//                f(6) = 2 -> +1 *2
//                f(7) = 3 - > +0.5 *2
//                f(8) = 3 -> +1 *2
//                f(9) =4 -> + 0.5 * 2
//                f(10) =4 -> +1 *2
                GroupOnTrack got = new GroupOnTrack(date, g, t);
                t.addGroupToTrack(got);
            }
            else
            {
                System.out.println("Track is too difficult for this group");
                return;
            }
        }).start();
    }
}