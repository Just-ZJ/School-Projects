import components.set.Set;
import components.set.Set1L;

/**
 * Layered implementations of secondary methods {@code add} and {@code remove}
 * for {@code Set}.
 *
 * @param <T>
 *            type of {@code Set} elements
 */
public final class SetSecondary1L<T> extends Set1L<T> {

    /**
     * No-argument constructor.
     */
    public SetSecondary1L() {
        super();
    }

    /**
     * Removes from {@code this} all elements of {@code s} that are also in
     * {@code this}, leaving {@code s} unchanged, and returns the elements
     * actually removed.
     *
     * @param s
     *            the {@code Set} whose elements are to be removed from
     *            {@code this}
     * @return the {@code Set} whose elements actually were removed from
     *         {@code this}
     * @updates this
     * @ensures <pre>
     * this = #this \ s  and
     * remove = #this intersection s
     * </pre>
     */
    @Override
    public Set<T> remove(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";
        Set<T> newSet = s.newInstance();

        if (s.size() > 0) {
            T element = s.removeAny();
            if (this.contains(element)) {
                newSet.add(element);
                this.remove(element);
            }
            this.remove(s);
            s.add(element);
        }

        return newSet;
    }

    /**
     * Adds to {@code this} all elements of {@code s} that are not already in
     * {@code this}, also removing just those elements from {@code s}.
     *
     * @param s
     *            the {@code Set} whose elements are to be added to {@code this}
     * @updates this, s
     * @ensures <pre>
     * this = #this union #s  and
     * s = #this intersection #s
     * </pre>
     */
    @Override
    public void add(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";

        for (T element : s) {
            if (!this.contains(element)) {
                this.add(element);
                s.remove(element);
            }
        }
    }

}
