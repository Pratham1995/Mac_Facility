package MAC_Facility.data;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



import MAC_Facility.model.Facility;
import MAC_Facility.model.MARForm;
import MAC_Facility.model.Repairer;
import MAC_Facility.util.SQLConnection;




public class FacilityDAO {
static SQLConnection DBMgr = SQLConnection.getInstance();
private static Facility fac;
	/*
	private static ArrayList<Facility> ReturnMatchingCompaniesList (String queryString, String action) {
		//ArrayList<Facility> FacilityListInDB = new ArrayList<Facility>();
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		ArrayList<Facility> FacilityListInDB = new ArrayList<Facility>();
		try {
			stmt = conn.createStatement();
			ResultSet companyList = stmt.executeQuery(queryString);
			if(action.equalsIgnoreCase("listfacilities")) {
				
				while (companyList.next()) {
					Facility fac = new Facility(); 
					fac.setIDFacility(companyList.getString("idfacility"));
					fac.setFacility_name(companyList.getString("facility_name"));
					fac.setInterval(companyList.getString("interval"));
					fac.setDuration(companyList.getString("duration"));
					fac.setType(companyList.getString("Type"));
					
					
					FacilityListInDB.add(fac);	
				}
				
				return FacilityListInDB;
				
			}
		} catch (SQLException e) {}
		
		return FacilityListInDB;
		
	}*/

	private static ArrayList<MARForm> ReturnMatchingMAR (String queryString, String action) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		ArrayList<MARForm> mar_list = new ArrayList<MARForm>();
		try {
			stmt = conn.createStatement();
			ResultSet companyList = stmt.executeQuery(queryString);
			while (companyList.next()) {
					MARForm mar = new MARForm();
					mar.setFacility_name(companyList.getString("facility_name"));
					mar.setFacility_type(companyList.getString("facility_type"));
					mar.setUrgency(companyList.getString("Urgency"));
					mar.setDescription(companyList.getString("description"));
					mar.setReportedBy(companyList.getString("reported_by"));
					mar.setFacility_name(companyList.getString("facility_name"));
					mar.setDate(companyList.getString("date"));
					mar.setTime(companyList.getString("time"));
					mar.setAssigned_to(companyList.getString("assigned_to"));
					mar.setMar(companyList.getString("mar_number"));
					
					
					mar_list.add(mar);
				}
		} catch (SQLException e) {}
		return mar_list;
	}

