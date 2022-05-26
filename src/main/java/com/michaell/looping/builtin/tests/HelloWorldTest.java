package com.michaell.looping.builtin.tests;

import com.michaell.looping.ScriptParameters;
import com.michaell.looping.ScriptRunner;
import com.michaell.looping.builtin.ConvertToScript;

public class HelloWorldTest {
    public static void main(String[] args) {
        ScriptRunner runner = new ScriptRunner();
        runner.addRequest(new HelloWorldRequest());
        ScriptParameters.GlobalVariable<Integer> var = new ScriptParameters.GlobalVariable<>("counter");
        var.setValue(0);
        runner.scriptParametersGlobal.addGlobalVariable(var);
        try {
            runner.addScript(new HelloWorldScript("Hello1"));
            runner.addScript(new HelloWorldScript("Hello2"));
            runner.addScript(new HelloWorldScript("Hello3"));
            runner.addScript(new RecursiveScript("Recursive1"));
            runner.addScript(new HelloWorldScript("Hello4"));
            runner.addScript(new HelloWorldScript("Hello5"));
            runner.addScript(new ConvertToScript("Francais", new ConvertToScriptTest(), "start", "main"));
        } catch (ScriptRunner.DuplicateScriptException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        runner.runConstantly(10000);
    }
}
