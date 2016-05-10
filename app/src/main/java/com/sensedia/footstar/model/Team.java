package com.sensedia.footstar.model;

import java.io.Serializable;

/**
 * Created by renanpetronilho on 10/05/16.
 */
public class Team implements Serializable {

    private Long id;

    private String name;

    private String initials;

    public Team(){}

    public Team(Long id, String name, String initials) {
        this.id = id;
        this.name = name;
        this.initials = initials;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }


}
