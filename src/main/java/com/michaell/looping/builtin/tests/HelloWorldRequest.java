package com.michaell.looping.builtin.tests;

import com.michaell.looping.ScriptParameters;

public class HelloWorldRequest extends ScriptParameters.Request {
    public HelloWorldRequest() {
        super("HelloWorldRequest");
    }

    @Override
    public Object issueRequest(Object parameters) {
        String param = (String) parameters;
        return param.length();
    }

    @Override
    public Class getOutputType() {
        return Integer.class;
    }

    @Override
    public Class getInputType() {
        return String.class;
    }
}
