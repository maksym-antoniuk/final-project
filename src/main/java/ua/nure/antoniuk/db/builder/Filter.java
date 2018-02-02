package ua.nure.antoniuk.db.builder;

import javax.servlet.http.HttpServletRequest;

public abstract class Filter {


    public Filter() {
    }

    public Filter(HttpServletRequest request) {

    }

    protected boolean ascId;

    public String toSQLQuery(){
        return "";
    }

}
