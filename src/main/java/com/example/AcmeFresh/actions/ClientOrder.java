package com.example.AcmeFresh.actions;

import com.example.AcmeFresh.actions.builder.Action;
import com.example.AcmeFresh.actions.builder.ActionHandler;
import com.example.AcmeFresh.exceptions.DataValidationException;
import com.example.AcmeFresh.models.Client;
import com.example.AcmeFresh.models.order;
import org.springframework.core.annotation.Order;

import java.util.Map;

public class ClientOrder extends ActionHandler<String> {

    @Override
    public Action handlingFor(){
        return Action.CLIENT_ORDER;
    }

    @Override
    public String executeAction(Map operateOn){
        if(operateOn.isEmpty())
            throw new DataValidationException("User cannot be empty");

        order client=mapper.convertValue(operateOn.get("user"), order.class);

        return persistenceStore.orderClient(client);

    }
}
