package ru.shlauzer.newconstraintlayoutdemo

import android.os.Bundle
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val items = listOf(
        DemosAdapter.Demo("MotionLayout", "Галерея фильмов") {
            startMotionLayoutActivity()
        },
        DemosAdapter.Demo("Flow", "Меню") {
            startFlowActivity()
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<RecyclerView>(R.id.recyclerview).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = DemosAdapter(items)
        }
    }

    private fun startMotionLayoutActivity() {
        val intent = Intent(this, MotionLayoutActivity::class.java)
        startActivity(intent)
    }

    private fun startFlowActivity() {
        val intent = Intent(this, FlowActivity::class.java)
        startActivity(intent)
    }
}