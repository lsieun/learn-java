package lsieun.jaas.sample;

import java.io.Serializable;
import java.security.Principal;

import javax.security.auth.Subject;

public class SamplePrincipal implements Principal, Serializable {
    private String name;

    public SamplePrincipal(String name) {
        if (name == null)
            throw new NullPointerException("illegal null input");

        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;

        if (this == o)
            return true;

        if (!(o instanceof SamplePrincipal))
            return false;

        SamplePrincipal that = (SamplePrincipal)o;

        if (this.getName().equals(that.getName()))
            return true;

        return false;
    }

    @Override
    public String toString() {
        return("SamplePrincipal:  " + name);
    }
}
