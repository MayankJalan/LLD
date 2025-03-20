package org.domain;

public class Tag {
    int id;
    String name;

    public Tag(String name) {
        this.id=(int) System.currentTimeMillis()%Integer.MAX_VALUE;
        this.name = name;
    }
    public int getId() { return id; }
    public String getName() { return name; }
}
