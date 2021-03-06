package com.mvvmdaggerroomdb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dagger2demo.BR
import com.dagger2demo.R
import com.dagger2demo.databinding.ItemUserBinding
import com.mvvmdaggerroomdb.UserItemClickListener
import com.mvvmdaggerroomdb.model.UserModel
import com.mvvmdaggerroomdb.util.DiffUtilUser

class UserAdapter(var clickListener: UserItemClickListener?) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private val userList: ArrayList<UserModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemUserBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_user, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]

        holder.bind(user)
        holder.binding.user = user
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserModel) {
            binding.setVariable(BR.viewModel, user)
            binding.executePendingBindings()
            binding.cardView.setOnClickListener {
            }

            binding.ivDelete.setOnClickListener {
            }
            binding.ivEdit.setOnClickListener {
            }
        }
    }

    fun setData(newData: ArrayList<UserModel>) {
        userList.let {
            val diffResult = DiffUtil.calculateDiff(DiffUtilUser(newData, it))
            diffResult.dispatchUpdatesTo(this)
            it.clear()
            it.addAll(newData)
        }
    }

}