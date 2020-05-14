package com.nitto.tushar.nrrii.Services;

import android.content.Context;
import android.widget.Toast;


import com.nitto.tushar.nrrii.DataRepository.CartItemsDataRepository;
import com.nitto.tushar.nrrii.Entity.CartItem;
import com.nitto.tushar.nrrii.Entity.ProductItem;
import com.nitto.tushar.nrrii.R;

import java.util.ArrayList;
import java.util.List;

public class CartService {

    //Listener.........................
    public interface OnUpdateUIListener {
        void onItemPriceUpdated(double totalPriceBeforeChange);
        void onItemDeleted(List<CartItem> cartItems);
        void onTotalPriceAndQuantityIndicatorUpdated();
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

    }

    public void InitializeOrderService() {
        this.cartItemsList = new ArrayList<>();
        this.getAllCartItemFromDB();
    }

    private void getAllCartItemFromDB() {
        CartItemsDataRepository.FetchAllCartItem(new CartItemsDataRepository.AllCartItemsFetchDoneListener() {
            @Override
            public void onAllCartItemFetchDone(List<CartItem> cartItems) {
                cartItemsList.addAll(cartItems);
            }
        });
    }

    public void addToCart(final CartItem dressItem) {
        CartItemsDataRepository.InsertCartItem(dressItem, new CartItemsDataRepository.CartItemInsertionDoneListener() {
            @Override
            public void onCartItemInsertionDone(Long insertID) {
                dressItem.setCartId(insertID);
                cartItemsList.add(dressItem);
                updateUIOnItemTotalPriceAndQuantityChanged();
            }
        });

    }

    public boolean isAvailableInCart(String productId) {
        for(int i=0;i<cartItemsList.size();i++) {
            if(cartItemsList.get(i).getProductId().equals(productId)) {
                return true;
            }
        }
        return false;
    }

    public void increaseQuantity(String productId){
        for (int i = 0; i < cartItemsList.size(); i++){
            if(cartItemsList.get(i).getProductId().equals(productId)){
                int quantity = cartItemsList.get(i).getProductQuantity();
                quantity++;
                cartItemsList.get(i).setProductQuantity(quantity);
            }
        }

        CartItemsDataRepository.UpdateAllCartItem(cartItemsList, new CartItemsDataRepository.CartItemUpdateDoneListener() {
            @Override
            public void onCartItemUpdateDone(int rowsUpdated) {
                updateUIOnItemTotalPriceAndQuantityChanged();
            }
        });

    }

    public void decreaseQuantity(String productId){
        for (int i = 0; i < cartItemsList.size(); i++){
            if(cartItemsList.get(i).getProductId().equals(productId)){
                int quantity = cartItemsList.get(i).getProductQuantity();
                quantity--;
                cartItemsList.get(i).setProductQuantity(quantity);
            }
        }

        CartItemsDataRepository.UpdateAllCartItem(cartItemsList, new CartItemsDataRepository.CartItemUpdateDoneListener() {
            @Override
            public void onCartItemUpdateDone(int rowsUpdated) {

            }
        });

    }

    public ArrayList<CartItem> getAllCartItem(){
        return cartItemsList;
    }

//    public boolean isEmpty() {
//        return cartItemsList.isEmpty();
//    }

    public void deleteCartItem(final long dressId) {
        CartItemsDataRepository.DeleteCartItem(dressId, new CartItemsDataRepository.CartItemDeleteDoneListener() {
            @Override
            public void onCartItemDeleteDone(int rowsUpdated) {
                for(int i=0; i<cartItemsList.size();i++){
                    if(cartItemsList.get(i).getCartId() == dressId){
                        cartItemsList.remove(i);
                    }
                }
                updateUIOnItemDelete(cartItemsList);
            }
        });

    }

    public void deleteAllCartItem() {
        CartItemsDataRepository.DeleteAllCartItem(new CartItemsDataRepository.CartItemDeleteDoneListener() {
            @Override
            public void onCartItemDeleteDone(int rowsUpdated) {
                cartItemsList.clear();
            }
        });

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

    public int getTotalQuantity(){
        int quantity = 0;
        for(int i=0;i<cartItemsList.size();i++) {
            quantity += cartItemsList.get(i).getProductQuantity();
        }
        return quantity;
    }

    private void updateUIOnItemInsert(double totalPriceBeforeChange) {
        for(int i=0;i<this.updateUIListeners.size();i++) {
            this.updateUIListeners.get(i).onItemPriceUpdated(totalPriceBeforeChange);
        }
    }

    private void updateUIOnItemDelete(List<CartItem> cartItems) {
        for(int i=0;i<this.updateUIListeners.size();i++) {
            this.updateUIListeners.get(i).onItemDeleted(cartItems);
        }
    }

    private void updateUIOnItemTotalPriceAndQuantityChanged() {
        for(int i=0;i<this.updateUIListeners.size();i++) {
            this.updateUIListeners.get(i).onTotalPriceAndQuantityIndicatorUpdated();
        }
    }
}