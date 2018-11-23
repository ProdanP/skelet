package eu.bufa.prodan.myapplication.rest.util.interceptors;

import java.io.IOException;

public class NoConnectivityException extends IOException {

    @Override
    public String getMessage() {
        return "No connectivity exception";
    }

}