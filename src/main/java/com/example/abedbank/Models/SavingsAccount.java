package com.example.abedbank.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class SavingsAccount extends Account { // the withdraw limit from savings
  private final DoubleProperty withdrawLimit;

  public SavingsAccount(String owner ,  String accountNumber , double balance, int withdrawLimit){
      super(owner , accountNumber , balance);
      this.withdrawLimit = new SimpleDoubleProperty(this ," With Draw Limit",withdrawLimit);
  }
  public  DoubleProperty withdrawLimitProp(){return withdrawLimit; }

    public double getWithdrawLimit() {
        return withdrawLimit.get();
    }

    public DoubleProperty withdrawLimitProperty() {
        return withdrawLimit;
    }

    public void setWithdrawLimit(double withdrawLimit) {
        this.withdrawLimit.set(withdrawLimit);
    }
}
