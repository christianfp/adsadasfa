public class MailboxMenu implements IState {


    private IConnection connection;
    public MailboxMenu(IConnection connection){
        this.connection=connection;
        showMailboxMenuOptions();
    }
    public boolean dial(String command) {
        if ("1".equals(command)) {
            changeToMessageMenuState();

        } else if ("2".equals(command)) {
            changeToChangePasscodeState();


        } else if ("3".equals(command)) {
            changeToChangeGreetingState();

        }
        return true;
    }

    private void changeToChangeGreetingState() {
        connection.setStatus(new ChangeGreating(connection));
    }

    private void changeToChangePasscodeState() {
        connection.setStatus(new ChangePasscode(connection));
    }

    private void changeToMessageMenuState() {
        connection.setStatus(new MessageMenu(connection));
    }


    public boolean hangup() {
        connection.resetConnection();
        return true;
    }
    private void showMailboxMenuOptions() {
        connection.setModelView(new MailboxMenuModelView());
    }
}
