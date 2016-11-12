package com.example.childcontrol.childcontrol;

import android.app.Application;
import android.os.SystemClock;

import org.greenrobot.greendao.database.Database;

import java.util.concurrent.TimeUnit;

public class App extends Application {

    /**
     * A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher.
     */
    public static final boolean ENCRYPTED = false;

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "childcontrol-db-encrypted" : "childcontrol-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

        SystemClock.sleep(TimeUnit.SECONDS.toMillis(3));
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
