package com.nullprogram.dp35d;

import static org.junit.Assert.*;
import org.junit.Test;

public class EditListTest {

    private ModifiableEditList<String> list = new ModifiableEditList<String>();

    @Test
    public void simpleTest() {
        String[] type = new String[0];
        list.add("Foo");
        list.add("Bar");
        list.add("Baz");
        list.edit(1, "RAB");
        assertArrayEquals(new String[] {"Foo", "RAB", "Baz"},
                          list.getList().toArray(type));
        list.undo();
        assertArrayEquals(new String[] {"Foo", "Bar", "Baz"},
                          list.getList().toArray(type));
        list.undo();
        list.undo();
        assertArrayEquals(new String[] {"Foo"},
                          list.getList().toArray(type));
        list.redo();
        list.redo();
        list.redo();
        assertArrayEquals(new String[] {"Foo", "RAB", "Baz"},
                          list.getList().toArray(type));
    }

    @Test(expected=IllegalStateException.class)
    public void underUndoTest() {
        list.undo();
    }

    @Test(expected=IllegalStateException.class)
    public void underRedoTest() {
        list.redo();
    }
}
