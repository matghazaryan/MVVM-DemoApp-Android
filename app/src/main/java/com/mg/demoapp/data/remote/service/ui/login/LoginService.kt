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
if 56c04cd0-9afd-4259-b0e6-373337abd922 will be not available just copy this json on mocky.io webiste and get new id
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
if 027648c9-0962-46bf-a45b-55de1e1b8c20 will be not available just copy this json on mocky.io webiste and get new id
{
status: "NOT_AUTH",
message: "Unauthorized",
errors: [ ]
}
 */


interface LoginService {

    @GET("56c04cd0-9afd-4259-b0e6-373337abd922")
    fun loginSuccessAsync(): Deferred<User>
    @GET("027648c9-0962-46bf-a45b-55de1e1b8c20")
//    @GET("6baa02ff-3792-4551-97b7-055708c8d249")
    fun loginErrorMessageAsync(): Deferred<User>
}