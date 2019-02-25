package fdmc2.domain.entities.enums;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 18.2.2019 г.
 * Time: 13:32 ч.
 */
public enum Gender {
    MALE, FEMALE;

    @Override
    public String toString() {
        String name = super.toString();

        return name.substring(0, 1) + name.substring(1).toLowerCase();
    }}