package com.revature.reimbapi.services;

import com.revature.reimbapi.daos.ERS_RoleDAO;
import com.revature.reimbapi.daos.ERS_UserDAO;
import com.revature.reimbapi.dtos.requests.LoginRequest;
import com.revature.reimbapi.dtos.requests.NewUserRequest;
import com.revature.reimbapi.dtos.responeses.Principal;
import com.revature.reimbapi.models.ERS_User;
import com.revature.reimbapi.utils.customexceptions.InvalidRequestException;
import com.revature.reimbapi.utils.customexceptions.NotFoundException;
import com.revature.reimbapi.utils.customexceptions.ResourceConflictException;

import java.util.List;
import java.util.UUID;

public class ERS_UserService {
    private final ERS_UserDAO userDAO;
    private final ERS_RoleService roleService = new ERS_RoleService(new ERS_RoleDAO()); //move to better location.

    public ERS_UserService(ERS_UserDAO userDAO) {
        this.userDAO = userDAO;

    }

    public Principal registerUser(NewUserRequest newUserRequest) {
        if(!isValidUsername(newUserRequest.getUsername())) {throw new InvalidRequestException("Username must be 3-15 characters long and can use only letters, numbers, or _ and -.");}
        if(!isUsernameAvailable(newUserRequest.getUsername())) {throw new ResourceConflictException("Username is already taken.");}
        if(!isValidPassword(newUserRequest.getPassword())) {throw new InvalidRequestException("Password must be at least 8 characters long and contain, upper and lower case letters, numbers, and special characters.");}
        if(!isValidEmail(newUserRequest.getEmail())) {throw new InvalidRequestException("Invalid email address.");}
        if(!isEmailAvailable(newUserRequest.getEmail())) {throw new ResourceConflictException("Email is already taken.");}
        if(!isValidGivenName(newUserRequest.getGivenName())) {throw new InvalidRequestException("First name must be 3 -20 characters long and can not contain any numbers or special symbols.");}
        if(!isValidSurname(newUserRequest.getSurname())) {throw new InvalidRequestException("Last name must be 3 -20 characters long and can not contain any numbers or special symbols.");}

        ERS_User newUser = new ERS_User(String.valueOf(UUID.randomUUID()), newUserRequest.getUsername(), newUserRequest.getPassword(), newUserRequest.getEmail(), newUserRequest.getGivenName(), newUserRequest.getSurname(), false, "E");

        try{
            userDAO.save(newUser);
            return new Principal(newUser.getUserId(), newUser.getUsername(), "Employee");

        } catch(Exception e) {
            e.printStackTrace();

        }

        return null;

    }

    public Principal Login(LoginRequest loginRequest) {
        ERS_User user = userDAO.getUserByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());

        if(user == null) { throw new NotFoundException("User not found."); }

        String role = roleService.getRoleById(user.getRoleId());

        return new Principal(user.getUserId(), user.getUsername(), role);

    }


    public boolean isValidUsername(String username) {
        return username.matches("^[a-zA-Z0-9_-]{3,15}$");
    }

    public boolean isUsernameAvailable(String username) {

        return !userDAO.doesUserExistUsername(username);

    }

    public boolean isEmailAvailable(String email) {

        return !userDAO.doesUserExistEmail(email);

    }

    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-])[^\\s ].{8,}$");
    }

    public boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9][A-Za-z0-9!#$%&'*+\\-/=?^_`{}|]{0,63}@[A-Za-z0-9.-]{1,253}.[A-Za-z]{2,24}$");
    }

    public boolean isValidGivenName(String givenName) {
        return givenName.matches("^[a-zA-Z]{3,20}$");
    }

    public boolean isValidSurname(String surname) {
        return surname.matches("^[a-zA-Z]{3,20}$");
    }

    public List<ERS_User> getUnactivatedUsers() {

        return userDAO.getAllUnactivated();

    }

    public void ActivateUser(String username, boolean activate) {
        if(isUsernameAvailable(username)) {throw new NotFoundException("User does not exist.");}

        userDAO.updateActivation(username, activate);

    }

}
