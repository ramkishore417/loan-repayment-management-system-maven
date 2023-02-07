package com.ramkishore.loanrms.service;

/*
 * @created 02/02/2023 - 07:47 PM
 * @project loan-repayment-management-system
 * @author RAMKISHORE
 */

import com.ramkishore.loanrms.dao.LoanInstallmtDAO;
import com.ramkishore.loanrms.exception.LoanRepaymentException;
import com.ramkishore.loanrms.model.LoanInstallmentPyt;
import com.ramkishore.loanrms.repository.ApplicationUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoanRepaymentService{

    LoanInstallmtDAO loanInstallmentDAO = new LoanInstallmtDAO();

    public static ArrayList <LoanInstallmentPyt> buildLoanInstallmentPytList(List <String> loanInstPytRecords) {


        final String COMMADELIMITER = ",";
        ArrayList <LoanInstallmentPyt> loanInstallmentPytList = new ArrayList<>();

        // TYPE YOUR CODE HERE
        try {
            for(String record : loanInstPytRecords){

                String[] recordValues = record.split(COMMADELIMITER);
                String pytId = recordValues[0];
                String customerId = recordValues[1];
                String loanId = recordValues[2];
                String customerName = recordValues[3];
                String loanType = recordValues[4];
                double currentInstallmentAmtInRs = Double.parseDouble(recordValues[5]);

                Date dueDate = ApplicationUtil.stringToDateConverter(recordValues[6]);
                Date actualPytDate = ApplicationUtil.stringToDateConverter(recordValues[7]);

                double loanAmount = Double.parseDouble(recordValues[9]);

                double revisedInstallmentInRs = calculateDiscountedInstallment(loanType, loanAmount, currentInstallmentAmtInRs);

                LoanInstallmentPyt loanInstallmentPytValue = new LoanInstallmentPyt(
                        pytId,
                        customerId,
                        loanId,
                        customerName,
                        loanType,
                        currentInstallmentAmtInRs,
                        dueDate,
                        actualPytDate,
                        revisedInstallmentInRs,
                        loanAmount
                );
                loanInstallmentPytList.add(loanInstallmentPytValue);
            }
        }catch (Exception e){
            System.out.println("Error: Data fields not given correctly in txt file - buildLoanInstallmentPytList | "+e.getMessage());
        }
        return loanInstallmentPytList;
    }

    public boolean addLoanInstallmentPytDetails(String inputFeed) throws LoanRepaymentException {

        // TYPE YOUR CODE HERE
        List<String> filteredList = ApplicationUtil.readFile(inputFeed);

        ArrayList<LoanInstallmentPyt> loanInstallmentPytList = buildLoanInstallmentPytList(filteredList);

        return loanInstallmentDAO.insertLoanInstallmentPyt(loanInstallmentPytList);
    }
    public static double calculateDiscountedInstallment(String loanType,double loanAmount,double currentInstallmentAmt) {
        double revisedInstallmentAmt=0.0;

        // TYPE YOUR CODE HERE
        double discount = 0.0;
        if(loanType.equalsIgnoreCase("HousingLoan")){
            if(loanAmount>=1000000 && loanAmount<=2500000){
                discount = 0.10;
            }else if(loanAmount<=5000000){
                discount = 0.12;
            }else{
                discount = 0.14;
            }
        }else if(loanType.equalsIgnoreCase("PersonalLoan")){
            if(loanAmount>=50000 && loanAmount<=100000){
                discount = 0.08;
            }else if(loanAmount<=500000){
                discount = 0.10;
            }else{
                discount = 0.12;
            }
        }else if(loanType.equalsIgnoreCase("VehicleLoan")){
            if(loanAmount>=100000 && loanAmount<=500000){
                discount = 0.08;
            }else if(loanAmount<=1000000){
                discount = 0.10;
            }else{
                discount = 0.12;
            }
        }else if(loanType.equalsIgnoreCase("EducationalLoan")){
            if(loanAmount>=100000 && loanAmount<=500000){
                discount = 0.11;
            }else if(loanAmount<=1000000){
                discount = 0.12;
            }else{
                discount = 0.13;
            }
        }
        currentInstallmentAmt-=currentInstallmentAmt*discount;
        revisedInstallmentAmt = currentInstallmentAmt;
        return Math.round(revisedInstallmentAmt);
    }


}



