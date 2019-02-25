package panda.domain.entities;

public enum Status {

    PENDING, SHIPPED, DELIVERED, ACQUIRED;


    @Override
    public String toString() {
        String name = super.toString();

        return name.substring(0, 1) + name.substring(1).toLowerCase();
    }
}
