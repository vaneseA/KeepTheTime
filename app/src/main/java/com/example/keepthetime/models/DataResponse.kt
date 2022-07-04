package com.example.keepthetime.models

class DataResponse(
    val user: UserData,
    val token: String,
    val users : List<UserData>,
    val places : List<PlaceData>
) {
}

//gittest45