/*	
	private static void StoreListinDB (Facility facility,String queryString) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String insertfacility = queryString + " VALUES ('"  
					+ facility.getIDFacility()  + "','"
					+ facility.getfacility_name() + "','"		
					+ facility.getStartDate() + "','"
					+ facility.getStartTime() + "',"
					+ facility.getEndTime() + "',"
					+ facility.getFloor() + "',"
					+ facility.getWing() + "',"
					+ facility.getType() + "',"
					+ " SYSDATE())";
			stmt.executeUpdate(insertfacility);	
			conn.commit(); 
		} catch (SQLException e) {}
	}
*/
	
	private static void runQuery(boolean update, String queryString) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			System.out.println("Firing Query: "+queryString);
			if(update)
				stmt.executeUpdate(queryString);	
			else 
				stmt.execute(queryString);
			//System.out.println("Facility Added!");
			conn.commit(); 
		} catch (SQLException e) {}
	}
	
	public static ArrayList<String> getUnassignedMARList(){
		String queryString = "SELECT concat(mar_number, ' | ', facility_name, ' | ', facility_type) as detail FROM Mac_Facility.mar_details";
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		ArrayList<String> list = new ArrayList<String>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(queryString);
			while (rs.next()) {
				list.add(rs.getString("detail"));
			}
		} catch (SQLException e) {
			System.out.println("Error occured: "+e);
		}
		return list;
		
	}
	
	public static void assignRepairer(String record, String repairer) {
		String mar_num = record.split("\\|")[0].trim();
		String queryString = "update mar_details set assigned_to = '"+repairer+"' where mar_number='"+mar_num+"'";
		runQuery(true, queryString);
	}
	
	public static void updateMAR(String record, String urgency, String time) {
		String mar_num = record.split("\\|")[0].trim();
		String queryString = "update mar_details set urgency = '"+urgency+"', time='"+time+"' where mar_number='"+mar_num+"'";
		runQuery(true, queryString);
	}
	
	
	public static ArrayList<Facility>  listfacilities(String queryString, String Action) throws SQLException{  
		System.out.print(queryString);
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		ArrayList<Facility> facilities = new ArrayList<Facility>();
		try {
			stmt = conn.createStatement();
			ResultSet facilityList = stmt.executeQuery(queryString);
			while (facilityList.next()) {
					Facility fac = new Facility();
					fac.setIDFacility(facilityList.getString("idFacility"));
					fac.setFacility_name(facilityList.getString("FacilityName"));
					fac.setType(facilityList.getString("Type"));
					fac.setInterval(facilityList.getString("Interval"));
					fac.setDuration(facilityList.getString("Duration"));
					facilities.add(fac);
			}
		} catch (SQLException e) {
			System.out.println("Error occured: "+e);
		}
		return facilities;

		
	}
	
	//search companies
	public static ArrayList<Facility>  searchfacilities(String facility_name)  {  
		//	return ReturnMatchingCompaniesList(" SELECT * from FACILITY WHERE Name LIKE '%"+facility_name+"%' ORDER BY idfacility", "");
		return null;
	}
	
	//determine if companyID is unique
	public static Boolean FacilityUniqueID(String idFacility)  {  
			//return (ReturnMatchingCompaniesList(" SELECT * from  WHERE idfacility = '"+idFacility+"' ORDER BY Name").isEmpty(), "");
			return null;
	}
	//search company with company ID
	public static ArrayList<Facility>   searchfacility (String idfacility)  {  
			//return ReturnMatchingCompaniesList(" SELECT * from FACILITY WHERE idfacility = '"+idfacility+"' ORDER BY Name");
		
		return  null;
	}
	public static ArrayList<MARForm> searchMAR(String searchMAR){
		return ReturnMatchingMAR("SELECT * from mar_details where assigned_to is null","searchMAR");
		
	}
	
	public static ArrayList<String> getRepairers(){
		String queryString = "select username from all_users where role='repairer'";
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		ArrayList<String> list = new ArrayList<String>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(queryString);
			while (rs.next()) {
				list.add(rs.getString("username"));
			}
		} catch (SQLException e) {
			System.out.println("Error occured: "+e);
		}
		return list;
		
	}
	public static boolean checkFacility(String name) {

		Connection conn = SQLConnection.getDBConnection();
		boolean isValid = false;
		try {
			java.sql.PreparedStatement prepareStatement = conn.prepareStatement("select * from Facility where idFacility = ?");
			prepareStatement.setString(1, name);
			ResultSet results = prepareStatement.executeQuery();
			if (results.next()) {
				isValid = true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return isValid;
	}
	
	public static void insertFacility(Facility facility){
		StringBuilder sb = new StringBuilder("Insert into Facility values(");
		sb.append("\""+facility.getIdfacility()+"\",");
		sb.append("\""+facility.getfacility_name()+"\",");
		sb.append("\""+facility.getType()+"\",");
		sb.append("\""+facility.getInterval()+"\",");
		sb.append("\""+facility.getDuration()+"\"");
		sb.append(");");
		
		runQuery(true, sb.toString());
		/*Connection conn = SQLConnection.getDBConnection();
		boolean isValid = false;
		System.out.println(facility.getIdfacility());
		System.out.println(facility.getfacility_name());
		System.out.println(facility.getDuration());
		System.out.println(facility.getInterval());
		System.out.println(facility.getType());
		try {
			java.sql.PreparedStatement prepareStatement5 = conn
					.prepareStatement("insert into Facility values (?,?,?,?,?)");
			prepareStatement5.setString(1,facility.getIdfacility() );
			prepareStatement5.setString(2,facility.getFacility_name() );
			prepareStatement5.setString(3,facility.getDuration()) ;
			prepareStatement5.setString(4,facility.getInterval()) ;
			prepareStatement5.setString(5,facility.getType() );
			int row5 = prepareStatement5.executeUpdate();
		if (row5 > 0) {
			isValid = true;
		}
		conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println(e.getMessage());
		}
		return isValid;*/
		
	}
	
	public static ArrayList<Facility> listfacilities1(String listfacilities) throws SQLException {
		return listfacilities("select * from Facility","listfacilities");
	}
	
	public static ArrayList<MARForm> searchAssignedMAR(){
		return ReturnMatchingMAR("SELECT * from mar_details WHERE assigned_to is not null ","searchAssignedMAR");
	}
	
	public static ArrayList<MARForm> searchDatedAssignedMAR(String date){
		return ReturnMatchingMAR("SELECT * from mar_details WHERE date='"+date+"'","searchDatedAssignedMAR");
	}
	public static ArrayList<MARForm> searchTypeAssignedMAR(String type){
		return ReturnMatchingMAR("SELECT * from mar_details WHERE facility_type='"+type+"'","searchTypeAssignedMAR");
	}
	public static ArrayList<Facility> viewParticularFacility(String id) throws SQLException{
		return listfacilities("select * from Facility where idFacility='"+id+"' ","viewParticularFacility");
	}
	
	public static ArrayList<Facility> assignedMAR(){
		//return ReturnMatchingCompaniesList("SELECT * from mar_details WHERE assigned_to is not null", "assignedMAR");
		return null;
	}
	
	public static int assignDayCount(String assignName,String startDate){ 
		int count=0;
		try{  
            Connection con = SQLConnection.getDBConnection();
            PreparedStatement ps=(PreparedStatement) con.prepareStatement("SELECT COUNT(*) FROM Mac_facility.mar_details WHERE assignedTo='"+assignName+"' AND startDate='"+startDate+"' "); 
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){ 
            	count=rs.getInt(0);
            }
        }catch(Exception e){
        	System.out.println(e);
        }  
        return count;  
    }

	
	public static int assignWeekCount(String assignName,String startDate,String endDate){ 
		int count=0;
		try{  
            Connection con = SQLConnection.getDBConnection();
            PreparedStatement ps=(PreparedStatement) con.prepareStatement("SELECT COUNT(*) FROM Mac_facility.mar_details  WHERE startDate BETWEEN '"+startDate+"' AND '"+endDate+"' AND assignedTo='"+assignName+"' "); 
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){ 
            	count=rs.getInt(0);
            }
        }catch(Exception e){
        	System.out.println(e);
        }  
        return count;  
    }

}

