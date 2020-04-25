package com.mg.demoapp.data.model

//{
//"status_code": 7,
//"status_message": "Invalid API key: You must be granted a valid key.",
//"success": false
//}

//open class Error(val message: String? = null, val errors: ArrayList<ErrorObj>?=null)

open class Error(
    var status_code: Int? = null,
    var status_message: String? = null
) {
    var success: Boolean? = null
        get() {
            return field ?: true
        }

}
