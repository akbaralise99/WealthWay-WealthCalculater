package com.WealthWay.WealthCalulater.serviceimpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.WealthWay.WealthCalulater.Utility.NumberToWordsIndian;
import com.WealthWay.WealthCalulater.model.LumsumDto;
import com.WealthWay.WealthCalulater.model.SipDto;
import com.WealthWay.WealthCalulater.model.WealthProjectedDto;
import com.WealthWay.WealthCalulater.services.WealthWayCalulater;

@Service
public class WealthWayCalculater implements WealthWayCalulater {

	WealthProjectedDto  wealthProjected;
	 double projectedValue=0.0;
	@Override
	public WealthProjectedDto lumpsumWeatlh(LumsumDto dto) {
		 wealthProjected= new WealthProjectedDto();
		   
		    double rate = dto.getAnnualRetrnRate() / 100;
	        projectedValue= dto.getLumpsumAmount() * Math.pow(1 + rate, dto.getNumberOfYears());
		
	        wealthProjected.setTotalWealthCreated(NumberToWordsIndian.convert((int) Math.round(projectedValue))+ "("+Math.round(projectedValue)+")"+  " INR." );
	         wealthProjected.setProfitAmount(NumberToWordsIndian.convert((int) Math.round(projectedValue-dto.getLumpsumAmount()))+ "("+Math.round(projectedValue-dto.getLumpsumAmount())+")"+  " INR." );
		     wealthProjected.setInvestedAmount(NumberToWordsIndian.convert((int) Math.round(dto.getLumpsumAmount()))+ "("+Math.round(dto.getLumpsumAmount())+")"+  " INR." );
	         wealthProjected.setAnnualRetrnRate(dto.getAnnualRetrnRate());
	        wealthProjected.setNumberOfYears(dto.getNumberOfYears());
		    wealthProjected.setCurrentAge(dto.getCurrentAge());
            wealthProjected.setOnYourFutureAge(dto.getCurrentAge()+ dto.getNumberOfYears() + " Years ");
	        wealthProjected.setInterestEarned(intestCalulate(dto.getLumpsumAmount(),projectedValue) + " %");
		    wealthProjected.setRate(Math.round(calculateCAGR(dto.getLumpsumAmount(),projectedValue, dto.getNumberOfYears())*100 )+" CAGR");
	        return wealthProjected;
	}

	@Override
	public WealthProjectedDto SipWealth(SipDto dto) {
		 wealthProjected= new WealthProjectedDto();
		     double monthlyRate = dto.getAnnualRetrnRate() / 12 / 100;
	         int months = dto.getNumberOfYears() * 12;
	         projectedValue =dto.getSipAmount() * (Math.pow(1 + monthlyRate, months) - 1) * (1 + monthlyRate) / monthlyRate;
		     double investmentAmount=dto.getNumberOfYears()* dto.getSipAmount()*12;
	         wealthProjected.setTotalWealthCreated(NumberToWordsIndian.convert((int) Math.round(projectedValue))+ "("+Math.round(projectedValue)+")"+  " INR." );
	         wealthProjected.setProfitAmount(NumberToWordsIndian.convert((int) Math.round(projectedValue-investmentAmount))+ "("+Math.round(projectedValue-investmentAmount)+")"+  " INR." );
		     wealthProjected.setInvestedAmount(NumberToWordsIndian.convert((int) Math.round(investmentAmount))+ "("+Math.round(investmentAmount)+")"+  " INR." );
	         wealthProjected.setAnnualRetrnRate(dto.getAnnualRetrnRate());
	         wealthProjected.setNumberOfYears(dto.getNumberOfYears());
		     wealthProjected.setOnYourFutureAge(dto.getCurrentAge()+ dto.getNumberOfYears()+ " Years");
		     wealthProjected.setCurrentAge(dto.getCurrentAge());
		     wealthProjected.setInterestEarned(intestCalulate(investmentAmount,projectedValue) + " %");
            try {
		     double xirr=getXIRR(dto.getSipAmount(),dto.getNumberOfYears(),dto.getAnnualRetrnRate());
		     wealthProjected.setRate("XIRR: " + (xirr * 100) + "%");
            }catch(Exception xe) {
            	 wealthProjected.setRate(xe.getMessage());
            }
            
             
             return wealthProjected;
	}

	public double intestCalulate(double initialInvestment , double finalValue) {
		if (initialInvestment == 0) return 0;
	    return Math.round(((finalValue - initialInvestment) / initialInvestment) * 100);
		
	}
	
	public double calculateCAGR(double initialInvestment, double finalValue, double years) {
	    if (initialInvestment <= 0 || years <= 0) return 0;
	    return Math.pow(finalValue / initialInvestment, 1.0 / years) - 1;
	}
	
	public static double calculateXirr(List<LocalDate> dates, List<Double> cashFlows, double guessRate) {
	    if (dates.size() != cashFlows.size()) {
	        throw new IllegalArgumentException("Dates and cash flows must have the same size.");
	    }

	    double x0 = guessRate;
	    double x1;
	    int maxIteration = 100;
	    double tol = 1e-6;

	    for (int i = 0; i < maxIteration; i++) {
	        double fValue = 0;
	        double fDerivative = 0;
	        LocalDate startDate = dates.get(0);

	        for (int j = 0; j < cashFlows.size(); j++) {
	            long days = ChronoUnit.DAYS.between(startDate, dates.get(j));
	            double fraction = days / 365.0;

	            double denominator = Math.pow(1 + x0, fraction);
	            if (denominator == 0 || Double.isNaN(denominator) || Double.isInfinite(denominator)) {
	                throw new ArithmeticException("Invalid denominator in XIRR calculation.");
	            }

	            double v = cashFlows.get(j) / denominator;
	            double vDeriv = -fraction * cashFlows.get(j) / (Math.pow(1 + x0, fraction + 1));

	            fValue += v;
	            fDerivative += vDeriv;
	        }

	        // Avoid division by very small number
	        if (Math.abs(fDerivative) < 1e-10) {
	            throw new ArithmeticException("Derivative too small – XIRR may not converge.");
	        }

	        x1 = x0 - fValue / fDerivative;

	        if (Double.isNaN(x1) || Double.isInfinite(x1)) {
	            throw new ArithmeticException("XIRR calculation diverged. Try different initial guess.");
	        }

	        if (Math.abs(x1 - x0) <= tol) {
	            return x1;
	        }

	        x0 = x1;
	    }

	    throw new ArithmeticException("XIRR did not converge within the maximum number of iterations.");
	}
	
	public  double getXIRR(double monthlyInvestment , int years  , double rate) {
	
		 double monthlyRate =rate / 12 / 100;
    int totalMonths = years * 12;

    List<LocalDate> dates = new ArrayList<>();
    List<Double> amounts = new ArrayList<>();

    LocalDate startDate = LocalDate.now();

    for (int i = 0; i < totalMonths; i++) {
        LocalDate date = startDate.plusMonths(i);
        dates.add(date);
        amounts.add(monthlyInvestment);
    }
    
    //it will show montly dates and cash inflow outflow 
//    for (int io = 0; io < dates.size(); io++) {
//        System.out.println("Date: " + dates.get(io) + ", Amount: " + amounts.get(io));
//    }
    
    return  calculateXirr(dates,amounts,monthlyRate);
    // Example output
   
}
}
