package servlets;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

import service.PatientInformationManager;
import servlets.helper.PatientHelper;

import dto.Patient;

import Util.Constants;
import Util.JSONUtils;

/**
 * Servlet implementation class AjaxSevlet
 */
@WebServlet("/AjaxSevlet")
public class AjaxSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Ajax success");
		if (request.getParameter("jsonData") == null) {
			JSONObject obj = new JSONObject();
			obj.put("Result", "fail");
			obj.put("Reason", "Unable to get request");
			writeToClient(response, obj);
			return;
		}
		JSONParser parser = new JSONParser();
		JSONObject jsonData;
		try {
			jsonData = (JSONObject) parser.parse(request.getParameter("jsonData"));
		} catch (Exception ex) {
			JSONObject obj = new JSONObject();
			obj.put("Result", "Fail");
			obj.put("Reason", "Unable to parse request");
			writeToClient(response, obj);
			return;
		}
		String requestType = (String) jsonData.get("requestType");
		if (requestType == null) {

		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		if (requestType.equals("SearchPatient")) {
			PatientHelper ph = new PatientHelper();
			JSONObject obj = ph.SearchPatient((JSONObject) jsonData.get("patient"));
			obj.put("Result", "Success");
			writeToClient(response, obj);
		} else if (requestType.equals("GetPatientData")) {
			Object pId = jsonData.get("patientId");
			PatientHelper ph = new PatientHelper();
			if (pId instanceof String) {
				writeToClient(response, ph.getPatientInformation((String) pId));
			} else {
				JSONObject obj = new JSONObject();
				obj.put("Result", "Fail");
				obj.put("Reason", "Patient Id shoyld be of type string");
				writeToClient(response, obj);
			}
		}
	}

	private void writeToClient(HttpServletResponse response, JSONObject jsonObject) throws IOException {
		response.getWriter().write(jsonObject.toJSONString());
		response.getWriter().close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
	}
}
