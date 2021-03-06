package com.example.flower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wan.databinding.FragmentPlantListBinding

/**
 * 植物列表
 * @author DQDana
 * @since 2019-05-16 20:20
 */
class PlantListFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlantListBinding.inflate(inflater, container, false)
//        val adapter = GardenPlantingAdapter()
//        binding.gardenList.adapter = adapter
//        subscribeUi(adapter, binding)
        return binding.root
    }
}