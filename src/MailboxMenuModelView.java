import java.util.ArrayList;

public class MailboxMenuModelView implements IModelView {
    private ArrayList<String> Options;
    private String Information;
    public MailboxMenuModelView ()
    {
        Options=new ArrayList<>();
        Options.add("listen to your messages");
        Options.add("change your passcode");
        Options.add("change your greeting");
        Information ="";
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
