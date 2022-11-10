package com.example.AcmeFresh.actions;

import com.example.AcmeFresh.actions.builder.Action;
import com.example.AcmeFresh.actions.builder.ActionHandler;
import com.example.AcmeFresh.exceptions.DataValidationException;
import com.example.AcmeFresh.models.Client;
import com.example.AcmeFresh.models.Customer;

import java.util.Map;

public class addClient extends ActionHandler<String> {

@Override
public Action handlingFor(){
        return Action.ADD_NEW_CLIENT;
        }

@Override
public String executeAction(Map operateOn){
        if(operateOn.isEmpty())
        throw new DataValidationException("User cannot be empty");

        Client client=mapper.convertValue(operateOn.get("user"), Client.class);

        return persistenceStore.addclient(client);

        }
        }
