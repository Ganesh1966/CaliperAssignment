package com.example.AcmeFresh.actions;

import com.example.AcmeFresh.actions.builder.Action;
import com.example.AcmeFresh.actions.builder.ActionHandler;
import com.example.AcmeFresh.exceptions.DataValidationException;
import com.example.AcmeFresh.models.Client;
import com.example.AcmeFresh.models.order;

import java.util.Map;

public class CustomerOrder extends ActionHandler<String> {

    @Override
    public Action handlingFor(){
        return Action.CUSTOMER_ORDER;
    }

    @Override
    public String executeAction(Map operateOn){
        if(operateOn.isEmpty())
            throw new DataValidationException("User cannot be empty");

        order customer=mapper.convertValue(operateOn.get("user"), order.class);

        return persistenceStore.orderCustomer(customer);

    }
}
