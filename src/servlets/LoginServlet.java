package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.tools.internal.ws.processor.model.Response;

import dto.User;

import service.AuthenticationService;
import service.UserInformationManager;

import Util.Constants;
import Util.RoleURLMapper;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String clinicID, email, password;
		clinicID = request.getParameter("clinicID");
		email = request.getParameter("email");
		password = request.getParameter("password");
		request.setAttribute(Constants.CLINICID, clinicID);
		if (clinicID == null || clinicID.trim() == "" || email == null || email.trim() == "" || password == null
				|| password.trim() == "") {
			request.setAttribute(Constants.IS_LOGINERROR, true);
			dispatchRequest(request, response, "Login.jsp");
		} else {
			request.getSession().setAttribute(Constants.CLINICID, clinicID);
			AuthenticationService service = new AuthenticationService();
			if (service.AuthenticateUser(clinicID, email, password)) {
				UserInformationManager uim = new UserInformationManager();
				User user = uim.getUserInformation(email);
				if (user != null) {
					request.getSession().setAttribute(Constants.IS_AUTHENTICATED, true);
					request.getSession().setAttribute(Constants.USERINFO, user);
					response.sendRedirect(RoleURLMapper.getURL(user.getDefaultRole()));
					return;
				} else {
					dispatchRequest(request, response, "Login.jsp");
				}
			} else {
				request.setAttribute(Constants.IS_LOGINERROR, true);
				dispatchRequest(request, response, "Login.jsp");
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("Login.jsp");
	}

	public void dispatchRequest(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
