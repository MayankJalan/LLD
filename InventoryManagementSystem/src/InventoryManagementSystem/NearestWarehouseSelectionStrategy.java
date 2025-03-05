package InventoryManagementSystem;

import java.util.List;

public class NearestWarehouseSelectionStrategy implements WarehouseSelectionStrategy{
    @Override
    public Warehouse selectWarehouse(List<Warehouse> warehouseList) {
        return warehouseList.get(0);
    }
}
