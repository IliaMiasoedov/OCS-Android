package ilya.myasoedov.ocs.features.presentation.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.findFragment
import ilya.myasoedov.ocs.R
import ilya.myasoedov.ocs.features.presentation.search.JobsListViewModel
import ilya.myasoedov.ocs.features.presentation.search.SearchFragment

class SearchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var viewModel: JobsListViewModel
    private val searchInput: EditText

    init {
        val view = LayoutInflater.from(context).inflate(
            R.layout.view_search,
            this,
            true
        )

        searchInput = view.findViewById(R.id.view_search_input)
        val removeActionButton = view.findViewById<ImageView>(R.id.view_search_remove_button)
        val viewSearchActionButton = view.findViewById<ImageView>(R.id.view_search_action_button)

        searchInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                setQuery()
            }
            false
        }
        searchInput.addTextChangedListener(onTextChanged = { text, _, _, _ ->
            removeActionButton.visibility = if (text.isNullOrEmpty()) View.GONE else View.VISIBLE
        })
        removeActionButton.setOnClickListener {
            searchInput.setText("")
            setQuery()
        }
        viewSearchActionButton.setOnClickListener {
            setQuery()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        viewModel = findFragment<SearchFragment>().jobsListViewModel
    }

    private fun setQuery() {
        viewModel.setQuery(searchInput.text.toString())
    }
}