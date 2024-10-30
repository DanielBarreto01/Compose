package com.example.twitchclone.rv_activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.twitchclone.R
import com.example.twitchclone.data.Transmission

class TransmissionViewModel : ViewModel() {


    private val _uiState = MutableLiveData(TransmissionUIState())
    val uiState: LiveData<TransmissionUIState> get() = _uiState

    // Inicialización del ViewModel
    init {
        loadTransmissions()  // Llamada inicial para cargar los datos
    }
    // Método para cargar las transmisiones
    private fun loadTransmissions() {
        _uiState.value = TransmissionUIState(isLoading = true)

        val titles = listOf(
            "Ninja", "Ibai", "auronplay", "Rubius", "KaiCenat", "xQc", "TheGrefg", "Tfue",
            "juansguarnizo", "Shroud", "ElMariana", "ElSpreen", "Pokimane", "Sodapoppin",
            "Clix", "Heelmike", "Tommyinnit", "Myth", "AdinRoss", "Alanzoka", "SypherPK",
            "TimTheTatman", "AriGameplays", "Mongraal", "Riot Games", "NICKMERCS", "Quackity",
            "ESLCS", "summit1g", "Amouranth", "Dream", "rivers_gg", "Jynxzi", "Fortnite",
            "Robleis", "elded", "loud_coringa", "Bugha", "Moistcr1tikal", "NickEh30",
            "MontanaBlack88", "loltyler1"
        )

        val imageResIds = listOf(
            R.drawable.ninja, R.drawable.ibai, R.drawable.auronplay, R.drawable.rubius,
            R.drawable.kaicenat, R.drawable.xqc, R.drawable.thegrefg, R.drawable.tfue,
            R.drawable.juansguarnizo, R.drawable.shroud, R.drawable.elmariana, R.drawable.elspreen,
            R.drawable.pokimane, R.drawable.sodapoppin, R.drawable.clix, R.drawable.heelmike,
            R.drawable.tommyinnit, R.drawable.myth, R.drawable.adinross, R.drawable.alanzoka,
            R.drawable.sypherpk, R.drawable.timthetatman, R.drawable.arigameplays, R.drawable.mongraal,
            R.drawable.riotgames, R.drawable.nickmercs, R.drawable.quackity, R.drawable.eslcs,
            R.drawable.summit1g, R.drawable.amouranth, R.drawable.dream, R.drawable.rivers_gg,
            R.drawable.jynxzi, R.drawable.fortnite, R.drawable.robleis, R.drawable.elded,
            R.drawable.loud_coringa, R.drawable.bugha, R.drawable.moistcr1tikal, R.drawable.nickeh30,
            R.drawable.montanablack88, R.drawable.loltyler1
        )

        val postList = titles.indices.map { i -> Transmission(imageResIds[i], titles[i]) }
        _uiState.value = TransmissionUIState(transmissions = postList)
    }
    fun setListActive(isActive: Boolean, deactivationTime: Int) {
        _uiState.value = _uiState.value?.copy(isListActive = isActive, deactivationTime = deactivationTime)
    }

}