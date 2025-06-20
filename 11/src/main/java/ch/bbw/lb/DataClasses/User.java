package ch.bbw.lb.DataClasses;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public final class User implements Comparable<User> {
    public final Integer id;
    public final String userName;
    public final String email;
    public final String password;
    public final LocalDateTime registeredAt;
    public final List<String> roles;

    public User(
            Integer id,
            String userName,
            String email,
            String password,
            LocalDateTime registeredAt,
            List<String> roles
    ) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.registeredAt = registeredAt;
        this.roles = roles;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (User) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.userName, that.userName) &&
                Objects.equals(this.email, that.email) &&
                Objects.equals(this.password, that.password) &&
                Objects.equals(this.registeredAt, that.registeredAt) &&
                Objects.equals(this.roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, email, password, registeredAt, roles);
    }

    @Override
    public String toString() {
        return "User[" +
                "id=" + id + ", " +
                "userName=" + userName + ", " +
                "email=" + email + ", " +
                "password=" + password + ", " +
                "registeredAt=" + registeredAt + ", " +
                "roles=" + roles + ']';
    }

    @Override
    public int compareTo(@NotNull User o) {
        return email.compareTo(o.email);
    }
}