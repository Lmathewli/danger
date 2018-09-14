package com.life.full.danger.user.model;

/**
 * @author LiHongHui
 * @date 2018/8/28 20:32
 * @description
 */
public class Car {
    private Long id;
    private String color;
    private String name;
    //用户id
    private Integer userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
