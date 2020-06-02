package model;

/**
 * 交易方向枚举
 */
public enum EDirect {
    Buy(1),
    Sell(-1);

    private int value;

    private EDirect(int num) {
        this.value = num;
    }

    public int toValue() {
        return value;
    }
}
