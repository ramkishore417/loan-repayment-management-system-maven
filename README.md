# loan-repayment-management-system-maven
A Prestigious Bank in the country wants to better their service to their customers and also in order to mobilize the funds, plans to reward the customers with discounts on their installments, who pay their loan instalments on time. Bank plans to add a module to their automated system for this purpose.
## Functional Requirement Specification:

##### 1 Parse Input 
The input file has to be parsed and Customer’s Loan Installment Records need to be filtered based on the Date of Payment.
##### 2 Update the Revised Installment and persist the data in the database 
The Customer’s discounted Installment has to be calculated and then the modified records need to be saved in the database.

------------


### A. Component Specification:
###### Requirement Name: 1. Parse Input
###### Component Definition: Reads the input text file, and convert the data into objects

#### Design Constraints 
    a. Input file format is .txt and is comma separated (Sample rows are added. You can add any number of rows to test your service class, from main method. 
    b. Do not hard code the input file path inside any method – has to be used from the input argument only as per code skeleton. 
    c. File Structure is like below: <pytId>,<customerId>,<loanId>,<customerName>,<loanType>,<installmentAmtInRs> <dueDate>,<actualPytDate>,<revisedInstallmentInRs>,<loanAmount> 
    d. In the input feed, filter LoanInstallmentPyt records which have the actualPytDate lesser than dueDate by 20 days and more. You can identify the records by comparing the dates. e. Assume that currencies are in INR
    f. Assume that the Date fields in the file will be in the format yyyy-MM-dd. g. Do not change the data types of the value object given in POJO. 
    h. Always convert the date field’s value to java.util.date with format, yyyy-MM-dd before setting in LoanInstallmentPytvalue object.
    i. Use ApplicationUtil.java for reading file, performing date operations, etc

#### Process Flow 
    a. The app will be invoked by calling theLoanRepaymentService.addLoanInstallmentPytDetails() with the input feed (.txt file). 
    b. Read the file using File I/O or Java Streams in ApplicationUtil 
    c. readFile returns List <String> of records, that were read from the file; It should filter only records where the payment date is atleast 20 days less than due date and returns, these records.(with each records’ s fields separated by comma) 
    d. Code the method LoanRepaymentService.buildLoanInstallmentPytList() pass the output of the readFile method to this method as arg. 
    e. In the method LoanRepaymentService.buildLoanInstallmentPytList() read the list returned by readFile method, split the records based on comma separatorand return the ArrayList of records of LoanInstallmentPyt. 
    f. Use the ApplicationUtil. convertStringToDate method to convert the date from String Format to java.util.Date format (yyyy-MM-dd). 
    g. Build the LoanInstallmentPytValue Object from the values obtained in every line (Check the Input file format in Design Constraints row)

------------


### B. Component Specification:
###### Requirement Name: 2. Persist Data into Database
###### Component Definition: Helps to calculate the discounted installment and add the changes made to database.

#### Design Constraints
    a. The database.properties has connection details required to connect to the backend 
    b. Do not change the keys of the property files, you can update the values based on the local database settings. For example, do not change the key, db.username. Rather you can have any value as user name based on local settings. 
    c. Use only JDBC to establish Database connection 
    d. Don't Hardcode the connection string to establish database connection. Read it from property files
    f. Use Prepared Statement to insert records 
    g. Close all the resources after use
    h. Catch all database related exception and throw Application specific exception only from DAO or from DBConnectionManager class. There has to be a private constructor in DBConnectionManager class, to load the database property file and to establish a database connection using JDBC 
    i. Rollback the Insert if any SQL exception has occurred. Throw application specific exception, LoanRepaymentException. 
    j. Revise the InstallmentAmt based on the constraints in the table given below


#### Process Flow 
    a. Modify the LoanRepaymentService.buildLoanInstallmentPytList() method (refer the above section) then set revisedInstallmentAmt to LoanInstallmentPytobjects. 
    b. Use LoanRepaymentService.calculateDiscountedInstallment() method to calculate revisedInstallmentAmt to the currentInstallmentAmt passed as parameter, based on loanType and loanAmount. 
    c. The method LoanRepaymentService.buildLoanInstallmentPytList() must return the list of LoanInstallmentPyt objects with revisedInstallmentAmt calculated 
    d. After reading file, building records as List<LoanInstallmentPyt>, call the LoanInstallmtDAO. insertLoanInstallmentPytmethod to insert values to database. You may have to convert the java.util.date to java.sql.date before storing to database. 
    e. If Insert has happened successfully, return true; false otherwise.
	

------------

