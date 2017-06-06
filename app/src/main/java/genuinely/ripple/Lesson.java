package genuinely.ripple;

/**
 * Created by neilyeung on 8/12/16. This interface has an inner abstract class named Stub that
 * extends Binder and implements methods from your AIDL interface. You must extend the Stub class
 * and implement the methods.
 */
public class Lesson {
    private String name;
    private int iconId;

    public Lesson(String name, int iconId){
        this.name = name;
        this.iconId = iconId;
    }

    public String getName() {
        return name;
    }
    public int geticonId() {
        return iconId;
    }
}
