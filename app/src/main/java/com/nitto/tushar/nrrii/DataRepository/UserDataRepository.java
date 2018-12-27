package com.nitto.tushar.nrrii.DataRepository;

import android.os.AsyncTask;

import com.nitto.tushar.nrrii.Database.AppDatabase;
import com.nitto.tushar.nrrii.Entity.User;

import java.util.List;

public class UserDataRepository {
    public interface UserInsertionDoneListener {
        void onUserInsertionDone(Long insertID);
    }

//    public interface AllOrderInsertionDoneListener {
//        void onAllOrderInsertionDone(long[] insertIDs);
//    }

    public interface AllUserFetchDoneListener {
        void onAllUserFetchDone(List<User> userList);
    }

    public interface UserUpdateDoneListener {
        void onUserUpdateDone(int rowsDeleted);
    }
//
//    public interface OrderAvailableListener{
//        void onOrderAvailable(boolean hasFordoChat);
//    }

    public static void InsertUser(User user, UserDataRepository.UserInsertionDoneListener listener) {
        new InsertUserTask(user, listener).execute();
    }

    public static void FetchAllUsers(AllUserFetchDoneListener listener) {
        new FetchAllUserTask(listener).execute();
    }

    public static void UpdateUser(UserUpdateDoneListener listener, User user) {
        new UpdateUserTask(listener, user).execute();
    }

//    public static void HasFordoChatInDB(OrderAvailableListener listener) {
//        new HasOrderCheckTask(listener).execute();
//    }

    private static class InsertUserTask extends AsyncTask<Void, Void, Long>{
        private User user;
        private UserInsertionDoneListener userInsertionDoneListener;
        public InsertUserTask(User user, UserInsertionDoneListener listener) {
            this.user = user;
            this.userInsertionDoneListener = listener;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return AppDatabase.getInstance().userDao().insert(this.user);
        }

        @Override
        protected void onPostExecute(Long insertID) {
            super.onPostExecute(insertID);
            this.userInsertionDoneListener.onUserInsertionDone(insertID);
        }
    }


    private static class FetchAllUserTask extends AsyncTask<Void, Void, List<User>>{
        private AllUserFetchDoneListener allUserFetchDoneListener;
        public FetchAllUserTask(AllUserFetchDoneListener listener) {
            this.allUserFetchDoneListener = listener;
        }

        @Override
        protected List<User> doInBackground(Void... voids) {
            return AppDatabase.getInstance().userDao().getAll();
        }

        @Override
        protected void onPostExecute(List<User> userList) {
            super.onPostExecute(userList);
            this.allUserFetchDoneListener.onAllUserFetchDone(userList);
        }
    }

    private static class UpdateUserTask extends AsyncTask<Void, Void, Integer>{
        private UserUpdateDoneListener userUpdateDoneListener;
        private User user;
        public UpdateUserTask( UserUpdateDoneListener listener, User user) {
            this.userUpdateDoneListener = listener;
            this.user = user;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return AppDatabase.getInstance().userDao().update(this.user);
        }

        @Override
        protected void onPostExecute(Integer numberOfUpdatedRows) {
            super.onPostExecute(numberOfUpdatedRows);
            this.userUpdateDoneListener.onUserUpdateDone(numberOfUpdatedRows);
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
