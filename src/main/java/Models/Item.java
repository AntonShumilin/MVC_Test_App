package Models;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Expose
    String modifiers;
    @Expose
    String name;
    @Expose
    String nds0;
    @Expose
    String nds10;
    @Expose
    String nds18;
    @Expose
    String ndsCalculated10;
    @Expose
    String ndsCalculated18;
    @Expose
    String ndsNo;
    @Expose
    String price;
    @Expose
    String quantity;
    @Expose
    String sum;
    @Expose
    String storno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiptId")
    public Receipt receipt;

    public Item() {
    }

    public long getId() {
        return id;
    }

    public String getModifiers() {
        return modifiers;
    }

    public String getName() {
        return name;
    }

    public String getNds0() {
        return nds0;
    }

    public String getNds10() {
        return nds10;
    }

    public String getNds18() {
        return nds18;
    }

    public String getNdsCalculated10() {
        return ndsCalculated10;
    }

    public String getNdsCalculated18() {
        return ndsCalculated18;
    }

    public String getNdsNo() {
        return ndsNo;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getSum() {
        return sum;
    }

    public String getStorno() {
        return storno;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModifiers(String modifiers) {
        this.modifiers = modifiers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNds0(String nds0) {
        this.nds0 = nds0;
    }

    public void setNds10(String nds10) {
        this.nds10 = nds10;
    }

    public void setNds18(String nds18) {
        this.nds18 = nds18;
    }

    public void setNdsCalculated10(String ndsCalculated10) {
        this.ndsCalculated10 = ndsCalculated10;
    }

    public void setNdsCalculated18(String ndsCalculated18) {
        this.ndsCalculated18 = ndsCalculated18;
    }

    public void setNdsNo(String ndsNo) {
        this.ndsNo = ndsNo;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public void setStorno(String storno) {
        this.storno = storno;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
}
