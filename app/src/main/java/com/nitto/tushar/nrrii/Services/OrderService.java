package com.nitto.tushar.nrrii.Services;


import com.nitto.tushar.nrrii.DataRepository.OrderListDataRepository;
import com.nitto.tushar.nrrii.Entity.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private ArrayList<OrderItem> orderItemsList;
    private OrderItem orderItem;

    public interface OnUpdateUIListener {
        void onItemInserted(List<OrderItem> orderItemList);
        void onItemUpdated(List<OrderItem> orderItemList);
        void onItemDeleted(List<OrderItem> orderItemList);
    }

    private List<OnUpdateUIListener> updateUIListeners;

    public void AddOnUpdateUIListener(OnUpdateUIListener listener) {
        this.updateUIListeners.add(listener);
    }

    public void RemoveOnUpdateUIListener(OnUpdateUIListener listener) {
        this.updateUIListeners.remove(listener);
    }


    private static OrderService orderService = new OrderService();

    public static OrderService getInstance() {
        if(orderService==null){
            Class clazz = OrderService.class;
            synchronized (clazz){
                orderService = new OrderService();
            }
        }

        return orderService;
    }

    // Constructor
    private OrderService(){
//        // all class related initialization goes here
//        this.orderItemsList = new ArrayList<>();
        this.updateUIListeners = new ArrayList<>();
    }

    public void InitializeOrderService() {
        this.orderItemsList = new ArrayList<>();
        this.getAllOrderFromDB();
    }

    public void getAllOrderFromDB() {
        OrderListDataRepository.FetchAllOrders(new OrderListDataRepository.AllOrderFetchDoneListener() {
            @Override
            public void onAllOrderFetchDone(List<OrderItem> orderItemList) {
                orderItemsList.addAll(orderItemList);
            }
        });
    }

//    public ArrayList<OrderItem> getAllOrderByDeliveredTo(String deliveredTo)
//    {
//        ArrayList<OrderItem> tmpOrderItemsList = new ArrayList<>();
//
//        for (int i=0; i<orderItemsList.size();i++)
//        {
//            if(orderItemsList.get(i).getDeliveredTo().equals(deliveredTo))
//            {
//                tmpOrderItemsList.add(orderItemsList.get(i));
//            }
//        }
//
//        return tmpOrderItemsList;
//    }

//    public String getLastOrderNumberById(String id){
//        for (int i = orderItemsList.size()-1; i>=0; i--){
//            if(orderItemsList.get(i).getDeliveredTo().equals(id)){
//                return orderItemsList.get(i).getOrderNumber();
//            }
//        }
//        return null;
//    }

    public void insertOrderInDB(final OrderItem orderItem) {
        OrderListDataRepository.InsertOrder(orderItem, new OrderListDataRepository.OrderInsertionDoneListener() {
            @Override
            public void onOrderInsertionDone(Long insertID) {
                orderItem.setOid(insertID);
                orderItemsList.add(orderItem);
                updateUIOnItemInsert();
            }
        });
    }

    private void updateUIOnItemInsert() {
        for(int i=0;i<this.updateUIListeners.size();i++) {
            this.updateUIListeners.get(i).onItemInserted(this.orderItemsList);
        }
    }

    public void deleteAllOrderFromDb(){
        OrderListDataRepository.DeleteAllOrders(new OrderListDataRepository.OrderDeleteDoneListener() {
            @Override
            public void onOrderDeleteDone(int rowsDeleted) {
                orderItemsList.clear();
                updateUIOnItemDelete();
            }
        });
    }

    private void updateUIOnItemDelete() {
        for(int i=0;i<this.updateUIListeners.size();i++) {
            this.updateUIListeners.get(i).onItemDeleted(this.orderItemsList);
        }
    }

    public void insertTmpOrderForDetails(OrderItem orderItem){
        this.orderItem = null;
        this.orderItem = orderItem;
    }

    public OrderItem getTmpOrderForDetails(){
        return this.orderItem;
    }

    public void clearOrderList(){
        this.orderItemsList.clear();
    }

//    public List<OrderItem> getProductCart() {
//        return orderItemsList;
//    }

//    public boolean isEmpty() {
//        return orderItemsList.isEmpty();
//    }

//    public void deleteCartItem(int oid) {
//        for(int i=0; i<orderItemsList.size();i++){
//            if(orderItemsList.get(i).getOid() == oid){
//                orderItemsList.remove(i);
//            }
//
//        }
//    }

//    public double getTotalPrice() {
//        double totalPrice = 0;
//        for(int i=0;i<orderItemsList.size();i++) {
//            totalPrice += orderItemsList.get(i).getOrderPrice();
//        }
//        return totalPrice;
//    }
}
