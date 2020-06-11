package ilya.myasoedov.ocs.features.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ilya.myasoedov.ocs.R
import ilya.myasoedov.ocs.features.domain.entites.Position
import ilya.myasoedov.ocs.providers.ImageLoadProvider

class PositionsAdapter(
    private val imageLoadProvider: ImageLoadProvider,
    private val onItemClickListener: (Position) -> Unit
) : PagedListAdapter<Position, RecyclerView.ViewHolder>(PositionsItemCallback()) {

    private var loadingInProgress = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            Type.ITEM.ordinal -> {
                return PositionViewHolder(
                    view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_position, parent, false),
                    onItemClickListener = { position ->
                        getItem(position)?.let { onItemClickListener(it) }
                    },
                    imageLoadProvider = imageLoadProvider
                )
            }
            Type.PROGRESS.ordinal -> {
                return ProgressViewHolder(
                    view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_progress, parent, false)
                )
            }
        }
        throw IllegalStateException("Unknown view type = $viewType")
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == Type.ITEM.ordinal) {
            val holder = viewHolder as PositionViewHolder
            getItem(position)?.let { holder.bind(position = it) }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            Type.PROGRESS.ordinal
        } else {
            Type.ITEM.ordinal
        }
    }

    fun showProgress(showProgress: Boolean) {
        val previousExtraRow = hasExtraRow()
        loadingInProgress = showProgress
        val newExtraRow = hasExtraRow()
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(itemCount)
            } else {
                notifyItemInserted(itemCount)
            }
        } else if (newExtraRow) {
            notifyItemChanged(itemCount - 1)
        }
    }

    /**
     * helper method. Useful for better understanding
     */
    private fun hasExtraRow(): Boolean = loadingInProgress

    class PositionViewHolder(
        view: View,
        onItemClickListener: (position: Int) -> Unit,
        private val imageLoadProvider: ImageLoadProvider
    ) : RecyclerView.ViewHolder(view) {

        private val itemPositionCompanyImage =
            itemView.findViewById<ImageView>(R.id.item_position_company_image)
        private val itemPositionHeaderText =
            itemView.findViewById<TextView>(R.id.item_position_header_text)
        private val itemPositionCompanyText =
            itemView.findViewById<TextView>(R.id.item_position_company_text)

        init {
            itemView.findViewById<View>(R.id.item_position_background)
                .setOnClickListener { onItemClickListener(adapterPosition) }
        }

        fun bind(position: Position) {
            imageLoadProvider.load(position.companyLogo, itemPositionCompanyImage)
            itemPositionHeaderText.text = position.title
            itemPositionCompanyText.text = position.location
        }
    }

    class ProgressViewHolder(view: View) : RecyclerView.ViewHolder(view)
}

class PositionsItemCallback : DiffUtil.ItemCallback<Position>() {
    override fun areContentsTheSame(oldItem: Position, newItem: Position): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Position, newItem: Position): Boolean {
        return oldItem.id == newItem.id
    }
}

enum class Type {
    ITEM,
    PROGRESS
}