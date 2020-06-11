package ilya.myasoedov.ocs.paging

import androidx.paging.DataSource
import kotlinx.coroutines.CoroutineScope

class PageDataFactory<T>(
    private val pageDataSourceCallback: PageDataSourceCallback<T>,
    private val scope: CoroutineScope
) : DataSource.Factory<Long, T>() {

    private lateinit var dataSource: PageDataSource<T>

    override fun create(): DataSource<Long, T> {
        if (this::dataSource.isInitialized) {
            dataSource.clearRes()
        }
        dataSource = PageDataSource(pageDataSourceCallback, scope)
        return dataSource
    }

    fun invalidate() {
        if (this::dataSource.isInitialized) {
            dataSource.invalidate()
        }
    }
}