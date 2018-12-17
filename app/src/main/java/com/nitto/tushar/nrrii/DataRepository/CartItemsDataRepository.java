package com.nitto.tushar.nrrii.DataRepository;

import android.os.AsyncTask;

import com.nitto.tushar.nrrii.Database.AppDatabase;
import com.nitto.tushar.nrrii.Entity.CartItem;
import com.nitto.tushar.nrrii.Entity.OrderItem;

import java.util.List;

public class CartItemsDataRepository {
    public interface CartItemInsertionDoneListener {
        void onCartItemInsertionDone(Long insertID);
    }

    public interface AllCartItemsFetchDoneListener {
        void onAllCartItemFetchDone(List<CartItem> cartItems);
    }

    public interface CartItemDeleteDoneListener {
        void onCartItemDeleteDone(int rowsUpdated);
    }

    public interface CartItemUpdateDoneListener {
        void onCartItemUpdateDone(int rowsUpdated);
    }

    public static void InsertCartItem(CartItem cartItem, CartItemsDataRepository.CartItemInsertionDoneListener listener) {
        new InsertCartItemTask(cartItem, listener).execute();
    }

    public static void FetchAllCartItem(AllCartItemsFetchDoneListener listener) {
        new FetchAllCartItemTask(listener).execute();
    }

    public static void UpdateAllCartItem(List<CartItem> cartItems,CartItemUpdateDoneListener listener) {
        new UpdateAllCartItemTask(cartItems, listener).execute();
    }

    public static void DeleteCartItem(Long cartId, CartItemDeleteDoneListener listener) {
        new DeleteCartItemTask(cartId, listener).execute();
    }

    private static class InsertCartItemTask extends AsyncTask<Void, Void, Long>{
        private CartItem cartItem;
        private CartItemInsertionDoneListener cartItemInsertionDoneListener;
        public InsertCartItemTask(CartItem cartItem, CartItemInsertionDoneListener listener) {
            this.cartItem = cartItem;
            this.cartItemInsertionDoneListener = listener;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return AppDatabase.getInstance().cartDao().insert(this.cartItem);
        }

        @Override
        protected void onPostExecute(Long insertID) {
            super.onPostExecute(insertID);
            this.cartItemInsertionDoneListener.onCartItemInsertionDone(insertID);
        }
    }

    private static class UpdateAllCartItemTask extends AsyncTask<Void, Void, Integer>{
        private List<CartItem> cartItems;
        private CartItemUpdateDoneListener cartItemUpdateDoneListener;
        public UpdateAllCartItemTask(List<CartItem> cartItems, CartItemUpdateDoneListener listener) {
            this.cartItems = cartItems;
            this.cartItemUpdateDoneListener = listener;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return AppDatabase.getInstance().cartDao().updateCartItem(this.cartItems);
        }

        @Override
        protected void onPostExecute(Integer insertID) {
            super.onPostExecute(insertID);
            this.cartItemUpdateDoneListener.onCartItemUpdateDone(insertID);
        }
    }


    private static class FetchAllCartItemTask extends AsyncTask<Void, Void, List<CartItem>>{
        private AllCartItemsFetchDoneListener allCartItemFetchDoneListener;
        public FetchAllCartItemTask(AllCartItemsFetchDoneListener listener) {
            this.allCartItemFetchDoneListener = listener;
        }

        @Override
        protected List<CartItem> doInBackground(Void... voids) {
            return AppDatabase.getInstance().cartDao().getAllCartItem();
        }

        @Override
        protected void onPostExecute(List<CartItem> cartItems) {
            super.onPostExecute(cartItems);
            this.allCartItemFetchDoneListener.onAllCartItemFetchDone(cartItems);
        }
    }

    private static class DeleteCartItemTask extends AsyncTask<Void, Void, Integer>{

        private Long cartId;
        private CartItemDeleteDoneListener CartItemDeleteDoneListener;

        public DeleteCartItemTask(Long cartId, CartItemDeleteDoneListener listener) {
            this.cartId = cartId;
            this.CartItemDeleteDoneListener = listener;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return AppDatabase.getInstance().cartDao().delete(cartId);
        }

        @Override
        protected void onPostExecute(Integer numberOfDeletedRows) {
            super.onPostExecute(numberOfDeletedRows);
            this.CartItemDeleteDoneListener.onCartItemDeleteDone(numberOfDeletedRows);
        }
    }
}
