package com.nitto.tushar.nrrii.Services;

import com.nitto.tushar.nrrii.DataRepository.UserDataRepository;
import com.nitto.tushar.nrrii.Entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private ArrayList<User> userArrayList;
    private User user;

    public interface OnUpdateUIListener {
        void onUserInserted(User user);
        void onUserUpdated(User user);
        void onUserDeleted(List<User> userList);
    }

    private OnUpdateUIListener updateUIListener;

    public void AddOnUpdateUIListener(OnUpdateUIListener listener) {
        this.updateUIListener = listener;
    }

    public void RemoveOnUpdateUIListener() {
        this.updateUIListener = null;
    }




    private static UserService userService = new UserService();

    public static UserService getInstance() {
        if(userService==null){
            Class clazz = UserService.class;
            synchronized (clazz){
                userService = new UserService();
            }
        }
        return userService;
    }

    // Constructor
    private UserService(){
//        // all class related initialization goes here
//        this.orderItemsList = new ArrayList<>();
//        this.updateUIListeners = new ArrayList<>();
    }

    public void InitializeUserService() {
        this.userArrayList = new ArrayList<>();
        this.getAllUserFromDB();
    }

    public void getAllUserFromDB() {
        UserDataRepository.FetchAllUsers(new UserDataRepository.AllUserFetchDoneListener() {
            @Override
            public void onAllUserFetchDone(List<User> userList) {
                userArrayList.addAll(userList);
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

    public void insertUserInDB(final User user) {
        UserDataRepository.InsertUser(user, new UserDataRepository.UserInsertionDoneListener() {
            @Override
            public void onUserInsertionDone(Long insertID) {
                user.setUserId(insertID);
                userArrayList.add(user);
                updateUIOnUserInsert(user);
            }
        });
    }

    public void UpdateUserInDB(final User user) {
        UserDataRepository.UpdateUser(new UserDataRepository.UserUpdateDoneListener() {
            @Override
            public void onUserUpdateDone(int rowsDeleted) {
                userArrayList.clear();
                userArrayList.add(user);
                updateUIOnUserUpdated(user);
            }
        }, user);
    }

    private void updateUIOnUserInsert(User user) {

            this.updateUIListener.onUserInserted(user);

    }

    private void updateUIOnUserUpdated(User user) {

        this.updateUIListener.onUserUpdated(user);

    }

//    public void deleteAllUserFromDb(){
//        UserDataRepository.DeleteAllUsers(new UserDataRepository.UserDeleteDoneListener() {
//            @Override
//            public void onUserDeleteDone(int rowsDeleted) {
//                userArrayList.clear();
//                updateUIOnItemDelete();
//            }
//        });
//    }

    private void updateUIOnItemDelete() {
            this.updateUIListener.onUserDeleted(this.userArrayList);
    }

    public int getStoredUsersNumber(){
        return userArrayList.size();
    }

    public ArrayList<User> getUserArrayList() {
        return userArrayList;
    }

    //    public void insertTmpOrderForDetails(User user){
//        this.user = null;
//        this.orderItem = orderItem;
//    }

//    public OrderItem getTmpOrderForDetails(){
//        return this.orderItem;
//    }

    public void clearUserList(){
        this.userArrayList.clear();
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

}
