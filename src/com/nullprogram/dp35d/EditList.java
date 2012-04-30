package com.nullprogram.dp35d;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import lombok.NonNull;
import net.jcip.annotations.Immutable;

/**
 * An immutable list of string that supports add, edit, delete, undo,
 * and redo by making new copies of this list.
 *
 * @param <V> the element type for this container
 */
@Immutable
public final class EditList<V> {

    /**
     * The list of strings.
     */
    @NonNull
    private final List<V> list;

    /**
     * Stack of available undo operations.
     */
    @NonNull
    private final Deque<EditList<V>> undo;

    /**
     * Stack of available redo operations.
     */
    @NonNull
    private final Deque<EditList<V>> redo;

    /**
     * A new empty EditList.
     */
    public EditList() {
        this(new ArrayList<V>(0),
             new ArrayDeque<EditList<V>>(0),
             new ArrayDeque<EditList<V>>(0));
    }

    /**
     * Create an EditList with the given collections.
     * @param newlist  the string list
     * @param newundo  the undo stack
     * @param newredo  the undo stack
     */
    private EditList(final List<V> newlist,
                     final Deque<EditList<V>> newundo,
                     final Deque<EditList<V>> newredo) {
        this.list = Collections.unmodifiableList(newlist);
        this.undo = newundo;
        this.redo = newredo;
    }

    /**
     * Get the list of strings.
     * @return an immutable list
     */
    public List<V> getList() {
        return Collections.unmodifiableList(list);
    }

    /**
     * Add an item to the list.
     * @param item  the item to add
     * @return an EditList with the new item
     */
    public EditList<V> add(final V item) {
        List<V> nextlist = new ArrayList<V>(list);
        Deque<EditList<V>> nextundo = new ArrayDeque<EditList<V>>(undo);
        nextlist.add(item);
        nextundo.push(this);
        return new EditList<V>(nextlist, nextundo,
                               new ArrayDeque<EditList<V>>(0));
    }

    /**
     * Change an item at a given index.
     * @param index  the index to change
     * @param item   the new item
     * @return an EditList with the edit
     */
    public EditList<V> edit(final int index, final V item) {
        List<V> nextlist = new ArrayList<V>(list);
        Deque<EditList<V>> nextundo = new ArrayDeque<EditList<V>>(undo);
        nextlist.set(index, item);
        nextundo.push(this);
        return new EditList<V>(nextlist, nextundo,
                               new ArrayDeque<EditList<V>>(0));
    }

    /**
     * Remove an item from the list.
     * @param index  the index to delete
     * @return an EditList with the deletion
     */
    public EditList<V> delete(final int index) {
        List<V> nextlist = new ArrayList<V>(list);
        Deque<EditList<V>> nextundo = new ArrayDeque<EditList<V>>(undo);
        nextlist.remove(index);
        nextundo.push(this);
        return new EditList<V>(nextlist, nextundo,
                               new ArrayDeque<EditList<V>>(0));
    }

    /**
     * Revert the last change.
     * @return the previous list, but redo-able
     */
    public EditList<V> undo() {
        if (undo.isEmpty()) {
            throw new IllegalStateException("Nothing to undo.");
        } else {
            EditList<V> last = undo.peek();
            Deque<EditList<V>> nextredo =
                new ArrayDeque<EditList<V>>(last.redo);
            nextredo.push(this);
            return new EditList<V>(last.list, last.undo, nextredo);
        }
    }

    /**
     * Revert the last undo.
     * @return the restored list
     */
    public EditList<V> redo() {
        if (redo.isEmpty()) {
            throw new IllegalStateException("Nothing to redo.");
        } else {
            return redo.peek();
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < list.size() - 1; i++) {
            str.append(list.get(i));
            str.append(", ");
        }
        str.append(list.get(list.size() - 1));
        return str.toString();
    }
}
