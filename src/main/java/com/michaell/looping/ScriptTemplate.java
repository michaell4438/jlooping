package com.michaell.looping;

public abstract class ScriptTemplate {
    public String name;
    public boolean needsInit;

    public ScriptTemplate(String name, boolean needsInit) {
        this.name = name;
        this.needsInit = needsInit;
    }

    public void init(ScriptParameters scriptParametersGlobal) {
        needsInit = false;
    }

    public abstract void run(ScriptParameters parameters);
}
