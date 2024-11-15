package com.herlandio7.stock.application.usecases;

import com.herlandio7.stock.application.gateways.ICheckCriticalStock;

public class CheckCriticalStockInteractor {

    private ICheckCriticalStock iCheckCriticalStock;

    public CheckCriticalStockInteractor(ICheckCriticalStock iCheckCriticalStock) {
        this.iCheckCriticalStock = iCheckCriticalStock;
    }

    public void execute() {
        iCheckCriticalStock.execute();
    }
}
