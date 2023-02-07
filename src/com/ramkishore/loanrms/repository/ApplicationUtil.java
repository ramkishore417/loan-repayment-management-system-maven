package com.ramkishore.loanrms.repository;

/*
 * @created 02/02/2023 - 07:32 PM
 * @project loan-repayment-management-system
 * @author RAMKISHORE
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ramkishore.loanrms.exception.LoanRepaymentException;

public class ApplicationUtil {
    public static List<String> readFile(String inputfeed) throws LoanRepaymentException {
        List<String> loanInstPytList = new ArrayList<>();
        // TYPE YOUR CODE HERE
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputfeed));
            String line="";
            while((line=br.readLine())!=null){
                String[] data = line.split(",");

                //converting the String formatted date to java util date.
                Date dueDate = stringToDateConverter(data[6]);
                Date actualDate = stringToDateConverter(data[7]);

                if(checkIfDateOfPytIsLessThanDueDate(actualDate, dueDate)){
                    loanInstPytList.add(line);
                }
            }
        } catch (Exception e){
            System.out.println("Error: date filed/fields not given correctly in txt file - readFile | "+e.getMessage());
        }
        return loanInstPytList;
    }

    //Coverts Java util Date to Java SQL Date
    public static java.sql.Date utilToSqlDateConverter(java.util.Date utilDate) {
        java.sql.Date sqlDate = null;
        // TYPE YOUR CODE HERE
        return new java.sql.Date(utilDate.getTime());
    }

    //Converts String Date to Java util Date
    public static java.util.Date stringToDateConverter(String stringDate) {
        // TYPE YOUR CODE HERE
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate= null;
        try {
            utilDate = dateFormat.parse(stringDate);
        } catch (Exception e) {
            System.out.println("Error: stringDate value not given correctly - stringToDateConverter | "+e.getMessage());
        }
        return utilDate;
    }
    public static boolean checkIfDateOfPytIsLessThanDueDate(Date dateOfPyt,Date dueDate)
    {
        //Date dtOPfyt,actDueDt;
        boolean eligibility = false;

//        double difInMSeconds=0.0;
//        double days=0.0;
        //Write the logic here to compare and see if the payment date is 20 days or more to due date

       Duration duration = Duration.between(dateOfPyt.toInstant(), dueDate.toInstant());
       long differenceInDays = duration.toDays();
       if(differenceInDays>=20) {
           eligibility = true;
       }
       return eligibility;
    }
}

