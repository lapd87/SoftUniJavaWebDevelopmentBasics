package panda.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static panda.constants.Constants.*;

@Entity
@Table(name = RECEIPT_ENTITIES)
public class Receipt extends BaseEntity {

    private BigDecimal fee;
    private LocalDateTime issuedOn;
    private User recipient;
    private Package aPackage;

    public Receipt() {
    }

    @Column(name = PARAMETER_FEE, nullable = false,
            columnDefinition = "DECIMAL(10, 2) DEFAULT '0.00'")
    @NotNull
    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @Column(name = PARAMETER_ISSUED_ON, nullable = false)
    @NotNull
    public LocalDateTime getIssuedOn() {
        return issuedOn;
    }

    public void setIssuedOn(LocalDateTime issuedOn) {
        this.issuedOn = issuedOn;
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

    @OneToOne(targetEntity = Package.class)
    @JoinColumn(
            name = PARAMETER_PACKAGE_ID,
            referencedColumnName = PARAMETER_ID
    )
    @NotNull
    public Package getaPackage() {
        return aPackage;
    }

    public void setaPackage(Package aPackage) {
        this.aPackage = aPackage;
    }
}
