package com.example.chedar.zombietracker

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chedar.R
import com.example.chedar.database.Zombie
import com.example.chedar.databinding.ListItemZombieBinding

class ZombieAdapter(val clickListener: ZombieListener) : ListAdapter<Zombie, ZombieAdapter.ZombieViewHolder>(ZombieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZombieViewHolder {
        return ZombieViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ZombieViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }

    class ZombieViewHolder private constructor(val binding: ListItemZombieBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(zombieListener: ZombieListener, item: Zombie) {
            binding.zombie = item
            binding.clickListener = zombieListener

            binding.deleteButton.setOnClickListener {
                val popupMenu = PopupMenu(it.context, it)
                popupMenu.inflate(R.menu.menu_delete)
                popupMenu.setOnMenuItemClickListener { it ->
                    when (it.getItemId()) {
                        R.id.action_delete_now -> { zombieListener.onDelete(item) }
                    }
                    false
                }
                popupMenu.show()
            }

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ZombieViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemZombieBinding.inflate(layoutInflater, parent, false)

                return ZombieViewHolder(binding)
            }
        }
    }

    class ZombieListener(
        val clickListener: (zombieId: Long) -> Unit,
        val deleteListener: (zombieId: Long) -> Unit) {
        fun onClick(zombie: Zombie) = clickListener(zombie.zombieId)
        fun onDelete(zombie: Zombie) = deleteListener(zombie.zombieId)
    }

    class ZombieDiffCallback : DiffUtil.ItemCallback<Zombie>() {
        override fun areItemsTheSame(oldItem: Zombie, newItem: Zombie): Boolean {
            return oldItem.zombieId == newItem.zombieId
        }

        override fun areContentsTheSame(oldItem: Zombie, newItem: Zombie): Boolean {
            return oldItem == newItem
        }

    }

}