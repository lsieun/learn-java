package lsieun.javaagent.handler;

import java.util.ArrayList;
import java.util.List;

public class ClassNameFilter {
    private String className;
    private boolean regex;
    private List<Handler> handlers;

    public ClassNameFilter(String className, boolean regex) {
        this.className = className;
        this.regex = regex;
        this.handlers = new ArrayList<Handler>();
    }

    public boolean matches(String entryName) {
        if (regex) {
            if (entryName.matches(className)) {
                return true;
            }
            return false;
        }
        else {
            if (entryName.equals(className)) {
                return true;
            }
            return false;
        }
    }

    public void addHandler(Handler h) {
        this.handlers.add(h);
    }

    public List<Handler> getHandlers() {
        return handlers;
    }

    public void setHandlers(List<Handler> handlers) {
        this.handlers = handlers;
    }
}
