package com.nitto.tushar.nrrii.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.nitto.tushar.nrrii.DAO.OrderDao;
import com.nitto.tushar.nrrii.DAO.ProductDao;
import com.nitto.tushar.nrrii.Entity.OrderItem;
import com.nitto.tushar.nrrii.Entity.ProductItem;


/**
 * Created by masud on 2/17/2018.
 */
@Database(entities = {ProductItem.class, OrderItem.class}, version = 11,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{
    private static volatile AppDatabase INSTANCE;

    public abstract ProductDao productDao();
    public abstract OrderDao orderDao();

    //new methods
    public static synchronized void AppDatabaseInitializer(Context context) {
        if(INSTANCE == null) {
            INSTANCE = create(context);
        }
    }

    //new methods
    public static synchronized AppDatabase getInstance() {
        return INSTANCE;
    }

    //new methods
    private static AppDatabase create(final Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "product-database")
                // allow queries on the main thread.
                // Don't do this on a real app! See PersistenceBasicSample for an example.
                //.allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    //old methods
//    public static AppDatabase getAppDatabase(Context context) {
////        if (INSTANCE == null) {
////            INSTANCE =
////                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "product-database")
////                            // allow queries on the main thread.
////                            // Don't do this on a real app! See PersistenceBasicSample for an example.
////                            .allowMainThreadQueries()
////                            .build();
////        }
////        return INSTANCE;
//        return Room.databaseBuilder(
//                context,
//                AppDatabase.class,
//                "product-database").fallbackToDestructiveMigration().build();
//    }

    //old methods
    public static void destroyInstance() {
        INSTANCE = null;
    }
}
