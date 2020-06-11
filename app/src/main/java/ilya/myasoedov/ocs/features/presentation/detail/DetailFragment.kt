package ilya.myasoedov.ocs.features.presentation.detail

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import ilya.myasoedov.ocs.R
import ilya.myasoedov.ocs.app.BaseFragment
import ilya.myasoedov.ocs.features.domain.entites.Position
import ilya.myasoedov.ocs.providers.ImageLoadProvider
import javax.inject.Inject

const val argPosition = "arg_position"

class DetailFragment : BaseFragment<DetailFragmentViewModel>() {

    @Inject
    lateinit var imageLoadProvider: ImageLoadProvider

    override fun layoutRes(): Int = R.layout.fragment_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (arguments?.getParcelable(argPosition) as? Position)?.let {
            viewModel.setPosition(it)
        }

        val toolbar = view.findViewById<Toolbar>(R.id.fragment_detail_toolbar)
        val iconCompany = view.findViewById<ImageView>(R.id.fragment_detail_icon_company)
        val header = view.findViewById<TextView>(R.id.fragment_detail_header)
        val companyName = view.findViewById<TextView>(R.id.fragment_detail_company_name)
        val locationName = view.findViewById<TextView>(R.id.fragment_detail_location_name)
        val typeName = view.findViewById<TextView>(R.id.fragment_detail_type_name)
        val descriptionName = view.findViewById<TextView>(R.id.fragment_detail_description_name)
        val howToApplyName = view.findViewById<TextView>(R.id.fragment_detail_how_to_apply_name)

        toolbar.setNavigationOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }

        viewModel.positionLiveData.observe(viewLifecycleOwner, Observer {
            imageLoadProvider.load(it.companyLogo, iconCompany)
            header.text = it.title
            companyName.text = it.company
            typeName.text = it.type
            locationName.text = it.location

            descriptionName.movementMethod = LinkMovementMethod.getInstance()
            howToApplyName.movementMethod = LinkMovementMethod.getInstance()

            descriptionName.text = HtmlCompat.fromHtml(it.description, HtmlCompat.FROM_HTML_MODE_LEGACY)
            howToApplyName.text = HtmlCompat.fromHtml(it.howToApply, HtmlCompat.FROM_HTML_MODE_LEGACY)
        })
    }
}