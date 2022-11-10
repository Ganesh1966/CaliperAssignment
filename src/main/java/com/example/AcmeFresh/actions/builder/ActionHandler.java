package com.example.AcmeFresh.actions.builder;

import com.example.AcmeFresh.db.PersistanceStore;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public abstract class ActionHandler<OUTPUT> {

    @Autowired
    protected PersistanceStore persistenceStore;

    @Autowired
    protected ObjectMapper mapper;

    public abstract Action handlingFor();
    public abstract OUTPUT executeAction(Map operateOn);

}
