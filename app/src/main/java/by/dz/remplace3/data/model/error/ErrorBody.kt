package by.dz.remplace3.data.model.error

class ErrorBody {

    private var field: String? = ""
    private var rejectedValue: String? = ""

    fun getField() = field ?: ""

    fun getRejectedValue() = rejectedValue ?: ""

}