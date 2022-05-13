package com.michaell.looping.builtin;

import com.michaell.looping.ScriptParameters;
import com.michaell.looping.ScriptTemplate;

public class RecursiveScript extends ScriptTemplate {
    public RecursiveScript(String name) {
        super(name, false);
    }

    private int counter = 0;

    @Override
    public void run(ScriptParameters parameters) {
        if (counter >= 5) {
            counter = 0;
            System.out.println("");
            System.out.println("");
            System.out.println("Doing loop");
            System.out.println("");
            System.out.println("");

            parameters.doOneLoop();

            System.out.println("");
            System.out.println("");
            System.out.println("Loop Done");
            System.out.println("");
            System.out.println("");
        } else {
            System.out.println("No recursive looping");
        }
        counter++;
    }
}
