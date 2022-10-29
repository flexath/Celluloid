package com.flexath.celluloid.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import coil.load
import com.flexath.celluloid.R
import com.flexath.celluloid.data.database.URL
import com.flexath.celluloid.data.database.people.Person
import com.flexath.celluloid.data.movie_viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie_person.*

class MoviePersonFragment : Fragment() {

    private lateinit var viewModel:MovieViewModel
    private val args:MoviePersonFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_person, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MovieViewModel::class.java]

        if(args.personString == "cast") {
            viewModel.getMoviePerson(args.personCast!!.id, URL.api_key)
            getPersonCastInformation()
        }else{
            viewModel.getMoviePerson(args.personCrew!!.id, URL.api_key)
            getPersonCrewInformation()
        }

        getPersonCastInformation()

    }

    private fun getPersonCastInformation() {
        viewModel.personMovieList.observe(viewLifecycleOwner) {
            personName.text = it.name
            personProfilePicture.load("https://image.tmdb.org/t/p/original"+it.profile_path)
            getGender(it)
            personBirthday.text = it.birthday
            personPlaceOfBirth.text = it.place_of_birth
            personHomePage.text = it.homepage
            personBiography.text = it.biography
        }
    }

    private fun getPersonCrewInformation() {
        viewModel.personMovieList.observe(viewLifecycleOwner) {
            personName.text = it.name
            personProfilePicture.load("https://image.tmdb.org/t/p/original"+it.profile_path)
            getGender(it)
            personBirthday.text = it.birthday
            personPlaceOfBirth.text = it.place_of_birth
            personHomePage.text = it.homepage
            personBiography.text = it.biography
        }
    }

    private fun getGender(person:Person) {
        when (person.gender) {
            1 -> personGender.text = "Female"
            2 -> personGender.text = "Male"
            else -> personGender.text = "Unspecified"
        }
    }
}