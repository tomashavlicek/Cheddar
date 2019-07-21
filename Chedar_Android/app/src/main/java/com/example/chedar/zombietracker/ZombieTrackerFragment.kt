package com.example.chedar.zombietracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chedar.R
import com.example.chedar.database.Zombie
import com.example.chedar.database.ZombieDatabase
import com.example.chedar.databinding.FragmentZombieTrackerBinding
import com.example.chedar.zombiedetail.ZombieDetailFragment
import com.example.chedar.zombiequality.ZombieQualityFragment
import com.example.chedar.zombietracker.ZombieAdapter.ZombieListener

class ZombieTrackerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Get a reference to a binding object and inflate the fragment views.
        val binding : FragmentZombieTrackerBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_zombie_tracker, container,  false)

        val application = requireNotNull(this.activity).application

        val dataSource = ZombieDatabase.getInstance(application).zombieDatabaseDao

        val viewModelFactory = ZombieTrackerViewModelFactory(dataSource, application)

        val zombieTrackerViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ZombieTrackerViewModel::class.java)

        binding.setLifecycleOwner(this)

        val adapter = ZombieAdapter(ZombieListener(
            clickListener = fun(zombieId: Long) {
            val fragmentManager = this.activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = ZombieDetailFragment.newInstance(zombieId)
            fragmentTransaction.replace(R.id.fragment_container, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }, deleteListener = fun(zombieId: Long) {
            zombieTrackerViewModel.delete(zombieId)
        }))

        val dividerItemDecoration = DividerItemDecoration(binding.zombiesList.context, LinearLayoutManager.VERTICAL)
        binding.zombiesList.addItemDecoration(dividerItemDecoration)
        binding.zombiesList.adapter = adapter

        zombieTrackerViewModel.zombies.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.addZombieButton.setOnClickListener {
            val fragmentManager = this.activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = ZombieQualityFragment()
            fragmentTransaction.replace(R.id.fragment_container, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        return binding.root
    }
}