package com.revature.reimbapi.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbapi.daos.ERS_ReimbursementDAO;
import com.revature.reimbapi.daos.ERS_UserDAO;
import com.revature.reimbapi.services.ERS_ReimbursementService;
import com.revature.reimbapi.services.ERS_UserService;
import com.revature.reimbapi.services.TokenService;
import com.revature.reimbapi.servlets.AuthenticationServlet;
import com.revature.reimbapi.servlets.EmployeeReimbursementsServlet;
import com.revature.reimbapi.servlets.ReimbursementServlet;
import com.revature.reimbapi.servlets.UserServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//So the contextloadlistener takes over the request routing from the web.xml?

public class ContextLoadListener implements ServletContextListener {
    @Override   //This creates the program on every startup?
    public void contextInitialized(ServletContextEvent sce) {
        //This is where all the servlets will be initialized.

        //First the ObjectMapper (Brought to you by Jackson databind)
        ObjectMapper mapper = new ObjectMapper();

        //Second is initializing any and all servlets.
        UserServlet userServlet = new UserServlet(mapper, new ERS_UserService(new ERS_UserDAO()));
        AuthenticationServlet authenticationServlet = new AuthenticationServlet(mapper, new TokenService(new JwtConfig()), new ERS_UserService(new ERS_UserDAO()));
        ReimbursementServlet reimbursementServlet = new ReimbursementServlet(mapper, new ERS_ReimbursementService(new ERS_ReimbursementDAO()));
        EmployeeReimbursementsServlet employeeReimbursementsServlet = new EmployeeReimbursementsServlet(mapper, new ERS_ReimbursementService(new ERS_ReimbursementDAO()));

        //Third is initializing a ServletContext object. NOTE: to lookup.
        ServletContext context = sce.getServletContext();

        //Fourth is mapping the servlets?
        context.addServlet("UserServlet", userServlet).addMapping("/user/*");
        context.addServlet("AuthenticationServlet", authenticationServlet).addMapping("/user/login/auth");
        context.addServlet("EmployeeReimbursementServlet", employeeReimbursementsServlet).addMapping("/empreimb/*");
        context.addServlet("ReimbursementServlet", reimbursementServlet).addMapping("/postreimbrequest"); //temp for testing


        //Leaving this to see what it does.
        ServletContextListener.super.contextInitialized(sce);
    }

    @Override   //This destroys the program on every exit?
    public void contextDestroyed(ServletContextEvent sce) {

        System.out.println("Shutingdown the application... Thank you for using Reimbursement Api.");

        //Leaving this to see what it does.
        ServletContextListener.super.contextDestroyed(sce);
    }
}
