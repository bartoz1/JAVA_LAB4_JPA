import com.rushkappa.Mage;
import com.rushkappa.MageController;
import com.rushkappa.MageRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MageControllerTest {
    @Mock
    private MageRepository mageRepository;

    private MageController mageController;

    @Before
    public void setup() {
        mageRepository = Mockito.mock(MageRepository.class);
        mageController = new MageController(mageRepository);
    }

//    @Test
//    public void demoMockito() {
//        DataSource source = Mockito.mock(DataSource.class);
//        Mockito.when(source.next()).thenReturn("woof").thenReturn("woof").thenReturn(null);
//        DataReader reader = new DataReader();
//        List<String> actual = reader.readData(source);
//        assertThat(actual).containsExactly("woof", "woof");

//    }
    @Test
    public void delete_existingObject_stringDone() {
        String name = "Test";
        doNothing().when(mageRepository).delete(name);

        assertThat(mageController.delete(name)).isEqualTo("done");
    }

    @Test
    public void delete_notExistingObject_stringNotFound() {
        String name = "Test";

        doThrow(IllegalArgumentException.class).when(mageRepository).delete(name);

        assertThat(mageController.delete(name)).isEqualTo("not found");
    }
    @Test
    public void find_notExistingObject_stringNotFound() {
        String name = "Test";

        doReturn(Optional.empty()).when(mageRepository).find(name);

        assertThat(mageController.find(name)).isEqualTo("not found");
    }
    @Test
    public void find_ElementInCollection_stringofObject() {
        String name = "tester";
        Mage testMage = new Mage(name, 12);

        doReturn(Optional.of(testMage)).when(mageRepository).find(name);

        assertThat(mageController.find(name)).isEqualTo(testMage.toString());
    }

    @Test
    public void save_ElementNotInCollection_stringDone() {
        String name = "tester";
        String level = "12";
        doNothing().when(mageRepository).save(any());

        assertThat(mageController.save(name, level)).isEqualTo("done");
    }
    @Test
    public void save_ElementAlreadyInCollection_stringBadRequest() {
        String name = "tester";
        String level = "12";
        doThrow(IllegalArgumentException.class).when(mageRepository).save(any());

        assertThat(mageController.save(name, level)).isEqualTo("bad request");
    }
}
