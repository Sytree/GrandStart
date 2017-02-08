package com.example.huii.data.local.database;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.huii.data.entities.gen.DaoMaster;
import com.example.huii.data.entities.gen.DaoSession;
import com.example.huii.data.local.LocalDataGlobal;

import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.identityscope.IdentityScopeType;

/**
 * Created by huii on 16/12/31.
 */
public class GsDbManager {

    public static DaoMaster getDaoMaster(@NonNull Context context) {
        DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(
                context, LocalDataGlobal.GS_DB_NAME,null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        return daoMaster;
    }

    public static DaoSession getDaoSession(@NonNull Context context) {
        return getDaoMaster(context).newSession();
    }

}
