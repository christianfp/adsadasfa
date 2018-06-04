
import java.util.ArrayList;

public class ChangeGreetingModelView implements IModelView {
    private ArrayList<String> Options;
    private String Information;
    public ChangeGreetingModelView ()
    {
        Options=new ArrayList<>();
        Information ="Record your greeting, then press the # key";
    }
    @Override
    public ArrayList<String> getOptions() {
        return Options;
    }

    @Override
    public String getInformation() {
        return Information;
    }

    
}
