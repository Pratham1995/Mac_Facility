package MAC_Facility.model;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;

public class FacilityError {
	private String errorMsg;
	private String FacilityIDError;
	private String FacilityNameError;
	private String TypeError;
	private String DurationError;
	private String IntervalError;
	
	
	public FacilityError() {
		this.errorMsg = "";
		this.FacilityIDError = "";
		this.FacilityNameError = "";
		this.TypeError = "";
		this.DurationError = "";
		this.IntervalError="";
	}

	public String getIntervalError() {
		return IntervalError;
	}

	public void setIntervalError(String intervalError) {
		IntervalError = intervalError;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg() {
		if (!FacilityIDError.equals("") || !FacilityNameError.equals("") || !DurationError.equals(""))
			this.errorMsg = "Please correct the following errors";
	}
	public String getFacilityIDError() {
		return FacilityIDError;
	}
	public void setFacilityIDError(String FacilityIDError) {
		this.FacilityIDError = FacilityIDError;
	}
	public String getFacilityNameError() {
		return FacilityNameError;
	}
	public void setFacilityNameError(String FacilityNameError) {
		this.FacilityNameError = FacilityNameError;
	}
	public String getTypeError() {
		return TypeError;
	}
	public void setTypeError(String Typeerror) {
		this.TypeError = Typeerror;
	}
	public String getDurationError() {
		return DurationError;
	}
	public void setDurationError(String DurationError) {
		this.DurationError = DurationError;
	}

	

	public void setFacilityIDError(FacilityError validateIDFacility) {
		// TODO Auto-generated method stub
		
	}


	/*public static boolean validate(String name, String duration,String id) {
		boolean isValid = true;
		FacilityError error = new FacilityError();
		if (name.isEmpty() || name.length() < 3 || name.length() >15
				) {
			isValid = false;
			error.setFacilityNameError(
					"name is required and length should be in range of 3 and 10 (inclusive) and should have only Capital letters");
		}
		if(duration.isEmpty()||duration.length()<3||duration.length()>15) {
			isValid=false;
			error.setDurationError("Duartion is Required");
		}
		if(id.isEmpty()||id.length()<3||id.length()>15) {
			isValid=false;
			error.setFacilityIDError("ID is Required");
		}
		
		
		if (isValid) {
			return true;
		} else {
			return false;
		}
	}*/

	

	

}

