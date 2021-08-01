package com.anroid.bottommenu

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class ReviewFragment : Fragment() {
    private var alias: String = ""
    private var title: String = ""
    private var reviewContent: String = ""
    private var description: String = ""
    private var ratingScore: Float = 0.0f
    private var emotion: String = ""
    private var recommend: String = ""


    lateinit var db: DBHelper

    lateinit var edt_alias: TextView

    lateinit var ratingBar: RatingBar

    lateinit var summary: EditText
    lateinit var edt_title: EditText
    lateinit var review: EditText

    lateinit var btn_add: Button
    lateinit var btn_rev: Button
    lateinit var btn_del: Button

    lateinit var btn_good: CheckBox
    lateinit var btn_hate: CheckBox

    lateinit var btn_happy: CheckBox
    lateinit var btn_sad: CheckBox
    lateinit var btn_bored: CheckBox

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_review, container, false)
        db = DBHelper(getActivity(), "REVIEW", null, 1)

        edt_alias = view.findViewById<TextView>(R.id.edt_alias)

        ratingBar = view.findViewById<RatingBar>(R.id.ratingBar)

        summary = view.findViewById<EditText>(R.id.summary)
        edt_title = view.findViewById<EditText>(R.id.edt_title)
        review = view.findViewById<EditText>(R.id.review)

        btn_good = view.findViewById<CheckBox>(R.id.btn_good)
        btn_hate = view.findViewById<CheckBox>(R.id.btn_hate)

        btn_add = view.findViewById<Button>(R.id.btn_add)
        btn_del = view.findViewById<Button>(R.id.btn_del)
        btn_rev = view.findViewById<Button>(R.id.btn_rev)

        btn_happy = view.findViewById<CheckBox>(R.id.btn_happy)
        btn_sad = view.findViewById<CheckBox>(R.id.btn_sad)
        btn_bored = view.findViewById<CheckBox>(R.id.btn_bored)


        arguments?.let {
            title = it.getString("title").toString()
            reviewContent = it.getString("reviewContent").toString()
            description = it.getString("description").toString()
            ratingScore = it.getFloat("ratingBar")
        }

        Log.d("A_Fragment", "영화 제목 : ${title}")

        edt_title.setText(title)
        summary.setText(description)
        review.setText(reviewContent)
        ratingBar.setRating(ratingScore)

        emotion_btnEvent()
        recommend_btnEvent()

        ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            ratingScore = rating
        }

        //Event
        btn_add.setOnClickListener {
            alias = "HELLOouo"
            title = edt_title.text.toString()
            reviewContent = review.text.toString()
            description = summary.text.toString()

            db.addReview(alias, title, reviewContent, description, ratingScore, emotion, recommend)
            (activity as MainActivity).reviewToMypage()
        }
        btn_rev.setOnClickListener {


        }
        btn_del.setOnClickListener {


        }

        return view
    }

    private fun emotion_btnEvent(){
        btn_happy.setOnClickListener{
            btn_sad.isChecked = false
            btn_bored.isChecked = false
            emotion = "HAPPY"
        }
        btn_sad.setOnClickListener {
            btn_happy.isChecked = false
            btn_bored.isChecked = false
            emotion = "SAD"
        }
        btn_bored.setOnClickListener {
            btn_sad.isChecked = false
            btn_good.isChecked = false
            emotion = "BORED"
        }
    }

    private fun recommend_btnEvent(){
        btn_good.setOnClickListener{
            btn_hate.isChecked = false
            recommend = "YES"
        }
        btn_hate.setOnClickListener {
            btn_good.isChecked = false
            recommend = "NOPE"
        }
    }

}