package com.gvs.framework.util.misc.pojo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;

@Component
@Scope("cucumber-glue")
public class springNatureContentType {

    private static final Logger LOGGER = Logger.getLogger(springNatureContentType.class);


    private final Map<com.gvs.framework.util.misc.pojo.springNatureContentType.Type, String> typeTotals = new HashMap<>();
    //private final Map<String, String> typeTotals = new HashMap<>();


    public synchronized void setNewTotalsNumber(com.gvs.framework.util.misc.pojo.springNatureContentType.Type  type, String actualTotal) {
    //public synchronized void setNewTotalsNumber(String author, String type, String actualTotal) {

        typeTotals.put(type, actualTotal);
        LOGGER.info("Setting ACTUAL total number: " + actualTotal);

    }

    public synchronized String getExistingTotalsNumber(com.gvs.framework.util.misc.pojo.springNatureContentType.Type type) {
        String number = typeTotals.get(type);
        if (number == null) {
            throw new IllegalStateException("missing");
        }
        LOGGER.info("Returning ACTUAL / EXISTING total: " + number);
        return number;
    }


    public enum Type {
        CHAPTER("", "Chapter"),
        ARTICLE("","Article"),
        CONFERENCE_PAPER("","Conference Paper"),
        REFERENCE_WORK_ENTRY("","Reference Work Entry"),
        PROTOCOL("","Protocol"),
        BOOK("","Book");

        private final String name;
        private String author;

        Type(String Author, String name) {
            this.author = Author;
            this.name = name;
        }


        public void setAuthor(String authorName) {

            this.author = authorName;
        }

        public static com.gvs.framework.util.misc.pojo.springNatureContentType.Type byName(String name) {
            return com.gvs.framework.util.misc.pojo.springNatureContentType.Type.valueOf(name.trim().replace(" ", "_").toUpperCase());
        }
    }

}

