package com.zhiyou.entity;

/**
 * AbstractUserRoleId entity provides the base persistence definition of the
 * UserRoleId entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUserRoleId implements java.io.Serializable {

    // Fields

    private Role role;
    private User user;

    // Constructors

    /**
     * default constructor
     */
    public AbstractUserRoleId() {
    }

    /**
     * full constructor
     */
    public AbstractUserRoleId(Role role, User user) {
        this.role = role;
        this.user = user;
    }

    // Property accessors

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof AbstractUserRoleId))
            return false;
        AbstractUserRoleId castOther = (AbstractUserRoleId) other;

        return ((this.getRole() == castOther.getRole()) || (this.getRole() != null
                && castOther.getRole() != null && this.getRole().equals(
                castOther.getRole())))
                && ((this.getUser() == castOther.getUser()) || (this.getUser() != null
                && castOther.getUser() != null && this.getUser()
                .equals(castOther.getUser())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result
                + (getRole() == null ? 0 : this.getRole().hashCode());
        result = 37 * result
                + (getUser() == null ? 0 : this.getUser().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "AbstractUserRoleId [role=" + role + ", user=" + user + "]";
    }

}