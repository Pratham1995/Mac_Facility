package MAC_Facility.data;

import java.awt.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



import MAC_Facility.model.Repairer;
import MAC_Facility.util.SQLConnection;


public class RepairerDAO {
	
	static SQLConnection DBMgr = SQLConnection.getInstance();
	public ArrayList<Repairer> searchRepairer(String assignedTo) throws SQLException, ClassNotFoundException {
		Statement stmt = null;   
		Class.forName("com.mysql.jdbc.Driver"); 
		Connection conn = SQLConnection.getDBConnection();
		String query = "SELECT * FROM mac_facility.assigned_repairs where assignedTo = '"+assignedTo+"';";
		System.out.println("User Search Query ..."+query);
	      ArrayList<Repairer> repairerList=new ArrayList<Repairer>();  
		try {
			conn = SQLConnection.getDBConnection();  
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(query);
			System.out.println(results);
			while(results.next()) {
				Repairer repairer = new Repairer();
				repairer.setId(results.getString("idAssigned_Repairs"));
				repairer.setFacility_name(results.getString("facilityName"));
				repairer.setFacility_type(results.getString("facilityType"));
				repairer.setStartDate(results.getDate("startDate"));
				repairer.setStartTime(results.getString("startTime"));
				repairer.setFloor(results.getString("floor"));
				repairerList.add(repairer);
			}
			System.out.println(repairerList);
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			stmt.close();
			conn.close();
		}
		return repairerList;
	}
	
	
	public static ArrayList<Repairer> viewRepairer(String fcType,String fcName,String stDate,String stTime){  
    	ArrayList<Repairer> list=new ArrayList<Repairer>();
        try{  
            Connection con = SQLConnection.getDBConnection();
            PreparedStatement ps=(PreparedStatement) con.prepareStatement("SELECT * FROM assigned_repairs WHERE facilityType='"+fcType+"' AND FacilityName='"+fcName+"' AND startTime='"+stTime+"' AND startDate='"+stDate+"' "); 
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
            	Repairer repairer=new Repairer();  
            	repairer.setId(rs.getString("idAssigned_Repairs"));
				repairer.setFacility_name(rs.getString("facilityName"));
				repairer.setFacility_type(rs.getString("facilityType"));
				repairer.setStartDate(rs.getDate("startDate"));
				repairer.setStartTime(rs.getString("startTime"));
				repairer.setFloor(rs.getString("floor"));
				repairer.setWing(rs.getString("wing"));
                list.add(repairer); 
            }
        }catch(Exception e){
        	System.out.println(e);
        }  
        return list;  
    }  
	
	public static int confirmRequest(String assignedTo,int uid){  
        int status=0;  
        try{  
        	Connection con = SQLConnection.getDBConnection(); 
            PreparedStatement ps=(PreparedStatement) con.prepareStatement("UPDATE assigned_repairs SET assignedTo='"+assignedTo+"' WHERE idAssigned_Repairs="+uid+" ");  
            status=ps.executeUpdate();  
        }catch(Exception e){
        	System.out.println(e);
        }  
        return status;  
    } 
	
	public static int cancelRequest(int uid){  
        int status=0;  
        try{  
        	Connection con = SQLConnection.getDBConnection(); 
            PreparedStatement ps=(PreparedStatement) con.prepareStatement("UPDATE assigned_repairs SET assignedTo='0' WHERE idAssigned_Repairs="+uid+" ");  
            status=ps.executeUpdate();  
        }catch(Exception e){
        	System.out.println(e);
        }  
        return status;  
    }
	
	public static int repairerResCheck(int uid){ 
		int count=0;
		try{  
            Connection con = SQLConnection.getDBConnection();
            PreparedStatement ps=(PreparedStatement) con.prepareStatement("SELECT COUNT(*) FROM assigned_repairs ar JOIN facility fc ON ar.facilityName=fc.FacilityName WHERE ar.idAssigned_Repairs='"+uid+"' AND fc.Duration='Same day' "); 
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
