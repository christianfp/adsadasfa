public class Recording implements IState {
    String message;
    private IConnection connection;
    private Mailbox currentMailbox;
    public Recording(IConnection connection)
    {
        this.connection=connection;
        this.currentMailbox=connection.getCurrentMailbox();

    }
    public boolean dial(String command) {
        if (isAMessage(command))
        {
            addMessage(command);
        }
        if (isNumericalCommand(command))
        {
            changeStateToLogin();
            return executeCommand(command);
        }
return true;
    }

    private boolean executeCommand(String command) {
        IRequest request=new ExecuteCommandRequest(command);
        return connection.executeCommand(request);
    }

    private void addMessage(String command) {
        message= command;
    }

    private boolean isAMessage(String key) {
        return key.length()>1;
    }

    private void changeStateToLogin() {
        connection.setStatus(new Login(connection));
    }


    public boolean hangup() {
        if (isNotTheMessageEmpty())
        {
            saveMessage();
        }
        connection.resetConnection();
        return true;
    }

    private boolean isNotTheMessageEmpty() {
        return message!=null;
    }

    private void saveMessage() {
        currentMailbox.addMessage(new Message(message));
        connection.saveChanges();
    }

    private boolean isNumericalCommand(String input) {
        return input.length() == 1
                && "1234567890#".contains(input);
    }

}
