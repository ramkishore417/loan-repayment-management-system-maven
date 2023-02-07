package com.ramkishore.loanrms;

/*
 * @created 02/02/2023 - 07:27 PM
 * @project loan-repayment-management-system
 * @author RAMKISHORE
 */

import com.ramkishore.loanrms.dao.LoanInstallmtDAO;
import com.ramkishore.loanrms.exception.LoanRepaymentException;
import com.ramkishore.loanrms.model.LoanInstallmentPyt;
import com.ramkishore.loanrms.service.LoanRepaymentService;

import java.util.List;

public class LoanRepayment {
    private static final LoanRepaymentService loanRepaymentService = new LoanRepaymentService();
    private static final LoanInstallmtDAO loanInstallmtDAO = new LoanInstallmtDAO();

    public static void main(String[] args) throws LoanRepaymentException {
        boolean isRecordsAdded = loanRepaymentService.addLoanInstallmentPytDetails("inputfeed.txt");
//        System.out.println("Records inserted");

        if(isRecordsAdded){
            List<LoanInstallmentPyt> insertedRecordsData = loanInstallmtDAO.getAllInsertedRecords();
            insertedRecordsData.forEach(System.out::println);
        }
    }
}
