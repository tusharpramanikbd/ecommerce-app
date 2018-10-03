package com.nitto.tushar.nrrii.Services;

import android.content.Context;
import android.widget.Toast;


import com.nitto.tushar.nrrii.Entity.ProductItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by masud on 2/24/2018.
 */

public class CartService {

    public interface OnUpdateUIListener {
        void onItemPriceUpdated(double totalPriceBeforeChange);
    }

    private List<OnUpdateUIListener> updateUIListeners = new ArrayList<>();

    public void AddOnUpdateUIListener(OnUpdateUIListener listener) {
        this.updateUIListeners.add(listener);
    }

    public void RemoveOnUpdateUIListener(OnUpdateUIListener listener) {
        this.updateUIListeners.remove(listener);
    }


    private static CartService CartServiceInstance;

    public static void InitCart(Context context) {
        if(CartServiceInstance==null){
            Class clazz = CartService.class;
            synchronized (clazz){
                CartServiceInstance = new CartService(context);
            }
        }
    }

    public static CartService getInstance(){
        return CartServiceInstance;
    }

    private ArrayList<ProductItem> cartItemsList;

    // Constructor
    public CartService(Context context){
        // all class related initialization goes here
        this.cartItemsList = new ArrayList<>();
    }

    public void addToCart(ProductItem productItem) {
        cartItemsList.add(productItem);
    }

    public boolean isAvailableInCart(ProductItem productItem) {
        for(int i=0;i<cartItemsList.size();i++) {
            if(cartItemsList.get(i).getUid()==productItem.getUid()) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<ProductItem> getProductCart() {
        return cartItemsList;
    }

    public boolean isEmpty() {
        return cartItemsList.isEmpty();
    }

    public void deleteCartItem(int uid) {
        for(int i=0; i<cartItemsList.size();i++){
            if(cartItemsList.get(i).getUid() == uid){
                cartItemsList.remove(i);
            }

        }
    }

    public void deleteAllCartItem(){
        cartItemsList.clear();
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for(int i=0;i<cartItemsList.size();i++) {
            if(cartItemsList.get(i).getQuantity()<1){
                totalPrice += cartItemsList.get(i).getPrice();
            }else {
                totalPrice += (cartItemsList.get(i).getPrice() * cartItemsList.get(i).getQuantity());
            }
        }
        return totalPrice;
    }

    public void changeTotalPrice(String productName, int quantity){
        double totalPriceBeforeChange = getTotalPrice();
        double currentProductPrice = 0;

        int previousQuantity = 0;

        for (int i = 0; i<this.cartItemsList.size(); i++){
            if(this.cartItemsList.get(i).getName().equals(productName)){
                currentProductPrice = this.cartItemsList.get(i).getPrice();
                previousQuantity = this.cartItemsList.get(i).getQuantity();
                this.cartItemsList.get(i).setQuantity(quantity);
            }
        }

        totalPriceBeforeChange = totalPriceBeforeChange - (currentProductPrice * previousQuantity);

        currentProductPrice = currentProductPrice * quantity;

        totalPriceBeforeChange = totalPriceBeforeChange + currentProductPrice;

        updateUIOnItemInsert(totalPriceBeforeChange);
    }

    private void updateUIOnItemInsert(double totalPriceBeforeChange) {
        for(int i=0;i<this.updateUIListeners.size();i++) {
            this.updateUIListeners.get(i).onItemPriceUpdated(totalPriceBeforeChange);
        }
    }
}