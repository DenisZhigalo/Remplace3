package by.dz.remplace3.ui.base

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.dz.remplace3.data.model.error.ErrorResponse
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import timber.log.Timber

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    private var compositeDisposable: CompositeDisposable? = null

    override fun onCleared() {
        super.onCleared()
        Timber.d("unsubscribeFromDataStore(): ")
        compositeDisposable?.let {
            it.dispose()
            it.clear()
            compositeDisposable = null
        }
    }

    protected fun addDisposable(disposable: Disposable?) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        disposable?.let {
            compositeDisposable?.add(it)
        }
    }

    protected fun removeDisposable(disposable: Disposable?) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        disposable?.let {
            compositeDisposable?.remove(it)
        }
    }

    fun getFieldValue(field: MutableLiveData<String>) = field.value ?: ""

    fun getFieldValue(field: ObservableField<String>) = field.get() ?: ""

    fun getFieldValue(field: ObservableField<Boolean>) = field.get() ?: false

    fun getFieldValue(field: MutableLiveData<Boolean>) = field.value ?: false

    fun getFieldValue(field: ObservableBoolean) = field.get()

    fun <T> getFieldValue(field: ObservableField<ArrayList<T>>): ArrayList<T> =
        field.get() ?: arrayListOf()

    fun <T> getFieldValue(field: MutableLiveData<ArrayList<T>>): ArrayList<T> =
        field.value ?: arrayListOf()

    fun parseNetError(t: Throwable): String {
        if (t is HttpException) {
            var error = ErrorResponse()
            try {
                error =
                    Gson().fromJson(t.response().errorBody()?.string(), ErrorResponse::class.java)
            } catch (e: Exception) {
                //DO NOTHING
            }
            if (error.getMessage().isNotEmpty()) {
                return error.getMessage()
            }
        }
        return t.message ?: ""
    }

    fun isNotBlank(field: MutableLiveData<String>) = getFieldValue(field).trim().isNotBlank()

    fun isNotBlank(field: ObservableField<String>) = getFieldValue(field).trim().isNotBlank()

    fun isNotBlankList(vararg fields: MutableLiveData<String>): Boolean {
        for (field in fields) {
            if (!isNotBlank(field)) {
                return false
            }
        }
        return true
    }

}