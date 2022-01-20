package com.project.safarr.ui.explore.model

class PlacesData {

    var town :String? = null
    var state :String? = null
    var img :String? = null

    constructor(){}

    constructor(
        town:String?,
        state:String?,
        img:String?
    ){
        this.town = town
        this.state = state
        this.img = img
    }

}