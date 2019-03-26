package com.example.wisielec2

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var name: String = ""
    var hiddenName: String = ""
    var chars: CharArray = charArrayOf()
    var hiddenChars: CharArray = charArrayOf()
    var tempLetter: String = ""
    var fail: Int = 0
    var goal: Int = 0
    var temp: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        word.gravity = Gravity.CENTER
        ifWon.gravity = Gravity.CENTER
        ifWon.visibility = View.INVISIBLE
        ok.visibility = View.INVISIBLE
        guessLetter.visibility = View.INVISIBLE
        val res = resources
        val names = res.getStringArray(R.array.nazwy)
        var images = arrayOf(w1, w2, w3, w4, w5, w6, w7, w8, w9, w10, w11, w12)

        start.setOnClickListener { newGame(names, images) }
        ok.setOnClickListener { play(names, images) }
        ifWon.setOnClickListener { newGame(names, images) }
    }
    fun newGame(names: Array<String>, images: Array<ImageView>){

        val random: Int = (0..16008).random()
        word.text = ""
        fail = 0
        goal = 0
        hiddenName = ""
        ifWon.text = ""
        ifWon.visibility = View.INVISIBLE
        guessLetter.visibility = View.VISIBLE
        guessLetter.visibility = View.VISIBLE
        name = names[random]
        chars = name.toCharArray()
        hiddenChars = name.toCharArray()
        ok.visibility = View.VISIBLE
        for ( view in images) {
            view.visibility = View.INVISIBLE
        }
        for ( i in 0..(chars.size-1) ){
            hiddenChars[i] = '_'
            hiddenName = hiddenName.plus(" ").plus(hiddenChars[i])
            word.text = hiddenName
        }
    }
    @SuppressLint("SetTextI18n")
    fun play(names: Array<String>, images: Array<ImageView>) {
        temp = 0
        hiddenName = ""
        tempLetter = guessLetter.getText().toString()
        for ( i in 0..(chars.size-1)) {
            if ( Character.toString(chars[i]).equals(tempLetter)) {
                goal++
                hiddenChars[i] = (tempLetter.toCharArray())[0]
                temp++
            }
        }
        if ( temp == 0 ){
            images[fail].visibility = View.VISIBLE
            fail++
        }
        for (j in 0..(chars.size-1)) {
            hiddenName = hiddenName.plus(" ").plus(hiddenChars[j])
            word.text = hiddenName
        }
        guessLetter.setText("")
        if ( fail == 12) {
            ok.visibility = View.INVISIBLE
            guessLetter.visibility = View.INVISIBLE
            word.text = name
            ifWon.text = "Przegrana"
            ifWon.visibility = View.VISIBLE
        }
        else if ( goal == chars.size ) { //wygrana
            ok.visibility = View.INVISIBLE
            guessLetter.visibility = View.INVISIBLE
            word.text = name
            ifWon.text = "Wygrana!"
            ifWon.visibility = View.VISIBLE
        }

    }

}

