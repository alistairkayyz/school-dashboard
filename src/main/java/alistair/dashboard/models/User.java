package alistair.dashboard.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table
public class User implements Serializable {
    @Id
    @GeneratedValue
    @NotNull(message = "Username cannot be null")
    private long id;

    @NotNull(message = "Password cannot be null")
    @Column(length = 1000)
    private String password;

    @NotNull(message = "Password cannot be null")
    @Column(length = 1000)
    private String salt;

    @NotNull(message = "Role cannot be null")
    private String role;

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

}
