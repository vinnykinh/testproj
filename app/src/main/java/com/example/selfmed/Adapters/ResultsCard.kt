package com.example.selfmed.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.selfmed.R
import com.example.selfmed.WebActivity
import com.example.selfmed.model.results
import com.example.selfmed.model.searchitems
import org.w3c.dom.Text
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ResultsCard(

    private var mContext: Context,
    private var mList: List<results>
) : RecyclerView.Adapter<ResultsCard.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title_of_search: TextView = itemView.findViewById(R.id.title_of_search)
        val name_card: TextView = itemView.findViewById(R.id.name_card)
        val age_card: TextView = itemView.findViewById(R.id.age_card)
        val recommendation_card: TextView = itemView.findViewById(R.id.recommendation_card)
        val gender_card: TextView = itemView.findViewById(R.id.gender_card)
        val tb_risk_card: TextView = itemView.findViewById(R.id.tb_risk_card)
        val details_card: TextView = itemView.findViewById(R.id.details_card)
        var time: TextView = itemView.findViewById(R.id.date_time)
        val buy_medicine: Button = itemView.findViewById(R.id.buy_medicine)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(mContext).inflate(R.layout.search_result, null)
        return ViewHolder(inflater)
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val current_card = mList[position]
        holder.title_of_search.text = "Results of Check-UP"
        holder.details_card.text = "Details: "+ current_card.getDetails()
        holder.name_card.text = "Patient Email: " +  current_card.getName()
        holder.age_card.text = "Age: "+current_card.getAge()
        holder.gender_card.text = "Gender: "+current_card.getGender()
        holder.tb_risk_card.text = "TB Risk: "+current_card.getTb_risk()
        holder.recommendation_card.text = "Recommendation: "+current_card.getRecommendation()
        holder.time.text = "Time: "+current_card.getTime()


        holder.buy_medicine.setOnClickListener {

            val intent = Intent(mContext, WebActivity::class.java)
            mContext.startActivity(intent)
        }


    }

    override fun getItemCount(): Int = mList.size
}