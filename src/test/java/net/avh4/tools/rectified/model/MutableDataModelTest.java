package net.avh4.tools.rectified.model;

import net.avh4.util.Observer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static net.avh4.tools.rectified.test.support.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class MutableDataModelTest {

    private MutableDataModel subject;
    @Mock private Design design2;
    @Mock private Observer<Design> observer;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subject = new MutableDataModel();
    }

    @Test
    public void shouldHaveInitialDesignWithMainComponent() throws Exception {
        assertThat(subject.design()).isNotNull();
        assertThat(subject.design().components()).isNotEmpty();
    }

    @Test
    public void swapDesign_shouldChangeDesign() throws Exception {
        subject.swapDesign(subject.design(), design2);
        assertThat(subject.design()).isSameAs(design2);
    }

    @Test
    public void swapDesign_shouldNotifyObserver() throws Exception {
        subject.watch(observer);
        subject.swapDesign(subject.design(), design2);
        verify(observer).update(design2);
    }
}
