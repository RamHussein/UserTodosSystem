package com.springboot.todos.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class TodoRequest {
    @NotEmpty(message = "You need to enter an title ")
    @Size(min = 3, max = 30, message = "Title must be atleast 3 characters long")
    private String title ;

    @NotEmpty(message = "You need to enter an description ")
    @Size(min = 3, max = 30, message = "description must be atleast 3 characters long")
    private String description ;

    @Min(1)
    @Max(5)

    private int priority ;


    public TodoRequest(String title, int priority, String description) {
        this.title = title;
        this.priority = priority;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
