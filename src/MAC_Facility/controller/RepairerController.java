package MAC_Facility.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import MAC_Facility.data.RepairerDAO;
import MAC_Facility.model.*;

@WebServlet("/RepairerController")
public class RepairerController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("uname");
		String user_id = username;
		Repairer repairer = new Repairer();
		ArrayList<Repairer> repairerList = new ArrayList<Repairer>();
		RepairerDAO repairerDAO = new RepairerDAO();
		try {
			repairerList = repairerDAO.searchRepairer(user_id);
		} catch (SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (null != repairer){
			request.setAttribute("habits", repairerList);
			session.setAttribute("repairerList", repairerList);
			getServletContext().getRequestDispatcher("/Repairer_home.jsp").forward(request, response);
		}

	}
}