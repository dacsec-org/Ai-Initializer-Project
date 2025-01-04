package net.dacss.views.feeds;

import lombok.Getter;
import lombok.Setter;

/**
 * Person class to store the data of the person:
 * THIS IS ONLY FOR FOR STUBBING PURPOSES.
 * refactored into a LLM feed class for use in vector databases(nio4j..etc)
 */
@Setter
@Getter
public class Person {

    private String image;
    private String name;
    private String date;
    private String post;
    private String likes;
    private String comments;
    private String shares;

    public Person() {
    }
}

