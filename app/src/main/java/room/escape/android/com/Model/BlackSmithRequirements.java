package room.escape.android.com.Model;

/**
 * Created by Janwel Ocampo on 7/5/2016.
 */
public class BlackSmithRequirements {
    private String name;
    private boolean value;
    private String dataName;

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }
}
