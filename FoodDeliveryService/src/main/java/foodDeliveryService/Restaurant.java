package foodDeliveryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Restaurant {
    private final String id;
    private final String name;
    private Location location;
    private HashMap<String,MenuItem> menu;

    public Restaurant(String name, Location location) {
        this.id= UUID.randomUUID().toString();
        this.name = name;
        this.location = location;
        menu=new HashMap<>();
    }
    public void addMenuItem(MenuItem item) {
        menu.put(item.getName(),item);
    }

    public void removeMenuItem(MenuItem item) {
        menu.remove(item.getName());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<String,MenuItem> getMenu() {
        return menu;
    }

    public Location getLocation() {
        return location;
    }
}
