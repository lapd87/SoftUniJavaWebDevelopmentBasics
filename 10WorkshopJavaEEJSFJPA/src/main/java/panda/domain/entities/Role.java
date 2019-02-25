package panda.domain.entities;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 21.2.2019 г.
 * Time: 14:40 ч.
 */
public enum Role {
    ADMIN, USER;

    @Override
    public String toString() {
        String name = super.toString();

        return name.substring(0, 1) + name.substring(1).toLowerCase();
    }
    }