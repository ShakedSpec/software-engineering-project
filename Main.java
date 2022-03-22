import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.*;

/** SE final project made by
 * Barak Lisker, 204610919
 * John Moallim, 315837872
 * Shaked Spector, 308132281
 */

public class Main
{
    public static void main(String[] args)
    {
        ArrayList<Traveler> travelers = new ArrayList<Traveler>();
        ArrayList<Guide> guides = new ArrayList<Guide>();
        ArrayList<Group> groups = new ArrayList<Group>();
        ArrayList<Track> tracks = new ArrayList<Track>();

        int year1 = 2021, year2 = 2021,year3 = 2021, year4 = 2021,year5 = 2021;
        int month1 = 1, month2 = 2, month3 = 3, month4 = 4, month5 = 5;
        int day1 = 5, day2 = 10, day3 = 15, day4 = 17, day5 = 20;
        int hour1 = 10, hour2 = 12, hour3 = 15, hour4 = 17, hour5 = 19;
        int min1 = 15, min2 = 15, min3 = 50, min4 = 20, min5 = 30;

        Traveler traveler1 = new Traveler("204610919","Barak Lisker","052-1122334",28, HealthStatus.values()[3]);
        Traveler traveler2 = new Traveler("315837872","John Moallim","055-5555555",25, HealthStatus.values()[3]);
        Traveler traveler3 = new Traveler("308132281","Shaked Spector","050-9999999",28, HealthStatus.values()[3]);
        Traveler traveler4 = new Traveler("963852741","Jon Doe","052-1234567",20, HealthStatus.values()[3]);
        Traveler traveler5 = new Traveler("147258369","Morty Smith","053-9898989",18, HealthStatus.values()[3]);
        Traveler traveler6 = new Traveler("987456321","Christiano Ronaldo","050-0101010",37, HealthStatus.values()[3]);
        Traveler traveler7 = new Traveler("123654789","Patrick Star","055-1111100",28, HealthStatus.values()[2]);
        Traveler traveler8 = new Traveler("147852369","Rick Sanchez","050-1111000",30, HealthStatus.values()[2]);
        Traveler traveler9 = new Traveler("369852147","Scarlet Johansson","052-2244667",28, HealthStatus.values()[3]);
        Traveler traveler10 = new Traveler("951357456","Gal Gadot","052-9988776",30, HealthStatus.values()[3]);

        travelers.add(traveler1);
        travelers.add(traveler2);
        travelers.add(traveler3);
        travelers.add(traveler4);
        travelers.add(traveler5);
        travelers.add(traveler6);
        travelers.add(traveler7);
        travelers.add(traveler8);
        travelers.add(traveler9);
        travelers.add(traveler10);

        Guide guide1= new Guide("111", "Leo Messi", "051-1111111",30,HealthStatus.values()[3]);
        Guide guide2= new Guide("222", "Elvis Presley", "052-2222222",27,HealthStatus.values()[3]);
        Guide guide3= new Guide("333", "Barbara Streisand", "053-3333333",25,HealthStatus.values()[3]);
        Guide guide4= new Guide("444", "Duke Elington", "054-4444444",23,HealthStatus.values()[3]);
        Guide guide5= new Guide("555", "Eugene Krabs", "055-5555555",22,HealthStatus.values()[3]);

        guides.add(guide1);
        guides.add(guide2);
        guides.add(guide3);
        guides.add(guide4);
        guides.add(guide5);

        Group group1 = new Group("123", 8, 18, 40,guide1);
        Group group2 = new Group("234", 9, 18, 45, guide2);
        Group group3 = new Group("345", 6, 9, 55,guide3);
        Group group4 = new Group("456", 7, 14, 55,guide4);
        Group group5 = new Group("567", 9, 18, 43,guide5);

        groups.add(group1);
        groups.add(group2);
        groups.add(group3);
        groups.add(group4);
        groups.add(group5);

        if(traveler1.askToJoin(group1))
            System.out.println(traveler1.getName() + " successfully joined group " +group1.getGroupId());
        else System.out.println(traveler1.getName() + " could not join group " +group1.getGroupId());

        if(traveler2.askToJoin(group1))
            System.out.println(traveler2.getName() + " successfully joined group " +group1.getGroupId());
        else System.out.println(traveler2.getName() + " could not join group " +group1.getGroupId());

        if(traveler3.askToJoin(group2))
            System.out.println(traveler3.getName() + " successfully joined group " +group2.getGroupId());
        else System.out.println(traveler3.getName() + " could not join group " +group2.getGroupId());

        if(traveler4.askToJoin(group2))
            System.out.println(traveler4.getName() + " successfully joined group " +group2.getGroupId());
        else System.out.println(traveler4.getName() + " could not join group " +group2.getGroupId());

        if(traveler5.askToJoin(group3))
            System.out.println(traveler5.getName() + " successfully joined group " +group3.getGroupId());
        else System.out.println(traveler5.getName() + " could not join group " +group3.getGroupId());

        if(traveler6.askToJoin(group3))
            System.out.println(traveler6.getName() + " successfully joined group " +group3.getGroupId());
        else System.out.println(traveler6.getName() + " could not join group " +group3.getGroupId());

        if(traveler7.askToJoin(group4))
            System.out.println(traveler7.getName() + " successfully joined group " +group4.getGroupId());
        else System.out.println(traveler7.getName() + " could not join group " +group4.getGroupId());

        if(traveler8.askToJoin(group4))
            System.out.println(traveler8.getName() + " successfully joined group " +group4.getGroupId());
        else System.out.println(traveler8.getName() + " could not join group " +group4.getGroupId());

        if(traveler9.askToJoin(group5))
            System.out.println(traveler9.getName() + " successfully joined group " +group5.getGroupId());
        else System.out.println(traveler9.getName() + " could not join group " +group5.getGroupId());

        if(traveler10.askToJoin(group5))
            System.out.println(traveler10.getName() + " successfully joined group " +group5.getGroupId());
        else System.out.println(traveler10.getName() + " could not join group " +group5.getGroupId());

        traveler1.rateGuide(guide1, 10);
        traveler2.rateGuide(guide1, 9);
        traveler3.rateGuide(guide2, 10);
        traveler4.rateGuide(guide2, 8);
        traveler5.rateGuide(guide3, 7);
        traveler6.rateGuide(guide3, 9);
        traveler7.rateGuide(guide4, 10);
        traveler8.rateGuide(guide4, 10);
        traveler9.rateGuide(guide5, 9);
        traveler10.rateGuide(guide5, 9);

        Track track1 = guide1.createTrack("11111","Netanya", Difficulty.values()[3],18.5);
        System.out.println("\nThe guide " + guide1.getName() + " successfully created track No. " + track1.getTrackCode());
        Track track2 = guide2.createTrack("22222","Eilat", Difficulty.values()[4], 25);
        System.out.println("The guide " + guide2.getName() + " successfully created track No. " + track1.getTrackCode());
        Track track3 = guide3.createTrack("33333", "Dimona", Difficulty.values()[3], 20);
        System.out.println("The guide " + guide3.getName() + " successfully created track No. " + track1.getTrackCode());
        Track track4 = guide4.createTrack("44444", "Hadera", Difficulty.values()[2], 15);
        System.out.println("The guide " + guide4.getName() + " successfully created track No. " + track1.getTrackCode());
        Track track5 = guide5.createTrack("55555","Raanana", Difficulty.values()[1], 10);
        System.out.println("The guide " + guide5.getName() + " successfully created track No. " + track1.getTrackCode());

        GregorianCalendar date1 = new GregorianCalendar(year1,month1,day1, hour1,min1);
        GregorianCalendar date2 = new GregorianCalendar(year2,month2,day2, hour1,min2);
        GregorianCalendar date3 = new GregorianCalendar(year3,month3,day3, hour3,min3);
        GregorianCalendar date4 = new GregorianCalendar(year4,month4,day4, hour4,min4);
        GregorianCalendar date5 = new GregorianCalendar(year5,month5,day5, hour5,min5);

        guide1.changeLength(track5, 11);
        System.out.println("\n"+ guide1.getName() + " has changed the length of track No. " + track1.getTrackCode() + " to " + track5.getTrackLength());

        tracks.add(track1);
        tracks.add(track2);
        tracks.add(track3);
        tracks.add(track4);
        tracks.add(track5);

        group1.createGroupOnTrack(date1, track1);
        group2.createGroupOnTrack(date2, track2);
        group1.createGroupOnTrack(date3, track3);
        group1.createGroupOnTrack(date4, track4);
        group1.createGroupOnTrack(date5, track5);

        System.out.println("\nTravelers:");
        for(Traveler t: travelers) { t.displayTravelerInfo(); }
        System.out.println("Guides:");
        for(Guide g: guides) { g.displayGuideInfo(); }
        System.out.println("Groups:");
        for(Group g: groups) { g.displayGroupInfo(); }
        System.out.println("Tracks:");
        for(Track t: tracks) { t.displayTrackInfo(); }

        System.out.println("Average rating of guides:");
        System.out.println(guide1.getName() +": " + guide1.calcAvgRating());
        System.out.println(guide2.getName() +": " + guide2.calcAvgRating());
        System.out.println(guide3.getName() +": " + guide3.calcAvgRating());
        System.out.println(guide4.getName() +": " + guide4.calcAvgRating());
        System.out.println(guide5.getName() +": " + guide5.calcAvgRating());

        System.out.println("\nTotal num of travelers in track " + track1.getTrackCode() + " is " + track1.calcNumOfTravelersInTrack());
        System.out.println("Total num of travelers in track " + track2.getTrackCode() + " is " + track2.calcNumOfTravelersInTrack());
        System.out.println("Total num of travelers in track " + track3.getTrackCode() + " is " + track3.calcNumOfTravelersInTrack());
        System.out.println("Total num of travelers in track " + track4.getTrackCode() + " is " + track4.calcNumOfTravelersInTrack());
        System.out.println("Total num of travelers in track " + track5.getTrackCode() + " is " + track5.calcNumOfTravelersInTrack());

        System.out.println( "\nGroup No. " + group1.getGroupId() + " is scheduled to use track No. " + track1.getTrackCode() + " at " + date1.getTime());
        System.out.println( "Group No. " + group2.getGroupId() + " is scheduled to use track No. " + track2.getTrackCode() + " at " + date2.getTime());
        System.out.println( "Group No. " +group3.getGroupId() + " is scheduled to use track No. " + track3.getTrackCode() + " at " + date3.getTime());
        System.out.println( "Group No. " +group4.getGroupId() + " is scheduled to use track No. " + track4.getTrackCode() + " at " + date4.getTime());
        System.out.println( "Group No. " +group5.getGroupId() + " is scheduled to use track No. " + track5.getTrackCode() + " at " + date5.getTime());

        /*
         * Allows command line interaction. Running only the code below instead of the above is recommended.
         */
        JFrame f = new JFrame();
        String name = JOptionPane.showInputDialog(f,"Enter your name");
        Trip application = new Trip(name);
    }
}
