package com.codecool.web.dto;

import com.codecool.web.model.User;
import com.codecool.web.model.Work;

import java.util.List;

public class UserDto {

    User user;
    private List<Work> works;

    public UserDto(User user, List<Work> works) {
        this.user = user;
        this.works = works;
    }

    public User getUser() {
        return user;
    }

    public List<Work> getWorks() {
        return works;
    }
}
