package com.example.selfmed.model

class results {

    private var name: String = ""
    private var age: String = ""
    private var gender: String = ""
    private var time:String =""
    private var title: String = ""
    private var tb_risk: String = ""
    private var Recommendation: String = ""
    private var details: String = ""

    constructor()
    constructor(
        title_1: String,
        details_1: String,
        tb_risk_1: String,
        time_1:String,
        name_1: String,
        Recommendation_1: String,
        age_1: String,
        gender_1: String
    ) {
        this.title = title_1
        this.details = details_1
        this.name = name_1
        this.age = age_1
        this.gender = gender_1
        this.tb_risk = tb_risk_1
        this.Recommendation = Recommendation_1
        this.time =time_1


    }
    fun setTime(time_1: String){
        this.time =time_1
    }
    fun getTime():String{
        return time
    }

    fun getDetails(): String {
        return details
    }

    fun setDetails(details_1: String) {
        this.details = details_1
    }

    fun getName(): String {
        return name
    }

    fun setName(name_1: String) {
        this.name = name_1
    }


  fun getTitle(): String {
        return title
    }

    fun setTitle(title_1: String) {
        this.title = title_1
    }


    fun getGender(): String {
        return gender
    }

    fun setGender(gender_1: String) {
        this.gender = gender_1
    }


    fun getAge(): String {
        return title
    }

    fun setAge(title_1: String) {
        this.title = title_1
    }


    fun getTb_risk(): String {
        return tb_risk
    }

    fun setTb_risk(tb_risk_1: String) {
        this.tb_risk = tb_risk_1
    }


    fun getRecommendation(): String {
        return Recommendation
    }

    fun setRecommendation(Recommendation_1: String) {
        this.Recommendation = Recommendation_1
    }

}