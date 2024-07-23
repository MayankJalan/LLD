package VendingMachineLLD;

import VendingMachineLLD.VendingStates.State;
import VendingMachineLLD.VendingStates.impl.HasMoneyState;

public class Main {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        try {
            System.out.println("|");
            System.out.println("filling up the inventory");
            System.out.println("|");

            fillUpInventory(vendingMachine);
            displayInventory(vendingMachine);

            System.out.println("|");
            System.out.println("clicking on InsertVCoin Button");
            System.out.println("|");

            State vendingState= vendingMachine.getVendingMachineState();
            vendingState.clickOnInsertCoinButton(vendingMachine);

            vendingState=vendingMachine.getVendingMachineState();
            vendingState.insertCoin(vendingMachine,Coin.TEN);
            vendingState.insertCoin(vendingMachine,Coin.TEN);

            System.out.println("|");
            System.out.println("clicking on ProductSelectionButton");
            System.out.println("|");

            vendingState.clickOnProductSelectionButton(vendingMachine);
            vendingState=vendingMachine.getVendingMachineState();
            vendingState.chooseProduct(vendingMachine,102);

            displayInventory(vendingMachine);








        } catch (Exception ex) {

        }
    }

    private static void displayInventory(VendingMachine vendingMachine) {
        for (ItemShelf itemShelf : vendingMachine.getInventory().getInventory()) {
            System.out.println("CodeNumber: " + itemShelf.getCode() +
                    " Item: " + itemShelf.getItem().getType().name() +
                    " Price: " + itemShelf.getItem().getPrice() +
                    " isAvailable: " + !itemShelf.isSoldOut());
        }
    }

    private static void fillUpInventory(VendingMachine vendingMachine) {
        ItemShelf[] slots = vendingMachine.getInventory().getInventory();
        for (int i = 0; i < slots.length; i++) {
            Item newItem = new Item();
            if (i < 3) {
                newItem.setItemType(ItemType.COKE);
                newItem.setPrice(15);
            } else if (i >= 3 && i < 5) {
                newItem.setItemType(ItemType.JUICE);
                newItem.setPrice(12);
            } else if (i >= 6 && i < 8) {
                newItem.setItemType(ItemType.PEPSI);
                newItem.setPrice(15);
            } else {
                newItem.setItemType(ItemType.SODA);
                newItem.setPrice(10);
            }
            slots[i].setItem(newItem);
            slots[i].setSoldOut(false);
        }


    }
}