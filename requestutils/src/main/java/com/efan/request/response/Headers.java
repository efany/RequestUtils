package com.efan.request.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 一帆 on 2016/4/10.
 */
public class Headers {
    private String[] namesAndValues;

    public Headers() {
    }

    private Headers(Builder builder) {
        this.namesAndValues = builder.namesAndValues.toArray(new String[builder.namesAndValues.size()]);
    }

    private Headers(String[] namesAndValues) {
        this.namesAndValues = namesAndValues;
    }

    public void add(String name, String value){
        namesAndValues[length()] = name;
        namesAndValues[length()+1] = value;
    }

    public void add(String[] namesAndValues){
        for (int i = 0; i<namesAndValues.length/2; i++){
            add(namesAndValues[i*2], namesAndValues[i*2+1]);
        }
    }

    public String name(int index) {
        return namesAndValues[index * 2];
    }

    /** Returns the value at {@code index}. */
    public String value(int index) {
        return namesAndValues[index * 2 + 1];
    }

    public String get(String name) {
        return get(namesAndValues, name);
    }



    private static String get(String[] namesAndValues, String name) {
        for (int i = namesAndValues.length - 2; i >= 0; i -= 2) {
            if (name.equalsIgnoreCase(namesAndValues[i])) {
                return namesAndValues[i + 1];
            }
        }
        return null;
    }

    private int length() {
        return namesAndValues.length;
    }

    public int size(){
        if (namesAndValues == null){
            return 0;
        }
        return (length()/2);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0, size = size(); i < size; i++) {
            result.append(name(i)).append(": ").append(value(i)).append("\n");
        }
        return result.toString();
    }

    public static final class Builder {
        private final List<String> namesAndValues = new ArrayList<>(20);

        public Builder add(String name, String value) {
            if(checkNameAndValue(name, value)){
               return addLenient(name, value);
            }
            return this;
        }

        public Builder removeAll(String name) {
            for (int i = 0; i < namesAndValues.size(); i += 2) {
                if (name.equalsIgnoreCase(namesAndValues.get(i))) {
                    namesAndValues.remove(i); // name
                    namesAndValues.remove(i); // value
                    i -= 2;
                }
            }
            return this;
        }

        public Builder set(String name, String value) {
            checkNameAndValue(name, value);
            removeAll(name);
            addLenient(name, value);
            return this;
        }

        public String get(String name) {
            for (int i = namesAndValues.size() - 2; i >= 0; i -= 2) {
                if (name.equalsIgnoreCase(namesAndValues.get(i))) {
                    return namesAndValues.get(i + 1);
                }
            }
            return null;
        }

        private Builder addLenient(String name, String value) {
            namesAndValues.add(name);
            namesAndValues.add(value.trim());
            return this;
        }

        private boolean checkNameAndValue(String name, String value){
            if(name == null){
                return false;
            }
            if(value == null){
                return false;
            }
            return true;
        }

        public Headers build() {
            return new Headers(this);
        }
    }
}
