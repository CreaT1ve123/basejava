import java.util.Arrays;
import java.util.Optional;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < size() + 1; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        storage[size()] = r;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].toString().equals(uuid)) {
                for (int j = i; j < size(); j++) {
                    storage[j] = storage[j + 1];
                }
                storage[size()] = null;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.stream(storage).filter(resume -> resume != null).toArray(Resume[]::new);
    }

    int size() {
        return (int) Arrays.stream(storage).filter(resume -> resume != null).count();
    }
}
