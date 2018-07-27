package com.codingtest.assignment;

import java.io.Serializable;
import java.util.Comparator;

public class EquityDetails implements Serializable {

        public String contractName;
        public String symbol;
        public String exchange;
        public String currency;
        public String cusip;
        public String issuer;
        public String country;
        public String subclass;
        public String pricingDate;
        public Float lastPrice;
        public Float openPrice;
        public Float highPrice;
        public Float lowPrice;
        public Float askPrice;
        public Float bidPrice;
        public Integer volume;
        public Integer splitRatio;
        public Integer cashDividend;

        public static Comparator<EquityDetails> equityDetailsNameComparator = new Comparator<EquityDetails>() {

                public int compare(EquityDetails s1, EquityDetails s2) {
                        String equityName1 = s1.contractName.toUpperCase();
                        String equityName2 = s2.contractName.toUpperCase();

                        //ascending order
                        return equityName1.compareTo(equityName2);

                        //descending order
                        //return StudentName2.compareTo(StudentName1);
                }
        };

        public static Comparator<EquityDetails> equityDetailsSymbolComparator = new Comparator<EquityDetails>() {

                public int compare(EquityDetails s1, EquityDetails s2) {
                        String equityName1 = s1.symbol.toUpperCase();
                        String equityName2 = s2.symbol.toUpperCase();

                        //ascending order
                        return equityName1.compareTo(equityName2);

                        //descending order
                        //return StudentName2.compareTo(StudentName1);
                }
        };

        public static Comparator<EquityDetails> equityDetailsCurrencyComparator = new Comparator<EquityDetails>() {

                public int compare(EquityDetails s1, EquityDetails s2) {
                        String equityName1 = s1.currency.toUpperCase();
                        String equityName2 = s2.currency.toUpperCase();

                        //ascending order
                        return equityName1.compareTo(equityName2);

                        //descending order
                        //return StudentName2.compareTo(StudentName1);
                }
        };

        public static Comparator<EquityDetails> equityDetailsPricingDateComparator = new Comparator<EquityDetails>() {

                public int compare(EquityDetails s1, EquityDetails s2) {
                        String equityName1 = s1.pricingDate.toUpperCase();
                        String equityName2 = s2.pricingDate.toUpperCase();

                        //ascending order
                        return equityName1.compareTo(equityName2);

                        //descending order
                        //return StudentName2.compareTo(StudentName1);
                }
        };

        public static Comparator<EquityDetails> equityDetailsLastPriceComparator = new Comparator<EquityDetails>() {

                public int compare(EquityDetails s1, EquityDetails s2) {
                        return Float.compare(s1.lastPrice,s2.lastPrice);

                        //descending order
                        //return StudentName2.compareTo(StudentName1);
                }
        };


}
