package com.example.selfmed.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.selfmed.Adapters.ResultsCard
import com.example.selfmed.R
import com.example.selfmed.model.results
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class fragment_1 : Fragment() {

    private var MainAdapter: ResultsCard? = null

    private var resultrecycler: RecyclerView? = null

    private var mList: MutableList<results>? = null

    private lateinit var firebaseUser: FirebaseUser


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_1, container, false)
        firebaseUser = FirebaseAuth.getInstance().currentUser!!

        resultrecycler = view.findViewById(R.id.results_recycler)
        resultrecycler?.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.stackFromEnd = true
        layoutManager.reverseLayout = true
        resultrecycler?.layoutManager = layoutManager

        mList = ArrayList()

        MainAdapter = context?.let { ResultsCard(it, mList as ArrayList<results>) }
        resultrecycler?.adapter = MainAdapter


        retrieveresults()


        return view
    }


    private fun retrieveresults() {
        val postRef = FirebaseDatabase.getInstance().reference
            .child("interviews")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {

                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        mList?.clear()
                        for (i in snapshot.children) {
                            var result = i.getValue(results::class.java)
                          mList?.add(result!!.let { it })
                        }
                        MainAdapter?.notifyDataSetChanged()
                    }
                }


            })
    }

}