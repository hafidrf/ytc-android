package com.yureka.technology.ytc.ui.beranda.materi.practice4

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.FragmentPractice4Binding
import com.yureka.technology.ytc.ui.beranda.materi.practice4.fragment.BottomSheetCorrectFragment
import com.yureka.technology.ytc.ui.beranda.materi.practice4.fragment.BottomSheetWrongFragment
import com.yureka.technology.ytc.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.*

@AndroidEntryPoint
class Practice4Fragment : BaseFragment<FragmentPractice4Binding>(), RecognitionListener {

    private val PERMISSION_GRANTED = PackageManager.PERMISSION_GRANTED
    private val AUDIO_PERMISSION = Manifest.permission.RECORD_AUDIO
    private val PERMISSION_REQUEST_CODE = 100

    //    private lateinit var binding: FragmentPractice4Binding
    val viewModel: Practice4ViewModel by viewModels()

    lateinit var mTTS: TextToSpeech

    // Speech Recognizer
    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var recognizerIntent: Intent

    private val question = "What can i do for you"
    private var answer = ""

    private var singleResult = true;
    override fun getLayoutResource(): Int = R.layout.fragment_practice4

    override fun initViews() {
        binding.toolbar.setupWithNavController(findNavController())
        binding.viewModel = viewModel

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
        speechRecognizer.setRecognitionListener(this)

        recognizerInit()

        buttonHandler()
        initTTS()
    }

    override fun initObservers() {

    }

    override fun initData() {

    }

    override fun onResume() {
        super.onResume()
        observeViewModel()
    }

    private fun observeViewModel() {
        binding.tvQuestion.text = question
        binding.chipAnswer.text = question
    }

    private fun buttonHandler() {
        binding.btnRekam.setOnClickListener {
            singleResult = true
            binding.btnRekam.visibility = View.INVISIBLE
            Timber.d("Permission is granted right? ${isPermissionGranted()}")
            if (isPermissionGranted()) {
                startListeningRecognition()
            } else {
                requestAudioPermission()
            }
        }

        binding.btnAudio.setOnClickListener {
            mTTS.speak(question, TextToSpeech.QUEUE_FLUSH, null)
        }

        binding.btnCek.setOnClickListener {
            showBottomSheet(isAnswerCorrect(answer))
        }
    }

    private fun startListeningRecognition() {
        speechRecognizer.startListening(recognizerIntent)
        binding.lottieSoundWave.visibility = View.VISIBLE
    }

    private fun initTTS() {
        mTTS = TextToSpeech(context, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                //if there is no error then set language
                mTTS.language = Locale.US
            }
        })
    }

    /*
        Permission Request
     */

    private fun requestAudioPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            requestPermissions(arrayOf(AUDIO_PERMISSION), PERMISSION_REQUEST_CODE)
    }

    private fun isPermissionGranted(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            this.let {
                ActivityCompat.checkSelfPermission(
                    requireContext(),
                    AUDIO_PERMISSION
                )
            } == PERMISSION_GRANTED
        else return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PERMISSION_GRANTED) {
                startListeningRecognition()
            }
        }
    }

    private fun showBottomSheet(answer: Boolean) {
        if (answer) {
            val bottomSheet = BottomSheetCorrectFragment.createInstance(question)
            bottomSheet.show(parentFragmentManager, BottomSheetCorrectFragment.TAG)
        } else {
            val bottomSheet = BottomSheetWrongFragment.createInstance(this.answer, question)
            bottomSheet.show(parentFragmentManager, BottomSheetWrongFragment.TAG)
        }
    }

    private fun recognizerInit() {
        recognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, R.string.prompt_speech)
    }

    /*
        Speech Recognition
     */

    override fun onReadyForSpeech(p0: Bundle?) {
        Timber.i("Ready for speech")
    }

    override fun onRmsChanged(p0: Float) {

    }

    override fun onBufferReceived(p0: ByteArray?) {

    }

    override fun onPartialResults(p0: Bundle?) {

    }

    override fun onEvent(p0: Int, p1: Bundle?) {

    }

    override fun onBeginningOfSpeech() {

    }

    override fun onEndOfSpeech() {

    }

    override fun onError(error: Int) {
        val errorMessage: String = getErrorText(error)
        Timber.d("Error while listening $errorMessage")
    }

    private fun getErrorText(error: Int): String {
        var message = ""
        message = when (error) {
            SpeechRecognizer.ERROR_AUDIO -> "Audio recording error"
            SpeechRecognizer.ERROR_CLIENT -> "Client side error"
            SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Insufficient permission"
            SpeechRecognizer.ERROR_NETWORK -> "Network Error"
            SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "Network Timeout"
            SpeechRecognizer.ERROR_NO_MATCH -> "No Match"
            SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "Recognizer busy"
            SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "No speech input"
            else -> "Error not recognize"
        }
        resetButton()
        return message
    }

    private fun resetButton() {
        binding.btnRekam.visibility = View.VISIBLE
        binding.lottieSoundWave.visibility = View.INVISIBLE
    }

    override fun onResults(results: Bundle?) {
        if (singleResult) {
            Timber.d("Recognize Result $results")
            resetButton()
            val speechResult = results!!.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            var text = ""
            if (speechResult != null) {
                for (result in speechResult) {
                    text = """
                $result
            """.trimIndent()
                }
            }
//            Timber.d("HASILNYA : $text")
            answer = text
            binding.chipAnswer.text = text
            binding.chipAnswer.visibility = View.VISIBLE
            binding.btnCek.visibility = View.VISIBLE
            singleResult = false
        }
    }

    private fun isAnswerCorrect(text: String): Boolean {
        return text.equals(question, true)
    }

    /////////////////////

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = DataBindingUtil.setContentView(this, R.layout.fragment_practice4)



        (this as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        (this as AppCompatActivity?)!!.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

    }*/

}