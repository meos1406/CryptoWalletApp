package ui;

import java.util.HashMap;

public class GlobalContext {
    private static GlobalContext globalContext;
    private HashMap<String, Object> states;

    private GlobalContext() {
        states = new HashMap<>();
    }

    public static GlobalContext getGlobalContext() {
        if (globalContext == null) {
            globalContext = new GlobalContext();
        }
        return globalContext;
    }

    public Object getStateFor(String key) {
        return states.get(key);
    }

    public void putStateFor(String key, Object state) {
        states.put(key, state);
    }

    public void removeStateFor(String key) {
        states.remove(key);
    }

    public void emptyAllStates() {
        states.clear();
    }
}
