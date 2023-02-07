package com.ramkishore.loanrms.model;

/*
 * @created 02/02/2023 - 07:49 PM
 * @project loan-repayment-management-system
 * @author RAMKISHORE
 */

import java.util.Date;

public class LoanInstallmentPyt {

    String pytId;
    String customerId;
    String loanId;
    String customerName;
    String loanType;
    double installmentAmtInRs;
    Date dueDate;
    Date actualPytDate;
    double revisedInstallmentInRs;
    double loanAmount;


    public LoanInstallmentPyt(String pytId, String customerId, String loanId, String customerName, String loanType,
                              double installmentAmtInRs, Date dueDate, Date actualPytDate, double revisedInstallmentInRs,
                              double loanAmount) {
        super();
        this.pytId = pytId;
        this.customerId = customerId;
        this.loanId = loanId;
        this.customerName = customerName;
        this.loanType = loanType;
        this.installmentAmtInRs = installmentAmtInRs;
        this.dueDate = dueDate;
        this.actualPytDate = actualPytDate;
        this.revisedInstallmentInRs = revisedInstallmentInRs;
        this.loanAmount = loanAmount;
    }
    public LoanInstallmentPyt() {
        super();
    }

    public double getLoanAmount() {
        return loanAmount;
    }
    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getPytId() {
        return pytId;
    }
    public void setPytId(String pytId) {
        this.pytId = pytId;
    }

    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getLoanId() {
        return loanId;
    }
    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getLoanType() {
        return loanType;
    }
    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public double getInstallmentAmtInRs() {
        return installmentAmtInRs;
    }
    public void setInstallmentAmtInRs(double installmentAmtInRs) {
        this.installmentAmtInRs = installmentAmtInRs;
    }

    public Date getDueDate() {
        return dueDate;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getActualPytDate() {
        return actualPytDate;
    }
    public void setActualPytDate(Date actualPytDate) {
        this.actualPytDate = actualPytDate;
    }

    public double getRevisedInstallmentInRs() {
        return revisedInstallmentInRs;
    }
    public void setRevisedInstallmentInRs(double revisedInstallmentInRs) {
        this.revisedInstallmentInRs = revisedInstallmentInRs;
    }

    @Override
    public String toString() {
        return String.format(pytId+" "
                +customerId+" "
                +loanId+" "
                +customerName+" "
                +loanType+" "
                +"Actual["+(int)installmentAmtInRs+"] "
                +dueDate+" "
                +actualPytDate+" "
                +"Revised["+(int)revisedInstallmentInRs+"] "
                +(int)loanAmount
        );
    }
}

