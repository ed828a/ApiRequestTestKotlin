package com.example.edward.myapplication.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Edward on 7/31/2018.
 */


class CharacterModel (

    val name: String,
    val height: String,
    val mass: String,
    // "SerializedName" is a Gson annotation to remap the original JSON field into another custom name
    @SerializedName("hair_color")
    val hairColor: String,

    @SerializedName("skin_color")
    val skinColor: String,

    @SerializedName("eye_color")
    val eyeColor: String,

    @SerializedName("birth_year")
    val birthYear: String,

    val gender: String,
    val homeworld: String,
    val films: List<String>,
    val species: List<String>,
    val vehicles: List<String>,
    val starships: List<String>,
    val created: String,
    val edited: String,
    val url: String
)