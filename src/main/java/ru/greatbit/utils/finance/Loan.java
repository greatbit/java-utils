package ru.greatbit.utils.finance;

/**
 * Created by azee on 14.10.16.
 */
public class Loan {
    
    public static double getAnnuityMonthlyPayment(double loanAmt, double interest, int months){
        double monthlyInterest = (interest / 12) / 100;
        double coefficient = (monthlyInterest * Math.pow(1 + monthlyInterest, months)) / (Math.pow(1 + monthlyInterest, months) - 1);
        return loanAmt * coefficient;
    }
}
