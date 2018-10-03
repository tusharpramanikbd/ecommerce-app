package com.nitto.tushar.nrrii.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "OrderItem")
public class OrderItem
{
    @PrimaryKey(autoGenerate = true)
    private long oid;

    @ColumnInfo(name = "orderNumber")
    private String orderNumber;

    @ColumnInfo(name = "order_description")
    private String orderDescription;

    @ColumnInfo(name = "order_price")
    private double orderPrice;

    @ColumnInfo(name = "products_quantity")
    private int quantity;

    @ColumnInfo(name = "order_date")
    private String orderDate;

    @ColumnInfo(name = "order_status")
    private boolean isOrderComplete;

    @ColumnInfo(name = "shop_id")
    private String shopId;

    @ColumnInfo(name = "shop_name")
    private String shopName;

    @ColumnInfo(name = "delivered_to")
    private String deliveredTo;


    public OrderItem()
    {
    }

    @Ignore
    public OrderItem(long oid, String orderNumber, String orderDescription, double orderPrice, int quantity, String orderDate, boolean isOrderComplete, String shopId, String shopName, String deliveredTo)
    {
        this.oid = oid;
        this.orderNumber = orderNumber;
        this.orderDescription = orderDescription;
        this.orderPrice = orderPrice;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.isOrderComplete = isOrderComplete;
        this.shopId = shopId;
        this.shopName = shopName;
        this.deliveredTo = deliveredTo;
    }

    public long getOid()
    {
        return oid;
    }

    public void setOid(long oid)
    {
        this.oid = oid;
    }

    public String getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public String getOrderDescription()
    {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription)
    {
        this.orderDescription = orderDescription;
    }

    public double getOrderPrice()
    {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice)
    {
        this.orderPrice = orderPrice;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public String getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(String orderDate)
    {
        this.orderDate = orderDate;
    }

    public boolean isOrderComplete()
    {
        return isOrderComplete;
    }

    public void setOrderComplete(boolean orderComplete)
    {
        isOrderComplete = orderComplete;
    }

    public String getShopId()
    {
        return shopId;
    }

    public void setShopId(String shopId)
    {
        this.shopId = shopId;
    }

    public String getShopName()
    {
        return shopName;
    }

    public void setShopName(String shopName)
    {
        this.shopName = shopName;
    }

    public String getDeliveredTo()
    {
        return deliveredTo;
    }

    public void setDeliveredTo(String deliveredTo)
    {
        this.deliveredTo = deliveredTo;
    }
}
