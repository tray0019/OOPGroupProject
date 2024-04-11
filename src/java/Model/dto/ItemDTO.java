package Model.dto;

public class ItemDTO {
    private int itemId;
    private String itemName;
    private int itemQuantity;
    private String date;
    private float price;

      public ItemDTO() {
        // No-argument constructor
    }
    
     public ItemDTO(String itemName, int itemQuantity, float price) {
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.price = price;
        // Initialize other fields
    }

    
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
