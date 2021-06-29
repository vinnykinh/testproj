package com.example.selfmed.model

class searchitems {
    private var title:String =""
    private var details:String=""
    constructor()
    constructor(title_1:String,details_1:String){
        this.title =title_1
        this.details =details_1
    }
    fun getDetails():String{
        return details
    }
    fun setDetails(details_1: String)
    {
        this.details =details_1
    }
    fun getTitle():String{
        return title
    }
    fun setTitle(title_1: String)
    {
        this.title =title_1
    }
}