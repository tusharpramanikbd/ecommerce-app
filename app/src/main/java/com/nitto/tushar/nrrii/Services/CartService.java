package com.nitto.tushar.nrrii.Services;

import android.content.Context;
import android.widget.Toast;


import com.nitto.tushar.nrrii.Entity.CartItem;
import com.nitto.tushar.nrrii.Entity.ProductItem;
import com.nitto.tushar.nrrii.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by masud on 2/24/2018.
 */

public class CartService {

    //Listener.........................
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
    //..................................



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

    private ArrayList<CartItem> cartItemsList;

    // Constructor
    public CartService(Context context){
        // all class related initialization goes here
        this.cartItemsList = new ArrayList<>();
    }

    public void addToCart(CartItem dressItem) {
        cartItemsList.add(dressItem);
    }

    public boolean isAvailableInCart(CartItem dressItem) {
        for(int i=0;i<cartItemsList.size();i++) {
            if(cartItemsList.get(i).getProductId().equals(dressItem.getProductId())) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<CartItem> getProductCart() {
        return cartItemsList;
    }

    public ArrayList<CartItem> getAllCartItem(){
        //ArrayList<CartItem> cartItems = new ArrayList<>();

        cartItemsList.clear();
        cartItemsList.add(new CartItem("1", R.mipmap.dress40,1, 2950, "M", "Pink"));
        cartItemsList.add(new CartItem("2",R.mipmap.dress50,1, 3450, "L", "Yellow"));
        cartItemsList.add(new CartItem("3",R.mipmap.dress60,1, 2250, "XL", "Pink"));

        return cartItemsList;
    }

//    public boolean isEmpty() {
//        return cartItemsList.isEmpty();
//    }

    public void deleteCartItem(String dressId) {
        for(int i=0; i<cartItemsList.size();i++){
            if(cartItemsList.get(i).getProductId().equals(dressId)){
                cartItemsList.remove(i);
            }
        }
    }

//    public void deleteAllCartItem(){
//        cartItemsList.clear();
//    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for(int i=0;i<cartItemsList.size();i++) {
            if(cartItemsList.get(i).getProductQuantity()<1){
                totalPrice += cartItemsList.get(i).getProductPrice();
            }else {
                totalPrice += (cartItemsList.get(i).getProductPrice() * cartItemsList.get(i).getProductQuantity());
            }
        }
        return totalPrice;
    }

    public void changeTotalPrice(String productId, int quantity){
        double totalPriceBeforeChange = getTotalPrice();
        double currentProductPrice = 0;

        int previousQuantity = 0;

        for (int i = 0; i<this.cartItemsList.size(); i++){
            if(this.cartItemsList.get(i).getProductId().equals(productId)){
                currentProductPrice = this.cartItemsList.get(i).getProductPrice();
                previousQuantity = this.cartItemsList.get(i).getProductQuantity();
                this.cartItemsList.get(i).setProductQuantity(quantity);
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