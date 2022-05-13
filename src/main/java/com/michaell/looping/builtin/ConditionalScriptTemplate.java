package com.michaell.looping.builtin;

import com.michaell.looping.ScriptParameters;
import com.michaell.looping.ScriptTemplate;

public abstract class ConditionalScriptTemplate extends ScriptTemplate {
    public ConditionalScriptTemplate(String name, boolean needsInit) {
        super(name, needsInit);
    }

    @Override
    public void run(ScriptParameters parameters) {
        if (shouldRun(parameters)) toRun(parameters);
    }

    public abstract void toRun(ScriptParameters parameters);

    public abstract boolean shouldRun(ScriptParameters parameters);
}
