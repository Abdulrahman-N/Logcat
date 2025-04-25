package app.assignment.logcat.feature.home

import app.assignment.logcat.feature.home.domain.GetNewsFeedUseCase
import app.assignment.logcat.feature.home.domain.NewsFeedItem
import app.assignment.logcat.feature.home.presentation.HomeEvents
import app.assignment.logcat.feature.home.presentation.HomeViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private lateinit var getNewsFeedUseCase: GetNewsFeedUseCase
    private val testDispatcher = UnconfinedTestDispatcher()

    private val mockNewsFeed = listOf(
        NewsFeedItem(id = "1", title = "News 1", publishDate = "2023-10-01", type = "Article"),
        NewsFeedItem(id = "2", title = "News 2", publishDate = "2023-10-02", type = "Event")
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getNewsFeedUseCase = mockk()
        coEvery { getNewsFeedUseCase() } returns mockNewsFeed
        viewModel = HomeViewModel(getNewsFeedUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `init fetches and sets news feed with types`() = runTest {
        val state = viewModel.uiState.first { it.news.isNotEmpty() }
        assertEquals(mockNewsFeed, state.news)
        assertEquals(setOf("All", "Article", "Event"), state.types)
        assertEquals("All", state.selectedType)
    }

    @Test
    fun `onEvent OnTypeSelected filters news by type`() = runTest {
        // Wait for initial news to load
        viewModel.uiState.first { it.news.isNotEmpty() }

        // When
        viewModel.onEvent(HomeEvents.OnTypeSelected("Article"))

        // Then
        val state = viewModel.uiState.first()
        assertEquals(listOf(mockNewsFeed[0]), state.news)
        assertEquals("Article", state.selectedType)
    }


    @Test
    fun `onEvent OnNewsItemClick sets navigateToNewsDetails`() = runTest {
        viewModel.onEvent(HomeEvents.OnNewsItemClick("1"))
        val state = viewModel.uiState.first()
        assertEquals("1", state.navigateToNewsDetails)
    }
}