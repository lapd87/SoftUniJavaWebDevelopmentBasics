package panda.domain.models.view;

import java.math.BigDecimal;

public class ReceiptDetailsViewModel {

    private String id;
    private BigDecimal fee;
    private String issuedOn;
    private PackageReceiptDetailsViewModel aPackage;

    public ReceiptDetailsViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getIssuedOn() {
        return issuedOn;
    }

    public void setIssuedOn(String issuedOn) {
        this.issuedOn = issuedOn;
    }

    public PackageReceiptDetailsViewModel getaPackage() {
        return aPackage;
    }

    public void setaPackage(PackageReceiptDetailsViewModel aPackage) {
        this.aPackage = aPackage;
    }
}
