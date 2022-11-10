package com.example.AcmeFresh.actions;

import com.example.AcmeFresh.actions.builder.Action;
import com.example.AcmeFresh.actions.builder.ActionHandler;
import com.example.AcmeFresh.exceptions.DataValidationException;
import com.example.AcmeFresh.models.Customer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class addcustomer extends ActionHandler<String> {

    @Override
    public Action handlingFor(){
        return Action.ADD_NEW_CUSTOMER;
    }

    @Override
    public String executeAction(Map operateOn){
        if(operateOn.isEmpty())
            throw new DataValidationException("User cannot be empty");

        Customer customer=mapper.convertValue(operateOn.get("user"), Customer.class);

        return persistenceStore.addcustomer(customer);

    }
}
