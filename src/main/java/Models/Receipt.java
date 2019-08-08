package Models;

import java.util.ArrayList;

public class Receipt {
    public String authorityUri;
    public String addressToCheckFiscalSign;
    public String babuyerAddress;
    public String cashTotalSum;
    public String dateTime;
    public String ecashTotalSum;
    public String fiscalDocumentNumber;
    public String fiscalDriveNumber;
    public String fiscalSign;
    ArrayList<Item> items = new ArrayList<>();
    public String kktRegId;
    public String nds10;
    public String nds18;
    public String operationType;
    public String operator;
    public String rawData;
    public String receiptCode;
    public String requestNumber;
    public String retailPlaceAddress;
    public String senderAddress;
    public String shiftNumber;
    public String taxationType;
    public String totalSum;
    public String user;
    public String userInn;
}
