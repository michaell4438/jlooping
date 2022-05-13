package com.michaell.looping.builtin;

import com.michaell.looping.ScriptParameters;

public class HelloWorldRequest extends ScriptParameters.Request {
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
