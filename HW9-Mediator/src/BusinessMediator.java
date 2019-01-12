import javax.swing.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

/*======================================*/
/* This class acts as an mediator class */
/* in the mediator design pattern       */
/*======================================*/

public class BusinessMediator {

	private ArrayList<ParticipantGUI> companyList = new ArrayList<ParticipantGUI>();

	public void register(ParticipantGUI company) {
		companyList.add(company);
	}

	public void addPossibleCus(String option, String text) {
		// Add
		Iterator e = companyList.iterator();
		while (e.hasNext()) {
			ParticipantGUI c = (ParticipantGUI) e.next();
			if (option.compareTo(AirlineGUI.AIR) == 0) {
				if (c instanceof HotelGUI || c instanceof TourGUI || c instanceof TouriststoreGUI)
					c.addPossibleCustomer(text);
			} else if (option.compareTo(HotelGUI.HOTEL) == 0) {
				if (c instanceof AirlineGUI || c instanceof TourGUI || c instanceof TouriststoreGUI)
					c.addPossibleCustomer(text);
			} else if (option.compareTo(TourGUI.TOUR) == 0) {
				if (c instanceof HotelGUI || c instanceof AirlineGUI || c instanceof TouriststoreGUI)
					c.addPossibleCustomer(text);
			} else if (option.compareTo(TouriststoreGUI.TOURISTSTORE) == 0) {
				if (c instanceof HotelGUI || c instanceof AirlineGUI || c instanceof TourGUI)
					c.addPossibleCustomer(text);
			}
		}
	}

	// update all other User interfaces
	public void updateALlGuis(String option, String text) {
		Iterator e = companyList.iterator();
		while (e.hasNext()) {
			ParticipantGUI c = (ParticipantGUI) e.next();

			if (option.compareTo(AirlineGUI.AIR) == 0) {
				if (c instanceof HotelGUI || c instanceof TourGUI || c instanceof TouriststoreGUI)
					c.displayInfo(text);
			} else if (option.compareTo(HotelGUI.HOTEL) == 0) {
				if (c instanceof AirlineGUI || c instanceof TourGUI || c instanceof TouriststoreGUI)
					c.displayInfo(text);
			} else if (option.compareTo(TourGUI.TOUR) == 0) {
				if (c instanceof HotelGUI || c instanceof AirlineGUI || c instanceof TouriststoreGUI)
					c.displayInfo(text);
			} else if (option.compareTo(TouriststoreGUI.TOURISTSTORE) == 0) {
				if (c instanceof HotelGUI || c instanceof AirlineGUI || c instanceof TourGUI)
					c.displayInfo(text);
			}
		}
	}
}