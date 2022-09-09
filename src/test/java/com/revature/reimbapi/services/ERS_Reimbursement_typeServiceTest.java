package com.revature.reimbapi.services;

import com.revature.reimbapi.daos.ERS_Reimbursements_typeDAO;
import com.revature.reimbapi.utils.customexceptions.InvalidTypeException;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ERS_Reimbursement_typeServiceTest {

    private ERS_Reimbursements_typeDAO mockDAO = mock(ERS_Reimbursements_typeDAO.class);

    private ERS_Reimbursement_typeService sut = new ERS_Reimbursement_typeService(mockDAO);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testGetTypeById_SpeaksToDAO() {
        String testId = "T";

        when(mockDAO.getTypeById(testId)).thenReturn("TRAVEL");

        sut.getTypeById(testId);

        verify(mockDAO).getTypeById(testId);
    }

    @Test (expected = InvalidTypeException.class)
    public void testGetTypeById_ThrowsException() {
        String testId = "X";

        sut.getTypeById(testId);
    }


}