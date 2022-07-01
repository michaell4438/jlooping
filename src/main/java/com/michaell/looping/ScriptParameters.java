package com.michaell.looping;

import java.util.ArrayList;

public class ScriptParameters {
    private ArrayList<Request> availableRequests = new ArrayList<>();
    public ArrayList<GlobalVariable> globalVariables = new ArrayList<>();
    public ScriptRunner runner;

    public ScriptParameters(ScriptRunner runner) {
        this.runner = runner;
    }

    public Request getRequest(Class<? extends Request> clazz) throws RequestNotFoundException {
        for (Request request:
             availableRequests) {
            if (clazz.isInstance(request)) {
                return request;
            }
        }
        throw new RequestNotFoundException(clazz.getName());
    }

    public static class DuplicateRequestException extends Exception {
        public DuplicateRequestException(String message) {
            super(message);
        }
    }

    public void addRequest(Request request) throws DuplicateRequestException {
        try {
            getRequest(request.name);
            throw new DuplicateRequestException(request.name);
        } catch (RequestNotFoundException e) {
            availableRequests.add(request);
        }
    }

    public Request getRequest(String name) throws RequestNotFoundException {
        for (Request request:
        availableRequests) {
            if (request.name.equals(name)) {
                return request;
            }
        }
        throw new RequestNotFoundException(name);
    }

    public GlobalVariable getGlobalVariable(String name) throws VariableNotFoundException {
        for (GlobalVariable var :
                globalVariables) {
            if (var.name == name) return var;
        }
        throw new VariableNotFoundException(name);
    }

    public void addGlobalVariable(GlobalVariable variable) {
        try {
            getGlobalVariable(variable.name);
        } catch (VariableNotFoundException e) {
            globalVariables.add(variable);
        }
    }

    /**
      * <p>This method may not be needed in some cases, but was still added</p>
     */
    public void updateGlobalVariable(GlobalVariable variable) {
        try {
            globalVariables.remove(getGlobalVariable(variable.name));
            globalVariables.add(variable);
        } catch (VariableNotFoundException e) {
            globalVariables.add(variable);
        }
    }

    public void doOneLoop() {
        int currentIndex = runner.scriptIndex;

        runner.scriptIndex++;

        do {
            runner.runOneScript();
        } while (currentIndex != runner.scriptIndex);
    }

    public Object issueRequest(Object inputParameters, Request request) throws InvalidParametersException {
        Class outputType = request.getOutputType();
        Class inputType = request.getInputType();

        if (inputType.isInstance(inputParameters)) {
            return request.issueRequest(inputParameters);
        } else {
            throw new InvalidParametersException(inputType.toString() + " was expected, but got " +
                    inputParameters.getClass().toString());
        }
    }

    public static abstract class Request {
        public String name;

        public Request(String name) {
            this.name = name;
        }

        public abstract Object issueRequest(Object parameters);
        public abstract Class getOutputType();
        public abstract Class getInputType();
    }

    public static class GlobalVariable<T> {
        public String name;

        public GlobalVariable(String name) {
            this.name = name;
        }

        private T value;
        public T getValue() {
            return value;
        }
        public void setValue(T value) {
            this.value = value;
        }
    }

    public static class RequestNotFoundException extends Exception {
        public RequestNotFoundException(String msg) {
            super("Request not found: " + msg);
        }
    }

    public static class InvalidParametersException extends Exception {
        public InvalidParametersException(String msg) {
            super("Invalid parameters: " + msg);
        }
    }

    public static class VariableNotFoundException extends Exception {
        public VariableNotFoundException(String msg) {
            super("Variable not found: " + msg);
        }
    }
}
