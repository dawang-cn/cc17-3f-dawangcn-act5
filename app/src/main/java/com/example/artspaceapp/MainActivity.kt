package com.example.artspaceapp

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

	private lateinit var artworkImageView: ImageView
	private lateinit var artworkTitleTextView: TextView
	private lateinit var artworkAuthorTextView: TextView
	private lateinit var artworkDescriptionTextView: TextView
	private lateinit var previousButton: MaterialButton
	private lateinit var nextButton: MaterialButton

	private val artworks = listOf(
		Artwork(
			R.drawable.pic1,
			"General Cleaning",
			"Dawang 2024",
			"Student Assistant General Cleaning dated at March 18, 2024."
		),
		Artwork(
			R.drawable.pic2,
			"Date at a Park",
			"Dawang 2024",
			"After School date with my beautiful Sugar Plum."
		),
		Artwork(
			R.drawable.pic3,
			"Ugly in Beauty",
			"Dawang 2024",
			"There's ugliness in every beauty, and it's my Sugar Plum being weird."
		),
		Artwork(
			R.drawable.pic4,
			"Aesthetic",
			"Baiwes 2024",
			"Felt cute, sent it to my Bebe Ghurl."
		),
		Artwork(
			R.drawable.pic5,
			"Maybe... Gay...?",
			"Dawang 2024",
			"Exploring barbie stuff, feelin' a little gay."
		)
	)

	private var currentArtworkIndex = 0
	private var isFirstArtwork: Boolean = true
	private var isLastArtwork: Boolean = false

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		artworkImageView = findViewById(R.id.artworkImageView)
		artworkTitleTextView = findViewById(R.id.artworkTitleTextView)
		artworkAuthorTextView = findViewById(R.id.artworkAuthorTextView)
		artworkDescriptionTextView = findViewById(R.id.artworkDescriptionTextView)
		previousButton = findViewById(R.id.previousButton)
		nextButton = findViewById(R.id.nextButton)

		updateArtworkDisplay()
		updateNavigationButtons()

		previousButton.setOnClickListener {
			navigateArtwork(false)
		}

		nextButton.setOnClickListener {
			navigateArtwork(true)
		}
	}

	private fun navigateArtwork(goForward: Boolean) {
		currentArtworkIndex = when {
			goForward -> (currentArtworkIndex + 1) % artworks.size
			else -> (currentArtworkIndex - 1 + artworks.size) % artworks.size
		}
		updateArtworkDisplay()
		updateNavigationButtons()
	}

	private fun updateArtworkDisplay() {
		val currentArtwork = artworks[currentArtworkIndex]
		artworkImageView.setImageResource(currentArtwork.imageResourceId)
		artworkTitleTextView.text = currentArtwork.title
		artworkAuthorTextView.text = currentArtwork.author
		artworkDescriptionTextView.text = currentArtwork.description
	}

	private fun updateNavigationButtons() {
		isFirstArtwork = currentArtworkIndex == 0
		isLastArtwork = currentArtworkIndex == artworks.size - 1

		previousButton.isEnabled = !isFirstArtwork
		nextButton.isEnabled = !isLastArtwork

		previousButton.alpha = if (isFirstArtwork) 0.5f else 1.0f
		nextButton.alpha = if (isLastArtwork) 0.5f else 1.0f
	}
}

data class Artwork(
	val imageResourceId: Int,
	val title: String,
	val author: String,
	val description: String
)
