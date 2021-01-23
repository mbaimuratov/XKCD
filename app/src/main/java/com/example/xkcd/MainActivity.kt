package com.example.xkcd

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*


class MainActivity : AppCompatActivity() {

    private var CURRENT_ID = 1
    private val MAX_NUM_COMICS = 2415

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showComic(CURRENT_ID)

        findViewById<Button>(R.id.action_prev).setOnClickListener {
            showPrevComic()
        }

        findViewById<Button>(R.id.action_next).setOnClickListener {
            showNextComic()
        }

        findViewById<Button>(R.id.action_rand).setOnClickListener {
            showRandomComic()
        }
    }


    private fun showComic(currID: Int) {
        val observer = object: Observer<Comic> {
            override fun onSubscribe(d: Disposable?) {
                Log.i("onSubscribe", "onSubscribe")
            }

            override fun onError(e: Throwable?) {
                Log.i("onError", e?.message.toString())
            }

            override fun onComplete() {
                Log.i("onComplete", "onComplete")
            }

            override fun onNext(t: Comic?) {
                findViewById<TextView>(R.id.tv_title).text = t?.safe_title
                Glide.with(this@MainActivity).load(t?.img).into(findViewById(R.id.image))
            }
        }

        val comicEndPoints = ComicRxService().getComicWithId(currID)
        comicEndPoints.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }

    private fun showPrevComic() {
        CURRENT_ID--
        if (CURRENT_ID < 1) {
            CURRENT_ID = MAX_NUM_COMICS
        }

        showComic(CURRENT_ID)
    }

    private fun showNextComic() {
        CURRENT_ID++
        if (CURRENT_ID > MAX_NUM_COMICS) {
            CURRENT_ID = 1
        }

        showComic(CURRENT_ID)
    }

    private fun showRandomComic() {
        CURRENT_ID = Random().nextInt(MAX_NUM_COMICS) + 1
        showComic(CURRENT_ID)
    }
}