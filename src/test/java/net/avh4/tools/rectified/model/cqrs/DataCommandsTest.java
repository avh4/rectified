package net.avh4.tools.rectified.model.cqrs;

import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.model.Design;
import net.avh4.tools.rectified.model.Group;
import net.avh4.tools.rectified.model.MutableDataModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class DataCommandsTest {

    private DataCommands subject;
    @Mock private MutableDataModel dataModel;
    @Mock private Design design;
    @Mock private Design newDesign;
    @Mock private Component c1;
    @Mock private Component c2;
    @Mock private Component c9;
    @Mock private Group p1;
    @Mock private Group p1ʹ;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        stub(dataModel.design()).toReturn(design);
        subject = new DataCommands(dataModel);
    }

    @Test
    public void replace_inTopLevel_shouldUpdateDesign() throws Exception {
        stub(design.swap(c1, c9)).toReturn(newDesign);
        subject.replace(null, c1, c9);
        verify(dataModel).swapDesign(design, newDesign);
    }

    @Test
    public void replace_inChild_shouldUpdateDesign() throws Exception {
        // d( c1, p1(c2) )  -> d'( c1, p1'(c9) )
        stub(p1.swap(c2, c9)).toReturn(p1ʹ);
        stub(design.swap(p1, p1ʹ)).toReturn(newDesign);
        subject.replace(p1, c2, c9);
        verify(dataModel).swapDesign(design, newDesign);
    }

    @Test
    public void add_shouldUpdateDesign() throws Exception {
        stub(design.add(c9)).toReturn(newDesign);
        subject.add(c9);
        verify(dataModel).swapDesign(design, newDesign);
    }
}
