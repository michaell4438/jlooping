package com.michaell.looping.builtin;

import com.michaell.looping.ScriptParameters;
import com.michaell.looping.ScriptTemplate;

public class HelloWorldScript extends ScriptTemplate {
    public HelloWorldScript(String name) {
        super(name, false);
    }

    @Override
    public void run(ScriptParameters parameters) {
        try {
            ScriptParameters.GlobalVariable<Integer> var = parameters.getGlobalVariable("counter");
            System.out.println(var.getValue());
            var.setValue(var.getValue() + 1);
        } catch (ScriptParameters.VariableNotFoundException e) {

        }
        System.out.println("Hello from " + name);
        try {
            System.out.println(parameters.issueRequest("Hola" + name, parameters.getRequest(HelloWorldRequest.class)));
        } catch (ScriptParameters.InvalidParametersException | ScriptParameters.RequestNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
