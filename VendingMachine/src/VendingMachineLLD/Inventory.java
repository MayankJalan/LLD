package VendingMachineLLD;

public class Inventory {

    private ItemShelf[] inventory = null;

    public Inventory(int itemCount) {
        this.inventory = new ItemShelf[itemCount];
        initialEmptyInventory();
    }

    public ItemShelf[] getInventory() {
        return inventory;
    }

    private void initialEmptyInventory() {
        int startCode = 101;
        for (int i = 0; i < inventory.length; i++) {
            ItemShelf itemShelf = new ItemShelf();
            itemShelf.setCode(startCode++);
            itemShelf.setSoldOut(true);
            inventory[i] = itemShelf;
        }
    }

    public void addItem(Item item, int codeNumber) throws Exception {
        for (ItemShelf itemShelf : inventory) {
            if (itemShelf.getCode() == codeNumber) {
                if (itemShelf.isSoldOut()) {
                    itemShelf.setItem(item);
                    itemShelf.setSoldOut(false);
                } else {
                    throw new Exception("Item is already present");
                }
            }
        }
    }

    public void updateSoldOutItem(int codeNumber) {
        for (ItemShelf itemShelf : inventory) {
            if (itemShelf.code == codeNumber) {
                itemShelf.setSoldOut(true);
            }
        }
    }

    public Item getItem(int codeNumber) throws Exception {
        for (ItemShelf itemShelf : inventory) {
            if (itemShelf.getCode() == codeNumber) {
                if (!itemShelf.soldOut) {
                    return itemShelf.getItem();
                } else {
                    throw new Exception("Item Sold Out");
                }
            }
        }
        throw new Exception("Invalid Code");
    }

}
