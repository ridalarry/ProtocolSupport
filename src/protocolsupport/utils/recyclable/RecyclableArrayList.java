/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.util.Recycler
 *  io.netty.util.Recycler$Handle
 */
package protocolsupport.utils.recyclable;

import io.netty.util.Recycler;
import java.util.ArrayList;
import protocolsupport.utils.recyclable.RecyclableCollection;

public class RecyclableArrayList<E>
extends ArrayList<E>
implements RecyclableCollection<E> {
    private static final long serialVersionUID = 1;
    private static final Recycler<RecyclableArrayList> RECYCLER = new Recycler<RecyclableArrayList>(){

        protected RecyclableArrayList newObject(Recycler.Handle handle) {
            return new RecyclableArrayList(handle);
        }
    };
    private final Recycler.Handle handle;

    public static <T> RecyclableArrayList<T> create() {
        return (RecyclableArrayList)RECYCLER.get();
    }

    private RecyclableArrayList(Recycler.Handle handle) {
        this.handle = handle;
    }

    @Override
    public void recycle() {
        this.clear();
        RECYCLER.recycle(this, handle);
    }

}

