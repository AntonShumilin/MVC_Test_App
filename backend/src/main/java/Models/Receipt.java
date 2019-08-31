package Models;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "receipts")
public class Receipt  extends SortedObject implements Serializable{

    @Expose
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Expose
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
    @Temporal(TemporalType.TIMESTAMP)
    public Date dateTime;
    @Expose
    public String ecashTotalSum;
    @Expose
//    @Column(name = "fiscalDocumentNumber", unique = true, updatable = false)
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

    public String name;

    public Receipt() {
    }

    public long getId() {
        return id;
    }

        public void setUserIDandName(long userId) {
        this.userId = userId;
        if (user != null) this.name = userInn + " " + user;
        else this.name = userInn;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Date getDateTime() {
        return dateTime;
    }
}
