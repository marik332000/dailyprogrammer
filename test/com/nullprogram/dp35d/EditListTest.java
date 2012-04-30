package com.nullprogram.dp35d;

import static org.junit.Assert.*;
import org.junit.Test;

public class EditListTest {

    private EditList<String> list = new EditList<String>();

    @Test
    public void simpleTest() {
        String[] type = new String[0];
        list = list.add("Foo");
        list = list.add("Bar");
        list = list.add("Baz");
        list = list.edit(1, "RAB");
        assertArrayEquals(new String[] {"Foo", "RAB", "Baz"},
                          list.getList().toArray(type));
        list = list.undo();
        assertArrayEquals(new String[] {"Foo", "Bar", "Baz"},
                          list.getList().toArray(type));
        list = list.undo();
        list = list.undo();
        assertArrayEquals(new String[] {"Foo"},
                          list.getList().toArray(type));
        list = list.redo();
        list = list.redo();
        list = list.redo();
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
