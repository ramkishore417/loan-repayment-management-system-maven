package com.ramkishore.loanrms.dao;

/*
 * @created 02/02/2023 - 07:48 PM
 * @project loan-repayment-management-system
 * @author RAMKISHORE
 */

import com.ramkishore.loanrms.exception.LoanRepaymentException;
import com.ramkishore.loanrms.model.LoanInstallmentPyt;
import com.ramkishore.loanrms.repository.ApplicationUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LoanInstallmtDAO {
    public static Connection connection = null;

    public boolean insertLoanInstallmentPyt(ArrayList<LoanInstallmentPyt> loanInstlmtPyts) throws LoanRepaymentException {
        boolean recordsAdded = false;
        PreparedStatement stmtLoanPytInsert = null;
        PreparedStatement stmtTruncateData = null;
        int totalInsertedRecords = 0;

        // TYPE YOUR CODE HERE
        try {
            connection = DBConnectionManager.getInstance().getConnection();

            stmtTruncateData = connection.prepareStatement("truncate table LoanInstallment");
            stmtTruncateData.executeUpdate();

            stmtLoanPytInsert = connection.prepareStatement("insert into LoanInstallment values(?,?,?,?,?,?,?,?,?,?)");
            for(LoanInstallmentPyt record : loanInstlmtPyts) {
                stmtLoanPytInsert.setString(1, record.getPytId());
                stmtLoanPytInsert.setString(2, record.getCustomerId());
                stmtLoanPytInsert.setString(3, record.getLoanId());
                stmtLoanPytInsert.setString(4, record.getCustomerName());
                stmtLoanPytInsert.setString(5, record.getLoanType());
                stmtLoanPytInsert.setDouble(6, record.getInstallmentAmtInRs());

                java.sql.Date sqlDueDate = ApplicationUtil.utilToSqlDateConverter(record.getDueDate());
                stmtLoanPytInsert.setDate(7, sqlDueDate);

                java.sql.Date sqlActualPayDate = ApplicationUtil.utilToSqlDateConverter(record.getActualPytDate());
                stmtLoanPytInsert.setDate(8, sqlActualPayDate);

                stmtLoanPytInsert.setDouble(9, record.getRevisedInstallmentInRs());
                stmtLoanPytInsert.setDouble(10, record.getLoanAmount());

                int rowCount = stmtLoanPytInsert.executeUpdate();
                totalInsertedRecords+=rowCount;
                System.out.println();
            }
            if(totalInsertedRecords==loanInstlmtPyts.size()) {
                recordsAdded = true;
            }
        } catch (Exception e) {
            throw new LoanRepaymentException("\nError: Unable to add the records to Database - insertLoanInstallmentPyt | "+e.getMessage());
        } finally {
            try{
                if(connection!=null){
                    connection.close();
                }
                if(stmtLoanPytInsert!=null){
                    stmtLoanPytInsert.close();
                }
                if(stmtTruncateData!=null){
                    stmtTruncateData.close();
                }
            }catch(SQLException e){
                System.out.println("Error: Unable to close the Database connection or statements - insertLoanInstallmentPyt | "+e.getMessage());
            }
        }
        return recordsAdded;
    }

    public List<LoanInstallmentPyt> getAllInsertedRecords() throws LoanRepaymentException {
        try {
            List<LoanInstallmentPyt> recordsList = new ArrayList<>();
            connection = DBConnectionManager.getInstance().getConnection();

            PreparedStatement stmt=connection.prepareStatement("select * from LoanInstallment");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                LoanInstallmentPyt record = new LoanInstallmentPyt();
                record.setPytId(rs.getString(1));
                record.setCustomerId(rs.getString(2));
                record.setLoanId(rs.getString(3));
                record.setCustomerName(rs.getString(4));
                record.setLoanType(rs.getString(5));
                record.setInstallmentAmtInRs(rs.getDouble(6));
                record.setDueDate(rs.getDate(7));
                record.setActualPytDate(rs.getDate(8));
                record.setRevisedInstallmentInRs(rs.getDouble(9));
                record.setLoanAmount(rs.getDouble(10));

                recordsList.add(record);
            }
            return recordsList;

        } catch (Exception e) {
            throw new LoanRepaymentException("error in displaying database data");
        }
    }
}


