public class RateGuide
{
   private int rating;
   private Traveler traveler;
   private Guide guide;

   public int getRating() { return rating; }
   public void setRating(int rating) { this.rating = rating;}
   public Traveler getTraveler() {
      return traveler;
   }
   public void setTraveler(Traveler traveler)
   {
      this.traveler = traveler;
   }
   public Guide getGuide() {
      return guide;
   }
   public void setGuide(Guide guide)
   {
      this.guide = guide;
   }

   public RateGuide(int rating, Traveler traveler, Guide guide)
   {
      this.rating = rating;
      this.traveler = traveler;
      this.guide = guide;
   }

}
