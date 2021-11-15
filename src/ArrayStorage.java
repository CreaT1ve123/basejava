import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        int size = size();
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        storage[size()] = r;
    }

    Resume get(String uuid) {
        Resume resume = null;
        for (int i = 0; i < size(); i++) {
            if (storage[i].toString().equals(uuid)) {
                resume = storage[i];
            }
        }
        return resume;
    }

    void delete(String uuid) {
        int size = size();
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(get(uuid))) {
                for (int j = i; j < size - 1; j++) {
                    storage[j] = storage[j + 1];
                }
                storage[size - 1] = null;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size());
    }

    int size() {
        int size = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                break;
            }
            size++;
        }
        return size;
    }
}
