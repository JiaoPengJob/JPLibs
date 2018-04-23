package com.libs.jiaop;

/**
 * <pre>
 *     author : jiaop
 *     time   : 2018/04/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class UserEvent {

    private String name;
    private int age;

    public UserEvent() {
    }

    public UserEvent(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
