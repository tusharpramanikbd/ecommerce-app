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

    @ColumnInfo(name = "orderTitle")
    private String orderTitle;

    @ColumnInfo(name = "orderNumber")
    private String orderNumber;

    @ColumnInfo(name = "products_quantity")
    private int quantity;

    @ColumnInfo(name = "order_date")
    private String orderDate;

    @ColumnInfo(name = "order_status")
    private boolean isOrderComplete;


    public OrderItem()
    {
    }

    @Ignore
    public OrderItem(int quantity, String orderDate, boolean isOrderComplete) {
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.isOrderComplete = isOrderComplete;
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

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }
}
