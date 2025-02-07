//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.quranapp.data.repository.QuranRepository
//import com.example.quranapp.ui.viewmodel.QuranViewModel
//
//class QuranViewModelFactory(private val repository: QuranRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(QuranViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return QuranViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}