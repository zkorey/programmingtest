package model;

/**
 * 具体每条记录
 */
public class Transaction {
    /**
     * 主键
     */
    private int transactionID;
    private int tradeID;
    private int version;
    private String securityCode;
    /**
     * 数量
     */
    private int quantity;
    /**
     * 动作：insert 一定是第一个版本，cancel 一定是最后一个版本
     */
    private EAction actions;
    private EDirect direct;

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getTradeID() {
        return tradeID;
    }

    public void setTradeID(int tradeID) {
        this.tradeID = tradeID;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public EAction getActions() {
        return actions;
    }

    public void setActions(EAction actions) {
        this.actions = actions;
    }

    public EDirect getDirect() {
        return direct;
    }

    public void setDirect(EDirect direct) {
        this.direct = direct;
    }

    public int getQuantityValue() {
        return this.quantity * this.direct.toValue();
    }


}
