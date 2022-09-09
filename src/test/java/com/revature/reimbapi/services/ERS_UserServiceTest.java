package com.revature.reimbapi.services;

import com.revature.reimbapi.daos.ERS_UserDAO;
import com.revature.reimbapi.dtos.requests.NewUserRequest;
import com.revature.reimbapi.models.ERS_User;
import com.revature.reimbapi.utils.customexceptions.InvalidRequestException;
import com.revature.reimbapi.utils.customexceptions.NotFoundException;
import com.revature.reimbapi.utils.customexceptions.ResourceConflictException;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class ERS_UserServiceTest {
    ERS_UserService sut;
    ERS_UserDAO mockDAO = mock(ERS_UserDAO.class);

    @Before
    public void setUp() throws Exception {
        this.sut = new ERS_UserService(mockDAO);
    }


    @Test
    public void testIsValidUsername() {
        String vaildName = "Honey";
        Assert.assertTrue(sut.isValidUsername(vaildName));
    }


    @Test
    public void testIsValidPassword() {
        String invalidPassword = "   A        9    *   z   ";
        Assert.assertFalse(sut.isValidPassword(invalidPassword));
    }

    @Test
    public void testIsValidEmail() {
        String email = "Test@gmail.com";

        Assert.assertTrue(sut.isValidEmail(email));

    }

    @Test
    public void testIsValidGivenName() {
        String validGivenName = "George";
        Assert.assertTrue(sut.isValidGivenName(validGivenName));
    }

    @Test
    public void testIsValidSurname() {
        String validSurname = "Brown";
        Assert.assertTrue(sut.isValidGivenName(validSurname));

    }

    @Test
    public void testActivateUser_SpeaksToDAO() {
        String testUsername = "Tester";
        boolean testActivate = true;

        when(sut.isUsernameAvailable(testUsername)).thenReturn(true);

        sut.ActivateUser(testUsername, testActivate);

        verify(mockDAO).updateActivation(testUsername, testActivate);

    }

    @Test (expected = NotFoundException.class)
    public void testActivateUser_ThrowsException() {
        String testUsername = "Tester";
        boolean testActivate = true;
        sut.ActivateUser(testUsername, testActivate);
    }
    @Test (expected = NullPointerException.class)
    public void getUnactivatedUsers() {
        sut.getUnactivatedUsers();

    }

    @Test
    public void isValidSurname() {
        String invalidSurname = "Jackson  OReily ";

        Assert.assertFalse(sut.isValidSurname(invalidSurname));
    }

    @Test
    public void isUsernameAvailable_SpeaksToDAO() {
        String testUsername = "Username";
        when(mockDAO.doesUserExistUsername(testUsername)).thenReturn(true);
        sut.isUsernameAvailable(testUsername);
        verify(mockDAO).doesUserExistUsername(testUsername);

    }

    @Test
    public void isEmailAvailable_SpeaksToDAO() {
        String testEmail = "Email@email.com";
        when(mockDAO.doesUserExistEmail(testEmail)).thenReturn(true);
        sut.isEmailAvailable(testEmail);
        verify(mockDAO).doesUserExistEmail(testEmail);

    }

    @Test
    public void isValidNewUser_MockTest() {
        NewUserRequest newUserRequest = new NewUserRequest("Username", "Email", "Password", "Password", "Tim", "Fin");
        
        when(sut.isValidUsername(newUserRequest.getUsername())).thenReturn(true); 
        when(sut.isUsernameAvailable(newUserRequest.getUsername())).thenReturn(true); 
        when(sut.isValidPassword(newUserRequest.getPassword())).thenReturn(true); 
        when(sut.isValidEmail(newUserRequest.getEmail())).thenReturn(true); 
        when(sut.isEmailAvailable(newUserRequest.getEmail())).thenReturn(true); 
        when(sut.isValidGivenName(newUserRequest.getGivenName())).thenReturn(true); 
        when(sut.isValidSurname(newUserRequest.getSurname())).thenReturn(true);

        Assert.assertTrue(sut.isValidNewUser(newUserRequest));

    }

    @Test
    public void isValidNewUser() {
        NewUserRequest newUserRequest = new NewUserRequest("Username", "Email@email.com", "Password0@", "Password0@", "Tim", "Fin");


        Assert.assertTrue(sut.isValidNewUser(newUserRequest));

    }

}