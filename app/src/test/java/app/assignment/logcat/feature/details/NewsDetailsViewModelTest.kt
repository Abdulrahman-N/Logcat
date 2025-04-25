package app.assignment.logcat.feature.details

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import app.assignment.logcat.feature.details.domain.GetNewsByIdUseCase
import app.assignment.logcat.feature.details.domain.NewsDetailsItem
import app.assignment.logcat.feature.details.presentation.NewsDetailsRoute
import app.assignment.logcat.feature.details.presentation.NewsDetailsViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class NewsDetailsViewModelTest {

    private lateinit var viewModel: NewsDetailsViewModel
    private lateinit var getNewsByIdUseCase: GetNewsByIdUseCase
    private lateinit var savedStateHandle: SavedStateHandle
    private val scheduler = TestCoroutineScheduler()
    private val testDispatcher = StandardTestDispatcher(scheduler)

    private val mockNewsDetailsItem = NewsDetailsItem(
        id = "1",
        title = "News 1",
        publishDate = "Oct 01, 2023",
        type = "Video",
        content = "Content 1",
        url = "url1",
        image = "image1",
        authors = listOf("43,2"),
        topics = listOf("43,43")
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getNewsByIdUseCase = mockk()
        savedStateHandle = SavedStateHandle()
        savedStateHandle.mockkToRoute(NewsDetailsRoute("1"))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `init emits loading state initially`() = runTest(scheduler) {
        // Given
        coEvery { getNewsByIdUseCase("1") } returns mockNewsDetailsItem

        // When
        viewModel = NewsDetailsViewModel(getNewsByIdUseCase, savedStateHandle)

        // Then
        val initialState = viewModel.uiState.value
        assertTrue(initialState.loading, "Initial state should be loading")
        assertEquals(NewsDetailsItem(), initialState.data, "Initial state should have empty data")
    }

    @Test
    fun `init emits news data when use case returns valid item`() = runTest(scheduler) {
        // Given
        coEvery { getNewsByIdUseCase("1") } returns mockNewsDetailsItem

        // When
        viewModel = NewsDetailsViewModel(getNewsByIdUseCase, savedStateHandle)

        scheduler.runCurrent()

        // Then
        val state = viewModel.uiState.first { !it.loading }
        assertEquals(mockNewsDetailsItem, state.data, "State data should match mock news item")
    }
}

inline fun <reified T : Any> SavedStateHandle.mockkToRoute(page: T) {
    mockkStatic("androidx.navigation.SavedStateHandleKt")
    every { toRoute<T>() } returns page
}