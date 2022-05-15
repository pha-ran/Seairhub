package com.seairhub.driver.src.main.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.seairhub.driver.R
import com.seairhub.driver.config.BaseFragment
import com.seairhub.driver.databinding.FragmentListBinding
import com.seairhub.driver.src.main.MainActivity
import com.seairhub.driver.src.main.list.models.ListItemData

class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::bind, R.layout.fragment_list) {
    lateinit var glideManager : RequestManager
    private val listItems = ArrayList<ListItemData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        glideManager = Glide.with(this)

        set()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun set() {
        val adaptor = ListAdaptor(listItems)
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rv.layoutManager = layoutManager
        binding.rv.adapter = adaptor

        adaptor.setItemClickListener(object : ListAdaptor.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                println("onClick : $position")
            }
        })

        listItems.add(ListItemData("22/05/13", "장소1", "화주1", "상차 대기"))
        listItems.add(ListItemData("22/05/13", "장소2", "화주2", "운송중"))
        listItems.add(ListItemData("22/05/12", "장소3", "화주3", "운송 완료"))
        listItems.add(ListItemData("22/05/08", "장소4", "화주4", "운송 완료"))
        listItems.add(ListItemData("22/05/08", "장소5", "화주5", "운송 완료"))
        listItems.add(ListItemData("22/05/07", "장소6", "화주6", "운송 완료"))
        listItems.add(ListItemData("22/05/06", "장소7", "화주7", "운송 완료"))
        listItems.add(ListItemData("22/05/06", "장소8", "화주8", "운송 완료"))
        listItems.add(ListItemData("22/05/06", "장소9", "화주9", "운송 완료"))
        listItems.add(ListItemData("22/05/05", "장소10", "화주10", "운송 완료"))
        adaptor.notifyDataSetChanged()
    }
}