package org.ak80.skeleton.entry

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import io.mockk.verify
import org.ak80.skeleton.R
import org.ak80.skeleton.anEntry
import org.ak80.skeleton.model.Entry
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class EntryPresenterTest {

    private lateinit var presenter: EntryPresenter

    @RelaxedMockK
    lateinit var entryRepository: EntryGateway

    @RelaxedMockK
    lateinit var view: EntryContract.View

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        presenter = EntryPresenter(entryRepository)
    }

    @Test
    fun loadAllEntryFromRepositoryAndLoadIntoView_whenTakeView() {
        // Given
        val entryList = listOf(anEntry(), anEntry(), anEntry())
        entryRepository.answerLoadCallbackWith(entryList)

        // When
        presenter.takeView(view)

        // Then
        verify { view.showEntries(entryList) }
    }

    @Test
    fun setEntryPassed_whenCompletedEntry() {
        // Given
        val entry = anEntry(passed = false)

        // When
        presenter.completeEntry(entry)

        // Then

        assertThat(entry.passed).isTrue()
    }

    @Test
    fun informView_whenCompletedEntry() {
        // Given
        val entry = anEntry(passed = false)
        presenter.takeView(view)

        // When
        presenter.completeEntry(entry)

        // Then

        verify { view.showEntryComplete() }
    }

    @Test
    fun setEntryNotPassed_whenActivateEntry() {
        // Given
        val entry = anEntry(passed = true)
        presenter.takeView(view)

        // When
        presenter.activateEntry(entry)

        // Then

        assertThat(entry.passed).isFalse()
    }

    @Test
    fun informView_whenActivateEntry() {
        // Given
        val entry = anEntry(passed = true)
        presenter.takeView(view)

        // When
        presenter.activateEntry(entry)

        // Then

        verify { view.showEntryActive() }
    }

    @Test
    fun loadAllEntryFromRepositoryAndLoadIntoView_whenClearEntries() {
        // Given
        val entryList = listOf(anEntry(), anEntry(), anEntry())
        entryRepository.answerLoadCallbackWith(entryList)
        presenter.takeView(view)

        // When
        presenter.clearEntries()

        // Then
        verify(exactly = 2) { view.showEntries(entryList) }
    }

    @Test
    fun showAboutInView_whenAbout() {
        // Given
        val entryList = listOf(anEntry(), anEntry(), anEntry())
        entryRepository.answerLoadCallbackWith(entryList)
        presenter.takeView(view)

        // When
        presenter.about()

        // Then
        verify { view.showAbout(R.string.about) }
    }

    @Test
    fun showMessageInView_whenAddNewEntry() {
        // Given
        val entryList = listOf(anEntry(), anEntry(), anEntry())
        entryRepository.answerLoadCallbackWith(entryList)
        presenter.takeView(view)

        // When
        presenter.addNewEntry()

        // Then
        verify { view.showMessage(R.string.add_new_entry) }
    }

    private fun EntryGateway.answerLoadCallbackWith(entryList: List<Entry>) {
        val loadCallback = slot<(List<Entry>) -> Unit>()
        every {
            loadEntries(capture(loadCallback))
        } answers {
            loadCallback.captured.invoke(entryList)
        }
    }

}