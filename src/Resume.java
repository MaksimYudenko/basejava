/**
 * com.urise.webapp.model.Resume class
 */
public class Resume implements Comparable<Resume> {

  // Unique identifier
  String uuid;

  @Override
  public String toString() {
    return uuid;
  }

  @Override
  public int compareTo(Resume o) {
    return o.uuid == null ? -1 : 1;
  }
}
