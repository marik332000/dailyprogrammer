package com.nullprogram.dp35d;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Just like the EditList, but without needing explicit
 * re-assignment. Delegates to an EditList.
 *
 * @param <V>  the type of the elements in the list
 */
@NoArgsConstructor
@AllArgsConstructor
public final class ModifiableEditList<V> {

    /**
     * The EditList being delegated to.
     */
    private EditList<V> list = new EditList<V>();

    /**
     * Add an item to the end of the list.
     * @param item  the item to add
     */
    public void add(final V item) {
        list = list.add(item);
    }

    /**
     * Change the item in the list.
     * @param index  the index to change
     * @param item   the new item
     */
    public void edit(final int index, final V item) {
        list = list.edit(index, item);
    }

    /**
     * Delete an item from the list.
     * @param index  the index to delete
     */
    public void delete(final int index) {
        list = list.delete(index);
    }

    /**
     * Undo the last modification.
     */
    public void undo() {
        list = list.undo();
    }

    /**
     * Redo the last undo.
     */
    public void redo() {
        list = list.redo();
    }

    /**
     * Get the list of items.
     * @return an unmodifiable list of items
     */
    public List<V> getList() {
        return list.getList();
    }
}
