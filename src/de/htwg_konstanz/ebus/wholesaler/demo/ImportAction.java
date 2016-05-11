package de.htwg_konstanz.ebus.wholesaler.demo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.htwg_konstanz.ebus.framework.wholesaler.api.bo.BOOrderCustomer;
import de.htwg_konstanz.ebus.framework.wholesaler.api.boa.OrderBOA;
import de.htwg_konstanz.ebus.framework.wholesaler.api.security.Security;
import de.htwg_konstanz.ebus.wholesaler.demo.util.Constants;

public class ImportAction implements IAction{

	
	/**
	   * The execute method is automatically called by the dispatching sequence of the {@link ControllerServlet}. 
	   * 
	   * @param request the HttpServletRequest-Object provided by the servlet engine
	   * @param response the HttpServletResponse-Object provided by the servlet engine
	   * @param errorList a Stringlist for possible error messages occured in the corresponding action
	   * @return the redirection URL
	   */
	public String execute(HttpServletRequest request, HttpServletResponse response, ArrayList<String> errorList)
	{
		// get the login bean from the session
		LoginBean loginBean = (LoginBean)request.getSession(true).getAttribute(Constants.PARAM_LOGIN_BEAN);

		// ensure that the user is logged in
		if (loginBean != null && loginBean.isLoggedIn())
		{
			// ensure that the user is allowed to execute this action (authorization)
			// at this time the authorization is not fully implemented.
			// -> use the "Security.RESOURCE_ALL" constant which includes all resources.
			if (Security.getInstance().isUserAllowed(loginBean.getUser(), Security.RESOURCE_ALL, Security.ACTION_READ))
			{
				return "import.jsp";
			}
			else{
					errorList.add("Authorization failed");
				
				// redirect to welcome
				return "welcome.jsp";
			}
			
		}
		else{
			// redirect to the login page
			return "login.jsp";	
		}
	}


	 /**
	   * Each action itself decides if it is responsible to process the corrensponding request or not.
	   * This means that the {@link ControllerServlet} will ask each action by calling this method if it
	   * is able to process the incoming action request, or not.
	   * 
	   * @param actionName the name of the incoming action which should be processed
	   * @return true if the action is responsible, else false
	   */
	public boolean accepts(String actionName) {
		// TODO Auto-generated method stub
		return actionName.equalsIgnoreCase(Constants.ACTION_IMPORT);
	}

}