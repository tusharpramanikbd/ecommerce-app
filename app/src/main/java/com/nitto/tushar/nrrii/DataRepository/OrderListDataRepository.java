package com.nitto.tushar.nrrii.DataRepository;

import android.os.AsyncTask;


import com.nitto.tushar.nrrii.Database.AppDatabase;
import com.nitto.tushar.nrrii.Entity.OrderItem;

import java.util.List;

public class OrderListDataRepository {
    public interface OrderInsertionDoneListener {
        void onOrderInsertionDone(Long insertID);
    }

//    public interface AllOrderInsertionDoneListener {
//        void onAllOrderInsertionDone(long[] insertIDs);
//    }

    public interface AllOrderFetchDoneListener {
        void onAllOrderFetchDone(List<OrderItem> orderItemList);
    }

    public interface OrderDeleteDoneListener {
        void onOrderDeleteDone(int rowsDeleted);
    }
//
//    public interface OrderAvailableListener{
//        void onOrderAvailable(boolean hasFordoChat);
//    }

    public static void InsertOrder(OrderItem orderItem, OrderListDataRepository.OrderInsertionDoneListener listener) {
        new InsertOrderTask(orderItem, listener).execute();
    }

    public static void FetchAllOrders(AllOrderFetchDoneListener listener) {
        new FetchAllOrdersTask(listener).execute();
    }

    public static void DeleteAllOrders(OrderDeleteDoneListener listener) {
        new DeleteOrderTask(listener).execute();
    }

//    public static void HasFordoChatInDB(OrderAvailableListener listener) {
//        new HasOrderCheckTask(listener).execute();
//    }

    private static class InsertOrderTask extends AsyncTask<Void, Void, Long>{
        private OrderItem orderItem;
        private OrderInsertionDoneListener orderInsertionDoneListener;
        public InsertOrderTask(OrderItem orderItem, OrderInsertionDoneListener listener) {
            this.orderItem = orderItem;
            this.orderInsertionDoneListener = listener;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return AppDatabase.getInstance().orderDao().insert(this.orderItem);
        }

        @Override
        protected void onPostExecute(Long insertID) {
            super.onPostExecute(insertID);
            this.orderInsertionDoneListener.onOrderInsertionDone(insertID);
        }
    }


    private static class FetchAllOrdersTask extends AsyncTask<Void, Void, List<OrderItem>>{
        private AllOrderFetchDoneListener allOrderFetchDoneListener;
        public FetchAllOrdersTask(AllOrderFetchDoneListener listener) {
            this.allOrderFetchDoneListener = listener;
        }

        @Override
        protected List<OrderItem> doInBackground(Void... voids) {
            return AppDatabase.getInstance().orderDao().getAllOrder();
        }

        @Override
        protected void onPostExecute(List<OrderItem> fordoChats) {
            super.onPostExecute(fordoChats);
            this.allOrderFetchDoneListener.onAllOrderFetchDone(fordoChats);
        }
    }

    private static class DeleteOrderTask extends AsyncTask<Void, Void, Integer>{
        private OrderDeleteDoneListener fordoChatDeleteDoneListener;
        public DeleteOrderTask( OrderDeleteDoneListener listener) {
            this.fordoChatDeleteDoneListener = listener;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return AppDatabase.getInstance().orderDao().delete();
        }

        @Override
        protected void onPostExecute(Integer numberOfDeletedRows) {
            super.onPostExecute(numberOfDeletedRows);
            this.fordoChatDeleteDoneListener.onOrderDeleteDone(numberOfDeletedRows);
        }
    }

//    private static class HasOrderCheckTask extends AsyncTask<Void, Void, Boolean>{
//        private OrderAvailableListener fordoChatAvailableListener;
//        public HasOrderCheckTask(OrderAvailableListener listener) {
//            this.fordoChatAvailableListener = listener;
//        }
//
//
//        @Override
//        protected Boolean doInBackground(Void... voids) {
//            return FordoDatabase.getInstance().fordoChatDao().hasFordoChatInDB()==null?false:true;
//        }
//
//        @Override
//        protected void onPostExecute(Boolean hasFordoChat) {
//            super.onPostExecute(hasFordoChat);
//            this.fordoChatAvailableListener.onOrderAvailable(hasFordoChat);
//        }
//    }
}
