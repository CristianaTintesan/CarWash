package entity;

import javax.persistence.*;
import javax.swing.*;
import java.awt.*;

@Entity
@Table(name = "user")
public class User {

    @Column(name = "idUser")
    private String id;

    @Column
    private String name;

    @Id
    private String username;

    @Column
    private String password;

    public User(String name) {
        this.name = name;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
