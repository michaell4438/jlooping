package com.michaell.looping;

import java.util.ArrayList;
import java.util.Date;

public class ScriptRunner {
    public ArrayList<ScriptTemplate> scripts = new ArrayList<>();
    public int scriptIndex = 0;
    public ScriptParameters scriptParametersGlobal = new ScriptParameters(this);
    public boolean shouldExit = false;

    public void addScript(ScriptTemplate script) throws DuplicateScriptException {
        String name = script.name;
        try {
            ScriptTemplate found = getScriptByName(name);
        } catch (ScriptNotFoundException e) {
            scripts.add(script);
        }
    }

    public ScriptTemplate getScriptByName(String name) throws ScriptNotFoundException {
        for (ScriptTemplate script :
                scripts) {
            if (script.name == name) {
                return script;
            }
        }
        throw new ScriptNotFoundException(name);
    }

    public void addRequest(ScriptParameters.Request request) throws ScriptParameters.DuplicateRequestException {
        scriptParametersGlobal.addRequest(request);
    }

    public void runOneScript() {
        try {
            ScriptTemplate nextScript = scripts.get(scriptIndex);
            if (nextScript.needsInit) {
                nextScript.init(scriptParametersGlobal);
            } else {
                nextScript.run(scriptParametersGlobal);
            }
            scriptIndex++;
        } catch (IndexOutOfBoundsException e) {
            scriptIndex = 0;
        }
    }

    public void runConstantly() {
        while (scripts.size() > 0 & !shouldExit) {
            runOneScript();
        }
    }

    public void runConstantly(long timeout) {
        Date timer = new Date();
        long initTime = timer.getTime();
        long currentTime = timer.getTime();
        timeout = timeout/2;
        while (scripts.size() > 0 && (currentTime < initTime + timeout)) {
            runOneScript();
            currentTime = new Date().getTime();
        }
    }

    public void setShouldExit(boolean shouldExit) {
        this.shouldExit = shouldExit;
    }

    public static class ScriptNotFoundException extends Exception {
        public ScriptNotFoundException(String msg) {
            super("Active script not found: " + msg);
        }
    }

    public static class DuplicateScriptException extends Exception {
        public DuplicateScriptException(String msg) {
            super("Duplicate scripts cannot be added: " + msg);
        }
    }
}
