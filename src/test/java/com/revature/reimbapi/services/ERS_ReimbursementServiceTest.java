package com.revature.reimbapi.services;

import com.revature.reimbapi.daos.ERS_ReimbursementDAO;
import com.revature.reimbapi.dtos.requests.NewReimbursementRequest;
import com.revature.reimbapi.dtos.requests.UpdateReimbEmployeeRequest;
import com.revature.reimbapi.models.ERS_Reimbursement;
import com.revature.reimbapi.utils.customexceptions.NotFoundException;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class ERS_ReimbursementServiceTest {

    private  final ERS_ReimbursementDAO mockDAO = mock(ERS_ReimbursementDAO.class);

    private final ERS_ReimbursementService sut = new ERS_ReimbursementService(mockDAO);


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testVerifyReimbursementFormCompletion_True() {
        ERS_Reimbursement reimbursement = new ERS_Reimbursement(UUID.randomUUID(), BigDecimal.valueOf(347.00), Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), "This is a description.", null, "Payment", UUID.randomUUID(), UUID.randomUUID(), "P", "F");

        Assert.assertTrue(sut.verifyReimbursementFormCompletion(reimbursement));

    }

    @Test
    public void testVerifyReimbursementFormCompletion_False() {
        ERS_Reimbursement reimbursement = new ERS_Reimbursement(UUID.randomUUID(), BigDecimal.valueOf(347.00), Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), "This is a description.", null, "Payment", null, UUID.randomUUID(), "P", "F");

        Assert.assertFalse(sut.verifyReimbursementFormCompletion(reimbursement));

    }

    @Test
    public void testSaveReimbursementRequest() {
        NewReimbursementRequest testRequest = new NewReimbursementRequest(923.32, "This is a description.", "PaymentId", "T");
        UUID testuserId = UUID.randomUUID();

        Assert.assertNotNull(sut.saveReimbursementRequest(testRequest, String.valueOf(testuserId)));;

    }

    @Test(expected = NotFoundException.class)
    public void testGetReimbursementByEmployee_Id() {

        sut.getReimbursementByEmployee_Id(UUID.randomUUID());

    }

    @Test
    public void testManagerUpdateReimbursementStatus() {

        UUID testId = UUID.randomUUID();
        sut.managerUpdateReimbursementStatus(testId, "P");
        verify(mockDAO).updateStatus(testId, "P");
    }

    @Test
    public void testGetReimbursementStatusId() {
        UUID testId = UUID.randomUUID();
        sut.getReimbursementStatusId(testId);

        verify(mockDAO).getStatusByReimbId(testId);
    }

    @Test (expected = NotFoundException.class)
    public void testGetAllReimbursementsOrderBy() {

        String columnName = "testColumn";
        boolean order = true;

        sut.getAllReimbursementsOrderBy(columnName, order);
    }
}