package entities;

import annotations.Column;
import annotations.PrimaryKey;
import annotations.Table;

import java.io.Serializable;

@Table(name = "users")
public class User implements Serializable {

    @PrimaryKey
    @Column(name = "username")
    private String name;

    private String password;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User: " + name;
    }
}
