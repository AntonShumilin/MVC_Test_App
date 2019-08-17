package Models;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "receipts")
public class Receipt {

    @Expose
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "userId")
    public long userId;


    @Expose
    public String authorityUri;
    @Expose
    public String addressToCheckFiscalSign;
    @Expose
    public String babuyerAddress;
    @Expose
    public String cashTotalSum;
    @Expose
    public String dateTime;
    @Expose
    public String ecashTotalSum;
    @Expose
    @Column(name = "fiscalDocumentNumber", unique = true, updatable = false)
    public String fiscalDocumentNumber;
    @Expose
    public String fiscalDriveNumber;
    @Expose
    public String fiscalSign;
    @Expose
    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL)
    public List<Item> items;
    @Expose
    public String kktRegId;
    @Expose
    public String nds10;
    @Expose
    public String nds18;
    @Expose
    public String operationType;
    @Expose
    public String operator;
    @Expose
    @Column(name = "rawData")
    @Type(type = "text")
    public String rawData;
    @Expose
    public String receiptCode;
    @Expose
    public String requestNumber;
    @Expose
    public String retailPlaceAddress;
    @Expose
    public String senderAddress;
    @Expose
    public String shiftNumber;
    @Expose
    public String taxationType;
    @Expose
    public String totalSum;
    @Expose
    @Column(name = "tsp_user_name")
    public String user;
    @Expose
    public String userInn;

    public Receipt() {
    }

    public long getId() {
        return id;
    }

    public String getAuthorityUri() {
        return authorityUri;
    }

    public String getAddressToCheckFiscalSign() {
        return addressToCheckFiscalSign;
    }

    public String getBabuyerAddress() {
        return babuyerAddress;
    }

    public String getCashTotalSum() {
        return cashTotalSum;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getEcashTotalSum() {
        return ecashTotalSum;
    }

    public String getFiscalDocumentNumber() {
        return fiscalDocumentNumber;
    }

    public String getFiscalDriveNumber() {
        return fiscalDriveNumber;
    }

    public String getFiscalSign() {
        return fiscalSign;
    }

    public List<Item> getItems() {
        return items;
    }

    public String getKktRegId() {
        return kktRegId;
    }

    public String getNds10() {
        return nds10;
    }

    public String getNds18() {
        return nds18;
    }

    public String getOperationType() {
        return operationType;
    }

    public String getOperator() {
        return operator;
    }

    public String getReceiptCode() {
        return receiptCode;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public String getRetailPlaceAddress() {
        return retailPlaceAddress;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public String getShiftNumber() {
        return shiftNumber;
    }

    public String getTaxationType() {
        return taxationType;
    }

    public String getTotalSum() {
        return totalSum;
    }

    public String getUserInn() {
        return userInn;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAuthorityUri(String authorityUri) {
        this.authorityUri = authorityUri;
    }

    public void setAddressToCheckFiscalSign(String addressToCheckFiscalSign) {
        this.addressToCheckFiscalSign = addressToCheckFiscalSign;
    }

    public void setBabuyerAddress(String babuyerAddress) {
        this.babuyerAddress = babuyerAddress;
    }

    public void setCashTotalSum(String cashTotalSum) {
        this.cashTotalSum = cashTotalSum;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setEcashTotalSum(String ecashTotalSum) {
        this.ecashTotalSum = ecashTotalSum;
    }

    public void setFiscalDocumentNumber(String fiscalDocumentNumber) {
        this.fiscalDocumentNumber = fiscalDocumentNumber;
    }

    public void setFiscalDriveNumber(String fiscalDriveNumber) {
        this.fiscalDriveNumber = fiscalDriveNumber;
    }

    public void setFiscalSign(String fiscalSign) {
        this.fiscalSign = fiscalSign;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setKktRegId(String kktRegId) {
        this.kktRegId = kktRegId;
    }

    public void setNds10(String nds10) {
        this.nds10 = nds10;
    }

    public void setNds18(String nds18) {
        this.nds18 = nds18;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setReceiptCode(String receiptCode) {
        this.receiptCode = receiptCode;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public void setRetailPlaceAddress(String retailPlaceAddress) {
        this.retailPlaceAddress = retailPlaceAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public void setShiftNumber(String shiftNumber) {
        this.shiftNumber = shiftNumber;
    }

    public void setTaxationType(String taxationType) {
        this.taxationType = taxationType;
    }

    public void setTotalSum(String totalSum) {
        this.totalSum = totalSum;
    }

    public void setUserInn(String userInn) {
        this.userInn = userInn;
    }

        public void setUserID(long userId) {
        this.userId = userId;
    }
}
