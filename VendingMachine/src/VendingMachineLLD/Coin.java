package VendingMachineLLD;

public enum Coin {
    ONE(1),
    TEN(10),
    TWENTY(20),
    FIFTY(50),
    HUNDREAD(100);

    public int value;
    Coin(int value){
        this.value=value;
    }

}
