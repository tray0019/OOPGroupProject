package Model;

public class ItemDTO {
    private int itemId;
    //private int inventoryId; //mm-added
    private String itemName;
    private int itemQuantity; //This should be int
    
    private boolean forConsumer;
    private boolean forCharity;
    private String retailerName;
    
    //-----------------------------------------------------------

    private String date;

    private float price;

    /*-------mm-added
    public int getInventoryId() {
        return inventoryId;
    }    
    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }    
    */
    
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

    /**
     * @return the itemQuantity
     */
    public int getItemQuantity() {
        return itemQuantity;
    }

    /**
     * @param itemQuantity the itemQuantity to set
     */
    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
    
    //
    /**
     * @return if the item is for consumers at a reduced price
     */
    public boolean isForConsumer() {
        return forConsumer;
    }

    /**
     * @param forConsumer the forConsumer to set
     */
    public void setForConsumer(boolean forConsumer) {
        this.forConsumer = forConsumer;
    }

    /**
     * @return if the item is available for free to charities
     */
    public boolean isForCharity() {
        return forCharity;
    }

    /**
     * @param forCharity the forCharity to set
     */
    public void setForCharity(boolean forCharity) {
        this.forCharity = forCharity;
    }
    /**
     * @return the name of the retailer
     */
    public String getRetailerName() {
        return retailerName;
    }

    /**
     * @param retailerName the name of the retailer to set
     */
    public void setRetailerName(String retailerName) {
        this.retailerName = retailerName;
    }
    
   
    //------------------------------------------
}
