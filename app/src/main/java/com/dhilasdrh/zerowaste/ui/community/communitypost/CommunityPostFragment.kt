package com.dhilasdrh.zerowaste.ui.community.communitypost

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhilasdrh.zerohunger.adapter.ArticleAdapter
import com.dhilasdrh.zerowaste.R
import com.dhilasdrh.zerowaste.activity.ArticleDetailsActivity
import com.dhilasdrh.zerowaste.adapter.PostAdapter
import com.dhilasdrh.zerowaste.databinding.FragmentCommunityPostBinding
import com.dhilasdrh.zerowaste.model.Event
import com.dhilasdrh.zerowaste.model.Post
import org.jetbrains.anko.support.v4.startActivity

class CommunityPostFragment : Fragment() {

    companion object {
        fun newInstance() = CommunityPostFragment()
    }

    private lateinit var viewModel: CommunityPostViewModel
    private lateinit var binding: FragmentCommunityPostBinding
    private val listPost = ArrayList<Post>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        //  viewModel = ViewModelProvider(this).get(CommunityPostViewModel::class.java)
        binding = FragmentCommunityPostBinding.inflate(layoutInflater, container, false)

        val postAdapter = PostAdapter(listPost){
            /* startActivity<PostDetailsActivity>(
                 EXTRA_ITEM to it)*/
        }

        listPost.addAll(getListPost())

        binding.rvCommunityPost.setHasFixedSize(true)
        binding.rvCommunityPost.layoutManager = LinearLayoutManager(context)
        binding.rvCommunityPost.adapter = postAdapter

        return binding.root
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
                dataName[position],
                dataImgAvatar[position],
                dataDate[position],
                dataActivity[position],
                dataActivityIcon[position],
                dataCaption[position],
                dataImgPost[position],
                dataUserId[position],
            )
            listPost.add(post)
        }
        return listPost
    }

}