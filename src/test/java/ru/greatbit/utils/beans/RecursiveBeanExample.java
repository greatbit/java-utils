package ru.greatbit.utils.beans;

/**
 * Created by azee on 17.08.14.
 */
public class RecursiveBeanExample {
    public String str;
    private int count;
    public RecursiveBeanExample childPublic;
    private RecursiveBeanExample childPrivate;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public RecursiveBeanExample getChildPublic() {
        return childPublic;
    }

    public void setChildPublic(RecursiveBeanExample childPublic) {
        this.childPublic = childPublic;
    }

    public RecursiveBeanExample getChildPrivate() {
        return childPrivate;
    }

    public void setChildPrivate(RecursiveBeanExample childPrivate) {
        this.childPrivate = childPrivate;
    }
}
