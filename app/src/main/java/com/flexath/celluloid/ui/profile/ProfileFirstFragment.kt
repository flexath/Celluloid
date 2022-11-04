package com.flexath.celluloid.ui.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flexath.celluloid.R
import kotlinx.android.synthetic.main.fragment_profile_first.*

class ProfileFirstFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageFacebookIcon.setOnClickListener {
            Intent(Intent.ACTION_VIEW).also { intent ->
                intent.data = Uri.parse("https://www.facebook.com/profile.php?id=100027249152491")
                startActivity(intent)
            }
        }

        imageInstagramIcon.setOnClickListener {
            Intent(Intent.ACTION_VIEW).also { intent ->
                intent.data = Uri.parse("https://www.instagram.com/flexath11/")
                startActivity(intent)
            }
        }

        imagePhoneIcon.setOnClickListener {
            Intent(Intent.ACTION_DIAL).also { intent ->
                intent.data = Uri.parse("tel: 09795448753")
                startActivity(intent)
            }
        }

        imageYoutubeIcon.setOnClickListener {
            Intent(Intent.ACTION_VIEW).also { intent ->
                intent.data = Uri.parse("https://www.youtube.com/channel/UCqTlaXw7ZFnRj-Bdm-UXQTg")
                startActivity(intent)
            }
        }

        btnGithubRepository.setOnClickListener {
            Intent(Intent.ACTION_VIEW).also { intent ->
                intent.data = Uri.parse("https://github.com/flexath/Celluloid")
                startActivity(intent)
            }
        }
    }

}