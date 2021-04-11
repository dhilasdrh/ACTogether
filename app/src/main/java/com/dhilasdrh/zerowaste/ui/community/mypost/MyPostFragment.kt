package com.dhilasdrh.zerowaste.ui.community.mypost

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhilasdrh.zerowaste.R
import com.dhilasdrh.zerowaste.adapter.MyPostAdapter
import com.dhilasdrh.zerowaste.adapter.PostAdapter
import com.dhilasdrh.zerowaste.databinding.FragmentCommunityPostBinding
import com.dhilasdrh.zerowaste.databinding.FragmentMyPostBinding
import com.dhilasdrh.zerowaste.model.Post

class MyPostFragment : Fragment() {

    companion object {
        fun newInstance() = MyPostFragment()
    }

    private lateinit var viewModel: MyPostViewModel
    private lateinit var binding: FragmentMyPostBinding
    private val listPost = ArrayList<Post>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val postAdapter = MyPostAdapter(listPost){
            /* startActivity<PostDetailsActivity>(
                 EXTRA_ITEM to it)*/
        }

        binding = FragmentMyPostBinding.inflate(layoutInflater, container, false)
        listPost.addAll(getListPost())

        binding.rvMyPost.setHasFixedSize(true)
        binding.rvMyPost.layoutManager = LinearLayoutManager(context)
        binding.rvMyPost.adapter = postAdapter

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyPostViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun getListPost(): ArrayList<Post> {
        val dataUserId = resources.getStringArray(R.array.post_name)
        val dataName = resources.getStringArray(R.array.post_name)
        val dataImgAvatar = resources.getStringArray(R.array.post_image_user)
        val dataDate = resources.getStringArray(R.array.post_date)
        val dataActivity = resources.getStringArray(R.array.post_activity)
        val dataActivityIcon = resources.getStringArray(R.array.activity_icon)
        // val dataActivityIcon = resources.getIntArray(R.array.post_activity_icon)
        val dataCaption = resources.getStringArray(R.array.post_caption)
        val dataImgPost = resources.getStringArray(R.array.post_image_post)

        val listPost = ArrayList<Post>()
        for (position in dataName.indices) {
            val post = Post (
                dataName[1],
                dataImgAvatar[1],
                dataDate[1],
                dataActivity[1],
                dataActivityIcon[1],
                dataCaption[1],
                dataImgPost[1],
                dataUserId[1],
            )
            listPost.add(post)
        }
        return listPost
    }

}