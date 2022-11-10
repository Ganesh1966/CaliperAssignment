package com.example.AcmeFresh.actions;

import com.example.AcmeFresh.actions.builder.Action;
import com.example.AcmeFresh.actions.builder.ActionHandler;
import com.example.AcmeFresh.exceptions.DataValidationException;
import com.example.AcmeFresh.models.Client;
import com.example.AcmeFresh.models.Customer;

import java.util.Map;

public class UpdateClient extends ActionHandler<String> {
@Override
public Action handlingFor(){
        return Action.UPDATE_CLIENT;
        }

@Override
public String executeAction(Map operateOn){
        if(operateOn.isEmpty())
        throw new DataValidationException("customer cannot be empty");

        Client client=mapper.convertValue(operateOn.get("customer"), Client.class);

        return persistenceStore.updateclient(client);

        }
        }
