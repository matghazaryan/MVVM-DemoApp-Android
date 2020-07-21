package com.mg.demoapp.data.remote.service.ui.login

import com.mg.demoapp.data.model.User
import kotlinx.coroutines.Deferred
import retrofit2.http.GET


/** if 5ea305594f00005f29d9f704 will be not available just copy this json on mocky.io webiste and get new id
 *
 *  {
id: 1,
first_name: "Matevos",
last_name: "Ghazaryan",
email: "matevos14@gmail.com",
github_url: "https://github.com/matghazaryan/",
is_logged_in: true
}
if 5ea54d4f3000008050ce2ed1 will be not available just copy this json on mocky.io webiste and get new id
{
status: "INVALID",
message: "Validation Failed",
errors: [
{
field_name: "username",
error_message: "Username is empty"
},
{
field_name: "password",
error_message: "Password is too short"
}
]
}
if 5ea54d063000006e00ce2ed0 will be not available just copy this json on mocky.io webiste and get new id
{
status: "NOT_AUTH",
message: "Unauthorized",
errors: [ ]
}
 */


interface LoginService {

    @GET("5ea305594f00005f29d9f704")
    fun loginSuccessAsync(): Deferred<User>
    @GET("5ea54d4f3000008050ce2ed1")
//    @GET("5ea54d063000006e00ce2ed0")
    fun loginErrorMessageAsync(): Deferred<User>
}