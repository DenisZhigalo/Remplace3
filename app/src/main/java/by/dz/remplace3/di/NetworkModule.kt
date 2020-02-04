package by.dz.remplace3.di

import by.dz.remplace3.BuildConfig
import by.dz.remplace3.data.local.prefs.user.IUserPrefsUtils
import by.dz.remplace3.data.remote.auth.AuthApi
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val HEADER_AUTH = "authorization"
const val NO_AUTH_HEADER_KEY = "No-Authentication"
const val NO_AUTH_HEADER_KEY_TRUE = "$NO_AUTH_HEADER_KEY:true"

private const val MODULE_NAME = "Network Module"

private const val CONNECT_TIMEOUT = 60
private const val WRITE_TIMEOUT = 60
private const val TIMEOUT = 60

private const val HEADER_USER_AGENT = "User-Agent"
const val USER_AGENT_VALUE = "android"

val networkModule = Module(MODULE_NAME, false) {
    importOnce(appModule)
    bind<OkHttpClient>() with singleton { getMockOkHttpClient(instance()) }
    bind<Retrofit>() with singleton { getMockRetrofit(instance()) }
    bind<AuthApi>() with singleton { getMockAuthApi(instance()) }
}

private fun getMockOkHttpClient(prefs: IUserPrefsUtils): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
        .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
        .addInterceptor { chain ->
            chain.proceed(
                chain.request()
                    .newBuilder().header(HEADER_USER_AGENT, USER_AGENT_VALUE).build()
            )
        }
        .addInterceptor(AuthInterceptor(prefs))

    if (BuildConfig.ENABLE_LOGGING) {
        val logging = HttpLoggingInterceptor()
        logging.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        builder.addInterceptor(logging)
    }
    return builder.build()
}

private fun getMockRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(BuildConfig.SERVER_PATH)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()

private fun getMockAuthApi(retrofit: Retrofit) = retrofit.create(AuthApi::class.java)