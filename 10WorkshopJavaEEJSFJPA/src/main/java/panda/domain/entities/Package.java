package panda.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import static panda.constants.Constants.*;

@Entity
@Table(name = PACKAGE_ENTITIES)
public class Package extends BaseEntity {

    private String description;
    private Double weight;
    private String shippingAddress;
    private Status status;
    private String estimatedDeliveryTime;
    private User recipient;

    public Package() {
    }

    @Column(name = PARAMETER_DESC, nullable = false)
    @NotNull
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = PARAMETER_WEIGHT, nullable = false)
    @NotNull
    @DecimalMin("0.01")
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Column(name = PARAMETER_ADDRESS, nullable = false)
    @NotNull
    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = PARAMETER_STATUS, nullable = false)
    @NotNull
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Column(name = PARAMETER_EDT)
    @NotNull
    public String getEstimatedDeliveryTime() {
        return estimatedDeliveryTime;
    }

    public void setEstimatedDeliveryTime(String estimatedDeliveryTime) {
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(
            name = PARAMETER_RECIPIENT_ID,
            referencedColumnName = PARAMETER_ID
    )
    @NotNull
    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }
}
