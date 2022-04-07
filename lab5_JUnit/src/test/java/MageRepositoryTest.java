import com.rushkappa.Mage;
import com.rushkappa.MageRepository;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

public class MageRepositoryTest {

    private MageRepository mageRepo;

    @Before
    public void setup() {
        mageRepo = new MageRepository();
    }

    @Test
    public void delete_ElementNotInCollection_ExceptionThrown() {
        String nameNotInRepo = "Veigar";

        Throwable thrown = catchThrowable(() -> {
            mageRepo.delete(nameNotInRepo);
        });
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Mage not found");

    }

    @Test
    public void find_ElementNotInCollection_EmptyOptional() {
        Optional<Mage> res = mageRepo.find("Veigar");

        assert res.isEmpty() : "should return Optional.empty()";
    }

    @Test
    public void find_ElementInCollection_OptionalWithMage() {
        String name = "Veigar";
        mageRepo.save(new Mage(name, 12));

        Optional<Mage> res = mageRepo.find(name);

        assertThat(res.get().getName()).isEqualTo(name);

    }

    @Test
    public void save_ElementAlreadyInCollection_OptionalWithMage() {
        String name = "Veigar";
        mageRepo.save(new Mage(name, 12));

        Throwable thrown = catchThrowable(() -> {
            mageRepo.save(new Mage(name, 12));
        });

        assertThat(thrown).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Already in collection");

    }

}
