


public class Client{

   private static BusinessMediator mediator;

   public static void main(String args[])
   {
      try{
	     mediator = new BusinessMediator();

	  	 ParticipantGUI airLine = new AirlineGUI(mediator);
		 ParticipantGUI hotel = new HotelGUI(mediator);
	  	ParticipantGUI tour = new TourGUI(mediator);
	  	ParticipantGUI tourists = new TouriststoreGUI(mediator);
	  	 mediator.register(airLine);
	  	 mediator.register(hotel);
	  	 mediator.register(tour);
	  	 mediator.register(tourists);
	  }
	  catch (Exception ee){
	  	 ee. printStackTrace();
	  }
   }
}