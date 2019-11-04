package MAC_Facility.model;

import MAC_Facility.data.FacilityDAO;

public class Facility {

	private static final long serialVersionUID = 3L;
	public String idfacility;
	public String facility_name;
	public String Duration;
	public String Type;
	

	public String duration;
	public String interval;
	
	


	public void setFacility (String idfacility, String facility_name,String Type , String duration, String interval) {
		setIDFacility(idfacility);
		setFacility_name(facility_name);
		setType(Type);
		setInterval(interval);
		setDuration(duration);
	
	
	}

	public Facility(String facilityType, String name, String interval, String duration, String venue) {
		this.Type = facilityType;
		this.facility_name = name;
		this.interval = interval;
		this.duration = duration;
		this.Type = venue;
	}
	
	
	public Facility(String facilityType, String name) {
		this.Type = facilityType;
		this.facility_name = name;
	}


	public Facility(String name) {
		// TODO Auto-generated constructor stub
		this.facility_name = name;
	}
	
	public Facility() {
		
	}
	
	public String getIDFacility() {
		return idfacility;
	}
	public void setIDFacility(String idfacility) {
		this.idfacility = idfacility;
	}
	
	public String getfacility_name() {
		return facility_name;
	}
	public void setFacility_name(String facility_name) {
		this.facility_name = facility_name;
	}
//	public String getDuration() {
//		return Duration;
//	}
	public void setType(String Type) {
        this.Type = Type;
	}
	
	public String getType() {
		return Type;
	}
	
	
	
	public String getIdfacility() {
		return idfacility;
	}

	public void setIdfacility(String idfacility) {
		this.idfacility = idfacility;
	}




	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public String getFacility_name() {
		return facility_name;
	}

	public void validateFacility (String action, Facility facility, FacilityError errorMsgs) {
		if (action.equals("InsertFacility")) {
			errorMsgs.setFacilityIDError(validateIDFacility(action,facility.getIDFacility()));
			errorMsgs.setFacilityNameError(validateFacility_name(action,facility.getfacility_name()));
			/*errorMsgs.setTypeError(validateType(facility.getType()));
			errorMsgs.setDurationError(validateDuration(facility.getDuration()));
			errorMsgs.setIntervalError(validateInterval(facility.getDuration()));*/
			errorMsgs.setErrorMsg();
		}}
		/*else
			if (action.equals("InsertFacility")) {
				if (facility_name.equals("") && idfacility.equals("")) 
					errorMsgs.setFacilityNameError("Both Facility Name and Facity ID cannot be blank");
				else
					if (!idfacility.equals("")&& !facility_name.equals(""))
						errorMsgs.setFacilityIDError(validateIDFacility(action, idfacility));
				errorMsgs.setErrorMsg();	
				
			}else if(action.equalsIgnoreCase("InsertFacility")) {
				// need to validate
				errorMsgs.setErrorMsg();}
			}
			else { //action=searchEmployee
				if (idfacility.equals(" ")) 
					errorMsgs.setFacilityIDError("Facility ID cannot be blank");
				else
					errorMsgs.setFacilityIDError(validateIdcompany(action,idfacility));
				errorMsgs.setErrorMsg();
			}*/
		
	

	public static String validateIDFacility(String action, String idfacility) {
		String result="";
		//FacilityError error = new FacilityError();
		//System.out.println("Inside validateIDFacility ");
		if (idfacility.isEmpty())
			result = "Should not be empty";
		else
			if (Character.isLowerCase(idfacility.charAt(0)))
				result="Must be capital";
		else
			if (!stringSize(idfacility,3,20))
				result = "Your facility ID must between 3 and 20 digits";
		
			
		
		return result;	
	}
	
	public static String validateFacility_name(String action,String facility_name) {
		String result="";
		if (facility_name.isEmpty())
			result = "Should not be empty";
		else
			if (Character.isLowerCase(facility_name.charAt(0)))
				result="Must be capital";
		else
			if (!stringSize(facility_name,3,20))
				result = "Your facility_name must between 3 and 20 digits";
		
		return result;		
	}
	
	/*private String validateType(String Type) {
		String result="";
		if (!stringSize(Type,3,45))
			result="Type Should be arounf 3 to 24 characters";
		else
			if (Character.isLowerCase(facility_name.charAt(0)))
				result="Must not be empty";
		
		return result;		
	}*/
	
	
	/*private String validateInterval(String Interval) {
		String result=" ";
		if (!stringSize(Interval,3,45))
			result="Type Should be arounf 3 to 24 characters";
		else
			if (Character.isLowerCase(facility_name.charAt(0)))
				result="Must not be empty";
		
		
		return result;		
	}*/

//	This section is for general purpose methods used internally in this class
	
	private static boolean stringSize(String string, int min, int max) {
		return string.length()>=min && string.length()<=max;
	}
//	private boolean isTextAnInteger (String string) {
//        boolean result;
//		try
//        {
//            Long.parseLong(string);
//            result=true;
//        } 
//        catch (NumberFormatException e) 
//        {
//            result=false;
//        }
//		return result;
//	}

}

