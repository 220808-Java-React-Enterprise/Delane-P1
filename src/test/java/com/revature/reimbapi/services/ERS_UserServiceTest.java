package com.revature.reimbapi.services;

import com.revature.reimbapi.daos.ERS_UserDAO;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;

import static org.mockito.Mockito.mock;

public class ERS_UserServiceTest extends TestCase {
    ERS_UserService sut;
    ERS_UserDAO mockDAO = mock(ERS_UserDAO.class);

    @Before
    public void setUp() throws Exception {
        this.sut = new ERS_UserService(mockDAO);
    }

    public void testRegisterUser() {
    }

    public void testLogin() {
    }

    public void testIsValidUsername() {
        String vaildName = "Honey";
        Assert.assertTrue(sut.isValidUsername(vaildName));
    }

    public void testIsUsernameAvailable() {
    }

    public void testIsValidPassword() {
    }

    public void testIsValidEmail() {
        String email = "Test@gmail.com";

        Assert.assertTrue(sut.isValidEmail(email));

    }

    public void testIsValidGivenName() {
        String validGivenName = "George";
        Assert.assertTrue(sut.isValidGivenName(validGivenName));
    }

    public void testIsValidSurname() {
        String validSurname = "Brown";
        Assert.assertTrue(sut.isValidGivenName(validSurname));

    }

    //public void testGetUnactivatedUsers() {}

    public void testActivateUser() {
    }
}