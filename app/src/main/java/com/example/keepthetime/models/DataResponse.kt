package com.example.keepthetime.models

class DataResponse(
    val user: UserData,
    val token: String,
    val users : List<UserData>,
    val friends : List<UserData>,
    val places : List<PlaceData>
) {
}


