package com.michaell.looping.builtin;

import com.michaell.looping.ScriptParameters;
import com.michaell.looping.ScriptTemplate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ConvertToScript extends ScriptTemplate {
    public Object code;
    public Class<?> codeClass;
    private Method initMethod, loopMethod;

    public ConvertToScript(String name, Object code, String initMethodName, String loopMethodName) throws NoSuchMethodException {
        super(name, true);
        this.code = code;

        this.codeClass = code.getClass();
        for (Method m:
                codeClass.getMethods()) {
            if (m.getName().equals(initMethodName)) this.initMethod = m;
        }
        if (this.initMethod == null) needsInit = false;


        for (Method m:
                codeClass.getMethods()) {
            if (m.getName().equals(loopMethodName)) this.loopMethod = m;
        }
        if (this.loopMethod == null) throw new NoSuchMethodException("Could not find " + initMethodName + " in " + codeClass);
    }

    @Override
    public void init(ScriptParameters parameters) {
        needsInit = false;
        if (initMethod != null) {
            try {
                initMethod.invoke(code);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run(ScriptParameters parameters) {
        if (loopMethod != null) {
            try {
                loopMethod.invoke(code);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private int dummy(String dummy, boolean function) { return 1; }
}
