package com.mg.demoapp.data.model

import java.io.IOException

//{
//"status_code": 7,
//"status_message": "Invalid API key: You must be granted a valid key.",
//"success": false
//}

//open class Error(val message: String? = null, val errors: ArrayList<ErrorObj>?=null)

open class Error(
    var status: String? = null,
    override var message:String? = null,
    var errors: ArrayList<ErrorObj> = ArrayList()
) : IOException() {
    var success: Boolean? = null
        get() {
            return field ?: true
        }
}
