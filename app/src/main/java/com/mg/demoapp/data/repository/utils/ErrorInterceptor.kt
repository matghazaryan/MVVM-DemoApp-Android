package com.mg.demoapp.data.repository.utils

import android.util.Log
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.mg.demoapp.data.model.Error
import com.mg.demoapp.data.model.ErrorObj
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import java.nio.charset.Charset

class ErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response: Response = chain.proceed(request)
        val body: ResponseBody? = response.body
        var customError: Error? = null
        // Only intercept JSON type responses and ignore the rest.
        if (body?.contentType()?.subtype?.toLowerCase() == "json") {
            var errorMessage: String = "";
            var errorCode = 200; // Assume default OK
            try {
                val source = body.source()
                source.request(Long.MAX_VALUE) // Buffer the entire body.
                val buffer = source.buffer
                val charset = (body.contentType())?.charset(Charset.forName("UTF-8"))
                // Clone the existing buffer is they can only read once so we still want to pass the original one to the chain.
                charset?.let { _charset ->
                    val json = buffer.clone().readString(_charset)
                    val obj: JsonElement = JsonParser().parse(json)
                    // Capture error code an message.
                    customError = Error()
                    if (obj is JsonObject) {
                        if (obj.has("status"))
                            customError?.status = obj.get("status").asString

                        if (obj.has("message"))
                            customError?.message = obj.get("message").asString

                        if (obj.has("errors")) {
                            val errorsArray = obj.getAsJsonArray("errors")
                            for (jsonObject in errorsArray) {
                                with(jsonObject as JsonObject) {
                                    val errorObj = ErrorObj(
                                        get("field_name").asString,
                                        get("error_message").asString
                                    )
                                    customError?.errors?.add(errorObj)
                                }
                            }
                        }
                    }
                }

            } catch (e: Exception) {
                Log.e("----", "Error: " + e.message)
            }
            if (customError?.status != null) {
                throw customError as Error
            }
        }

        return response
    }
}