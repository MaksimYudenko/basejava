import com.sun.org.apache.regexp.internal.RE;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
  private Resume[] storage = new Resume[10];

  void clear() {
    storage = new Resume[0];
  }

  void save(Resume r) {
    for (int i = 0; i < storage.length; i++) {
      if (storage[i] == null) {
        storage[i] = r;
        break;
      }
    }
  }

  Resume get(String uuid) {
    Resume r = new Resume();
    for (Resume res : storage) {
      if (res != null && res.uuid.equals(uuid)) r = res;
    }
    return r;
  }

  void delete(String uuid) {
    for (int i = 0; i < storage.length; i++) {
      if (storage[i] != null && storage[i].uuid.equals(uuid)) storage[i] = null;
    }
  }

  /**
   * @return array, contains only Resumes in storage (without null)
   */
  Resume[] getAll() {
    int counter = 0;
    for (Resume r : storage) {
      if (r != null) counter++;
    }
    Resume[] cleanStorage;
    Arrays.sort(storage, Comparator.nullsLast(Comparator.naturalOrder()));
    cleanStorage = Arrays.copyOf(storage, counter);
    return cleanStorage;
  }

  int size() {
    return storage.length;
  }
}