package io.ultratechies.ghala.statistics.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.time.Month;
import java.time.Year;

public class OrderStats {
    private BigInteger sum;
    private Double month;
    private Double year;
    private Month monthName;
    private Year yearName;

    public OrderStats(BigInteger sum, Double month, Double year) {
        this.sum = sum;
        this.month = month;
        this.year = year;
        this.monthName = monthName;
        this.yearName = yearName;
    }

    public BigInteger getSum() {
        return sum;
    }

    public void setSum(BigInteger sum) {
        this.sum = sum;
    }

    public Double getMonth() {
        return month;
    }

    public void setMonth(Double month) {
        this.month = month;
    }

    public Double getYear() {
        return year;
    }

    public void setYear(Double year) {
        this.year = year;
    }

    public Month getMonthName() {
        return monthName;
    }

    public void setMonthName(Month monthName) {
        this.monthName = monthName;
    }

    public Year getYearName() {
        return yearName;
    }

    public void setYearName(Year yearName) {
        this.yearName = yearName;
    }
}
