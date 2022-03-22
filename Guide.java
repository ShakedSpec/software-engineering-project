import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Guide implements Serializable
{
    private String guideId;
    private String name;
    private String phoneNumber;
    private int age;
    private HealthStatus healthCondition;
    private List<Group> groupList;
    private List<RateGuide> ratings;
    private static final int MIN_AGE = 18;

    public HealthStatus getHealthCondition() { return healthCondition; }

    public void setHealthCondition(HealthStatus healthCondition){
        if (healthCondition != HealthStatus.EXCELLENT)
            throw new IllegalArgumentException("Guide must have EXCELLENT health condition");
        this.healthCondition = healthCondition;
    }
    public Guide(String guideId_,String name_, String phoneNumber_, int age_, HealthStatus healthCondition_)
    {
        this.guideId = guideId_;
        this.name = name_;
        setPhoneNumber(phoneNumber_);
        this.ratings = new ArrayList<RateGuide>();
        this.groupList = new ArrayList<Group>();
        setAge(age_);
        setHealthCondition(healthCondition_);
    }

    public String getGuideId() { return guideId; }
    public void setGuideId(String guideId_) { guideId = guideId_; }
    public String getName() { return name; }
    public void setName(String name_) { name = name_; }
    public void setPhoneNumber(String phoneNumber_)
    {
        if(!phoneNumber_.matches("[0][5][0-9][-][0-9]{7}"))
            throw  new IllegalArgumentException("Invalid phone format");
        phoneNumber = phoneNumber_;
    }
    public String getPhoneNumber() { return phoneNumber; }
    public void setAge(int age_)
    {
        if(age_ < MIN_AGE)
            throw new IllegalArgumentException("Too young to guide");
        age = age_;
    }
    public int getAge() { return age; }
    public void setRatings(List<RateGuide> Ratings_) { ratings = Ratings_; }
    public List<RateGuide> getRatings() { return ratings; }

    public void addRating(RateGuide rg) { ratings.add(rg); }

    public double calcAvgRating()
    {
        if(ratings.size() == 0)
            return 0;
        double sum = 0;
        for(RateGuide rg : ratings)
            sum += rg.getRating();
        return sum / ratings.size();
    }

    public String toString()
    {
        return "Id: " + guideId + "\nName: " + name + "\nPhone number: " + phoneNumber + "\nAge: " + age +  "\nAverage rating: " + calcAvgRating() + "\n" ;
    }
    public void displayGuideInfo() {
        System.out.println("Guide Info:");
        System.out.println(this); }
    public Track createTrack(String TrackCode, String location, Difficulty difficulty,double length) { return new Track(TrackCode, location, length, difficulty);}
    public void changeLength(Track t, double length) { t.setTrackLength(length); }
}