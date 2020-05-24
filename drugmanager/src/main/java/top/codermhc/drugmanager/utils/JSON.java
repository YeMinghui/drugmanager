package top.codermhc.drugmanager.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSON {

    public static String stringExcludeNull(Object o) {
        return new Gson().toJson(o);
    }

    public static String string(Object o) {
        return new GsonBuilder().serializeNulls().create().toJson(o);
    }

}
