package com.a21buttons.stickyheaders.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.a21buttons.stickyheaders.StickyHeaderAdapter
import com.a21buttons.stickyheaders.StickyHeaderLayoutManager
import com.a21buttons.stickyheaders.StickyHeaderViewHolder

class Simple : AppCompatActivity() {
  companion object {
    fun getCallingIntent(context: Context) = Intent(context, Simple::class.java)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.recycler_view)

    val recyclerView: RecyclerView = findViewById(R.id.recyclerView) as RecyclerView

    recyclerView.layoutManager = StickyHeaderLayoutManager()
    recyclerView.adapter = Adapter(layoutInflater)
  }

  class Adapter(val inflater: LayoutInflater) : StickyHeaderAdapter<Adapter.ViewHolder>() {
    override fun getItemCount(): Int = 100

    override fun onCreateViewHolder2(parent: ViewGroup, viewType: Int): ViewHolder {
      return ViewHolder(inflater.inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, sectionId: Long) {
      holder.bind("Position $position\tSection $sectionId")
    }

    override fun getSectionId(position: Int) = (position / 2).toLong()

    override fun getHeaderPosition(sectionId: Long) = (sectionId * 2).toInt()

    class ViewHolder(val v: View) : StickyHeaderViewHolder(v) {
      fun bind(s: String) {
        val view = v.findViewById(R.id.text1)
        if (view is TextView) {
          view.text = s
        }
      }
    }
  }
}
