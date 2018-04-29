package lonqt.example.com.marktable;

/**
 * Created by locnq on 3/30/2018.
 */

public class Class {

    private String id;
    private String name;
    private int soTC;

    public Class(String id, String name, int soTC) {
        this.id = id;
        this.name = name;
        this.soTC = soTC;
    }

    public Class() {
    }

    public String getId() {
        return id;
    }

    public Class setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Class setName(String name) {
        this.name = name;
        return this;
    }

    public int getSoTC() {
        return soTC;
    }

    public Class setSoTC(int soTC) {
        this.soTC = soTC;
        return this;
    }
}
