import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Trip implements Serializable
{
    Scanner scanner = new Scanner(System.in);
    String name;

    private ArrayList<Traveler> travelers;
    private ArrayList<Group> groups;
    private ArrayList<Track> tracks;
    private ArrayList<Guide> guides;

    public ArrayList<Guide> getGuides() { return guides; }
    public void setGuides(ArrayList<Guide> guides) { this.guides = guides; }
    public ArrayList<Traveler> getTravelers() { return travelers; }
    public void setTravelers(ArrayList<Traveler> travelers) { this.travelers = travelers; }
    public ArrayList<Group> getGroup() { return groups; }
    public void setGroups(ArrayList<Group> groups) { this.groups = groups; }
    public ArrayList<Track> getTracks() { return tracks; }
    public void setTracks(ArrayList<Track> tracks) { this.tracks = tracks; }

    public Trip(String name)
    {
        travelers = new ArrayList<Traveler>();
        groups = new ArrayList<Group>();
        tracks = new ArrayList<Track>();
        guides = new ArrayList<Guide>();
        this.name = name;
        displayMenu();
    }

    private void displayMenu()
    {
        Scanner scanner = new Scanner(System.in);
        boolean stop = false;
        while (!stop)
        {
            Track track;
            Guide guide;
            Group group;
            Traveler traveler;
            System.out.println("\nWelcome " + this.name + " to trip planner app\n");
            switch ( mainMenu())
            {
                case "1":
                    switch (objectsMenu())
                    {
                        case "1":
                            try {
                                travelers.add(addTraveler());
                                System.out.println("traveler added successfully");
                            }catch (IllegalArgumentException e){
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "2":
                            try {
                                guides.add(addGuide());
                                System.out.println("Guide added successfully");
                            }catch (IllegalArgumentException e){
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "3":
                            groups.add(addGroup());
                            System.out.println("Group added successfully");
                            break;
                        case "4":
                            System.out.println("Only guide can add track");
                            break;
                    }
                    break;
                case "2":
                    System.out.println("Enter object id: ");
                    String object = scanner.nextLine();
                    traveler = getTraveler(object);
                    if (traveler != null) traveler.displayTravelerInfo();
                    guide = getGuide(object);
                    if (guide != null) guide.displayGuideInfo();
                    group = getGroup(object);
                    if (group != null) group.displayGroupInfo();
                    track = getTrack(object);
                    if (track != null) track.displayTrackInfo();
                    if (traveler == null && guide == null && group == null && track == null)
                        System.out.println("No such object");
                    break;
                case "3":
                    writeObjectToFile();
                    break;
                case "4":
                    ReadObjectFromFile();
                    break;
                case "5":
                    moreOptionsMenu();
                    break;
                case "6":
                    stop = true;
                    break;
            }
        }
    }

    private Track addTrack(Guide guide)
    {
        String trackCode;
        String location;
        double trackLength;
        int difficulty;
        System.out.println("Enter Track Code: ");
        trackCode = scanner.nextLine();
        if(searchId(trackCode))
            throw new IllegalArgumentException("Id already exists");
        System.out.println("Enter track location: ");
        location = scanner.nextLine();
        System.out.println("Enter track length: ");
        trackLength = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter difficulty: 0. VERY_EASY, 1. EASY, 2. MEDIUM, 3. HIGH, 4. EXTREME");
        difficulty = Integer.parseInt(scanner.nextLine());
        System.out.println("Track created successfully:");
        return guide.createTrack(trackCode, location, Difficulty.values()[difficulty], trackLength);
    }

    private Group addGroup()
    {
        String groupId;
        int acceptingRate, min_Age, max_Age;
        System.out.println("(*)To create a group, there must be a guide to associate them with the group.");
        System.out.println("Enter group id: ");
        groupId = scanner.nextLine();
        if(searchId(groupId))
            throw new IllegalArgumentException("Id already exists");
        System.out.println("Enter accepting rate(between 1-10): ");
        acceptingRate = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter min age: ");
        min_Age = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter max age: ");
        max_Age = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter guide id: ");
        Guide guide = this.getGuide(scanner.nextLine());
        while (guide == null)
        {
            System.out.println("No such guide, try again");
            guide = this.getGuide(scanner.nextLine());
        }
        return new Group(groupId, acceptingRate, min_Age, max_Age, guide);
    }

    private Guide addGuide()
    {
        String id, fullName, phoneNum;
        int age, healthcond;

        System.out.println("Enter Guide id: ");
        id = scanner.nextLine();
        if(searchId(id))
            throw new IllegalArgumentException("Id already exists");
        System.out.println("Enter Guide full name: ");
        fullName = scanner.nextLine();
        System.out.println("Enter Guide phone number: ");
        phoneNum = scanner.nextLine();
        System.out.println("Enter Guide age: ");
        age = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Guide health condition: 0. LOW, 1. MEDIUM, 2. HIGH, 3. EXCELLENT");
        healthcond = Integer.parseInt(scanner.nextLine());
        return new Guide(id, fullName, phoneNum, age, HealthStatus.values()[healthcond]);
    }

    private Traveler addTraveler()
    {
        String id, fullName, phoneNum;
        int age, healthcond;

        Scanner s = new Scanner(System.in);
        System.out.println("Enter traveler id: ");
        id = s.nextLine();
        if(searchId(id))
            throw new IllegalArgumentException("Id already exists");
        System.out.println("Enter traveler full name: ");
        fullName = s.nextLine();
        System.out.println("Enter traveler phone number: ");
        phoneNum = s.nextLine();
        System.out.println("Enter traveler age: ");
        age = Integer.parseInt(s.nextLine());
        System.out.println("Enter traveler health condition: 0. LOW, 1. MEDIUM, 2. HIGH, 3. EXCELLENT");
        healthcond = Integer.parseInt(s.nextLine());
        return new Traveler(id, fullName, phoneNum, age, HealthStatus.values()[healthcond]);
    }

    public void writeObjectToFile()
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream("Trip");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this.travelers);
            objectOut.writeObject(this.guides);
            objectOut.writeObject(this.groups);
            objectOut.writeObject(this.tracks);
            objectOut.close();
            System.out.println("The data was successfully written to a file");
        }
        catch (Exception ex) { ex.printStackTrace(); }
    }

    public void ReadObjectFromFile()
    {
        try
        {
            FileInputStream fileIn = new FileInputStream("Trip");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            this.travelers = (ArrayList<Traveler>)objectIn.readObject();
            this.guides = (ArrayList<Guide>)objectIn.readObject();
            this.groups = (ArrayList<Group>)objectIn.readObject();
            this.tracks = (ArrayList<Track>)objectIn.readObject();
            System.out.println("The data has been read from the file");
            objectIn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Track getTrack(String trackCode)
    {
        for (Track track : tracks)
        {
            if (track.getTrackCode().equals(trackCode))
                return track;
        }
        return null;
    }

    public Guide getGuide(String guideId)
    {
        for (Guide guide : guides)
        {
            if (guide.getGuideId().equals(guideId))
                return guide;
        }
        return null;
    }

    public Group getGroup(String groupId)
    {
        for (Group group : groups)
        {
            if (group.getGroupId().equals(groupId))
                return group;
        }
        return null;
    }

    public Traveler getTraveler(String travelerId)
    {
        for (Traveler traveler : travelers)
        {
            if (traveler.getId().equals(travelerId))
                return traveler;
        }
        return null;
    }

    public String mainMenu()
    {
        System.out.println("Please select one one the options below:\n" +
                           "1.Add Object to trip\n" +
                           "2.View Object details\n" +
                           "3.Save data to file\n" +
                           "4.Load data from file\n" +
                           "5.More options\n" +
                           "6.Exit\n");
        return scanner.nextLine();
    }

    public void moreOptionsMenu()
    {
        int rating;
        Traveler traveler;
        Guide guide;
        Group group;
        Track track;
        switch (objectsMenu())
        {
            case "1":
                switch (travelerMenu())
                {

                    case "1":
                        System.out.println("Enter traveler id: ");
                        traveler = getTraveler(scanner.nextLine());
                        System.out.println("Enter guide id: ");
                        guide = this.getGuide(scanner.nextLine());
                        System.out.println("Enter rating between 1-10: ");
                        rating = Integer.parseInt(scanner.nextLine());
                        if (traveler == null || guide == null) System.out.println("No such traveler or guide");
                        else
                        {
                            traveler.rateGuide(guide, rating);
                            System.out.println("Guide rated successfully");
                        }
                        break;
                    case "2":
                        System.out.println("Enter traveler id: ");
                        traveler = getTraveler(scanner.nextLine());

                        System.out.println("Groups: ");
                        for (Group g: this.groups)
                            System.out.println("Group Id: " + g.getGroupId() + ", Accepting rate: " + g.getAcceptingRate() + ", Traveler match: " + g.getMatchScore(traveler));
                        System.out.println("Enter group id: ");
                        group = this.getGroup(scanner.nextLine());
                        if (traveler == null || group == null)
                            System.out.println("No such traveler or group");
                        else if (traveler.askToJoin(group))
                            System.out.println("Traveler Successfully joined the group");
                        else
                            System.out.println("Traveler Cannot join the group");
                        break;
                    case "3":
                        for(Traveler t: travelers)
                        {
                            System.out.println("Id: " + t.getId() + ", Name: " + t.getName());
                        }

                        break;
                    case "4":
                        return;
                }
                break;
            case "2":
                switch (guideMenu())
                {
                    case "1":
                        System.out.println("Enter guide id: ");
                        guide = this.getGuide(scanner.nextLine());
                        if(guide == null) System.out.println("No such guide");
                        else tracks.add(addTrack(guide));
                        break;
                    case "3":
                        System.out.println("Enter guide id: ");
                        guide = this.getGuide(scanner.nextLine());
                        System.out.println("Enter track id: ");
                        track = this.getTrack(scanner.nextLine());
                        if(track == null || guide == null) System.out.println("No such guide or track");
                        else
                        {
                            System.out.println("Enter new length:");
                            guide.changeLength(track, Double.parseDouble(scanner.nextLine()));
                        }
                        break;
                    case "4":
                        for (Guide g: guides)
                        {
                            System.out.println("Id: " + g.getGuideId() + ", Name: " + g.getName());
                        }
                        break;
                    case "5":
                        return;
                }
                break;
            case "3":
                switch (groupMenu())
                {
                    case "1":
                        System.out.println("Enter track id: ");
                        track = this.getTrack(scanner.nextLine());

                        System.out.println("Enter group id: ");
                        group = this.getGroup(scanner.nextLine());
                        System.out.println("Enter Year: ");
                        int year = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter Month: ");
                        int month = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter Day: ");
                        int dayOfMonth = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter Hour: ");
                        int hourOfDay = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter Minutes: ");
                        int minute = Integer.parseInt(scanner.nextLine());
                        GregorianCalendar date = new GregorianCalendar(year,month,dayOfMonth, hourOfDay,minute);
                        if(group == null || track == null) System.out.println("No such group or track");
                        else
                        {
                            group.createGroupOnTrack(date, track);
                            System.out.println("group scheduled on track in " + date.get(Calendar.DATE) + "/" +
                                    date.get(Calendar.MONTH) + "/" + date.get(Calendar.YEAR) + " " +
                                    date.get(Calendar.HOUR) + ":" + date.get(Calendar.MINUTE));
                        }
                        break;
                    case "2":
                        for (Group g: groups)
                        {
                            System.out.println("Id: " + g.getGroupId() + ", Average age: " + g.getAverageAge());
                        }
                        break;
                    case "3":
                        return;
                }
                break;
            case "4":
                switch (trackMenu())
                {
                    case "1":
                        System.out.println("Enter track id: ");
                        track = this.getTrack(scanner.nextLine());
                        System.out.println("Amount of travelers on track is: " + track.calcNumOfTravelersInTrack());
                        break;
                    case "2":
                        for (Track t: tracks)
                        {
                            System.out.println("Id: " + t.getTrackCode() + ", Difficulty: " + t.getDifficulty() + ", Length: " + t.getTrackLength() );
                        }
                        break;
                    case "3":
                        return;
                }
                break;
        }
    }

    public String objectsMenu()
    {
        System.out.println("Please select one one the below options:\n" +
                           "1.Traveler\n" +
                           "2.Guide\n" +
                           "3.Group\n" +
                           "4.Track\n" +
                           "5.Back to main menu\n");
        return scanner.nextLine();
    }

    public String travelerMenu()
    {
        System.out.println("Please select one one the options below:\n" +
                           "1.Rate guide\n" +
                           "2.Ask to join group\n" +
                           "3.Print all travelers\n" +
                           "4.Back to more options menu\n");
        return scanner.nextLine();
    }

    public String guideMenu()
    {
        System.out.println("Please select one one the options below:\n" +
                           "1.Create track\n" +
                           "2.Calculate average rating\n" +
                           "3.Change length\n" +
                           "4.Print all guides\n" +
                           "5.Back to more options menu\n");
        return scanner.nextLine();
    }

    public String groupMenu()
    {
        System.out.println("Please select one one the below options:\n" +
                           "1.Schedule group on track\n" +
                           "2.Print all groups\n" +
                           "3.Back to more options menu\n");
        return scanner.nextLine();
    }
    public String trackMenu()
    {
        System.out.println("Please select one one the below options:\n" +
                           "1.Display number of travelers in track\n" +
                           "2.Print all tracks\n" +
                           "3.Back to more options menu\n");
        return scanner.nextLine();
    }

    public boolean searchId(String id)
    {
        for(Traveler t: this.travelers)
        {
            if (t.getId().equals(id))
                return true;
        }
        for(Guide g: this.guides)
        {
            if (g.getGuideId().equals(id))
                return true;
        }
        for(Track t: this.tracks)
        {
            if (t.getTrackCode().equals(id))
                return true;
        }
        for(Group g: this.groups)
        {
            if (g.getGroupId().equals(id))
                return true;
        }
        return false;
    }
}