package com.revature.reimbapi.services;

import com.revature.reimbapi.daos.ERS_Reimbursements_statusDAO;
import com.revature.reimbapi.utils.customexceptions.InvalidTypeException;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ERS_Reimbursement_statusServiceTest {
    private ERS_Reimbursements_statusDAO mockDAO = mock(ERS_Reimbursements_statusDAO.class);
    private ERS_Reimbursement_statusService sut = new ERS_Reimbursement_statusService(mockDAO);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testGetStatusById_SpeaksToDAO() {
        String testId = "P";

        when(mockDAO.getStatusById(testId)).thenReturn("PENDING");

        sut.getStatusById(testId);

        verify(mockDAO).getStatusById(testId);
    }

    @Test (expected = InvalidTypeException.class)
    public void testGetStatusById_ThrowsException() {
        String testId = "P";

        sut.getStatusById(testId);
    }
}