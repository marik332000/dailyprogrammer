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
 */
@Immutable
public final class EditList {

    /**
     * The empty undo/redo stack.
     */
    private static final Deque<EditList> EMPTY = new ArrayDeque<EditList>();

    /**
     * The list of strings.
     */
    @NonNull
    private final List<String> list;

    /**
     * Stack of available undo operations.
     */
    @NonNull
    private final Deque<EditList> undo;

    /**
     * Stack of available redo operations.
     */
    @NonNull
    private final Deque<EditList> redo;

    /**
     * A new empty EditList.
     */
    public EditList() {
        this(new ArrayList<String>(), EMPTY, EMPTY);
    }

    /**
     * Create an EditList with the given collections.
     * @param newlist  the string list
     * @param newundo  the undo stack
     * @param newredo  the undo stack
     */
    private EditList(final List<String> newlist,
                     final Deque<EditList> newundo,
                     final Deque<EditList> newredo) {
        this.list = Collections.unmodifiableList(newlist);
        this.undo = newundo;
        this.redo = newredo;
    }

    /**
     * Get the list of strings.
     * @return an immutable list
     */
    public List<String> getList() {
        return Collections.unmodifiableList(list);
    }

    /**
     * Add an item to the list.
     * @param item  the item to add
     * @return an EditList with the new item
     */
    public EditList add(final String item) {
        List<String> nextlist = new ArrayList<String>(list);
        Deque<EditList> nextundo = new ArrayDeque<EditList>(undo);
        nextlist.add(item);
        nextundo.push(this);
        return new EditList(nextlist, nextundo, EMPTY);
    }

    /**
     * Change an item at a given index.
     * @param index  the index to change
     * @param item   the new item
     * @return an EditList with the edit
     */
    public EditList edit(final int index, final String item) {
        List<String> nextlist = new ArrayList<String>(list);
        Deque<EditList> nextundo = new ArrayDeque<EditList>(undo);
        nextlist.set(index, item);
        nextundo.push(this);
        return new EditList(nextlist, nextundo, EMPTY);
    }

    /**
     * Remove an item from the list.
     * @param index  the index to delete
     * @return an EditList with the deletion
     */
    public EditList delete(final int index) {
        List<String> nextlist = new ArrayList<String>(list);
        Deque<EditList> nextundo = new ArrayDeque<EditList>(undo);
        nextlist.remove(index);
        nextundo.push(this);
        return new EditList(nextlist, nextundo, EMPTY);
    }

    /**
     * Revert the last change.
     * @return the previous list, but redo-able
     */
    public EditList undo() {
        if (undo.isEmpty()) {
            throw new IllegalStateException("Nothing to undo.");
        } else {
            EditList last = undo.peek();
            Deque<EditList> nextredo = new ArrayDeque<EditList>(last.redo);
            nextredo.push(this);
            return new EditList(last.list, last.undo, nextredo);
        }
    }

    /**
     * Revert the last undo.
     * @return the restored list
     */
    public EditList redo() {
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
