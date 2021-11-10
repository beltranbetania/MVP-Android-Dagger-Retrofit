package com.reign.hackernewstest


import android.app.Application
import com.reign.hackernewstest.di.component.ApplicationComponent
import com.reign.hackernewstest.di.component.DaggerApplicationComponent
import com.reign.hackernewstest.di.module.ApplicationModule
import io.realm.Realm
import io.realm.RealmConfiguration


class BaseApp : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        setup()

        initRealm();
    }

    fun initRealm() {
        // initialize Realm
        Realm.init(this)
        // create your Realm configuration
        val config: RealmConfiguration = RealmConfiguration.Builder()
            .name(Realm.DEFAULT_REALM_NAME)
            .schemaVersion(0)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
    }

    fun setup() {
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: BaseApp private set

    }
}