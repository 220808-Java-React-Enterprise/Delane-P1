package com.revature.reimbapi.services;

import com.revature.reimbapi.daos.ERS_RoleDAO;
import com.revature.reimbapi.utils.customexceptions.ResourceConflictException;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ERS_RoleServiceTest {

    private ERS_RoleDAO mockDAO = mock(ERS_RoleDAO.class);
    private ERS_RoleService sut = new ERS_RoleService(mockDAO);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testGetRoleById_SpeaksToDAO() {
        String testId = "E";
        when(mockDAO.getRoleById(testId)).thenReturn("Employee");
        sut.getRoleById(testId);
        verify(mockDAO).getRoleById(testId);
    }


    @Test (expected = ResourceConflictException.class)
    public void testGetRoleById_ThrowsException() {
        String testId = "Default";
        sut.getRoleById(testId);
    }

}