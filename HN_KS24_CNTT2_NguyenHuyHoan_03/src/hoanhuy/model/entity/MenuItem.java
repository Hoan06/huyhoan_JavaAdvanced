package hoanhuy.model.entity;

import java.math.BigDecimal;

public class MenuItem {
    private int id;
    private String name;
    private BigDecimal price;
    private MenuItemType type;
    private int stock;

    public MenuItem() {
    }

    public MenuItem(int id, String name, BigDecimal price, MenuItemType type, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.stock = stock;
    }

    public MenuItem(String name, BigDecimal price, MenuItemType type, int stock) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public MenuItemType getType() {
        return type;
    }

    public void setType(MenuItemType type) {
        this.type = type;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}