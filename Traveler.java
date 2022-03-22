import java.io.Serializable;

public class Traveler implements Serializable
{
    private String id;
    private String name;
    private String phoneNumber;
    private int age;
    private HealthStatus healthCondition;
    private static final int MIN_AGE = 8;

    public Traveler(String id_,String name_, String phoneNumber_, int age_, HealthStatus healthCondition_)
    {
       setId(id_);
       this.name = name_;
       setPhoneNumber(phoneNumber_);
       setAge(age_);
       this.healthCondition = healthCondition_;
    }

    public void setId(String id_)
    {
        if (!id_.matches("[0-9]{9}"))
            throw  new IllegalArgumentException("Invalid ID format");
        id = id_;
    }
    public String getId() { return id; }
    public void setName(String name_) { name = name_; }
    public String getName() { return name; }
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
    public void setHealthCondition(HealthStatus healthCondition_) { healthCondition = healthCondition_; }
    public HealthStatus getHealthCondition() { return healthCondition; }

    public String toString()
    {
        return "Id: " + id + "\nName: " + name + "\nPhone number: " + phoneNumber + "\nAge: " + age + "\nHealth condition: " + healthCondition + "\n" ;
    }
    public void displayTravelerInfo()
    {
        System.out.println("Traveler Info:");
        System.out.println(this);
    }

    public boolean askToJoin(Group group)
    {
       return group.addTravelerToGroup(this);
    }

    public void rateGuide(Guide guide, int rating )
    {
     RateGuide rg = new RateGuide(rating,this, guide );
     guide.addRating(rg);
    }
}