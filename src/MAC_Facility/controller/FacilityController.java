package MAC_Facility.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MAC_Facility.data.FacilityDAO;
import MAC_Facility.model.Facility;
import MAC_Facility.model.FacilityError;
import MAC_Facility.model.MARForm;
import MAC_Facility.model.MARForm1;

@WebServlet("/FacilityController")
public class FacilityController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private void getCompanyParam(HttpServletRequest request, Facility facility) {
		facility.setFacility(request.getParameter("idfacility"), request.getParameter("facility_name"),
				request.getParameter("type"), request.getParameter("duration"), request.getParameter("interval"));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		session.removeAttribute("errorMsgs");

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action"), url = "";
		HttpSession session = request.getSession();
		Facility facility = new Facility();
		FacilityError CerrorMsgs = new FacilityError();
		int selectedFacilityIndex;
		session.removeAttribute("errorMsgs");

		if (action.equalsIgnoreCase("listfacilities")) {
			getCompanyParam(request, facility);
			ArrayList<Facility> result = null;
			try {
				result = FacilityDAO.listfacilities1("listfacilities");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.removeAttribute("facility");
			session.setAttribute("FACILITIES", result);
			url = "/listCompany.jsp";

		} else if (action.equalsIgnoreCase("ADDFacility")) {
			session.removeAttribute("errorMsgs");
			FacilityError EerrorMsgs = new FacilityError();
			session.setAttribute("errorMsgs", EerrorMsgs);

			url = "/ADDFacility.jsp";

		} else if (action.equalsIgnoreCase("InsertFacility")) {
			String facilityID = request.getParameter("idfacility");
			String name = request.getParameter("facility_name");
			String interval = request.getParameter("interval");
			String duration = request.getParameter("duration");
			String type=request.getParameter("Type");
			getCompanyParam(request, facility);
			session.setAttribute("errorMsg", CerrorMsgs);
			facility.validateIDFacility(action, facilityID);
			System.out.print(facility.validateIDFacility(action, facilityID));
			facility.validateFacility(action, facility, CerrorMsgs);
			//session.setAttribute("facility", facility);
			if (CerrorMsgs.getErrorMsg().equals(""))
			{	getCompanyParam(request, facility);
				FacilityDAO.insertFacility(facility);
				url="/listCompany.jsp";
				
				System.out.println("Successfully Added");
					
				
			}else {
				
				getCompanyParam(request,facility);
				session.setAttribute("errorMsg", CerrorMsgs);
				CerrorMsgs.setErrorMsg("Invalid Facility ID or Name");
				url="/ADDFacility.jsp";
				System.out.println("UnSuccessfully ");
				
			
				
				}
			/*getCompanyParam(request,facility);
			facility.validateFacility(action,facility,CerrorMsgs);
			session.setAttribute("company", facility);
			if (!CerrorMsgs.getErrorMsg().equals("")) {// if error messages
				getCompanyParam(request,facility);
				session.setAttribute("errorMsgs", CerrorMsgs);
				url="/listCompany.jsp";
			}
			else {// if no error messages
				FacilityDAO.insertFacility(facility);
				
				url="/fmHome.jsp";
			*/
		}

		
		 else if (action.equalsIgnoreCase("searchMAR")) {

			ArrayList<MARForm> result = FacilityDAO.searchMAR("searchMAR");
			session.removeAttribute("MARS");
			session.setAttribute("MARS", result);
			url = "/viewUnassignedMAR.jsp";
		} else if (action.equalsIgnoreCase("searchAssignedMAR")) {

			ArrayList<MARForm> result = FacilityDAO.searchAssignedMAR();
			session.removeAttribute("MARS");
			session.setAttribute("MARS", result);
			url = "/viewAssigned.jsp";

		} else if (action.equalsIgnoreCase("DatedMAR")) {
			ArrayList<MARForm> result = FacilityDAO.searchDatedAssignedMAR(request.getParameter("date"));
			session.removeAttribute("MARS");
			session.setAttribute("MARS", result);
			url = "/viewAssigned.jsp";

		} else if (action.equalsIgnoreCase("typeMAR")) {
			ArrayList<MARForm> result = FacilityDAO.searchTypeAssignedMAR(request.getParameter("type"));
			session.removeAttribute("MARS");
			session.setAttribute("MARS", result);
			url = "/viewAssigned.jsp";

		} else if (action.equalsIgnoreCase("assignMAR")) {
			ArrayList<String> result1 = FacilityDAO.getRepairers();
			ArrayList<String> result2 = FacilityDAO.getUnassignedMARList();
			session.removeAttribute("repairerList");
			session.setAttribute("repairerList", result1);
			session.removeAttribute("marList");
			session.setAttribute("marList", result2);
			url = "/assignMAR.jsp";

		} else if (action.equalsIgnoreCase("assignRepairer")) {
			String repairer = request.getParameter("repairer");
			String facilityNew = request.getParameter("facility");
			String date = request.getParameter("date");
			String time = request.getParameter("time");
			if(repairer==null &facilityNew==null &date==null &time==null) {
				url = "/error_assignRepair.jsp?name='"+repairer+"'&flag=INMAR";
			}
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
			String strDate = dateFormat.format(new Date());
			Date now = new Date(); 
			Date weekStartDate = getWeekStartDate(now);
			Date weekEndDate = getWeekEndDate(now);

			int assignWeekCount = FacilityDAO.assignWeekCount(repairer, dateFormat.format(weekStartDate),dateFormat.format(weekEndDate));
			if(assignWeekCount < 10) {
				int assignDayCount = FacilityDAO.assignDayCount(repairer, strDate);
				if (assignDayCount < 5) {
					FacilityDAO.assignRepairer(request.getParameter("facility"),request.getParameter("repairer"));
					url = "/fmHome.jsp";
				} else {
					url = "/error_assignRepair.jsp?name='"+repairer+"'&flag=Day";
				}
			}else {
				url = "/error_assignRepair.jsp?name='"+repairer+"'&flag=Week";
			}
		} else if (action.equalsIgnoreCase("changeMAR")) {
			ArrayList<String> result = FacilityDAO.getUnassignedMARList();
			session.removeAttribute("marList");
			session.setAttribute("marList", result);
			url = "/updateMAR.jsp";

		} else if (action.equalsIgnoreCase("updateMAR")) {
			FacilityDAO.updateMAR(request.getParameter("facility"), request.getParameter("urgency"),
					request.getParameter("time"));
			url = "/fmHome.jsp";

		} else if (action.equalsIgnoreCase("viewParticularFacility")) {
			String view = request.getParameter("id");
			System.out.print(view);
			ArrayList<Facility> result=null;
			try {
				result= FacilityDAO.viewParticularFacility(request.getParameter("id"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.removeAttribute("facility");
			session.setAttribute("FACILITIES", result);

			url = "/viewParticular.jsp";

		} else {
			session.setAttribute("facility", facility);
			session.setAttribute("errorMsgs", CerrorMsgs);
			url = "/searchMAR.jsp";
		}

		System.out.println("URL: " + url);
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	public static Date getWeekStartDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			calendar.add(Calendar.DATE, -1);
		}
		return calendar.getTime();
	}

	public static Date getWeekEndDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			calendar.add(Calendar.DATE, 1);
		}
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}
	/*private Object getFacilityForm(HttpServletRequest request) {

		String facilityID = request.getParameter("idfacility");
		String name = request.getParameter("facility_name");
		String interval = request.getParameter("interval");
		String duration = request.getParameter("duration");
		String Type = request.getParameter("Type");
		FacilityError error = FacilityError.validate(name, duration);
		if (error != null) {
			return error;
		} else {
			return new Facility();
		}
	}*/

}
