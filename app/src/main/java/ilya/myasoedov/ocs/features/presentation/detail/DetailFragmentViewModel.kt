package ilya.myasoedov.ocs.features.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ilya.myasoedov.ocs.features.domain.entites.Position
import javax.inject.Inject

class DetailFragmentViewModel @Inject constructor() : ViewModel() {

    private val positionData = MutableLiveData<Position>()

    val positionLiveData: LiveData<Position>
        get() = positionData

    fun setPosition(position: Position) {
        positionData.value = position
    }
}